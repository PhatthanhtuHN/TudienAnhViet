package Tudien;

import java.util.ArrayList;

public class Dictionary {
    private ArrayList<Word> list = new ArrayList<Word>();
    public ArrayList<Word> getList() {
        return list;
    }

    public Dictionary(){}

    public void addWord(String w1, String w2, String w3) {
        Word w = new Word(w1, w2, w3);
        this.list.add(w);
    }
    public boolean contain(Word word){
        return list.contains(word);
    }
}
