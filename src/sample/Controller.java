package sample;

import Tudien.DictionaryManagement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;

public class Controller {
    DictionaryManagement dictionaryManagement;

    public Controller() throws IOException {
        dictionaryManagement = new DictionaryManagement();
        dictionaryManagement.insertFromFile();
    }

    @FXML
    private TextField textField;
    @FXML
    private TextArea textArea;
    @FXML
    private ListView<String> listView;
    public void submit(ActionEvent actionEvent) {
        String a = textField.getText();
        textArea.setText(a);
    }
}
