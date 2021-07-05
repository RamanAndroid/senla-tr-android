package com.example.senlastudy.database.querybuilder

class CreateQueryBuilder {

    private val tableName = mutableListOf<String>()
    private val tableFieldsWithParameter = mutableMapOf<String, String>()

    fun table(tableName: String): CreateQueryBuilder {
        this.tableName.add(tableName)
        return this
    }

    fun pkField(id: String, autoIncrement: Boolean): CreateQueryBuilder {
        val field = StringBuilder()
        field.append("INTEGER NOT NULL PRIMARY KEY")
        if (!autoIncrement) {
            field.append(" AUTOINCREMENT")
        }
        this.tableFieldsWithParameter[id] = field.toString()
        return this
    }

    fun insertIntegerField(name: String, isEqualNull: Boolean): CreateQueryBuilder {
        val field = StringBuilder()
        field.append("INTEGER")
        if (!isEqualNull) {
            field.append(" NOT NULL")
        }
        this.tableFieldsWithParameter[name] = field.toString()
        return this
    }

    fun insertTextField(name: String, isEqualNull: Boolean): CreateQueryBuilder {
        val field = StringBuilder()
        field.append("TEXT")
        if (!isEqualNull) {
            field.append(" NOT NULL")
        }
        this.tableFieldsWithParameter[name] = field.toString()
        return this
    }

    fun insertBlobField(name: String, isEqualNull: Boolean): CreateQueryBuilder {
        val field = StringBuilder()
        field.append("BLOB")
        if (!isEqualNull) {
            field.append(" NOT NULL")
        }
        this.tableFieldsWithParameter[name] = field.toString()
        return this
    }

    fun insertRealField(name: String, isEqualNull: Boolean): CreateQueryBuilder {
        val field = StringBuilder()
        field.append("REAL")
        if (!isEqualNull) {
            field.append(" NOT NULL")
        }
        this.tableFieldsWithParameter[name] = field.toString()
        return this
    }

    fun create(): String {
        val requestForCreateDatabaseColumn = mutableListOf<String>()
        tableFieldsWithParameter.forEach {
            requestForCreateDatabaseColumn.add("${it.key} ${it.value}")
        }

        return requestForCreateDatabaseColumn.joinToString(
            ",",
            "CREATE TABLE IF NOT EXISTS ${tableName[0]} (",
            ")"
        )
    }

}