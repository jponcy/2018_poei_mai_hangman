
package com.tactfactory.poei.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.Map;

/** DP: Singleton class to manage database only one connection. */
public final class DatabaseManager {

    private static final String DB_NAME = "2018_poei_java_hang";

    private static DatabaseManager instance;

    private Map<Class<?>, Repository> repositories =
            Collections.singletonMap(WordRepository.class, new WordRepository());

    private Connection connection;

    /** Singleton constructor. */
    private DatabaseManager() {
    }

    /* Free connection. */
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        this.connection.close();
    }

    /** Returns the singleton instance. */
    public static DatabaseManager instance() {
        if (DatabaseManager.instance == null) {
            DatabaseManager.instance = new DatabaseManager();

            DatabaseManager.instance.connection = new ConnectionCreator(DatabaseManager.DB_NAME)
                    .drop()
                    .create()
                    .createWordTable(true)
                    .build();
        }

        return DatabaseManager.instance;
    }

    @SuppressWarnings("unchecked")
    public <T> T getRepository(Class<?> repository) {
        return (T) this.repositories.get(repository);
    }

    public static <T> T repository(Class<?> repository) {
        return DatabaseManager.instance().getRepository(repository);
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
