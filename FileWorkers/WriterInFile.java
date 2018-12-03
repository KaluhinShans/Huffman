package FileWorkers;

import Haffman.Dictionary;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;


public class WriterInFile {


    public static void writeCompressedFile(int oneByte, NameOfFile name) {
        try (FileOutputStream fos = new FileOutputStream(name.getName() + ".hf", true)) {
            fos.write(oneByte);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeDecompressedFile(byte[] content, NameOfFile name) {
        try (FileOutputStream decompressedFos = new FileOutputStream(name.getName() + ".decompress")) {
            decompressedFos.write(content);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void writeDictionaryFile(Dictionary dictionary, NameOfFile name) {
        try (FileOutputStream dict = new FileOutputStream(name.getName() + ".tbl", false)) {

            ObjectOutputStream oos = new ObjectOutputStream(dict);
            oos.writeObject(dictionary.getMap());
            oos.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}