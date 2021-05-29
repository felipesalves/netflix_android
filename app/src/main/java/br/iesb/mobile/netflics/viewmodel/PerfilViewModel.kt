package br.iesb.mobile.netflics.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.iesb.mobile.netflics.R
import br.iesb.mobile.netflics.domain.AppResultado
import br.iesb.mobile.netflics.domain.Profile
import br.iesb.mobile.netflics.interactor.PerfilInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PerfilViewModel @Inject constructor(
    app: Application,
    private val interactor: PerfilInteractor
) : AndroidViewModel(app) {

    var currentProfile: MutableLiveData<Profile>? = null

    val profile1 = MutableLiveData(Profile(name = app.getString(R.string.new_profile)))
    val profile2 = MutableLiveData(Profile(name = app.getString(R.string.new_profile)))
    val profile3 = MutableLiveData(Profile(name = app.getString(R.string.new_profile)))
    val profile4 = MutableLiveData(Profile(name = app.getString(R.string.new_profile)))

    val result = MutableLiveData<AppResultado<Nothing>?>()

    fun loadProfiles() {
        viewModelScope.launch {
            val data = interactor.loadProfiles()
            result.value = when (data) {
                is AppResultado.Success -> {
                    data.value?.forEach {
                        when (it.id) {
                            "Profile1" -> profile1.value = Profile(it.id, it.name)
                            "Profile2" -> profile2.value = Profile(it.id, it.name)
                            "Profile3" -> profile3.value = Profile(it.id, it.name)
                            else -> profile4.value = Profile(it.id, it.name)
                        }
                    }
                    AppResultado.Success()
                }
                is AppResultado.Error -> data
            }
        }
    }

    fun selectProfile(index: Int) {
        currentProfile = when (index) {
            1 -> profile1
            2 -> profile2
            3 -> profile3
            else -> profile4
        }
    }

    fun createOrUpdateProfile() {
        currentProfile?.value?.let {
            viewModelScope.launch {
                when (it.id) {
                    "Profile1" -> profile1.value = Profile(it.id, it.name)
                    "Profile2" -> profile2.value = Profile(it.id, it.name)
                    "Profile3" -> profile3.value = Profile(it.id, it.name)
                    else -> profile4.value = Profile(it.id, it.name)
                }
                result.value = interactor.createOrUpdateProfile(it)
            }
        }
    }

}