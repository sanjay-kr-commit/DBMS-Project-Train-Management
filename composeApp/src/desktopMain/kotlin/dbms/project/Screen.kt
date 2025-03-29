package dbms.project

enum class Screen {
    MainScreen ,
    LoginScreen ,
    RegisterScreen ,
    UserListScreen ,
    ;

    override fun toString(): String = name
}