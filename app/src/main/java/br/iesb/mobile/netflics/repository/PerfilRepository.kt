package br.iesb.mobile.netflics.repository

import br.iesb.mobile.netflics.domain.AppResultado
import br.iesb.mobile.netflics.domain.Profile
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class PerfilRepository @Inject constructor(
    private val firestore: FirebaseFirestore,
) {

    suspend fun createOrUpdateProfile(p: Profile): AppResultado<Nothing> = suspendCoroutine { nextStep ->
        val data = hashMapOf(
            "id" to p.id,
            "name" to p.name
        )
        firestore.collection("profile").document(p.id!!)
            .set(data)
            .addOnCompleteListener { op ->
                val res = if (op.isSuccessful) {
                    AppResultado.Success()
                } else {
                    AppResultado.Error(op.exception?.localizedMessage, op.exception)
                }
                nextStep.resume(res)
            }
    }

    suspend fun loadProfiles(): AppResultado<List<Profile>> = suspendCoroutine { nextStep ->
        firestore.collection("profile")
            .get()
            .addOnCompleteListener { op ->
                val res = if (op.isSuccessful) {
                    val list = mutableListOf<Profile>()
                    op.result?.documents?.forEach { document ->
                        val p = document.toObject<Profile>()
                        if (p != null) {
                            list.add(p)
                        }
                    }
                    AppResultado.Success(list)
                } else {
                    AppResultado.Error(op.exception?.localizedMessage, op.exception)
                }
                nextStep.resume(res)
            }
    }
}