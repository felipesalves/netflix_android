package br.iesb.mobile.netflics.domain

data class Filme (
    val id: Int,
    val nome: String? = null,
    val sinopse: String? = null,
    val foto: String? = null
)