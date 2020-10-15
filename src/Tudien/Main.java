package Tudien;

import java.util.Scanner;

public class Main {
    static DictionaryManagement dictionaryManagement = new DictionaryManagement();
    public static void functions(){
        System.out.println("       CHƯƠNG TRÌNH TỪ ĐIỂN       ");
        System.out.println("**********CÁC CHỨC NĂNG***********");
        System.out.println(" 1. TRA TU:                       ");
        System.out.println(" 2. THÊM TỪ MỚI VÀO TỪ ĐIỂN:      ");
        System.out.println(" 3. SỬA TỪ:                       ");
        System.out.println(" 4. XÓA TỪ:                       ");
        System.out.println(" 5. HIỆN TẤT CẢ CÁC TỪ TRONG TỪ ĐIỂN");
        System.out.println(" 6. THOÁT KHỎI CHƯƠNG TRÌNH       ");
        System.out.println("                                  ");
        System.out.println();
    }

    public static void main(String[] args) {
        functions();
        Scanner scanner = new Scanner(System.in);
        dictionaryManagement.readFromFile();
        while (true) {
            System.out.println("Nhập vào lựa chọn của bạn");
            switch (scanner.nextInt()) {
                case 1:
                    dictionaryManagement.dictionaryLookup();
                    break;
                case 2:
                    dictionaryManagement.insertFromCommandline();
                    break;
                case 3:
                    dictionaryManagement.editWord();
                    break;
                case 4:
                    dictionaryManagement.deleteWord();
                    break;
                case 5:
                    dictionaryManagement.showallWord();
                case 6:
                    return;

            }
        }
    }
}
