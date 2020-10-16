package Tudien;

import java.io.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;

public class DictionaryManagement {
    Dictionary dict = new Dictionary();
    private String FileTudien = "./src/Tudien/anhviet.txt";

    public void insertFromFile() {
        Scanner sc = null;
        File file = null;
        try {
            file = new File(FileTudien);
            InputStream in = new FileInputStream(file);
            sc = new Scanner(in);
            sc.nextLine();
            for (String line = sc.nextLine(); line != null; ){
                StringBuilder define = new StringBuilder();
                String w[] = {};
                if (line.startsWith("@")) {
                     w = line.split("/", 2);
                    line = sc.nextLine();
                }
                while (line != null && !line.startsWith("@")){
                    define.append(line).append("\n");
                    line = (sc.hasNext()) ? sc.nextLine() : null;
                }
                dict.addWord(w[0].substring(1).trim(), w.length > 1 ? w[1] : "", define.toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public void exporttoFile() {
//        Scanner sc = null;
//        FileWriter fw = null;
//        File file = null;
//        try {
//            file = new File("./src/Tudien/anhviet_1.txt");
//            if (!file.exists()) {
//                file.createNewFile();
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public void insertFromCommandline() {
        System.out.print("Số từ bạn muốn thêm là: ");

        int n = new Scanner(System.in).nextInt();

        for(int i=0; i<n; i++) {
            System.out.println("Nhập từ bạn muốn thêm: ");
            String target = new Scanner(System.in).nextLine();
            System.out.println("Nhập phát âm của từ bạn muốn thêm: ");
            String spell = new Scanner(System.in).nextLine();
            System.out.println("Nhập nghĩa của từ: ");
            String explain = new Scanner(System.in).nextLine();
            dict.addWord(target, spell, explain);
        }
        System.out.println("Xong!");
    }

    public  void dictionaryLookup() {
        System.out.print("Nhập từ bạn muốn dịch : ");
        String search = new Scanner(System.in).nextLine();
        boolean f = false;
        for(Word w : dict.getList()){
            if(w.getWord_target().equals(search)) {
                System.out.println("Phát âm: /" + w.getWord_spell() + " \n" + w.getWord_explain());
                f = true;
            }
        }
        if(!f) System.out.println("Không tìm thấy từ!");
    }

    public void editWord() {
        System.out.print("Nhập từ bạn muốn chỉnh sửa: ");
        String needEdit = new Scanner(System.in).nextLine();
        System.out.print("Nhập nghĩa bạn muốn chỉnh sửa: ");
        String edit = new Scanner(System.in).nextLine();
        for(Word w : dict.getList()){
            if(w.getWord_target().equals(needEdit)) {
                w.setWord_explain(edit);
                break;
            }
        }
        System.out.println("Xong!");
    }

    public void deleteWord() {
        System.out.print("Nhập từ bạn muốn xóa: ");
        Word delete = new Word(new Scanner(System.in).nextLine(), "", "");
        while (dict.contain(delete)) dict.getList().remove(delete);
        System.out.println("Xong!");
    }

    public void showallWord(){
        for (Word w : dict.getList()){
            System.out.println(w.getWord_target());
        }
    }

//    public void dictionarySearcher() {
//        System.out.print("Nhập một vài chữ cái bạn nhớ: ");
//        String suggestions = new Scanner(System.in).nextLine();
//        for(Word w : dict.getList()){
//            if(w.getWord_target().startsWith(suggestions, 0)) {
//                System.out.println(w.getWord_target() + ":  " + w.getWord_explain());
//            }
//        }
//    }
}
