package com.example.senlastudy.database

class SelectQueryBuilder {
    private val tableName = mutableListOf<String>()
    private val selectForDatabase = mutableListOf<String>()

    fun table(tableName: String): SelectQueryBuilder {
        this.tableName.add(tableName)
        return this
    }

    fun where():SelectQueryBuilder{
        selectForDatabase.add("WHERE")
        return this
    }

    fun field(fieldName:String):SelectQueryBuilder{
        selectForDatabase.add(fieldName)
        return this
    }

    fun argument(argument:String):SelectQueryBuilder{

        selectForDatabase.add(argument)
        return this
    }

}