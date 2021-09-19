/*
 * Copyright (c) 2021/9/15
 * Author : Roberto Garcia
 */

package com.example.rgc.opendotaktor.users.local

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table

data class UserLocal(val id : Int,
                     val username : String,
                     val password : String,
                     val firstname : String,
                     val email : String,
                     val description : String)


object Users : Table("users") {
    val userId = integer("id").autoIncrement()
    val username = varchar("username", 100).uniqueIndex()
    val password = varchar("password", 255)
    val firstname = varchar("firstname", 100)
    val email = varchar("email", 100).uniqueIndex()
    val description = varchar("description", 512)
    override val primaryKey = PrimaryKey(userId, name = "pk_user")

}

fun mapperToUserLocal(dbModel : ResultRow) : UserLocal {
    return UserLocal(
        id = dbModel[Users.userId],
        username = dbModel[Users.username],
        password = dbModel[Users.password],
        firstname = dbModel[Users.firstname],
        email = dbModel[Users.email],
        description = dbModel[Users.description]
        )
}
