package br.com.mangerotte.simplecrudkotlin.data.vo.v2

import com.fasterxml.jackson.annotation.JsonProperty
import com.github.dozermapper.core.Mapping
import org.springframework.hateoas.RepresentationModel
import java.util.*


data class PersonVO (

    @Mapping("id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    var key : Long = 0,
    var firstName : String = "",
    var lastName : String = "",
    var address : String = "",
    var gender : String = "",
    var birthDay : Date? = null
        ) : RepresentationModel<PersonVO>()