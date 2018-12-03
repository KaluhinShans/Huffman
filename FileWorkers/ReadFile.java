package FileWorkers;

import Haffman.Dictionary;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class ReadFile {

    public static byte[] readFile(NameOfFile name) {
        Path path = Paths.get(name.getName() + name.getExt());
        byte[] content;
        try {
            content = Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return content;
    }

    public static void readDictionary(Dictionary dictionary, NameOfFile name){
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(name.getName() + ".tbl"))) {
            dictionary.setMap((Map<Integer, String>) inputStream.readObject());

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}