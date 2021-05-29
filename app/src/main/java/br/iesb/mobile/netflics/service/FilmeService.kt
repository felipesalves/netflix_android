package br.iesb.mobile.netflics.service

import br.iesb.mobile.netflics.domain.Filme
import retrofit2.http.GET
import retrofit2.http.Headers

interface FilmeService {

    @GET("r-api/?api=filmes")
    @Headers("Content-Type: application/json")
    suspend fun FilmeListAsync(): List<Filme>
}