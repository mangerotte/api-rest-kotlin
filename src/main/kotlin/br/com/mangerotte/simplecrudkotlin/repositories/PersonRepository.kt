package br.com.mangerotte.simplecrudkotlin.repositories

import br.com.mangerotte.simplecrudkotlin.models.Person
import org.springframework.data.jpa.repository.JpaRepository

interface PersonRepository : JpaRepository<Person, Long?> {
}