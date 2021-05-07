package lesson7.server;

import java.io.*;

/**
 * homework lesson3. Practice3
 *
 * @author Amina
 * 06.05.2021
 */
/*
1. Добавить в сетевой чат запись локальной истории в текстовый файл на клиенте.
2. После загрузки клиента показывать ему последние 100 строк чата.
3. Добавить "цензуру"
 */
public class ChatStream {

    private static final String fileName = "C:\\JavaPractic\\Prctic2\\src\\lesson7\\chatHistory\\";
    private static BufferedWriter writer;
    private static FileWriter fileWriter;

    public static void setTextHistory(String nick, String textHistory) {
        try {
            fileWriter = new FileWriter(fileName + "history_" + nick + ".txt", true);
            writer = new BufferedWriter(fileWriter);
            writer.write(textHistory +"\n");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null)
                    writer.close();

                if (fileWriter != null)
                    fileWriter.close();
            } catch (IOException ex) {
                System.err.format("IOException: %s%n", ex);
            }

        }
    }

    public static String getTextHistory(String nick) {
        StringBuilder textHistory = new StringBuilder();
        String str;
        BufferedReader reader;
        int countString = 0;
        try {
            reader = new BufferedReader(new FileReader(fileName + "history_" + nick + ".txt"));
            while ((str = reader.readLine()) != null && countString < 101) {
                textHistory.append(str).append("\n");
                countString++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return textHistory.toString();
    }
}
