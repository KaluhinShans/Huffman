package Haffman;

import java.util.HashMap;
import java.util.Map;


public class Dictionary {
    Map<Integer, String> dictionary = new HashMap<>();

    public void setMap(Map<Integer, String> dictionary){
        this.dictionary = dictionary;
    }

    public Map<Integer, String> getMap() {
        return dictionary;
    }

    public void putInMap(Integer i, String code) {
        dictionary.put(i, code);
    }

    public String getKey(Integer i) {
        return dictionary.get(i);
    }
}
