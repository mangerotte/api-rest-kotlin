package br.com.mangerotte.simplecrudkotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SimpleCrudKotlinApplication

fun main(args: Array<String>) {
	runApplication<SimpleCrudKotlinApplication>(*args)
}
