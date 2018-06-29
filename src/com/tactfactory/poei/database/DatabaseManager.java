
package com.tactfactory.poei.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/** DP: Singleton class to manage database only one connection. */
public final class DatabaseManager {

    private static final String DB_NAME = "2018_poei_java_hang";

    private static final DatabaseManager instance = new DatabaseManager();

    private Connection connection;

    /** Singleton constructor. */
    private DatabaseManager() {
        this.connection = new ConnectionCreator(DatabaseManager.DB_NAME)
                .drop()
                .create()
                .build();

        System.out.println(this.connection);
    }

    /* Free connection. */
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        this.connection.close();
    }

    /** Returns the singleton instance. */
    public static DatabaseManager instance() {
        return DatabaseManager.instance;
    }

    /** Returns a new created prepared statement. */
    public PreparedStatement prepare(String sql) {
        PreparedStatement result = null;

        try {
            result = this.connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    /** Returns a new created statement. */
    public Statement statement() {
        Statement result = null;

        try {
            result = this.connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}
