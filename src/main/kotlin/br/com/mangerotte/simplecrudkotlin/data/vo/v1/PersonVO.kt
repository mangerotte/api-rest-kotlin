package br.com.mangerotte.simplecrudkotlin.data.vo.v1

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import com.github.dozermapper.core.Mapping
import org.springframework.hateoas.RepresentationModel

@JsonPropertyOrder("id", "endereco", "nome", "sobrenome", "genero")
data class PersonVO (

    @Mapping("id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    var key : Long = 0,
    @field: JsonProperty("nome")
    var firstName : String = "",
    @field: JsonProperty("sobrenome")
    var lastName : String = "",
    @field: JsonProperty("endereco")
    var address : String = "",
    @field: JsonProperty("genero")
    var gender : String = ""
        ) : RepresentationModel<PersonVO>()