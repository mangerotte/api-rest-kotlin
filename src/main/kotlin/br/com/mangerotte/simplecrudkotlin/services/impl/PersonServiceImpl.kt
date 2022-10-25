package br.com.mangerotte.simplecrudkotlin.services.impl

import br.com.mangerotte.simplecrudkotlin.config.CustomMapper
import br.com.mangerotte.simplecrudkotlin.config.DozerMapper
import br.com.mangerotte.simplecrudkotlin.controllers.PersonController
import br.com.mangerotte.simplecrudkotlin.data.vo.v1.PersonVO
import br.com.mangerotte.simplecrudkotlin.data.vo.v2.PersonVO as PersonVOV2
import br.com.mangerotte.simplecrudkotlin.models.Person
import br.com.mangerotte.simplecrudkotlin.repositories.PersonRepository
import br.com.mangerotte.simplecrudkotlin.services.PersonService
import br.com.mangerotte.simplecrudkotlin.error.exceptions.ResourceNotFound
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class PersonServiceImpl : PersonService {

    @Autowired
    private lateinit var personRepository : PersonRepository
    @Autowired
    private lateinit var mapper : CustomMapper

    private val logger = Logger.getLogger(PersonServiceImpl::class.java.name)

    override fun findAll(): List<PersonVO> {
        logger.info("Finding all people")
        return DozerMapper.parseListObject(personRepository.findAll(), PersonVO::class.java)
    }

    override fun findbyId(id: Long): PersonVO? {
       logger.info("Finding one person")
        var person = personRepository.findById(id)
        val personVO: PersonVO = DozerMapper.parseObject(person
            .orElseThrow{ ResourceNotFound("No records found for this $id") }, PersonVO::class.java)
        val withSelfRel = linkTo(PersonController::class.java).slash(personVO.key).withSelfRel()
        return personVO.add(withSelfRel)
    }

    override fun insert(person : PersonVO): PersonVO {
        logger.info("Creating person with name ${person.firstName}")
        var entity = DozerMapper.parseObject(person, Person::class.java)
        val personVO: PersonVO = DozerMapper.parseObject(personRepository.save(entity), PersonVO::class.java)
        val withSelfRel = linkTo(PersonController::class.java).slash(personVO.key).withSelfRel()
        return personVO.add(withSelfRel)

    }

    override fun insertV2(personV2: PersonVOV2): PersonVOV2 {
        logger.info("Creating personV2 with name ${personV2.firstName}")
        val entity = mapper.mapVOToEntity(personV2)
        val personVO: PersonVOV2 = mapper.mapEntityToVO(personRepository.save(entity))
        val withSelfRel = linkTo(PersonController::class.java).slash(personV2.key).withSelfRel()
        return personV2.add(withSelfRel)
    }


    override fun update(personRequest: PersonVO, id: Long): PersonVO {
        try {
            logger.info("Updating entity with id $id")
            val entityDb : Person = personRepository.getReferenceById(id)
            updateData(entityDb, personRequest)
            val personVO: PersonVO = DozerMapper
                                    .parseObject(personRepository.save(entityDb),
                                                PersonVO::class.java)
            val withSelfRel = linkTo(PersonController::class.java).slash(personVO.key).withSelfRel()
            return personVO.add(withSelfRel)

        }catch (e: ResourceNotFound){
            throw ResourceNotFound("$id not found!")
        }
    }

    override fun updateData(personDb: Person, personRequest: PersonVO) {
        logger.info("Changing entity attributes")
        personDb.firstName = personRequest.firstName
        personDb.lastName = personRequest.lastName
        personDb.address = personRequest.address
        personDb.gender = personRequest.gender
    }

    override fun delete(id: Long) {
       try {
           logger.info("Deleting person with id $id")
           return personRepository.deleteById(id)
       } catch (e : ResourceNotFound){
           throw ResourceNotFound("No records found for this $id")
       }
    }




}