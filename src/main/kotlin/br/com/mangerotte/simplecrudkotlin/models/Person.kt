package br.com.mangerotte.simplecrudkotlin.models

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "person")
data class Person (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long = 0,
    @Column(name = "first_name", nullable = false)
    var firstName : String = "",
    @Column(name = "last_name", nullable = false)
    var lastName : String = "",
    @Column(nullable = false)
    var address : String = "",
    @Column(nullable = false)
    var gender : String = ""
        )