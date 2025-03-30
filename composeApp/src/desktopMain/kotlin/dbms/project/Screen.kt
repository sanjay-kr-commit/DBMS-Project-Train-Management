package dbms.project

enum class Screen {
    MainScreen ,
    LoginScreen ,
    RegisterScreen ,
    UserListScreen ,
    HomeScreen
    ;

    override fun toString(): String = name
}