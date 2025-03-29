package dbms.project.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import dbms.project.TrainDatabase
import dbms.project.databaseName
import dbms.project.inMemory
import dbms.project.path
import dbms.project.sqlDriver

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class DriverFactory {
    actual fun createDriver(): SqlDriver {
        val driver: SqlDriver = if ( inMemory ) JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY) else JdbcSqliteDriver(
            "$sqlDriver$path$databaseName"
        )
        TrainDatabase.Schema.create(driver)
        return driver
    }
}