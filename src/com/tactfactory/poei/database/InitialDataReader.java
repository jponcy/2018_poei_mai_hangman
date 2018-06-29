
package com.tactfactory.poei.database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.stream.Stream;

public class InitialDataReader<T> {

    private Stream<T> data;

    @SuppressWarnings("unchecked")
    public InitialDataReader(String filename) {
        try (BufferedReader r = new BufferedReader(new FileReader(new File(filename)))) {
            this.data = (Stream<T>) r.lines();//.collect(Collectors.toList());
        } catch (Exception e) {
        }
    }

    public Stream<T> get() {
        return this.data;
    }
}
