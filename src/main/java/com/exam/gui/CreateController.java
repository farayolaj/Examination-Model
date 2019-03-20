package com.exam.gui;

import com.exam.Grade;
import com.exam.examiner.Subject;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


/**
 * FXML Controller class
 *
 * @author FarayolaJ
 */
public class CreateController extends Application implements Initializable {

    private Grade g;
    private Subject s;

    private Stage stage;
    private Scene scene;

/*    @FXML
    private Button add;
    @FXML
    private Button remove;
    @FXML
    private TextField grade;
    @FXML
    private TextField name;
    @FXML
    private Button addOption;*/

    @FXML
    private TextArea output;
    @FXML
    private ListView<String> options;
    @FXML
    private TextArea question;
    @FXML
    private TextField option;

    @FXML
    public void addToOptions() {
        String st = option.getText();
        option.clear();
        if (!st.equals("")) {
            options.getItems().add(st);
            options.refresh();
        }
    }

    @FXML
    public void removeFromOptions() {
        String st = options.getSelectionModel().getSelectedItem();
        if (st != null && !st.equals("")) {
            options.getItems().remove(st);
            options.refresh();
        }
    }

    @FXML
    public void removeOptionOnDelete(KeyEvent event) {
        if(event.getCode().equals(KeyCode.DELETE)) removeFromOptions();
    }

    @FXML
    public void addQuestion(ActionEvent event) {
        String q = question.getText();
        ArrayList<String> o = new ArrayList<>(options.getItems());

        if (!q.equals("")) {
            add(q, o);
            clear();
            showOutput();
            question.requestFocus();
        }
    }

    private void add(String q, ArrayList<String> o) {
        if(o.size() != 0) {
            s.add(q, o);
        }
        else {
            s.add(q);
        }
    }

    @FXML
    public void clear() {
        options.getItems().clear();
        options.refresh();
        option.clear();
        question.clear();
    }

    private void showOutput() {
        output.clear();
        output.setText(s.stringify());
    }

    @FXML
    public void save(ActionEvent ae) {
        s.jsonWithSpacing();
    }

    @FXML
    public void open(ActionEvent ae) {
        s = Subject.parse("file2.json");
        showOutput();
    }

    @FXML
    public void close(ActionEvent ae) {
        System.exit(0);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        g = new Grade("Class");
        s = g.addSubject("Subject");

        output.setWrapText(true);
        question.setWrapText(true);
        question.requestFocus();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        AnchorPane root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("create.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Set Questions Easily");
        primaryStage.show();
    }

}
