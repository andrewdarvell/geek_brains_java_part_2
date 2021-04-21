package lessons.chatapp.client.history;

import lessons.chatapp.client.exceptions.ChatClientException;

import java.io.*;

public class ChatHistoryWorker {

    public static final String HISTORY_FILE_PATH = "chat_history/history.txt";

    public static void writeMessage(String message) throws ChatClientException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(HISTORY_FILE_PATH, true))) {
            bw.write(message);
            bw.newLine();
            bw.flush();
        } catch (IOException e) {
            throw new ChatClientException("Something wrong with history.txt.log writing process", e);
        }
    }

    public static String readLastMessages(int messagesCount) {
        StringBuilder sb = new StringBuilder();
        try {
            RandomAccessFile raf = new RandomAccessFile(HISTORY_FILE_PATH, "r");
            long filePosition = raf.length() - 1;
            int readLinesCount = 0;

            while (filePosition >= 0 && readLinesCount < messagesCount) {
                raf.seek(filePosition);
                char c = (char) raf.read();
                if (c == '\n') {
                    readLinesCount++;
                }
                sb.append(c);
                filePosition -= 1;
            }
            raf.close();

        } catch (IOException e) {
            System.out.println("Something wrong with history reading process");
            e.printStackTrace();
        }
        return sb.reverse().toString();
    }
}
