/*
 * Copyright (c) 2021/9/15
 * Author : Roberto Garcia
 */

package com.example.rgc.opendotaktor.users.domain

data class User(val id : Int,
                val username : String,
                val firstname : String,
                val email : String,
                val description : String)
