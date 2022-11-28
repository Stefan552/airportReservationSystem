package logicUtils;

import dataUsersFlights.FileInfo;

import java.io.BufferedWriter;
import java.io.IOException;

public class WriterManager {
    private final BufferedWriter bufferedWriter;
    public  WriterManager(){
        bufferedWriter= FileInfo.createWriter();
    }
    public WriterManager(BufferedWriter bufferedWriter) {
        this.bufferedWriter = bufferedWriter;
    }

    public void write(String str){
        try {
            bufferedWriter.write(str);
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void flush() {
        try {

            bufferedWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
