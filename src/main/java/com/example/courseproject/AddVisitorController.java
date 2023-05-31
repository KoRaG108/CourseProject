package com.example.courseproject;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class AddVisitorController implements Initializable{
    @FXML
    private DatePicker addVisitor_birthday_date;

    @FXML
    private TextField addVisitor_callName;

    @FXML
    private Button addVisitor_clearBtn;

    @FXML
    private TextField addVisitor_familyName;

    @FXML
    private TextField addVisitor_fatherName;

    @FXML
    private ComboBox<?> addVisitor_genderBox;

    @FXML
    private TextField addVisitor_numberPassport;

    @FXML
    private TextField addVisitor_phoneNumber;

    @FXML
    public Button addVisitor_saveBtn;

    @FXML
    private TextField addVisitor_seriesPassport;



    private String genderName[] = {"М", "Ж"};
    public void visitorLogGenderType(){
        List<String> listData = new ArrayList<>();

        for(String data: genderName){
            listData.add(data);
        }

        ObservableList list = FXCollections.observableArrayList(listData);
        addVisitor_genderBox.setItems(list);
    }



    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    public void visitorLogSave(){
        visitorNumber1();

        String insertVisitorData = "INSERT INTO visitor (visitorId, fullName, genderName, dateBirth, phoneNumber, seriesPassport, numberPassport) VALUES (?, ?, ?, ?, ?, ?, ?)";
        connect = database.connectDb();
        try{
            String idNum = String.valueOf(getData.visitorNum1 + 1);
            String fullN = addVisitor_familyName.getText() + " " + addVisitor_callName.getText() + " " + addVisitor_fatherName.getText();
            String genderN = (String)addVisitor_genderBox.getSelectionModel().getSelectedItem();
            String dateBirthDate = String.valueOf(addVisitor_birthday_date.getValue());
            String phoneNum = addVisitor_phoneNumber.getText();
            String seriesPass = addVisitor_seriesPassport.getText();
            String numberPass = addVisitor_numberPassport.getText();

            Alert alert;
            if(fullN == null || fullN.isEmpty() || genderN == null || genderN.isEmpty() || dateBirthDate == null || dateBirthDate.isEmpty() || phoneNum == null || phoneNum.isEmpty() || seriesPass == null || seriesPass.isEmpty() || numberPass == null || numberPass.isEmpty()){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Сообщение об ошибке");
                alert.setHeaderText(null);
                alert.setContentText("Заполните, пожалуйста, все пустые поля!");
                alert.showAndWait();
            }else{
                prepare = connect.prepareStatement(insertVisitorData);
                prepare.setString(1, idNum);
                prepare.setString(2, fullN);
                prepare.setString(3, genderN);
                prepare.setString(4, dateBirthDate);
                prepare.setString(5, phoneNum);
                prepare.setString(6, seriesPass);
                prepare.setString(7, numberPass);

                prepare.executeUpdate();

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Информационное сообщение");
                alert.setHeaderText(null);
                alert.setContentText("Пользователь № "+idNum+" успешно добавлен!");
                alert.showAndWait();

                closeVisitorLogSaveButtonAction();
            }
        }catch(Exception e){e.printStackTrace();}
    }



    public void visitorNumber1(){
        String visitorNum1 = "SELECT visitorId FROM visitor";
        connect = database.connectDb();
        try{
            prepare = connect.prepareStatement(visitorNum1);
            result = prepare.executeQuery();
            while(result.next()){
                getData.visitorNum1 = result.getInt("visitorId");
            }
        }catch(Exception e){e.printStackTrace();}
    }



    public void visitorLogClear(){
        addVisitor_familyName.setText("");
        addVisitor_callName.setText("");
        addVisitor_fatherName.setText("");
        addVisitor_genderBox.getSelectionModel().clearSelection();
        addVisitor_birthday_date.setValue(null);
        addVisitor_phoneNumber.setText("");
        addVisitor_seriesPassport.setText("");
        addVisitor_numberPassport.setText("");
    }

    private void closeVisitorLogSaveButtonAction(){
        Stage stage = (Stage) addVisitor_saveBtn.getScene().getWindow();
        stage.close();
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        visitorLogGenderType();
    }
}