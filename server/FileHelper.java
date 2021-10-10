package server;
import java.io.*;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import data.Ticket;

/**
 * Help to work with files to save and download collection.
 */

public class FileHelper {
    private String filename;
    private String env;
    private Gson gson = new Gson();
    //private String filePath = "C:\\Users\\User\\IdeaProjects\\lab5\\Ticket";

    public FileHelper(String env) {
        this.env = env;
    }

    public void saveCollectionToFile(Collection<?> collection) throws IOException {
        if (System.getenv().get(env) != null) {
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(System.getenv().get(env));
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(new File(System.getenv().get(env))));
                String tmp = gson.toJson(collection);
                byte[] buffer = tmp.getBytes();
                //System.out.println(tmp);
                //fileOutputStream.write(tmp.getBytes());
                bufferedOutputStream.write(buffer, 0, buffer.length);
                bufferedOutputStream.flush();
                bufferedOutputStream.close();
                //collectionFileWriter.write(gson.toJson(collection));
                System.out.println("Коллекция успешна сохранена в файл!");
            } catch (IOException exception) {
                System.err.println("Загрузочный файл является директорией/не может быть открыт!");
            }
        } else System.err.println("Системная переменная с загрузочным файлом не найдена!");
    }

    /*public static String read(BufferedInputStream bufferedInputStream) throws IOException {
        String string;
        int i;
        ArrayList<String> a = new ArrayList<String>();
        while ((i = bufferedInputStream.read()) != -1) {
            char j = (char) i;
            String s = String.valueOf(new char[]{j});
            a.add(s);
        }
        System.out.println("a");
        return a.toString();
    }*/

    public HashSet<Ticket> readCollectionFromFile() {
        if (System.getenv().get(env) != null) {
            try {
                HashSet<Ticket> collection;
                FileInputStream fileInputStream = new FileInputStream(System.getenv().get(env));
                BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
                int i;
                String str = "";
                while((i = bufferedInputStream.read())!= -1){
                    str = str + (char)i;
                }
                Type collectionType = new TypeToken<HashSet<Ticket>>() {}.getType();
                //System.out.println(str);
                collection = gson.fromJson(str, collectionType);
                //collection = gson.fromJson(read(bufferedInputStream), collectionType);
                System.out.println("Коллекция успешна загружена из файла!");
                return collection;
            } catch (IOException exception) {
                System.err.println("Загрузочный файл является директорией/не может быть открыт!");
            }
        }
        return new HashSet<Ticket>();
    }
}
