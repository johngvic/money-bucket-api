package br.com.moneybucket.helpers

class StringParser {
    companion object {
        fun split(authHeader: String): String {
            return authHeader.replace("Bearer ", "")
        }
    }
}