package br.iesb.mobile.netflics.repository

import br.iesb.mobile.netflics.domain.Filme
import br.iesb.mobile.netflics.service.FilmeService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FilmeRepository @Inject constructor(
    private val service: FilmeService
){
    suspend fun filmeList(): List<Filme>{

        return withContext(Dispatchers.IO){
            val responseService = service.FilmeListAsync()

            val responseStore = responseService.map {
                Filme(
                    id = it.id,
                    nome = it.nome,
                    sinopse = it.sinopse,
                    foto = it.foto
                )
            }
            responseStore
        }
    }
}