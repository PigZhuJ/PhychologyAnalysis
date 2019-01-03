package Controller;


import DBUtils.DBInsert;
import DBUtils.DBSelect;
import Utils.Controller;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

public class AddGraphController extends Controller {

    @FXML
    private ComboBox<String> selectedFieldsCombox;

    @FXML
    private TextField graphTitleTextField;

    @FXML
    private ComboBox<String> graphTypeCombox;

    @FXML
    private ComboBox<String> physicalORMentalCombox;

    @FXML
    private ComboBox<String> pormcombox;

    @FXML
    private ComboBox<String> selectedOptionsCombox;

    @FXML
    public void initialize() throws SQLException, ClassNotFoundException {

        //set selectedFieldsCombox
        ResultSet urs = DBSelect.selectuserField();
        while (urs.next()) {
            selectedFieldsCombox.getItems().add(urs.getString(1));
        }
        //set selectedOptionsCombox
        selectedFieldsCombox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                selectedOptionsCombox.getItems().remove(0, selectedOptionsCombox.getItems().size());
                try {
                    TreeSet<String> ts = DBSelect.selectField(newValue);
                    selectedOptionsCombox.getItems().addAll(ts);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        //set pormcombox
        pormcombox.getItems().add("Physical");
        pormcombox.getItems().add("Mental");
        //set physicalORMentalCombox
        pormcombox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.equals("Physical")) {
                    physicalORMentalCombox.getItems().remove(0, physicalORMentalCombox.getItems().size());
                    try {
                        ResultSet rs = DBSelect.selectPhysicalField();
                        while (rs.next()) {
                            physicalORMentalCombox.getItems().add(rs.getString(1));
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                } else if (newValue.equals("Mental")) {
                    physicalORMentalCombox.getItems().remove(0, physicalORMentalCombox.getItems().size());
                    try {
                        ResultSet rs = DBSelect.selectMentalField();
                        while (rs.next()) {
                            physicalORMentalCombox.getItems().add(rs.getString(1));
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
        graphTypeCombox.getItems().add("Pie");
        graphTypeCombox.getItems().add("Bar");
        graphTypeCombox.getItems().add("Line");
        graphTypeCombox.getItems().add("Scatter");


    }

    @FXML
    void handleConfirm() throws SQLException, ClassNotFoundException {

        if (graphTypeCombox.getValue().trim().equals("Pie")) {
            if (pormcombox.getValue().equals("Physical")) {
                String selectedFields = physicalORMentalCombox.getValue();
                String userFields = selectedFieldsCombox.getValue();
                String options = selectedOptionsCombox.getValue();
                //get id resultSet
                ResultSet rs = DBSelect.selectUserID(userFields, options);
                HashMap<String, Integer> hm = new HashMap<>();

                while (rs.next()) {
                    System.out.println(rs.getString(1));
                    ResultSet result = DBSelect.selectPhysicalData(rs.getString(1), selectedFields);
                    while (result.next()) {
                        if (hm.containsKey(result.getString(1))) {
                            hm.put(result.getString(1), hm.get(result.getString(1) + 1));
                        } else {
                            hm.put(result.getString(1), 1);
                        }
                    }
                }
                ObservableList<PieChart.Data> pieChartData =
                        FXCollections.observableArrayList();
                for (String key : hm.keySet()) {
                    pieChartData.add(new PieChart.Data(key, hm.get(key)));
                }
                final PieChart chart = new PieChart(pieChartData);
                chart.setTitle(graphTitleTextField.getText().trim() + "   " + selectedFields);
                Scene scene = new Scene(new Group(), 500, 400);
                ((Group) scene.getRoot()).getChildren().add(chart);
                stage.setScene(scene);
                stage.show();
                DBInsert.insertGraph((DBSelect.SelectRow()+1),graphTitleTextField.getText(),pormcombox.getValue(),physicalORMentalCombox.getValue(),graphTypeCombox.getValue());
            } else if (pormcombox.getValue().equals("Mental")) {
                String selectedFields = physicalORMentalCombox.getValue();
                String userFields = selectedFieldsCombox.getValue();
                String options = selectedOptionsCombox.getValue();
                //get id resultSet
                ResultSet rs = DBSelect.selectUserID(userFields, options);
                HashMap<String, Integer> hm = new HashMap<>();
                while (rs.next()) {
                    System.out.println(rs.getString(1));
                    ResultSet result = DBSelect.selectMentalData(rs.getString(1), selectedFields);
                    while (result.next()) {
                        if (hm.containsKey(result.getString(1))) {
                            hm.put(result.getString(1), hm.get(result.getString(1) + 1));
                        } else {
                            hm.put(result.getString(1), 1);
                        }
                    }
                }


                ObservableList<PieChart.Data> pieChartData =
                        FXCollections.observableArrayList();
                for (String key : hm.keySet()) {
                    pieChartData.add(new PieChart.Data(key, hm.get(key)));
                }
                final PieChart chart = new PieChart(pieChartData);
                chart.setTitle(graphTitleTextField.getText().trim() + "   " + selectedFields);
                Scene scene = new Scene(new Group(), 500, 400);
                ((Group) scene.getRoot()).getChildren().add(chart);
                stage.setScene(scene);
                stage.show();
                DBInsert.insertGraph((DBSelect.SelectRow()+1),graphTitleTextField.getText(),pormcombox.getValue(),physicalORMentalCombox.getValue(),graphTypeCombox.getValue());
            }

        } else if (graphTypeCombox.getValue().trim().equals("Bar")) {

            if (pormcombox.getValue().equals("Physical")) {
                String selectedFields = physicalORMentalCombox.getValue();
                String userFields = selectedFieldsCombox.getValue();
                String options = selectedOptionsCombox.getValue();
//                //defining the axes
                final CategoryAxis xAxis = new CategoryAxis();
                final NumberAxis yAxis = new NumberAxis();
                xAxis.setLabel("Number");
//                //creating the chart
                final BarChart<String, Number> lineChart =
                        new BarChart<>(xAxis, yAxis);

                lineChart.setTitle(graphTitleTextField.getText().trim() + "   " + selectedFields);
//                //defining a series
                XYChart.Series series = new XYChart.Series();
//                //populating the series with data
                //get id resultSet
                ResultSet rs = DBSelect.selectUserID(userFields, options);
                HashMap<String, Integer> hm = new HashMap<>();
                while (rs.next()) {
                    System.out.println(rs.getString(1));
                    ResultSet result = DBSelect.selectPhysicalData(rs.getString(1), selectedFields);
                    while (result.next()) {
                        if (hm.containsKey(result.getString(1))) {
                            hm.put(result.getString(1), hm.get(result.getString(1) + 1));
                        } else {
                            hm.put(result.getString(1), 1);
                        }
                    }
                }
                for (String s : hm.keySet()) {
                    series.getData().add(new XYChart.Data(s, hm.get(s)));
                }
                Scene scene = new Scene(lineChart, 400, 400);
                lineChart.getData().add(series);
                stage.setScene(scene);
                stage.show();
                DBInsert.insertGraph((DBSelect.SelectRow()+1),graphTitleTextField.getText(),pormcombox.getValue(),physicalORMentalCombox.getValue(),graphTypeCombox.getValue());

            }else if (pormcombox.getValue().equals("Mental")) {
                String selectedFields = physicalORMentalCombox.getValue();
                String userFields = selectedFieldsCombox.getValue();
                String options = selectedOptionsCombox.getValue();
//                //defining the axes
                final CategoryAxis xAxis = new CategoryAxis();
                final NumberAxis yAxis = new NumberAxis();
                xAxis.setLabel("Number");
//                //creating the chart
                final BarChart<String, Number> lineChart =
                        new BarChart<>(xAxis, yAxis);

                lineChart.setTitle(graphTitleTextField.getText().trim() + "   " + selectedFields);
//                //defining a series
                XYChart.Series series = new XYChart.Series();
//                //populating the series with data
                //get id resultSet
                ResultSet rs = DBSelect.selectUserID(userFields, options);
                HashMap<String, Integer> hm = new HashMap<>();
                while (rs.next()) {
                    System.out.println(rs.getString(1));
                    ResultSet result = DBSelect.selectMentalData(rs.getString(1), selectedFields);
                    while (result.next()) {
                        if (hm.containsKey(result.getString(1))) {
                            hm.put(result.getString(1), hm.get(result.getString(1) + 1));
                        } else {
                            hm.put(result.getString(1), 1);
                        }
                    }
                }
                for (String s : hm.keySet()) {
                    series.getData().add(new XYChart.Data(s, hm.get(s)));
                }
                Scene scene = new Scene(lineChart, 400, 400);
                lineChart.getData().add(series);
                stage.setScene(scene);
                stage.show();
                DBInsert.insertGraph((DBSelect.SelectRow()+1),graphTitleTextField.getText(),pormcombox.getValue(),physicalORMentalCombox.getValue(),graphTypeCombox.getValue());
            }
        } else if (graphTypeCombox.getValue().trim().equals("Scatter")) {
            if (pormcombox.getValue().equals("Physical")) {
                String selectedFields = physicalORMentalCombox.getValue();
                String userFields = selectedFieldsCombox.getValue();
                String options = selectedOptionsCombox.getValue();

                stage.setTitle("Scatter Chart Sample");
                final CategoryAxis xAxis = new CategoryAxis();
                final NumberAxis yAxis = new NumberAxis();
                final ScatterChart<String,Number> sc = new
                        ScatterChart<>(xAxis,yAxis);
                sc.setTitle(graphTitleTextField.getText().trim() + "   " + selectedFields);
//                //defining a series
                XYChart.Series series = new XYChart.Series();

//                //populating the series with data
                //get id resultSet
                ResultSet rs = DBSelect.selectUserID(userFields, options);
                HashMap<String, Integer> hm = new HashMap<>();
                while (rs.next()) {
                    System.out.println(rs.getString(1));
                    ResultSet result = DBSelect.selectPhysicalData(rs.getString(1), selectedFields);
                    while (result.next()) {
                        if (hm.containsKey(result.getString(1))) {
                            hm.put(result.getString(1), hm.get(result.getString(1) + 1));
                        } else {
                            hm.put(result.getString(1), 1);
                        }
                    }
                }
                for (String s : hm.keySet()) {
                    series.getData().add(new XYChart.Data(s, hm.get(s)));
                }
                Scene scene = new Scene(sc, 400, 400);
                sc.getData().add(series);
                stage.setScene(scene);
                stage.show();
                DBInsert.insertGraph((DBSelect.SelectRow()+1),graphTitleTextField.getText(),pormcombox.getValue(),physicalORMentalCombox.getValue(),graphTypeCombox.getValue());

            }else if (pormcombox.getValue().equals("Mental")) {
                String selectedFields = physicalORMentalCombox.getValue();
                String userFields = selectedFieldsCombox.getValue();
                String options = selectedOptionsCombox.getValue();

                stage.setTitle("Scatter Chart Sample");
                final CategoryAxis xAxis = new CategoryAxis();
                final NumberAxis yAxis = new NumberAxis();
                final BarChart<String,Number> sc = new
                        BarChart<>(xAxis,yAxis);
                sc.setTitle(graphTitleTextField.getText().trim() + "   " + selectedFields);
//                //defining a series
                XYChart.Series series = new XYChart.Series();

//                //populating the series with data
                //get id resultSet
                ResultSet rs = DBSelect.selectUserID(userFields, options);
                HashMap<String, Integer> hm = new HashMap<>();
                while (rs.next()) {
                    System.out.println(rs.getString(1));
                    ResultSet result = DBSelect.selectMentalData(rs.getString(1), selectedFields);
                    while (result.next()) {
                        if (hm.containsKey(result.getString(1))) {
                            hm.put(result.getString(1), hm.get(result.getString(1) + 1));
                        } else {
                            hm.put(result.getString(1), 1);
                        }
                    }
                }
                for (String s : hm.keySet()) {
                    series.getData().add(new XYChart.Data(s, hm.get(s)));
                }
                Scene scene = new Scene(sc, 400, 400);
                sc.getData().add(series);
                stage.setScene(scene);
                stage.show();
                DBInsert.insertGraph((DBSelect.SelectRow()+1),graphTitleTextField.getText(),pormcombox.getValue(),physicalORMentalCombox.getValue(),graphTypeCombox.getValue());

            }
        } else if (graphTypeCombox.getValue().trim().equals("Line")) {

            if (pormcombox.getValue().equals("Physical")) {
                String selectedFields = physicalORMentalCombox.getValue();
                String userFields = selectedFieldsCombox.getValue();
                String options = selectedOptionsCombox.getValue();
//                //defining the axes
                final CategoryAxis xAxis = new CategoryAxis();
                final NumberAxis yAxis = new NumberAxis();
                xAxis.setLabel("Number");
//                //creating the chart
                final LineChart<String, Number> lineChart =
                        new LineChart<String, Number>(xAxis, yAxis);

                lineChart.setTitle(graphTitleTextField.getText().trim() + "   " + selectedFields);
//                //defining a series
                XYChart.Series series = new XYChart.Series();
//                //populating the series with data
                //get id resultSet
                ResultSet rs = DBSelect.selectUserID(userFields, options);
                HashMap<String, Integer> hm = new HashMap<>();
                while (rs.next()) {
                    System.out.println(rs.getString(1));
                    ResultSet result = DBSelect.selectPhysicalData(rs.getString(1), selectedFields);
                    while (result.next()) {
                        if (hm.containsKey(result.getString(1))) {
                            hm.put(result.getString(1), hm.get(result.getString(1) + 1));
                        } else {
                            hm.put(result.getString(1), 1);
                        }
                    }
                }
                for (String s : hm.keySet()) {
                    series.getData().add(new XYChart.Data(s, hm.get(s)));
                }
                Scene scene = new Scene(lineChart, 400, 400);
                lineChart.getData().add(series);
                stage.setScene(scene);
                stage.show();
                DBInsert.insertGraph((DBSelect.SelectRow()+1),graphTitleTextField.getText(),pormcombox.getValue(),physicalORMentalCombox.getValue(),graphTypeCombox.getValue());

            }else if (pormcombox.getValue().equals("Mental")) {
                String selectedFields = physicalORMentalCombox.getValue();
                String userFields = selectedFieldsCombox.getValue();
                String options = selectedOptionsCombox.getValue();
//                //defining the axes
                final CategoryAxis xAxis = new CategoryAxis();
                final NumberAxis yAxis = new NumberAxis();
                xAxis.setLabel("Number");
//                //creating the chart
                final LineChart<String, Number> lineChart =
                        new LineChart<String, Number>(xAxis, yAxis);

                lineChart.setTitle(graphTitleTextField.getText().trim() + "   " + selectedFields);
//                //defining a series
                XYChart.Series series = new XYChart.Series();
//                //populating the series with data
                //get id resultSet
                ResultSet rs = DBSelect.selectUserID(userFields, options);
                HashMap<String, Integer> hm = new HashMap<>();
                while (rs.next()) {
                    System.out.println(rs.getString(1));
                    ResultSet result = DBSelect.selectMentalData(rs.getString(1), selectedFields);
                    while (result.next()) {
                        if (hm.containsKey(result.getString(1))) {
                            hm.put(result.getString(1), hm.get(result.getString(1) + 1));
                        } else {
                            hm.put(result.getString(1), 1);
                        }
                    }
                }
                for (String s : hm.keySet()) {
                    series.getData().add(new XYChart.Data(s, hm.get(s)));
                }
                Scene scene = new Scene(lineChart, 400, 400);
                lineChart.getData().add(series);
                stage.setScene(scene);
                stage.show();
                DBInsert.insertGraph((DBSelect.SelectRow()+1),graphTitleTextField.getText(),pormcombox.getValue(),physicalORMentalCombox.getValue(),graphTypeCombox.getValue());

            }
        } else if (graphTypeCombox.getValue().trim().equals("Scatter")) {
            if (pormcombox.getValue().equals("Physical")) {
                String selectedFields = physicalORMentalCombox.getValue();
                String userFields = selectedFieldsCombox.getValue();
                String options = selectedOptionsCombox.getValue();

                stage.setTitle("Scatter Chart Sample");
                final CategoryAxis xAxis = new CategoryAxis();
                final NumberAxis yAxis = new NumberAxis();
                final ScatterChart<String,Number> sc = new
                        ScatterChart<>(xAxis,yAxis);
                sc.setTitle(graphTitleTextField.getText().trim() + "   " + selectedFields);
//                //defining a series
                XYChart.Series series = new XYChart.Series();

//                //populating the series with data
                //get id resultSet
                ResultSet rs = DBSelect.selectUserID(userFields, options);
                HashMap<String, Integer> hm = new HashMap<>();
                while (rs.next()) {
                    System.out.println(rs.getString(1));
                    ResultSet result = DBSelect.selectPhysicalData(rs.getString(1), selectedFields);
                    while (result.next()) {
                        if (hm.containsKey(result.getString(1))) {
                            hm.put(result.getString(1), hm.get(result.getString(1) + 1));
                        } else {
                            hm.put(result.getString(1), 1);
                        }
                    }
                }
                for (String s : hm.keySet()) {
                    series.getData().add(new XYChart.Data(s, hm.get(s)));
                }
                Scene scene = new Scene(sc, 400, 400);
                sc.getData().add(series);
                stage.setScene(scene);
                stage.show();
                DBInsert.insertGraph((DBSelect.SelectRow()+1),graphTitleTextField.getText(),pormcombox.getValue(),physicalORMentalCombox.getValue(),graphTypeCombox.getValue());

            }else if (pormcombox.getValue().equals("Mental")) {
                String selectedFields = physicalORMentalCombox.getValue();
                String userFields = selectedFieldsCombox.getValue();
                String options = selectedOptionsCombox.getValue();

                stage.setTitle("Scatter Chart Sample");
                final CategoryAxis xAxis = new CategoryAxis();
                final NumberAxis yAxis = new NumberAxis();
                final ScatterChart<String,Number> sc = new
                        ScatterChart<>(xAxis,yAxis);
                sc.setTitle(graphTitleTextField.getText().trim() + "   " + selectedFields);
//                //defining a series
                XYChart.Series series = new XYChart.Series();

//                //populating the series with data
                //get id resultSet
                ResultSet rs = DBSelect.selectUserID(userFields, options);
                HashMap<String, Integer> hm = new HashMap<>();
                while (rs.next()) {
                    System.out.println(rs.getString(1));
                    ResultSet result = DBSelect.selectMentalData(rs.getString(1), selectedFields);
                    while (result.next()) {
                        if (hm.containsKey(result.getString(1))) {
                            hm.put(result.getString(1), hm.get(result.getString(1) + 1));
                        } else {
                            hm.put(result.getString(1), 1);
                        }
                    }
                }
                for (String s : hm.keySet()) {
                    series.getData().add(new XYChart.Data(s, hm.get(s)));
                }
                Scene scene = new Scene(sc, 400, 400);
                sc.getData().add(series);
                stage.setScene(scene);
                stage.show();
                DBInsert.insertGraph((DBSelect.SelectRow()+1),graphTitleTextField.getText(),pormcombox.getValue(),physicalORMentalCombox.getValue(),graphTypeCombox.getValue());

            }
        }

    }

    @FXML
    void handleCancel() {
        stage.close();
    }
}
