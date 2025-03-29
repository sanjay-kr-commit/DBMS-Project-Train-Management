package dbms.project

private var _path : String? = null
private var _inMemory : Boolean? = null

var path : String
    get() = _path ?: throw CallBeforeAssignException("path")
    set(value) { _path = value }

var inMemory : Boolean
    get() = _inMemory ?: throw CallBeforeAssignException("inMemory")
    set(value) { _inMemory = value }
