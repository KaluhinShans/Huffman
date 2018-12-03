package Haffman;

public class Node {

    private int value;
    private int weight;
    private String code;
    private Node left;
    private Node right;

    public void setCode(String code) {
        this.code = code;
        if (left != null) left.setCode(code + "0");
        if (right != null) right.setCode(code + "1");
    }

    public String getCode(){
        return code;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }


}
