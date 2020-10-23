package Tudien;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

import java.io.*;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;


public class DictionaryManagement {
    Dictionary dict = new Dictionary();
    private String Tudien = "anhviet.txt";
    String TudienEdited = "anhviet_edited.txt";

    public void insertFromFile() {
        Scanner sc = null;
        File file = null;
        try {
            file = new File(TudienEdited);
            if (!file.exists()) file = new File(Tudien);
            InputStream in = new FileInputStream(file);
            sc = new Scanner(in);
//            sc.nextLine();
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
            sc.close();
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
        return "";
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
        Voice syntheticVoice = VoiceManager.getInstance().getVoice("kevin16");
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

    public static String translate(String langFrom, String langTo, String text) throws IOException {
        // INSERT YOU URL HERE
        String urlStr = "https://script.google.com/macros/s/AKfycbxKw8SInMj5zU1lIIAd4DnNY9Mx94JulwA6g8mPAst4uDQC-d0/exec" +
                "?q=" + URLEncoder.encode(text, "UTF-8") +
                "&target=" + langTo +
                "&source=" + langFrom;
//        System.out.println(urlStr);
        URL url = new URL(urlStr);
        StringBuilder response = new StringBuilder();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
//        System.out.println(con);
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
//        return "";
    }


    public void savetoFile() {
        File file = new File("anhviet_edited.txt");
        try {
//            FileOutputStream outputStream = new FileOutputStream(file);
            FileWriter writer = new FileWriter(file);
            for (Word i : dict.getList()) {
                writer.write("@" + i.getWord_target());
                writer.write(i.getWord_spell() + "\n");
                writer.write(i.getWord_explain().trim() + "\n");
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
