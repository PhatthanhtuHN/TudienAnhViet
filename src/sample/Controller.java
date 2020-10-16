package sample;

import Tudien.DictionaryManagement;

import java.io.IOException;

public class Controller {
    DictionaryManagement dictionaryManagement;


    public Controller() throws IOException {
        dictionaryManagement = new DictionaryManagement();
        dictionaryManagement.readFromFile();
    }

    public void submit(){

    }
}
