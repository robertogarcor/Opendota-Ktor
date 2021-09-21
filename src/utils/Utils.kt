/*
 * Copyright (c) 2021/9/21
 * Author : Roberto Garcia
 */

package com.example.rgc.opendotaktor.utils

import com.google.gson.JsonElement
import com.google.gson.JsonParser

object Utils {

    fun parseJson(string : String) : JsonElement = JsonParser.parseString(string)

}