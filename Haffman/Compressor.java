package Haffman;

import FileWorkers.NameOfFile;
import FileWorkers.ReadFile;
import FileWorkers.WriterInFile;

import java.util.*;

public class Compressor {
    NameOfFile nameOfFile;
    private byte[] fileBytes;
    private Dictionary dictionary = new Dictionary();


    public void compress(NameOfFile name) {
        nameOfFile = name;
        fileBytes = ReadFile.readFile(name);

        addForQueue();
        Node root = createTree();
        createdTable(root);
        WriterInFile.writeDictionaryFile(dictionary, name);
        builderBytes(root);
    }

    public int[] countRepeatsOfBytes() {
        int[] repeats = new int[256];
        for (int i : fileBytes) {
            repeats[i]++;
        }
        return repeats;
    }

    public PriorityQueue<Node> addForQueue() {
        int[] repeats = countRepeatsOfBytes();
        PriorityQueue<Node> nodes = new PriorityQueue<>(Comparator.comparingInt(Node::getWeight));
        for (int i = 0; i < repeats.length; i++) {
            if (repeats[i] > 0) {
                Node node = new Node();
                node.setValue(i);
                node.setWeight(repeats[i]);
                nodes.add(node);
            }
        }
        return nodes;
    }

    public Node createTree() {
        PriorityQueue<Node> nodes = addForQueue();
        Node root;

        if (nodes.size() == 1) {
            root = nodes.poll();
            root.setCode("0");
            return root;
        } else {
            while (nodes.size() != 1) {
                Node node1 = nodes.poll();
                Node node2 = nodes.poll();
                Node ultraNode = new Node();

                ultraNode.setWeight(node1.getWeight() + node2.getWeight());
                ultraNode.setLeft(node1);
                ultraNode.setRight(node2);

                nodes.add(ultraNode);
            }
            root = nodes.poll();
            root.setCode("");
        }
        return root;
    }

    public void createdTable(Node root) {
        if (root.getLeft() != null) {
            createdTable(root.getLeft());
        }
        if (root.getRight() != null) {
            createdTable(root.getRight());
        }
        if (root.getValue() != 0) dictionary.putInMap(root.getValue(), root.getCode());
    }

    public void builderBytes(Node root) {

        String[] path = getPathStringArray(root);

        String oneByte = "";
        int counterByte = 0;

        for (String code : path) {

            for (char c : code.toCharArray()) {
                if (counterByte > 8) {
                    WriterInFile.writeCompressedFile(Integer.parseInt(oneByte, 2), nameOfFile);
                    counterByte = 0;
                    oneByte = "";
                }
                if (c == '0') oneByte += "0";
                if (c == '1') oneByte += "1";
                counterByte++;
            }
        }

        if (counterByte != 0) {
            int size = 8 - counterByte;
            for (int i = 0; i < size; i++) {
                oneByte += "0";
            }
            WriterInFile.writeCompressedFile(Integer.parseInt(oneByte, 2), nameOfFile);
        }

    }

    public String[] getPathStringArray(Node root) {
        String[] path = new String[root.getWeight()]; //Root weight, it is all bytes in file
        int counter = 0;

        for (int i : fileBytes) {

            for (int key : dictionary.getMap().keySet()) {
                if (i == key) {
                    path[counter++] = dictionary.getKey(key);
                }
            }
        }

        return path;
    }
}