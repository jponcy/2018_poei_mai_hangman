
package com.tactfactory.poei.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordRepository implements Repository {
    private static final String TABLE_NAME = "word";

    /* package. */ WordRepository() {
    }

    public void create(String ...words) {
        this.create(Arrays.asList(words).stream());
    }

    public void create(Stream<String> words) {
        String values = words
            .map(elt -> String.format("('%s')", elt))
            .collect(Collectors.joining(","));

        String sql = String.format("INSERT INTO "
                + TABLE_NAME
                + " (word) VALUES ", values);

        System.out.println("SQL: " + sql);

        try {
            this.statement().executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Inserrtions failed");
        }
    }

    public int count() {
        int result = 0;
        String sql = "SELECT COUNT(*) AS count FROM " + TABLE_NAME;

        try (ResultSet rs = statement().executeQuery(sql)) {
            rs.next();
            result = rs.getInt("count");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public String findByIndex(int index) {
        String sql = "SELECT word FROM " + TABLE_NAME + " ORDER BY id ASC LIMIT 1, " + index;
        String result = null;

        try (ResultSet rs = this.statement().executeQuery(sql)) {
            rs.next();
            result = rs.getString("word");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    private DatabaseManager manager() {
        return DatabaseManager.instance();
    }

    /**
     * @return
     */
    private Statement statement() {
        return this.manager().statement();
    }
}
