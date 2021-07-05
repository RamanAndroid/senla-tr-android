package com.example.senlastudy.database.querybuilder

class SelectQueryBuilder {
    private val tableName = mutableListOf<String>()
    private val selectForDatabase = mutableListOf<String>()

    fun table(tableName: String): SelectQueryBuilder {
        this.tableName.add(tableName)
        return this
    }

    fun selectByField(field: String, value: Int): String {
        return "SELECT * FROM ${tableName[0]} WHERE $field = '$value'"
    }

}