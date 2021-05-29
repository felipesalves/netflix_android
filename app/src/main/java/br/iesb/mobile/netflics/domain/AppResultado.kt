package br.iesb.mobile.netflics.domain

sealed class AppResultado<out T: Any> {
    data class Success<out T: Any>(val value: T? = null): AppResultado<T>()
    data class Error(val message: String?, val error: Throwable?): AppResultado<Nothing>()
}