package Tudien;

import java.util.Scanner;

public class Main {
    static DictionaryManagement dictionaryManagement = new DictionaryManagement();
    public static void functions(){

        System.out.println("************CÁC CHỨC NĂNG*************");
        System.out.println(" 1. Tra từ Anh - Việt:                       ");
        System.out.println(" 2. Thêm từ mới vào từ điển:      ");
        System.out.println(" 3. Sửa từ:                       ");
        System.out.println(" 4. Xóa từ:                       ");
        System.out.println(" 5. Hiện tất cả các từ trong từ điển");
        System.out.println(" 6. Tra cứu lại các chức năng cửa từ điển");
        System.out.println(" 7. Thoát khỏi chương trình");
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("    CHƯƠNG TRÌNH TỪ ĐIỂN ANH - VIỆT     ");
        functions();
        Scanner scanner = new Scanner(System.in);
        dictionaryManagement.readFromFile();
        while (true) {
            System.out.println("Nhập vào lựa chọn của bạn (Nếu không nhớ, hãy nhập 6)");
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
                    break;
                case 6:
                    functions();
                    break;
                case 7:
                    return;
            }
        }
    }
}
