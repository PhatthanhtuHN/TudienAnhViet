package sample;

import Tudien.DictionaryManagement;
import Tudien.Word;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

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
            textArea.setText(txt + " " + search);
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
        if (sugg == null || sugg.isEmpty()) {
        } else
            textField.setText(sugg);
        textArea.setText(sugg + " " + dictionaryManagement.dictionaryLookup(sugg));
    }

    public void fasttranslate(KeyEvent keyEvent) {
        String txt = textField.getText().trim();
        String result = dictionaryManagement.dictionaryLookup(txt);
        if (txt == null) {
        }
//        else if (txt != result) {
//            textArea.setText("Không tìm thấy từ");
//        }
        else textArea.setText(txt + " " + result);
    }

    public void add(ActionEvent actionEvent) {
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Thêm từ");
        dialog.setHeaderText("Nhập vào từ bạn muốn thêm: ");

        ButtonType addButton = new ButtonType("Thêm từ", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButton, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField target = new TextField();
        grid.add(target, 0, 0);
        TextArea explain = new TextArea();
        grid.add(explain, 0, 1);


        Node loginButton = dialog.getDialogPane().lookupButton(addButton);
        loginButton.setDisable(true);

        target.textProperty().addListener((observableValue, s, t1) -> {
            loginButton.setDisable(t1.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButton) {
                String str = dictionaryManagement.dictionaryLookup(target.getText().trim());
                if (str != null) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Từ bạn nhập vào đã tồn tại.");
                    alert.show();
                } else {
                    return new Pair<>(target.getText().trim(), explain.getText());
                }
            }
            return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();
        result.ifPresent(userPass -> {
            dictionaryManagement.add(new Word(userPass.getKey().trim(), "", userPass.getValue()));
        });
    }


    public void edit(ActionEvent actionEvent) {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Sửa từ");

        ButtonType changeButton = new ButtonType("Sửa từ", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(changeButton, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextArea wordchange = new TextArea();
        wordchange.setText(dictionaryManagement.dictionaryLookup(textField.getText().trim()));
        grid.add(wordchange, 0, 0);


        Node loginButton = dialog.getDialogPane().lookupButton(changeButton);
        loginButton.setDisable(true);

        wordchange.textProperty().addListener((observableValue, s, t1) -> {
            loginButton.setDisable(t1.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == changeButton) {
                return new String(wordchange.getText());
            }
            return null;
        });

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(userPass -> {
            dictionaryManagement.editWord(textField.getText().trim(), "", result.get());
        });
    }

    public void delete(ActionEvent actionEvent) {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Xóa từ");
        dialog.setHeaderText("Nhập từ bạn muốn xóa");

        ButtonType deleteButton = new ButtonType("Xóa", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(deleteButton, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField word = new TextField();

        grid.add(word, 0, 0);


        Node loginButton = dialog.getDialogPane().lookupButton(deleteButton);
        loginButton.setDisable(true);

        word.textProperty().addListener((observableValue, s, t1) -> {
            loginButton.setDisable(t1.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == deleteButton) {
                String str = word.getText().trim();
                String re = dictionaryManagement.dictionaryLookup(str);
                if (re != null) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Xác nhận");
                    alert.setHeaderText("Bạn chắc chắn muốn xóa từ?");
                    ButtonType buttonYes = new ButtonType("Có", ButtonBar.ButtonData.YES);
                    ButtonType buttonCancel = new ButtonType("Không", ButtonBar.ButtonData.CANCEL_CLOSE);
                    alert.getButtonTypes().setAll(buttonYes, buttonCancel);
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == buttonYes) {
                        return new String(word.getText().trim());
                    }
                } else {
                    Alert alert2 = new Alert(Alert.AlertType.ERROR);
                    alert2.setHeaderText("Từ không tồn tại.");
                    alert2.showAndWait();
                }
            }
            return null;
        });

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(userPass -> {
            dictionaryManagement.deleteWord(result.get().trim());
        });
    }

}

