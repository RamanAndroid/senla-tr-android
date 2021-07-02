package com.example.senlastudy.database

import java.util.*

class InsertQueryBuilder {

    private val tableName = mutableListOf<String>()
    private val tableFieldsWithArgument = mutableMapOf<String, String>()

    fun table(tableName: String): InsertQueryBuilder {
        this.tableName.add(tableName)
        return this
    }

    fun insertTextValue(nameField: String, argument: String): InsertQueryBuilder {
        tableFieldsWithArgument[nameField] = argument
        return this
    }

    fun insertIntegerValue(nameField: String, argument: Int): InsertQueryBuilder {
        tableFieldsWithArgument[nameField] = argument.toString()
        return this
    }

    fun insertBlobValue(nameField: String, argument: Objects): InsertQueryBuilder {
        tableFieldsWithArgument[nameField] = argument.toString()
        return this
    }

    fun insertRealValue(nameField: String, argument: Double): InsertQueryBuilder {
        tableFieldsWithArgument[nameField] = argument.toString()
        return this
    }

    fun insert(): String {
        val whichColumn = mutableListOf<String>()
        val whichValue = mutableListOf<String>()

        tableFieldsWithArgument.forEach {
            whichColumn.add("${it.key} ")
            whichValue.add(it.value)
        }

        val firstPartRequest = whichColumn.joinToString(
            ",",
            "INSERT INTO ${tableName[0]} (",
            ") VALUES"
        )

        val secondPartRequest = whichValue.joinToString(
            ",",
            "(",
            ")"
        )

        return firstPartRequest + secondPartRequest
    }

}