package edu.ijse.sms.controller;

import edu.ijse.sms.dto.AttendanceDto;
import edu.ijse.sms.service.custom.AttendanceService;
import edu.ijse.sms.service.impl.AttendanceServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class LecturerDashboardController {
    @FXML private DatePicker datePicker;
    @FXML private TextField txtSubjectId;
    @FXML private TextField txtStudentId;
    @FXML private RadioButton rbPresent;
    @FXML private RadioButton rbAbsent;
    @FXML private ToggleGroup statusGroup;
    @FXML private TableView<AttendanceDto> tblAttendance;
    @FXML private TableColumn<AttendanceDto, Date> colDate;
    @FXML private TableColumn<AttendanceDto, String> colSubjectId;
    @FXML private TableColumn<AttendanceDto, String> colStudentId;
    @FXML private TableColumn<AttendanceDto, String> colStatus;

    private ObservableList<AttendanceDto> attendanceList = FXCollections.observableArrayList();
    private AttendanceService attendanceService = new AttendanceServiceImpl();

    @FXML
    public void initialize() {
        // Initialize table columns
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colSubjectId.setCellValueFactory(new PropertyValueFactory<>("subjectId"));
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        // Initialize toggle group for radio buttons
        statusGroup = new ToggleGroup();
        rbPresent.setToggleGroup(statusGroup);
        rbAbsent.setToggleGroup(statusGroup);
        rbPresent.setSelected(true);
    }

    @FXML
    public void setDateAndSubject() {
        try {
            if (datePicker.getValue() == null || txtSubjectId.getText().isEmpty()) {
                showAlert("Error", "Please select a date and enter Subject ID");
                return;
            }

            Date date = Date.valueOf(datePicker.getValue());
            String subjectId = txtSubjectId.getText();

            // Load attendance records for the selected date and subject
            loadAttendance(date, subjectId);
        } catch (Exception e) {
            showAlert("Error", "Failed to set date and subject: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void clearFields() {
        datePicker.setValue(null);
        txtSubjectId.clear();
        txtStudentId.clear();
        rbPresent.setSelected(true);
        attendanceList.clear();
    }

    @FXML
    public void saveAttendance() {
        try {
            if (datePicker.getValue() == null || txtSubjectId.getText().isEmpty() || txtStudentId.getText().isEmpty()) {
                showAlert("Error", "Please set date, subject ID, and enter student ID");
                return;
            }

            Date date = Date.valueOf(datePicker.getValue());
            String subjectId = txtSubjectId.getText();
            String studentId = txtStudentId.getText();
            String status = rbPresent.isSelected() ? "Present" : "Absent";

            if (attendanceService.markAttendance(date, subjectId, studentId, status)) {
                showAlert("Success", "Attendance marked successfully!");
                loadAttendance(date, subjectId);
                txtStudentId.clear();
            } else {
                showAlert("Error", "Failed to mark attendance!");
            }
        } catch (Exception e) {
            showAlert("Error", "Failed to mark attendance: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void editAttendance() {
        AttendanceDto selected = tblAttendance.getSelectionModel().getSelectedItem();
        if (selected != null) {
            datePicker.setValue(selected.getDate().toLocalDate());
            txtSubjectId.setText(selected.getSubjectId());
            txtStudentId.setText(selected.getStudentId());
            if (selected.getStatus().equals("Present")) {
                rbPresent.setSelected(true);
            } else {
                rbAbsent.setSelected(true);
            }
        }
    }

    private void loadAttendance(Date date, String subjectId) throws Exception {
        attendanceList.clear();
        List<AttendanceDto> attendance = attendanceService.getAttendanceByDateAndSubject(date, subjectId);
        attendanceList.addAll(attendance);
        tblAttendance.setItems(attendanceList);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
