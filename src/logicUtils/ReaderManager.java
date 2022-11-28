package logicUtils;


import dataUsersFlights.FileInfo;

import java.io.BufferedReader;
import java.io.IOException;

public class ReaderManager {

    private final BufferedReader bufferedReader;

    public ReaderManager() {
        bufferedReader = FileInfo.createReader();
    }

    public String readLine() {
        try {
           return bufferedReader.readLine();

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getCause());
        }
    }
}
