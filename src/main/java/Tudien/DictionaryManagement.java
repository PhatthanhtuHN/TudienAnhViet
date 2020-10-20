package Tudien;

import java.io.*;

import java.util.ArrayList;
import java.util.Scanner;

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
                dict.addWord(w[0].substring(1).trim(), w.length > 1 ? w[1] : "", define.toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


//    public void insertFromCommandline() {
//        System.out.print("Số từ bạn muốn thêm là: ");
//
//        int n = new Scanner(System.in).nextInt();
//
//        for(int i=0; i<n; i++) {
//            System.out.println("Nhập từ bạn muốn thêm: ");
//            String target = new Scanner(System.in).nextLine();
//            System.out.println("Nhập phát âm của từ bạn muốn thêm: ");
//            String spell = new Scanner(System.in).nextLine();
//            System.out.println("Nhập nghĩa của từ: ");
//            String explain = new Scanner(System.in).nextLine();
//            dict.addWord(target, spell, explain);
//        }
//        System.out.println("Xong!");
//    }

    public String dictionaryLookup(String target) {
//        String nf = " Không tìm thấy từ";
        for (Word w : dict.getList()) {
            if (w.getWord_target().equals(target)) {
                return w.getWord_spell() + "\n" + w.getWord_explain();
            }
//            else return nf;
        }
        return null;
    }

//    public void editWord() {
//        System.out.print("Nhập từ bạn muốn chỉnh sửa: ");
//        String needEdit = new Scanner(System.in).nextLine();
//        System.out.print("Nhập nghĩa bạn muốn chỉnh sửa: ");
//        String edit = new Scanner(System.in).nextLine();
//        for(Word w : dict.getList()){
//            if(w.getWord_target().equals(needEdit)) {
//                w.setWord_explain(edit);
//                break;
//            }
//        }
//        System.out.println("Xong!");
//    }
//
//    public void deleteWord() {
//        System.out.print("Nhập từ bạn muốn xóa: ");
//        Word delete = new Word(new Scanner(System.in).nextLine(), "", "");
//        while (dict.contain(delete)) dict.getList().remove(delete);
//        System.out.println("Xong!");
//    }
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
}
