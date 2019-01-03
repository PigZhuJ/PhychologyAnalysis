package Controller;

import DBUtils.DBAdd;
import DBUtils.DBDelete;
import DBUtils.DBInsert;
import DBUtils.DBSelect;
import Model.*;
import Utils.Controller;
import Utils.ViewLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

public class psychologyBoundaryController extends Controller {
    @FXML
    private TableView<Mental> mentalTableView;
    @FXML
    private TableColumn<?, ?> mentaldataNoCol;

    @FXML
    private TableColumn<?, ?> checkListDataCol;

    @FXML
    private TableColumn<?, ?> mentalCareDataCol;

    @FXML
    private TableColumn<?, ?> mentalCareHistoryCol;

    @FXML
    private TableColumn<?, ?> userIdCol;

    @FXML
    private TableView<graph> graphTableView;
    @FXML
    private TextField textFileld1;

    @FXML
    private Button Add5;

    @FXML
    private Button Add4;

    @FXML
    private Button Add3;

    @FXML
    private Button Add2;

    @FXML
    private TableColumn<?, ?> administratorId;


    @FXML
    private TableColumn<?, ?> administratorName;
    @FXML
    private TableColumn<?, ?> administratorPwd;

    @FXML
    private ComboBox<String> field1;

    @FXML
    private TextField textField2;

    @FXML
    private TextField textField4;

    @FXML
    private TextField textField3;

    @FXML
    private Button confirm3;

    @FXML
    private TextField textField5;

    @FXML
    private Button confirm2;

    @FXML
    private Button confirm1;

    @FXML
    private ComboBox<String> field3;

    @FXML
    private Button add1;

    @FXML
    private ComboBox<String> field2;

    @FXML
    private Button confirm5;

    @FXML
    private ComboBox<String> field5;

    @FXML
    private Button confirm4;

    @FXML
    private ComboBox<String> field4;

    @FXML
    private TableView<adminstrator> tableView;

    @FXML
    private TableView<user> userTableView;
    @FXML
    private Button userAdd;

    @FXML
    private Button userDelete;

    @FXML
    private TabPane tabpane;
    @FXML
    private TableView<Physical> physicalTableView;

    @FXML
    private TableColumn physicaldataNoCol;
    @FXML
    private TableColumn heartbeatCol;
    @FXML
    private TableColumn bloodPressureCol;
    @FXML
    private TableColumn autonomicNerveCol;
    @FXML
    private TableColumn puserIdCol;


    private ObservableList<adminstrator> data1;
    private ObservableList<user> data2;
    private ObservableList<Mental> data3;
    private ObservableList<Physical> data4;
    private ObservableList<graph> data5;


    @FXML
    void handlePhysicalReFresh(ActionEvent event) throws SQLException, ClassNotFoundException {

        ResultSet physicalResult = DBSelect.selectPhysicalField();
        ArrayList<String> physicalList = new ArrayList<>();
        while (physicalResult.next()) {
            physicalList.add(physicalResult.getString(1));
        }
        Physical physical = Physical.create(physicalList);


        data4 = FXCollections.observableArrayList();
        ResultSet resultSet4 = DBSelect.selectPhysical();
        while (resultSet4.next()) {
            ArrayList<String> list = new ArrayList<>();
            for (int i = 0; i < physicalList.size(); i++) {
                try {
                    list.add(resultSet4.getString(physicalList.get(i)));
                } catch (Exception e) {
                    list.add(String.valueOf(resultSet4.getInt(physicalList.get(i))));
                }
            }
            data4.add(physical.putNewRow(list));
        }
        physicalTableView.getColumns().removeAll(physicalTableView.getColumns());
        for (int i = 0; i < physicalList.size(); i++) {
            String s = physicalList.get(i);
            TableColumn firstNameCol = new TableColumn();
            firstNameCol.setText(s);
            System.out.println("s: " + s);
            firstNameCol.setId(physical.getColId(i));
            System.out.println(physical.getColId(i));
            firstNameCol.setCellValueFactory(physical.getMapCellValueFactory());
            firstNameCol.prefWidthProperty().bind(tableView.widthProperty().divide(physicalList.size()));
            physicalTableView.getColumns().addAll(firstNameCol);
        }
        System.out.println(data4.get(0).toString());
        physicalTableView.setItems(data4);
    }

    @FXML
    void handlePhysicalDelete(ActionEvent event) throws SQLException, ClassNotFoundException {
        Physical m = physicalTableView.getSelectionModel().getSelectedItem();
        physicalTableView.getItems().remove(m);
        DBDelete.deletePhysicalRecord(m.getRow().get("physicaldataNo").get());
    }

    @FXML
    void handlePhysicalADD(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        ResultSet rs = DBSelect.selectPhysicalField();
        String s = "please input format: ";
        while (rs.next()) {
            s += rs.getString(1) + ",";
        }
        s = s.substring(0, s.length() - 1);
        TextInputDialog inputDialog = new TextInputDialog();
        inputDialog.setTitle("Add Mental");
        inputDialog.setHeaderText(s);
        inputDialog.setContentText("Input : ");
        Optional<String> result = inputDialog.showAndWait();
        if (result.isPresent()) {
            String sql = result.get();
            try {
                DBInsert.insertPhysical(sql);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Successfully");
                alert.setTitle("ADD");
                alert.showAndWait();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Error");
                alert.setTitle("ADD Error");
                alert.showAndWait();
            }
        }


    }

    @FXML
    void handleMentalADD(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        ResultSet rs = DBSelect.selectMentalField();
        String s = "please input format: ";
        while (rs.next()) {
            s += rs.getString(1) + ",";
        }
        s = s.substring(0, s.length() - 1);
        TextInputDialog inputDialog = new TextInputDialog();
        inputDialog.setTitle("Add Mental");
        inputDialog.setHeaderText(s);
        inputDialog.setContentText("Input : ");
        Optional<String> result = inputDialog.showAndWait();
        if (result.isPresent()) {
            String sql = result.get();
            try {
                DBInsert.insertMentabl(sql,s.split(":")[1]);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Successfully");
                alert.setTitle("ADD");
                alert.showAndWait();
                mentalTableView.refresh();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Error");
                alert.setTitle("ADD Error");
                alert.showAndWait();
            }
        }

    }

    @FXML
    void handleMentalDelete(ActionEvent event) throws SQLException, ClassNotFoundException {
        Mental m = mentalTableView.getSelectionModel().getSelectedItem();
        mentalTableView.getItems().remove(m);
        DBDelete.deleteMentalRecord(m.getRow().get("mentaldataNo").get());
    }

    @FXML
    void handleMentablReFresh(ActionEvent event) throws SQLException, ClassNotFoundException {
        ResultSet mentalResult = DBSelect.selectMentalField();
        ArrayList<String> mentalList = new ArrayList<>();
        while (mentalResult.next()) {
            mentalList.add(mentalResult.getString(1));
        }
        Mental mental = Mental.create(mentalList);


        data3 = FXCollections.observableArrayList();
        ResultSet resultSet3 = DBSelect.selectMental();
        while (resultSet3.next()) {
            ArrayList<String> list = new ArrayList<>();
            for (int i = 0; i < mentalList.size(); i++) {
                try {
                    list.add(resultSet3.getString(mentalList.get(i)));
                } catch (Exception e) {
                    list.add(String.valueOf(resultSet3.getInt(mentalList.get(i))));
                }
            }
            data3.add(mental.putNewRow(list));
        }
        mentalTableView.getColumns().removeAll(mentalTableView.getColumns());
        for (int i = 0; i < mentalList.size(); i++) {
            String s = mentalList.get(i);
            TableColumn firstNameCol = new TableColumn();
            firstNameCol.setText(s);
            System.out.println("s: " + s);
            firstNameCol.setId(mental.getColId(i));
            System.out.println(mental.getColId(i));
            firstNameCol.setCellValueFactory(mental.getMapCellValueFactory());
            firstNameCol.prefWidthProperty().bind(tableView.widthProperty().divide(mentalList.size()));
            mentalTableView.getColumns().addAll(firstNameCol);
        }
        System.out.println(data3.get(0).toString());
        mentalTableView.setItems(data3);


    }

    @FXML
    void handleAddUserInformation(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        ResultSet rs = DBSelect.selectuserField();
        String s = "please input format: ";
        while (rs.next()) {
            s += rs.getString(1) + ",";
        }
        s = s.substring(0, s.length() - 1);
        TextInputDialog inputDialog = new TextInputDialog();
        inputDialog.setTitle("Add User");
        inputDialog.setHeaderText(s);
        inputDialog.setContentText("Input : ");
        Optional<String> result = inputDialog.showAndWait();
        if (result.isPresent()) {
            String sql = result.get();
            try {
                DBInsert.insertUser(sql);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Successfully");
                alert.setTitle("ADD");
                alert.showAndWait();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Error");
                alert.setTitle("ADD Error");
                alert.showAndWait();
            }
        }
    }

    @FXML
    void handleDeleteUserInformation(ActionEvent event) throws SQLException, ClassNotFoundException {

        user u = userTableView.getSelectionModel().getSelectedItem();
        userTableView.getItems().remove(u);
        DBDelete.deleteUserRecord(u.getRow().get("naturalDataNo").get());

    }

    @FXML
    void handleAdd1(ActionEvent event) {
        try {
            DBAdd.addAdminstratorField(textFileld1.getText().trim());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Successfully");
            alert.setTitle("ADD");
            alert.showAndWait();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Input Error");
            alert.setTitle("Error Input");
            alert.setContentText("You should input text: filed + fieldType ;eg: age int");
            alert.showAndWait();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleAdd2(ActionEvent event) {
        try {
            DBAdd.addUserField(textField2.getText().trim());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Successfully");
            alert.setTitle("ADD");
            alert.showAndWait();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Input Error");
            alert.setTitle("Error Input");
            alert.setContentText("You should input text: filed + fieldType ;eg: age int");
            alert.showAndWait();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleAdd3(ActionEvent event) {
        try {
            DBAdd.addPhysicalField(textField3.getText().trim());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Successfully");
            alert.setTitle("ADD");
            alert.showAndWait();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Input Error");
            alert.setTitle("Error Input");
            alert.setContentText("You should input text: filed + fieldType ;eg: age int");
            alert.showAndWait();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleAdd4(ActionEvent event) {
        try {
            DBAdd.addMentalField(textField4.getText().trim());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Successfully");
            alert.setTitle("ADD");
            alert.showAndWait();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Input Error");
            alert.setTitle("Error Input");
            alert.setContentText("You should input text: filed + fieldType ;eg: age int");
            alert.showAndWait();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleAdd5(ActionEvent event) {
        try {
            DBAdd.addGraphField(textField5.getText().trim());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Successfully");
            alert.setTitle("ADD");
            alert.showAndWait();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Input Error");
            alert.setTitle("Error Input");
            alert.setContentText("You should input text: filed + fieldType ;eg: age int");
            alert.showAndWait();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleConfirm5(ActionEvent event) {
        try {
            DBDelete.deletegraph(field5.getValue());
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Can not delete!");
            alert.setContentText("");
            alert.showAndWait();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleConfirm4(ActionEvent event) {
        try {
            DBDelete.deleteMental(field4.getValue());
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Can not delete!");
            alert.setContentText("");
            alert.showAndWait();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleConfirm3(ActionEvent event) {
        try {
            DBDelete.deletePsysical(field3.getValue());
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Can not delete!");
            alert.setContentText("");
            alert.showAndWait();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleConfirm2(ActionEvent event) {
        try {
            DBDelete.deleteUser(field2.getValue());
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Can not delete!");
            alert.setContentText("");
            alert.showAndWait();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleConfirm1(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Can not change!");
        alert.setContentText("");
        alert.showAndWait();
    }

    @FXML
    void handleRefresh() throws SQLException, ClassNotFoundException {
        ResultSet userResult = DBSelect.selectuserField();
        ArrayList<String> userList = new ArrayList<>();
        while (userResult.next()) {
            userList.add(userResult.getString(1));
        }
        user us = user.create(userList);


        data2 = FXCollections.observableArrayList();
        ResultSet resultSet2 = DBSelect.selectUser();
        while (resultSet2.next()) {
            ArrayList<String> list = new ArrayList<>();
            for (int i = 0; i < userList.size(); i++) {
                try {
                    list.add(resultSet2.getString(userList.get(i)));
                } catch (Exception e) {
                    list.add(String.valueOf(resultSet2.getInt(userList.get(i))));
                }
            }
            data2.add(us.putNewRow(list));
        }
        userTableView.getColumns().removeAll(userTableView.getColumns());
        for (int i = 0; i < userList.size(); i++) {
            String s = userList.get(i);
            TableColumn firstNameCol = new TableColumn();
            firstNameCol.setText(s);
            System.out.println("s: " + s);
            firstNameCol.setId(us.getColId(i));
            System.out.println(us.getColId(i));
            firstNameCol.setCellValueFactory(us.getMapCellValueFactory());
            firstNameCol.prefWidthProperty().bind(userTableView.widthProperty().divide(userList.size()));
            userTableView.getColumns().addAll(firstNameCol);
        }
        System.out.println(data2.get(0).toString());
        userTableView.setItems(data2);


    }

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        data1 = FXCollections.observableArrayList();
        administratorId.setCellValueFactory(new PropertyValueFactory<>("administratorId"));
        administratorName.setCellValueFactory(new PropertyValueFactory<>("administratorName"));
        administratorPwd.setCellValueFactory(new PropertyValueFactory<>("administratorPwd"));
        ResultSet rs = DBSelect.selectAdminstrator();
        while (rs.next()) {
            data1.add(new adminstrator(rs.getString(1), rs.getString(2), rs.getString(3)));
        }
        tableView.setItems(data1);

        //combox
        ResultSet rs1 = DBSelect.selectAdminstratorField();
        while (rs1.next()) {
            System.out.println(rs1.getString(1));
            field1.getItems().add(rs1.getString(1));
        }
        ResultSet rs2 = DBSelect.selectuserField();
        while (rs2.next()) {
            System.out.println(rs2.getString(1));
            field2.getItems().add(rs2.getString(1));
        }
        ResultSet rs3 = DBSelect.selectPhysicalField();
        while (rs3.next()) {
            System.out.println(rs3.getString(1));
            field3.getItems().add(rs3.getString(1));
        }
        ResultSet rs4 = DBSelect.selectMentalField();
        while (rs4.next()) {
            System.out.println(rs4.getString(1));
            field4.getItems().add(rs4.getString(1));
        }
        ResultSet rs5 = DBSelect.selectGraphField();
        while (rs5.next()) {
            System.out.println(rs5.getString(1));
            field5.getItems().add(rs5.getString(1));
        }


        ResultSet userResult = DBSelect.selectuserField();
        ArrayList<String> userList = new ArrayList<>();
        while (userResult.next()) {
            userList.add(userResult.getString(1));
        }
        user us = user.create(userList);


        data2 = FXCollections.observableArrayList();
        ResultSet resultSet2 = DBSelect.selectUser();
        while (resultSet2.next()) {
            ArrayList<String> list = new ArrayList<>();
            for (int i = 0; i < userList.size(); i++) {
                try {
                    list.add(resultSet2.getString(userList.get(i)));
                } catch (Exception e) {
                    list.add(String.valueOf(resultSet2.getInt(userList.get(i))));
                }
            }
            data2.add(us.putNewRow(list));
        }
        userTableView.getColumns().removeAll(userTableView.getColumns());
        for (int i = 0; i < userList.size(); i++) {
            String s = userList.get(i);
            TableColumn firstNameCol = new TableColumn();
            firstNameCol.setText(s);
            System.out.println("s: " + s);
            firstNameCol.setId(us.getColId(i));
            System.out.println(us.getColId(i));
            firstNameCol.setCellValueFactory(us.getMapCellValueFactory());
            firstNameCol.prefWidthProperty().bind(userTableView.widthProperty().divide(userList.size()));
            userTableView.getColumns().addAll(firstNameCol);
        }
        userTableView.setItems(data2);


        ResultSet mentalResult = DBSelect.selectMentalField();
        ArrayList<String> mentalList = new ArrayList<>();
        while (mentalResult.next()) {
            mentalList.add(mentalResult.getString(1));
        }
        Mental mental = Mental.create(mentalList);


        data3 = FXCollections.observableArrayList();
        ResultSet resultSet3 = DBSelect.selectMental();
        while (resultSet3.next()) {
            ArrayList<String> list = new ArrayList<>();
            for (int i = 0; i < mentalList.size(); i++) {
                try {
                    list.add(resultSet3.getString(mentalList.get(i)));
                } catch (Exception e) {
                    list.add(String.valueOf(resultSet3.getInt(mentalList.get(i))));
                }
            }
            data3.add(mental.putNewRow(list));
        }
        mentalTableView.getColumns().removeAll(mentalTableView.getColumns());
        for (int i = 0; i < mentalList.size(); i++) {
            String s = mentalList.get(i);
            TableColumn firstNameCol = new TableColumn();
            firstNameCol.setText(s);
            System.out.println("s: " + s);
            firstNameCol.setId(mental.getColId(i));
            System.out.println(mental.getColId(i));
            firstNameCol.setCellValueFactory(mental.getMapCellValueFactory());
            firstNameCol.prefWidthProperty().bind(tableView.widthProperty().divide(mentalList.size()));
            mentalTableView.getColumns().addAll(firstNameCol);
        }
        mentalTableView.setItems(data3);


        ResultSet physicalResult = DBSelect.selectPhysicalField();
        ArrayList<String> physicalList = new ArrayList<>();
        while (physicalResult.next()) {
            physicalList.add(physicalResult.getString(1));
        }
        Physical physical = Physical.create(physicalList);
        data4 = FXCollections.observableArrayList();
        ResultSet resultSet4 = DBSelect.selectPhysical();
        while (resultSet4.next()) {
            ArrayList<String> list = new ArrayList<>();
            for (int i = 0; i < physicalList.size(); i++) {
                try {
                    list.add(resultSet4.getString(physicalList.get(i)));
                } catch (Exception e) {
                    list.add(String.valueOf(resultSet4.getInt(physicalList.get(i))));
                }
            }
            data4.add(physical.putNewRow(list));
        }
        physicalTableView.getColumns().removeAll(physicalTableView.getColumns());
        for (int i = 0; i < physicalList.size(); i++) {
            String s = physicalList.get(i);
            TableColumn firstNameCol = new TableColumn();
            firstNameCol.setText(s);
            System.out.println("s: " + s);
            firstNameCol.setId(physical.getColId(i));
            System.out.println(physical.getColId(i));
            firstNameCol.setCellValueFactory(physical.getMapCellValueFactory());
            firstNameCol.prefWidthProperty().bind(tableView.widthProperty().divide(physicalList.size()));
            physicalTableView.getColumns().addAll(firstNameCol);
        }
        physicalTableView.setItems(data4);



        ResultSet graphResult = DBSelect.selectGraphField();
        ArrayList<String> graphList = new ArrayList<>();
        while (graphResult.next()) {
            graphList.add(graphResult.getString(1));
        }
        System.out.println(graphList);
        graph g = graph.create(graphList);
        data5 = FXCollections.observableArrayList();
        ResultSet graphResultSet = DBSelect.selectGraph();
        while (graphResultSet.next()) {
            ArrayList<String> list = new ArrayList<>();
            for (int i = 0; i < graphList.size(); i++) {
                System.out.println(graphList.get(i));
                try {
                    list.add(graphResultSet.getString(graphList.get(i)));
                } catch (Exception e) {
                    list.add(String.valueOf(graphResultSet.getInt(graphList.get(i))));
                }
            }
            data5.add(g.putNewRow(list));
        }
        graphTableView.getColumns().removeAll(graphTableView.getColumns());
        for (int i = 0; i < graphList.size(); i++) {
            String s = graphList.get(i);
            TableColumn firstNameCol = new TableColumn();
            firstNameCol.setText(s);
            System.out.println("s: " + s);
            firstNameCol.setId(g.getColId(i));
            System.out.println(g.getColId(i));
            firstNameCol.setCellValueFactory(g.getMapCellValueFactory());
            firstNameCol.prefWidthProperty().bind(graphTableView.widthProperty().divide(graphList.size()));
            graphTableView.getColumns().addAll(firstNameCol);
        }
        graphTableView.setItems(data5);
    }


    @FXML
    void handleGraphAdd() throws IOException {
        ViewLoader.showStage(model, "/View/addGraph.fxml", "add graph", new Stage());
        ;
    }

    @FXML
    void handleGraphRefresh() throws SQLException, ClassNotFoundException {
        ResultSet graphResult = DBSelect.selectGraphField();
        ArrayList<String> graphList = new ArrayList<>();
        while (graphResult.next()) {
            graphList.add(graphResult.getString(1));
        }
        graph g = graph.create(graphList);
        data5 = FXCollections.observableArrayList();
        ResultSet graphResultSet = DBSelect.selectGraph();
        while (graphResultSet.next()) {
            ArrayList<String> list = new ArrayList<>();
            for (int i = 0; i < graphList.size(); i++) {
                try {
                    list.add(graphResult.getString(graphList.get(i)));
                } catch (Exception e) {
                    list.add(String.valueOf(graphResult.getInt(graphList.get(i))));
                }
            }
            data5.add(g.putNewRow(list));
        }
        graphTableView.getColumns().removeAll(graphTableView.getColumns());
        for (int i = 0; i < graphList.size(); i++) {
            String s = graphList.get(i);
            TableColumn firstNameCol = new TableColumn();
            firstNameCol.setText(s);
            System.out.println("s: " + s);
            firstNameCol.setId(g.getColId(i));
            System.out.println(g.getColId(i));
            firstNameCol.setCellValueFactory(g.getMapCellValueFactory());
            firstNameCol.prefWidthProperty().bind(graphTableView.widthProperty().divide(graphList.size()));
            graphTableView.getColumns().addAll(firstNameCol);
        }
        graphTableView.setItems(data5);
    }

    @FXML
    void handleGraphDelete() throws SQLException, ClassNotFoundException {
        graph g = graphTableView.getSelectionModel().getSelectedItem();
        graphTableView.getItems().remove(g);
        DBDelete.deleteGraphRecord(g.getRow().get("graphId").get());
    }
}
