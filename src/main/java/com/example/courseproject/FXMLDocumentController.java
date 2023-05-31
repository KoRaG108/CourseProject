package com.example.courseproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;

public class FXMLDocumentController implements Initializable {
    @FXML
    private Button cabinetLog_addBtn;

    @FXML
    private Button cabinetLog_btn;

    @FXML
    private TableColumn<?, ?> cabinetLog_col_category;

    @FXML
    private TableColumn<?, ?> cabinetLog_col_floor;

    @FXML
    private TableColumn<?, ?> cabinetLog_col_number;

    @FXML
    private TableColumn<?, ?> cabinetLog_col_status;

    @FXML
    private Button cabinetLog_deleteBtn;

    @FXML
    private Button cabinetLog_editBtn;

    @FXML
    private AnchorPane cabinetLog_form;

    @FXML
    private TextField cabinetLog_search;

    @FXML
    private Button regLog_addBtn;

    @FXML
    private Button regLog_btn;

    @FXML
    private Button regLog_deleteBtn;

    @FXML
    private Button regLog_editBtn;

    @FXML
    private AnchorPane regLog_form;

    @FXML
    private TextField regLog_search;

    @FXML
    private Button serviceLog_addBtn;

    @FXML
    private Button serviceLog_btn;

    @FXML
    private TableColumn<?, ?> serviceLog_col_serviceName;

    @FXML
    private TableColumn<?, ?> serviceLog_col_servicePrice;

    @FXML
    private Button serviceLog_deleteBtn;

    @FXML
    private Button serviceLog_editBtn;

    @FXML
    private AnchorPane serviceLog_form;

    @FXML
    private TextField serviceLog_search;

    @FXML
    public Button visitorLog_addBtn;

    @FXML
    private Button visitorLog_btn;

    @FXML
    private TableColumn<visitorData, String> visitorLog_col_visitorId;

    @FXML
    private TableColumn<visitorData, String> visitorLog_col_dateBirth;

    @FXML
    private TableColumn<visitorData, String> visitorLog_col_fullName;

    @FXML
    private TableColumn<visitorData, String> visitorLog_col_genderName;

    @FXML
    private TableColumn<visitorData, String> visitorLog_col_numberPassport;

    @FXML
    private TableColumn<visitorData, String> visitorLog_col_phoneNumber;

    @FXML
    private TableColumn<visitorData, String> visitorLog_col_seriesPassport;

    @FXML
    private Button visitorLog_deleteBtn;

    @FXML
    public Button visitorLog_editBtn;

    @FXML
    private AnchorPane visitorLog_form;

    @FXML
    private TextField visitorLog_search;

    @FXML
    private TableView<visitorData> visitorLog_tableView;

    @FXML
    private Label test_birth;

    @FXML
    private Label test_gender;

    @FXML
    private Label test_id;

    @FXML
    private Label test_name;

    @FXML
    private Label test_number;

    @FXML
    private Label test_phone;

    @FXML
    private Label test_series;



    public void switchForm(ActionEvent event){
        if(event.getSource() == regLog_btn){
            regLog_form.setVisible(true);
            visitorLog_form.setVisible(false);
            cabinetLog_form.setVisible(false);
            serviceLog_form.setVisible(false);
            test_id.setText("0");
        }else if(event.getSource() == visitorLog_btn){
            regLog_form.setVisible(false);
            visitorLog_form.setVisible(true);
            cabinetLog_form.setVisible(false);
            serviceLog_form.setVisible(false);
            showAddVisitorListData();
            test_id.setText("0");
        }else if(event.getSource() == cabinetLog_btn){
            regLog_form.setVisible(false);
            visitorLog_form.setVisible(false);
            cabinetLog_form.setVisible(true);
            serviceLog_form.setVisible(false);
            test_id.setText("0");
        }else if(event.getSource() == serviceLog_btn){
            regLog_form.setVisible(false);
            visitorLog_form.setVisible(false);
            cabinetLog_form.setVisible(false);
            serviceLog_form.setVisible(true);
            test_id.setText("0");
        }
    }



    public void visitorLogAdd(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("AddVisitor.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);

            Image image = new Image("file:./src/main/resources/assets/window-icon.png");
            stage.getIcons().add(image);
            stage.setTitle("Добавление посетителя");

            stage.setMinHeight(536);
            stage.setMinWidth(414);

            stage.setScene(scene);
            stage.show();

            stage.setOnHidden(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent we) {
                    showAddVisitorListData();
                }
            });

            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent we) {
                    test_id.setText("0");
                }
            });

        }catch(Exception e){e.printStackTrace();}
    }



    public void visitorLogEdit(){
        String idNumber = test_id.getText();
        int compare = Integer.parseInt (idNumber);
        try {
            Alert alert;
            if(compare == 0){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Сообщение об ошибке");
                alert.setHeaderText(null);
                alert.setContentText("Выберите для начала, пожалуйста, строку таблицы!");
                alert.showAndWait();
            }else {
                Parent root = FXMLLoader.load(getClass().getResource("EditVisitor.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);

                Image image = new Image("file:./src/main/resources/assets/window-icon.png");
                stage.getIcons().add(image);
                stage.setTitle("Редактирование информации о посетителе");

                stage.setMinHeight(536);
                stage.setMinWidth(414);

                stage.setScene(scene);
                stage.show();

                stage.setOnHidden(new EventHandler<WindowEvent>() {
                    public void handle(WindowEvent we) {
                        showAddVisitorListData();
                    }
                });

                stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    public void handle(WindowEvent we) {
                        test_id.setText("0");
                    }
                });
            }
        }catch(Exception e){e.printStackTrace();}
    }



    public void visitorNumber(){
        String visitorNum = "SELECT visitorId FROM visitor";
        connect = database.connectDb();
        try{
            prepare = connect.prepareStatement(visitorNum);
            result = prepare.executeQuery();
            while(result.next()){
                getData.visitorNum = result.getInt("visitorId");
            }
        }catch(Exception e){e.printStackTrace();}
    }



    public void visitorLogSelectData(){
        visitorData visitorD = visitorLog_tableView.getSelectionModel().getSelectedItem();
        int num = visitorLog_tableView.getSelectionModel().getSelectedIndex();
        if((num -1) < -1){
            return;
        }
        test_id.setText(String.valueOf(visitorD.getVisitorId()));

        test_name.setText(String.valueOf(visitorD.getFullName()));
        test_gender.setText(String.valueOf(visitorD.getGenderName()));
        test_birth.setText(String.valueOf(visitorD.getDateBirth()));

        test_phone.setText(String.valueOf(visitorD.getPhoneNumber()));
        test_series.setText(String.valueOf(visitorD.getSeriesPassport()));
        test_number.setText(String.valueOf(visitorD.getNumberPassport()));


        String idNumber = test_id.getText();
        int compareId = Integer.parseInt (idNumber);
        getData.visitorNum2 = compareId;

        String NameOfVisitor = test_name.getText();
        int spaceIndex = NameOfVisitor.indexOf(" ");
        int spaceIndex2 = NameOfVisitor.lastIndexOf(" ");
        String NameOfVisitor1 = NameOfVisitor.substring(0,spaceIndex);
        String NameOfVisitor2 = NameOfVisitor.substring(spaceIndex + 1,spaceIndex2);
        String NameOfVisitor3 = NameOfVisitor.substring(spaceIndex2 + 1);
        getData.testEditFullName1 = NameOfVisitor1;
        getData.testEditFullName2 = NameOfVisitor2;
        getData.testEditFullName3 = NameOfVisitor3;

        String NameOfGender = test_gender.getText();
        getData.testEditGenderName = NameOfGender;

        String DateOfBirth = test_birth.getText();
        int tildeIndex = DateOfBirth.indexOf("-");
        int tildeIndex2 = DateOfBirth.lastIndexOf("-");
        String DateOfBirth1 = DateOfBirth.substring(0,tildeIndex);
        String DateOfBirth2 = DateOfBirth.substring(tildeIndex + 1,tildeIndex2);
        String DateOfBirth3 = DateOfBirth.substring(tildeIndex2 + 1);
        getData.testEditDateBirth1 = DateOfBirth1;
        getData.testEditDateBirth2 = DateOfBirth2;
        getData.testEditDateBirth3 = DateOfBirth3;


        String phoneNumber = test_phone.getText();
        int comparePhone = Integer.parseInt (phoneNumber);
        getData.testEditPhoneNumber = comparePhone;

        String seriesNumber = test_series.getText();
        int compareSeries = Integer.parseInt (seriesNumber);
        getData.testEditSeriesPassport = compareSeries;

        String numberNumber = test_number.getText();
        int compareNumber = Integer.parseInt (numberNumber);
        getData.testEditNumberPassport = compareNumber;
    }



    public void visitorLogDelete(){
        String idNumber = test_id.getText();
        int compare = Integer.parseInt (idNumber);
        String deleteData = "DELETE FROM visitor WHERE visitor.visitorId = '"+idNumber+"'";
        connect = database.connectDb();
        try{
            Alert alert;
            if(compare == 0){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Сообщение об ошибке");
                alert.setHeaderText(null);
                alert.setContentText("Выберите для начала, пожалуйста, строку таблицы!");
                alert.showAndWait();
            }else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Подтверждающее сообщение");
                alert.setHeaderText(null);
                alert.setContentText("Вы уверены, что хотите удалить Посетителя № " +idNumber+ "?");

                Optional<ButtonType> option = alert.showAndWait();
                if (option.get().equals(ButtonType.OK)) {
                    statement = connect.createStatement();
                    statement.executeUpdate(deleteData);

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Информационное сообщение");
                    alert.setHeaderText(null);
                    alert.setContentText("Успешно удалено!");
                    alert.showAndWait();

                    showAddVisitorListData();
                    test_id.setText("0");
                } else {
                    return;
                }
            }
        }catch(Exception e){e.printStackTrace();}
    }



    public void visitorLogSearch(){
        FilteredList<visitorData> filter = new FilteredList<>(visitorDataList, e -> true);
        visitorLog_search.textProperty().addListener((Observable, oldValue, newValue) ->{
            filter.setPredicate(predicateVisitor ->{
                if(newValue == null && newValue.isEmpty()){
                    return true;
                }
                String searchKey = newValue.toLowerCase();
                if(predicateVisitor.getVisitorId().toString().contains(searchKey)){
                    return true;
                }else if(predicateVisitor.getFullName().toLowerCase().contains(searchKey)){
                    return true;
                }else if(predicateVisitor.getGenderName().toLowerCase().contains(searchKey)){
                    return true;
                }else if(predicateVisitor.getDateBirth().toString().contains(searchKey)){
                    return true;
                }else if(predicateVisitor.getPhoneNumber().toString().contains(searchKey)){
                    return true;
                }else if(predicateVisitor.getSeriesPassport().toString().contains(searchKey)){
                    return true;
                }else if(predicateVisitor.getNumberPassport().toString().contains(searchKey)){
                    return true;
                }else return false;
            });
        });
        SortedList<visitorData> sortList = new SortedList<>(filter);
        sortList.comparatorProperty().bind(visitorLog_tableView.comparatorProperty());
        visitorLog_tableView.setItems(sortList);
    }



    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    public ObservableList<visitorData> addVisitorListData(){
        ObservableList<visitorData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM visitor";

        connect = database.connectDb();

        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            visitorData visitorD;

            while(result.next()){
                visitorD = new visitorData(result.getInt("visitorId"), result.getString("fullName"), result.getString("genderName"), result.getDate("dateBirth"), result.getInt("phoneNumber"), result.getInt("seriesPassport"), result.getInt("numberPassport"));

                listData.add(visitorD);
            }

        }catch(Exception e){e.printStackTrace();}
        return listData;
    }

    private ObservableList<visitorData> visitorDataList;
    public void showAddVisitorListData(){
        visitorDataList = addVisitorListData();

        visitorLog_col_visitorId.setCellValueFactory(new PropertyValueFactory<>("visitorId"));
        visitorLog_col_fullName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        visitorLog_col_genderName.setCellValueFactory(new PropertyValueFactory<>("genderName"));
        visitorLog_col_dateBirth.setCellValueFactory(new PropertyValueFactory<>("dateBirth"));
        visitorLog_col_phoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        visitorLog_col_seriesPassport.setCellValueFactory(new PropertyValueFactory<>("seriesPassport"));
        visitorLog_col_numberPassport.setCellValueFactory(new PropertyValueFactory<>("numberPassport"));

        visitorLog_tableView.setItems(visitorDataList);
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showAddVisitorListData();
        visitorLogSearch();
    }
}