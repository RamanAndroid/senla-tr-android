package com.example.senlastudy.database

class QueryBuilder {

    private val sqlText = StringBuilder()
    private val sqlText2 = StringBuilder()
    private val sqlStart = mutableListOf<String>()
    private val sqlArguments = mutableListOf<String>()
    private val sqlFields = mutableListOf<String>()

    fun table(tableName: String): QueryBuilder {
        this.sqlStart.add(tableName)
        return this
    }

    fun pkField(id: String): QueryBuilder {
        this.sqlFields.add(" $id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,")
        return this
    }

    fun field(name: String, type: String): QueryBuilder {
        this.sqlFields.add("$name $type")

        return this
    }

    fun create(): String {
        sqlFields.forEach {

            var i = 0
            if (i == sqlFields.size - 1) {
                sqlText.append("")
            } else {
                sqlText.append(it)
            }
            i += 1
        }
        return StringBuilder().append("CREATE TABLE if not EXISTS ${sqlStart[0]} ($sqlText)")
            .toString()
    }

    fun insertField(argument: String): QueryBuilder {
        this.sqlArguments.add(argument)
        this.sqlArguments.add(",")
        return this
    }

    fun insert(): String {
        var i = 0
        sqlArguments.forEach {

            if (i == sqlArguments.size - 1) {
                sqlText2.append("")
            } else {
                sqlText2.append(it)
            }
            i += 1
        }

        sqlFields.forEach {
            var a = 0
            if (a == sqlFields.size - 1) {
                sqlText.append("")
            } else {
                sqlText.append(it)
            }
            a += 1
        }
        return StringBuilder().append("INSERT INTO ${sqlStart[0]} ($sqlText) VALUES ($sqlText2)")
            .toString()
    }


}