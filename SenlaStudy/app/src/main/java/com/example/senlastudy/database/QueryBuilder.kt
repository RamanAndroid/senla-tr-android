package com.example.senlastudy.database

class QueryBuilder {

    private val tableName = mutableListOf<String>()
    private val fieldValues = mutableListOf<String>()
    private val tableFieldsWithParameter = mutableListOf<String>()

    fun table(tableName: String): QueryBuilder {
        this.tableName.add(tableName)
        return this
    }

    fun pkField(id: String): QueryBuilder {
        this.tableFieldsWithParameter.add("$id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT")
        return this
    }

    fun insertField(name: String, type: String): QueryBuilder {
        this.tableFieldsWithParameter.add("$name $type")
        return this
    }

    fun create(): String {
        return "CREATE TABLE if not EXISTS ${tableName[0]} (${
            tableFieldsWithParameter.joinToString(
                ",",
                "",
                ""
            )
        })"

    }

    fun insertValue(argument: String): QueryBuilder {
        this.fieldValues.add(argument)
        return this
    }

    fun insert(): String {
        return "INSERT INTO ${tableName[0]} (${
            tableFieldsWithParameter.joinToString(
                ",",
                "",
                ""
            )
        }) VALUES (${fieldValues.joinToString("", "", "")})"

    }

}