package br.com.moneybucket.exception

data class ResourceNotFoundException(override val message: String) : RuntimeException()