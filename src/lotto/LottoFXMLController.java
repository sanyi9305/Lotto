/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lotto;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;

import java.util.ResourceBundle;
import java.util.Set;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 *
 * @author Sannyi
 */
public class LottoFXMLController implements Initializable {

    //<editor-fold defaultstate="collapsed" desc="Class_Variable">
    private final int MAX = 90;
    private final int MIN = 1;
    private int genNum1;
    private int genNum2;
    private int genNum3;
    private int genNum4;
    private int genNum5;

    private int selNum1;
    private int selNum2;
    private int selNum3;
    private int selNum4;
    private int selNum5;
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="FXML_items_import">
    @FXML
    private Pane basePane;
    @FXML
    private Pane alertPane;
    @FXML
    private Label alertText;
    @FXML
    private Button alertButton;

    @FXML
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    private Label label3;
    @FXML
    private Label label4;
    @FXML
    private Label label5;
    @FXML
    private Label resultLabel;
    @FXML
    private TextField input1;
    @FXML
    private TextField input2;
    @FXML
    private TextField input3;
    @FXML
    private TextField input4;
    @FXML
    private TextField input5;
//</editor-fold>

    @FXML
    private void handleAlertButton(ActionEvent event) {
        basePane.setDisable(false);
        basePane.setOpacity(1);
        alertPane.setVisible(false);
        alertText.setText("");
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {

//generated numbers
        genNum1 = 0;
        genNum2 = 0;
        genNum3 = 0;
        genNum4 = 0;
        genNum5 = 0;
        genNum1 = getRandomNumber();
        genNum2 = getRandomNumber();
        genNum3 = getRandomNumber();
        genNum4 = getRandomNumber();
        genNum5 = getRandomNumber();
//set label to generated numbers
        label1.setText("" + genNum1);
        label2.setText("" + genNum2);
        label3.setText("" + genNum3);
        label4.setText("" + genNum4);
        label5.setText("" + genNum5);
        calculate();

    }

    private String calculate() {
        try {
            selNum1 = Integer.parseInt(input1.getText());
            selNum2 = Integer.parseInt(input2.getText());
            selNum3 = Integer.parseInt(input3.getText());
            selNum4 = Integer.parseInt(input4.getText());
            selNum5 = Integer.parseInt(input5.getText());
        } catch (Exception e) {
            alert("Nem számot adtál meg");
            return "";
        }

        Set<Integer> selectedNumbers = new HashSet<>();
        selectedNumbers.add(selNum1);
        selectedNumbers.add(selNum2);
        selectedNumbers.add(selNum3);
        selectedNumbers.add(selNum4);
        selectedNumbers.add(selNum5);

        if (selectedNumbers.size() < 5) {
            alert("A számoknak különbözni kell");
            return "";
        }

        ArrayList<Integer> userNumbers = new ArrayList<>(selectedNumbers);
        ArrayList<Integer> genNumbers = new ArrayList<>();

        genNumbers.add(genNum1);
        genNumbers.add(genNum2);
        genNumbers.add(genNum3);
        genNumbers.add(genNum4);
        genNumbers.add(genNum5);

        for (int i = 0; i < userNumbers.size(); i++) {

            if (userNumbers.get(i) < 0 || userNumbers.get(i) > 90) {
                alert("1 és 90 közötti számot adj meg");
                return "";
            }

        }
        int result = 0;

        for (int k = 0; k < 5; k++) {
            for (int j = 0; j < 5; j++) {
                if ((int)userNumbers.get(k)== (int)genNumbers.get(j)) {
                    result++;
                    System.out.println(result);
                }

            }
        }
        switch (result) {
            case 0:
                resultLabel.setText("NINCS találat");
                break;
            case 1:
                resultLabel.setText("Egyes találat ez nem elég");
                break;
            case 2:
                resultLabel.setText("Kettes találat ez már fizet");
                break;
            case 3:
                resultLabel.setText("Hármas találat");
                break;
            case 4:
                resultLabel.setText("Négyes találat ez már alakul");
                break;
            case 5:
                resultLabel.setText("Ötös találat tied a főnyeremény");
                break;

        }
        return "";
    }

    private void alert(String text) {
        basePane.setDisable(true);
        basePane.setOpacity(0.3);
        alertPane.setVisible(true);
        alertText.setText(text);
    }

    private int getRandomNumber() {
        int random = (int) (Math.random() * MAX + MIN);

        if (random == genNum1 || random == genNum2 || random == genNum3 || random == genNum4 || random == genNum5) {
            return random = getRandomNumber();
        }
        return random;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
