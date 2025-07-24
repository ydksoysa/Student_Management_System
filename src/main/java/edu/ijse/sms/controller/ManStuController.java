package edu.ijse.sms.controller;

import edu.ijse.sms.dto.StudentDto;
import edu.ijse.sms.service.custom.StudentService;
import edu.ijse.sms.service.impl.StudentServiceImpl;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class ManStuController {
    @FXML private TextField txtRegisteredId;
    @FXML private TextField txtName;
    @FXML private TextField txtCourse;
    @FXML private TextField txtContactNum;
    @FXML private TableView<StudentDto> tblStudents;
    @FXML private TableColumn<StudentDto, String> colRegId;
    @FXML private TableColumn<StudentDto, String> colName;
    @FXML private TableColumn<StudentDto, String> colCourse;
    @FXML private TableColumn<StudentDto, String> colContact;

    private ObservableList<StudentDto> studentList = FXCollections.observableArrayList();
    private StudentService studentService = new StudentServiceImpl();

    @FXML
    public void initialize() {
        // Set up table columns
        colRegId.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRegisteredId()));
        colName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        colCourse.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCourse()));
        colContact.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getContactNum()));

        // Load initial data
        loadAllStudents();
    }

    @FXML
    public void addStudent() {
        try {
            StudentDto dto = new StudentDto(
                    txtRegisteredId.getText(),
                    txtName.getText(),
                    txtCourse.getText(),
                    txtContactNum.getText()
            );

            if (studentService.addStudent(dto)) {
                clearFields();
                loadAllStudents();
                showAlert("Success", "Student added successfully!");
            } else {
                showAlert("Error", "Failed to add student!");
            }
        } catch (Exception e) {
            showAlert("Error", "Failed to add student: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void updateStudent() {
        try {
            StudentDto dto = new StudentDto(
                    txtRegisteredId.getText(),
                    txtName.getText(),
                    txtCourse.getText(),
                    txtContactNum.getText()
            );

            if (studentService.updateStudent(dto)) {
                clearFields();
                loadAllStudents();
                showAlert("Success", "Student updated successfully!");
            } else {
                showAlert("Error", "Failed to update student!");
            }
        } catch (Exception e) {
            showAlert("Error", "Failed to update student: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void deleteStudent() {
        try {
            String registeredId = txtRegisteredId.getText();
            if (registeredId.isEmpty()) {
                showAlert("Error", "Please enter a Registered ID to delete");
                return;
            }

            if (studentService.deleteStudent(registeredId)) {
                clearFields();
                loadAllStudents();
                showAlert("Success", "Student deleted successfully!");
            } else {
                showAlert("Error", "Failed to delete student!");
            }
        } catch (Exception e) {
            showAlert("Error", "Failed to delete student: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void viewList() {
        loadAllStudents();
    }

    private void loadAllStudents() {
        try {
            studentList.clear();
            List<StudentDto> students = studentService.getAllStudents();
            studentList.addAll(students);
            tblStudents.setItems(studentList);
        } catch (Exception e) {
            showAlert("Error", "Failed to load students: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void clearFields() {
        txtRegisteredId.clear();
        txtName.clear();
        txtCourse.clear();
        txtContactNum.clear();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
