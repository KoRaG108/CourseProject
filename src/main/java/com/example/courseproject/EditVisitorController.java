package com.example.courseproject;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class EditVisitorController implements Initializable{
    @FXML
    private DatePicker editVisitor_birthday_date;

    @FXML
    private TextField editVisitor_callName;

    @FXML
    private Button editVisitor_clearBtn;

    @FXML
    private TextField editVisitor_familyName;

    @FXML
    private TextField editVisitor_fatherName;

    @FXML
    private ComboBox<?> editVisitor_genderBox;

    @FXML
    private TextField editVisitor_numberPassport;

    @FXML
    private TextField editVisitor_phoneNumber;

    @FXML
    public Button editVisitor_saveBtn;

    @FXML
    private TextField editVisitor_seriesPassport;

    @FXML
    private Label test_id;



    private String genderName[] = {"М", "Ж"};
    public void visitorLogGenderType(){
        List<String> listData = new ArrayList<>();

        for(String data: genderName){
            listData.add(data);
        }

        ObservableList list = FXCollections.observableArrayList(listData);
        editVisitor_genderBox.setItems(list);
    }



    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    public void visitorLogUpdate(){

        String fullN = editVisitor_familyName.getText() + " " + editVisitor_callName.getText() + " " + editVisitor_fatherName.getText();
        String genderN = (String)editVisitor_genderBox.getSelectionModel().getSelectedItem();
        String dateBirthDate = String.valueOf(editVisitor_birthday_date.getValue());
        String phoneNum = editVisitor_phoneNumber.getText();
        String seriesPass = editVisitor_seriesPassport.getText();
        String numberPass = editVisitor_numberPassport.getText();

        String idNum = test_id.getText();

        String sql = "UPDATE visitor SET fullName = '"+fullN+"', genderName = '"+genderN+"', dateBirth = '"+dateBirthDate+"', phoneNumber = '"+phoneNum+"', seriesPassport = '"+seriesPass+"', numberPassport = '"+numberPass+"' WHERE visitorId = '"+idNum+"'";

        connect = database.connectDb();
        try{
            Alert alert;
            if(fullN == null || fullN.isEmpty() || genderN == null || genderN.isEmpty() || dateBirthDate == null || dateBirthDate.isEmpty() || phoneNum == null || phoneNum.isEmpty() || seriesPass == null || seriesPass.isEmpty() || numberPass == null || numberPass.isEmpty()){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Сообщение об ошибке");
                alert.setHeaderText(null);
                alert.setContentText("Заполните, пожалуйста, все пустые поля!");
                alert.showAndWait();
            }else{
                prepare = connect.prepareStatement(sql);
                prepare.executeUpdate();

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Информационное сообщение");
                alert.setHeaderText(null);
                alert.setContentText("Информация о Пользователе № "+idNum+" успешно отредактирована!");
                alert.showAndWait();

                closeVisitorLogSaveButtonAction();
            }
        }catch(Exception e){e.printStackTrace();}
    }



    public void visitorLogClear(){
        test_id.setText("");
        editVisitor_familyName.setText("");
        editVisitor_callName.setText("");
        editVisitor_fatherName.setText("");
        editVisitor_genderBox.getSelectionModel().clearSelection();
        editVisitor_birthday_date.setValue(null);
        editVisitor_phoneNumber.setText("");
        editVisitor_seriesPassport.setText("");
        editVisitor_numberPassport.setText("");
    }

    private void closeVisitorLogSaveButtonAction(){
        Stage stage = (Stage) editVisitor_saveBtn.getScene().getWindow();
        stage.close();
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        visitorLogGenderType();
        test_id.setText(String.valueOf(getData.visitorNum2));

        editVisitor_familyName.setText(String.valueOf(getData.testEditFullName1));
        editVisitor_callName.setText(String.valueOf(getData.testEditFullName2));
        editVisitor_fatherName.setText(String.valueOf(getData.testEditFullName3));

        String compareGender = String.valueOf(getData.testEditGenderName);
        System.out.println(compareGender);
        String str1 = "М";
        if(compareGender.equals(str1)){
            editVisitor_genderBox.getSelectionModel().selectFirst();
        }else {
            editVisitor_genderBox.getSelectionModel().selectLast();
        }

        String yearN = String.valueOf(getData.testEditDateBirth1);
        int yearN1 = Integer.parseInt (yearN);
        String monthN = String.valueOf(getData.testEditDateBirth2);
        int monthN1 = Integer.parseInt (monthN);
        String dayN = String.valueOf(getData.testEditDateBirth3);
        int dayN1 = Integer.parseInt (dayN);
        editVisitor_birthday_date.setValue(LocalDate.of(yearN1,monthN1,dayN1));

        editVisitor_phoneNumber.setText(String.valueOf(getData.testEditPhoneNumber));
        editVisitor_seriesPassport.setText(String.valueOf(getData.testEditSeriesPassport));
        editVisitor_numberPassport.setText(String.valueOf(getData.testEditNumberPassport));
    }
}