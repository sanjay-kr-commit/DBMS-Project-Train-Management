package dbms.project

enum class Screen {
    MainScreen ,
    LoginScreen ,
    RegisterScreen ,
    UserListScreen ,
    HomeScreen ,
    TicketList ,
    RegisterTicket ,
    ;

    override fun toString(): String = name
}