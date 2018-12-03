package FileWorkers;

import Haffman.Compressor;
import Haffman.Decompressor;

import java.util.Scanner;

public class NameOfFile {
    static String name;
    static String ext;

    public static void getFile() {
        NameOfFile nameOfFile = new NameOfFile();
        nameOfFile.setLocation();
        nameOfFile.typeOfFile(nameOfFile);

    }

    private void setLocation() {
        Scanner scan = new Scanner(System.in);
        System.out.println("File location: ");
        String location = scan.nextLine();

        int lastIndex = location.lastIndexOf('.');
        if(lastIndex == -1) System.out.println("ERROR: File have't type");
        String extType = location.substring(lastIndex);
        String file = location.substring(0, lastIndex);
        setName(file);
        setExt(extType);
    }

    private void typeOfFile(NameOfFile nameOfFile) {
        if (ext.equals(".hf")) {
            Decompressor dec = new Decompressor();
            dec.decompress(nameOfFile);
        } else {
            Compressor com = new Compressor();
            com.compress(nameOfFile);
        }
    }

    private static void setName(String nameOfFile) {
        name = nameOfFile;
    }

    private static void setExt(String extType) {
        ext = extType;
    }

    public String getName() {
        return name;
    }

    public String getExt() {
        return ext;
    }
}
