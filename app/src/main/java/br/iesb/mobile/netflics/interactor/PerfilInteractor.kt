package br.iesb.mobile.netflics.interactor

import br.iesb.mobile.netflics.domain.AppResultado
import br.iesb.mobile.netflics.domain.Profile
import br.iesb.mobile.netflics.repository.PerfilRepository
import javax.inject.Inject

class PerfilInteractor @Inject constructor(
    private val repo: PerfilRepository
) {

    suspend fun createOrUpdateProfile(p: Profile) = repo.createOrUpdateProfile(p)
    suspend fun loadProfiles(): AppResultado<List<Profile>> = repo.loadProfiles()

}