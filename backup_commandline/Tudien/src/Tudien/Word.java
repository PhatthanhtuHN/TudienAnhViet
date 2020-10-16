package Tudien;

import java.util.Objects;

public class Word {
    private String word_target;
    private String word_spell;
    private String word_explain;


    public String getWord_target() {
        return word_target;
    }
    public void setWord_target(String word_target) {
        this.word_target = word_target;
    }

    public String getWord_spell() {
        return word_spell;
    }
    public void setWord_spell(String word_target) {
        this.word_spell = word_spell;
    }

    public String getWord_explain() {
        return word_explain;
    }
    public void setWord_explain(String word_explain) {
        this.word_explain = word_explain;
    }


    public Word(String w1, String w2, String w3) {
        this.word_target = w1;
        this.word_spell = w2;
        this.word_explain = w3;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word = (Word) o;
        return word_target.equals(word.word_target);
    }
}
