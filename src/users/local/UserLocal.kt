/*
 * Copyright (c) 2021/9/15
 * Author : Roberto Garcia
 */

package com.example.rgc.opendotaktor.users.local

import org.jetbrains.exposed.sql.Table

data class UserLocal(val username : String,
                     val password : String,
                     val firstname : String,
                     val email : String,
                     val description : String)


object Users : Table("users") {
    private val id = integer("id").autoIncrement()
    val username = varchar("username", 100).uniqueIndex()
    val password = varchar("password", 255)
    val firstname = varchar("firstname", 100)
    val email = varchar("email", 100)
    val description = varchar("description", 512)
    override val primaryKey = PrimaryKey(id, name = "id")

}
