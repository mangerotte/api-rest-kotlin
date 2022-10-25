package br.com.mangerotte.simplecrudkotlin.services

import br.com.mangerotte.simplecrudkotlin.data.vo.v1.PersonVO
import br.com.mangerotte.simplecrudkotlin.data.vo.v2.PersonVO as PersonVOV2
import br.com.mangerotte.simplecrudkotlin.models.Person
import java.util.Optional

interface PersonService {

    fun findbyId(id : Long) : PersonVO?
    fun findAll() : List<PersonVO>
    fun insert(person: PersonVO) : PersonVO
    fun insertV2(personV2: PersonVOV2) : PersonVOV2
    fun update(personRequest: PersonVO, id: Long) : PersonVO
    fun updateData(personDb : Person, personRequest: PersonVO)
    fun delete (id : Long)
}