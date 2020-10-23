package Tudien;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

import java.io.*;

import java.util.ArrayList;
import java.util.Scanner;

import static com.sun.speech.freetts.Gender.FEMALE;

public class DictionaryManagement {
    Dictionary dict = new Dictionary();
    private String FileTudien = "anhviet.txt";

    public void insertFromFile() {
        Scanner sc = null;
        File file = null;
        try {
            file = new File(FileTudien);
            InputStream in = new FileInputStream(file);
            sc = new Scanner(in);
            sc.nextLine();
            for (String line = sc.nextLine(); line != null; ) {
                StringBuilder define = new StringBuilder();
                String w[] = {};
                if (line.startsWith("@")) {
                    w = line.split("/", 2);
                    line = sc.nextLine();
                }
                while (line != null && !line.startsWith("@")) {
                    define.append(line).append("\n");
                    line = (sc.hasNext()) ? sc.nextLine() : null;
                }
                dict.addWord(w[0].substring(1).trim(), w.length > 1 ? "/" + w[1] : "", define.toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String dictionaryLookup(String target) {
        String nf = " Không tìm thấy từ";
        for (Word w : dict.getList()) {
            if (w.getWord_target().equals(target)) {
                return w.getWord_spell() + "\n" + w.getWord_explain();
            }
//            else return nf;
        }
        return null;
    }


    public void add(Word w) {
        String target = w.getWord_target();
        String spell = w.getWord_spell();
        String explain = w.getWord_explain();

        dict.addWord(target, spell, explain);
    }

//
//    public void showallWord(){
//        for (Word w : dict.getList()){
//            System.out.println(w.getWord_target());
//        }
//    }


    public ArrayList dictionarySearcher(String str) {
        ArrayList arrayList = new ArrayList();
        int num = str.length();
        for (Word w : dict.getList()) {
            if (w.getWord_target().length() >= num)
            if (w.getWord_target().substring(0, num).equals(str)) {
                arrayList.add(w.getWord_target());

            }
        }
        return arrayList;
    }

    public void editWord(String needEdit, String spell, String edit) {
        for (int i = 0; i < dict.getList().size(); i++) {
            if ((dict.getList().get(i)).getWord_target().equals(needEdit)) {
                (dict.getList().get(i)).setWord_explain(edit);
            }
        }
    }

    public void deleteWord(String needDelete){
        for (int i = 0; i < dict.getList().size(); i++) {
            if ((dict.getList().get(i)).getWord_target().equals(needDelete)) {
                dict.getList().remove(i);
            }
        }
    }

    public void speech(String txt) {
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        com.sun.speech.freetts.Voice syntheticVoice = VoiceManager.getInstance().getVoice("kevin16");
        if (syntheticVoice != null) {
            syntheticVoice.allocate();
            try {
                syntheticVoice.setRate(150);
                syntheticVoice.setPitch(70);
                syntheticVoice.setVolume(3);
                syntheticVoice.speak(txt);
                syntheticVoice.deallocate();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        else {
            throw new IllegalStateException("Cannot find voice: kevin16");
        }
    }

}
