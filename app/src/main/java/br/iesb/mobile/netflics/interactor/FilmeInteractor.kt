package br.iesb.mobile.netflics.interactor

import br.iesb.mobile.netflics.domain.Filme
import br.iesb.mobile.netflics.repository.FilmeRepository
import javax.inject.Inject

class FilmeInteractor @Inject constructor(
    private val repo: FilmeRepository
){
    suspend fun filmeList(): List<Filme>{
        return repo.filmeList()
    }
}