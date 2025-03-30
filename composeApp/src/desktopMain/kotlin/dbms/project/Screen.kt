package dbms.project

enum class Screen {
    MainScreen ,
    ERDiagramScreen ,
    LoginScreen ,
    RegisterScreen ,
    UserListScreen ,
    HomeScreen ,
    TicketList ,
    RegisterTicket ,
    ;

    override fun toString(): String = name
}