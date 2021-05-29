package br.iesb.mobile.netflics.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import br.iesb.mobile.netflics.domain.Filme
import br.iesb.mobile.netflics.interactor.FilmeInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class FilmeListaViewModel @Inject constructor(
    val app: Application,
    private val interactor: FilmeInteractor
) : AndroidViewModel(app), CoroutineScope {

    override val coroutineContext: CoroutineContext = Dispatchers.Main

    val filmeLista = MutableLiveData<List<Filme>>()

    fun filmeList(){

        launch {
            try {
                val filmeListInteractor = interactor.filmeList()
                filmeLista.value = filmeListInteractor
            }catch (t: Throwable){
                Log.d("Store", "Error to List Filme: ${t.localizedMessage}" )
            }
        }
    }
}