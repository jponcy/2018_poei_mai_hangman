
package com.tactfactory.poei.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

/**
 * Helps to create the database (table and data).
 */
class ConnectionCreator {
    private static final String DB_HOST = "127.0.0.1";

    private static final String DB_PASSWORD = "jepreferepostgres";

    private static final String DB_USERNAME = "root";

    private String dbName;

    public ConnectionCreator(String databaseName) {
        this.dbName = databaseName;
    }

    /** Drops the database.
     * @return */
    public ConnectionCreator drop() {
        this.actionWithoutUseDatabase("DROP DATABASE IF EXISTS " + this.dbName);
        return this;
    }

    /** Creates the database.
     * @return */
    public ConnectionCreator create() {
        this.actionWithoutUseDatabase("CREATE DATABASE IF NOT EXISTS " + this.dbName);
        return this;
    }

    /** Finalizes the builder, then returns the built connection. */
    public Connection build() {
        Connection connection = this.from(this.dbName);

        if (connection == null) {
            System.err.println("Fail to initialize the " + this.dbName + " database");
            System.exit(-1);
        }

        return connection;
    }

    /** Do action on SGBDr without selected database (to CREATE/DROP databases for example). */
    private void actionWithoutUseDatabase(String sql) {
        Connection connection = this.from();

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /** Returns connection to SGBDr not connecting to specific database. */
    private Connection from() {
        return this.from(null);
    }

    /** Returns connection to SGBDr using database with given name. */
    private Connection from(String dbName) {
        Connection connection = null;
        MysqlDataSource dataSource = new MysqlDataSource();

        dataSource.setUser(DB_USERNAME);
        dataSource.setPassword(DB_PASSWORD);
        dataSource.setServerName(DB_HOST);

        if (dbName != null) {
             dataSource.setDatabaseName(dbName);
        }

        try {
            dataSource.setServerTimezone("UTC");
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }
}
