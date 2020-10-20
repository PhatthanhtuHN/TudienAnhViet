package sample;

import Tudien.DictionaryManagement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.ArrayList;

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

    ObservableList list = FXCollections.observableArrayList();
    @FXML
    private ListView<String> listView;

    public void translate(ActionEvent actionEvent) {
        String txt = textField.getText().trim();
        String search = dictionaryManagement.dictionaryLookup(txt);
        if (search != null) {
            textArea.setText(txt + " /" + search);
        }
    }


    public void suggest(KeyEvent keyEvent) {
        listView.getItems().clear();
        list.removeAll(list);
        if (textField.getText().trim().isEmpty()) {

        } else {
            ArrayList arrayList = dictionaryManagement.dictionarySearcher(textField.getText().trim());
            list.addAll(arrayList);
            listView.getItems().addAll(list);
        }
    }

    public void displaySelected(MouseEvent mouseEvent) {
        String sugg = listView.getSelectionModel().getSelectedItem();
        if (sugg == null||sugg.isEmpty()){}
        else
            textField.setText(sugg);
            textArea.setText(sugg + " /" + dictionaryManagement.dictionaryLookup(sugg));
    }

    public void fasttranslate(KeyEvent keyEvent) {
        String txt = textField.getText().trim();
        String result = dictionaryManagement.dictionaryLookup(txt);
        if (txt == null){}
//        else if (txt != result) {
//            textArea.setText("Không tìm thấy từ");
//        }
        else textArea.setText(txt + " /" + result);
    }
}
