package com.example.senlastudy.database

class InsertQueryBuilder {

    private val tableName = mutableListOf<String>()
    private val tableFieldsWithArgument = mutableMapOf<String, String>()

    fun table(tableName: String): InsertQueryBuilder {
        this.tableName.add(tableName)
        return this
    }

    fun insertValue(nameField: String, argument: String): InsertQueryBuilder {
        tableFieldsWithArgument[nameField] = argument
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