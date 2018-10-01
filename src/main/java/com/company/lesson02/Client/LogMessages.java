package com.company.lesson02.Client;

import java.io.*;

public class LogMessages {

    ClassLoader classLoader = this.getClass().getClassLoader();
    File logFile = new File("logMessages.log");
    FileWriter fileWriter;
    FileInputStream fileInputStream;
    BufferedReader bufferedReader;

    public LogMessages() {
        if (!logFile.exists()) {
            try {
                boolean fileCreated = logFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            fileWriter = new FileWriter(logFile,true);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void writeMessage(String mesage) {
        try {
            fileWriter.append(mesage);
            fileWriter.append('\n');
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readMessege(int messagesCount) {

    }

    public void readMesseges(int messagesCount) {

        String tmp;
        StringBuffer stringBuffer = new StringBuffer();

        try {
            fileInputStream = new FileInputStream(logFile);
            bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            while ((tmp = bufferedReader.readLine()) != null && messagesCount > 0) {
                stringBuffer.append(tmp);
                stringBuffer.append("\n");
                messagesCount--;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(stringBuffer.toString());

    }
}
