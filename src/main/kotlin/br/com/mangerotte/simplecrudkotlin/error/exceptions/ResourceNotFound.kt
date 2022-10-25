package br.com.mangerotte.simplecrudkotlin.error.exceptions

import java.lang.RuntimeException

class ResourceNotFound (message : String): RuntimeException (message) {
}