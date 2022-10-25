package br.com.mangerotte.simplecrudkotlin.config

import br.com.mangerotte.simplecrudkotlin.data.vo.v2.PersonVO
import br.com.mangerotte.simplecrudkotlin.models.Person
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.*

@Configuration
class CustomMapper {


    fun mapEntityToVO(person: Person): PersonVO{
        val vo = PersonVO()
        vo.key = person.id
        vo.firstName = person.firstName
        vo.lastName = person.lastName
        vo.address = person.address
        vo.gender = person.gender
        vo.birthDay = Date()
        return vo
    }

    fun mapVOToEntity(personVO: PersonVO): Person{
        val entity = Person()
        entity.id = personVO.key
        entity.firstName = personVO.firstName
        entity.lastName = personVO.lastName
        entity.address = personVO.address
        entity.gender = personVO.gender
        return entity
    }

}