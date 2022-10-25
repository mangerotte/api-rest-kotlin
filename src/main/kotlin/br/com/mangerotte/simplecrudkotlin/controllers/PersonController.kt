package br.com.mangerotte.simplecrudkotlin.controllers

import br.com.mangerotte.simplecrudkotlin.data.vo.v1.PersonVO
import br.com.mangerotte.simplecrudkotlin.data.vo.v2.PersonVO as PersonVOV2
import br.com.mangerotte.simplecrudkotlin.services.PersonService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/api/person/v1"])
class PersonController {

    @Autowired
    private lateinit var personService: PersonService

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE])
    fun findAll() : ResponseEntity<List<PersonVO>>{
        return ResponseEntity(personService.findAll(), HttpStatus.OK)
    }

    @GetMapping(value = ["/{id}"],
                produces = [MediaType.APPLICATION_JSON_VALUE,
                            MediaType.APPLICATION_XML_VALUE])
    fun findById(@PathVariable(value = "id") id : Long) : ResponseEntity<PersonVO> {
        return ResponseEntity(personService.findbyId(id), HttpStatus.OK)
    }

    @PostMapping(produces = [MediaType.APPLICATION_JSON_VALUE,
                             MediaType.APPLICATION_XML_VALUE])
    fun insert(@RequestBody personVo: PersonVO) : ResponseEntity<PersonVO>{
        return ResponseEntity(personService.insert(personVo), HttpStatus.CREATED)
    }

    @PostMapping(value = ["/v2"],
                produces = [MediaType.APPLICATION_JSON_VALUE,
                            MediaType.APPLICATION_XML_VALUE])
    fun insertV2(@RequestBody personVo: PersonVOV2) : ResponseEntity<PersonVOV2>{
        return ResponseEntity(personService.insertV2(personVo), HttpStatus.CREATED)
    }

    @DeleteMapping(value = ["/{id}"],
                   produces = [MediaType.APPLICATION_JSON_VALUE,
                               MediaType.APPLICATION_XML_VALUE])
    fun delete(@PathVariable(value = "id") id: Long): ResponseEntity<PersonVO> {
        personService.delete(id)
        return ResponseEntity.noContent().build()
    }

    @PutMapping(value = ["/{id}"],
                produces = [MediaType.APPLICATION_JSON_VALUE,
                            MediaType.APPLICATION_XML_VALUE])
    fun update(@RequestBody person: PersonVO, @PathVariable(value = "id") id: Long )
            : ResponseEntity<PersonVO> {
        return ResponseEntity(personService.update(person, id), HttpStatus.NO_CONTENT)

    }


}