package edu.ijse.sms.controller;

import edu.ijse.sms.dto.LecturerDto;
import edu.ijse.sms.service.custom.LecturerService;
import edu.ijse.sms.service.custom.SubjectService;
import edu.ijse.sms.service.impl.LecturerServiceImpl;
import edu.ijse.sms.service.impl.SubjectServiceImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class ManLecController {
    @FXML private TextField txtLecturerId;
    @FXML private TextField txtLecturerName;
    @FXML private TextField txtSubjectIdToAdd;

    private LecturerService lecturerService = new LecturerServiceImpl();
    private SubjectService subjectService = new SubjectServiceImpl();

    @FXML
    public void saveLecturer() {
        try {
            LecturerDto dto = new LecturerDto(
                    txtLecturerId.getText(),
                    txtLecturerName.getText()
            );

            if (lecturerService.addLecturer(dto)) {
                clearFields();
                showAlert("Success", "Lecturer added successfully!");
            } else {
                showAlert("Error", "Failed to add lecturer!");
            }
        } catch (Exception e) {
            showAlert("Error", "Failed to add lecturer: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void updateLecturer() {
        try {
            LecturerDto dto = new LecturerDto(
                    txtLecturerId.getText(),
                    txtLecturerName.getText()
            );

            if (lecturerService.updateLecturer(dto)) {
                clearFields();
                showAlert("Success", "Lecturer updated successfully!");
            } else {
                showAlert("Error", "Failed to update lecturer!");
            }
        } catch (Exception e) {
            showAlert("Error", "Failed to update lecturer: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void deleteLecturer() {
        try {
            String lecturerId = txtLecturerId.getText();
            if (lecturerId.isEmpty()) {
                showAlert("Error", "Please enter a Lecturer ID to delete");
                return;
            }

            if (lecturerService.deleteLecturer(lecturerId)) {
                clearFields();
                showAlert("Success", "Lecturer deleted successfully!");
            } else {
                showAlert("Error", "Failed to delete lecturer!");
            }
        } catch (Exception e) {
            showAlert("Error", "Failed to delete lecturer: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void addSubjectToLecturer() {
        try {
            String lecturerId = txtLecturerId.getText().trim();
            String subjectId = txtSubjectIdToAdd.getText().trim();

            if (lecturerId.isEmpty() || subjectId.isEmpty()) {
                showAlert("Error", "Please enter both Lecturer ID and Subject ID");
                return;
            }

            // Check if lecturer exists
            if (lecturerService.findLecturer(lecturerId) == null) {
                showAlert("Error", "Lecturer ID not found!");
                return;
            }

            // Check if subject exists (you'll need subjectService)
            if (subjectService.findSubject(subjectId) == null) {
                showAlert("Error", "Subject ID not found!");
                return;
            }

            if (lecturerService.addSubjectToLecturer(lecturerId, subjectId)) {
                txtSubjectIdToAdd.clear();
                showAlert("Success", "Subject added to lecturer successfully!");
            } else {
                showAlert("Error", "Failed to add subject to lecturer!");
            }
        } catch (Exception e) {
            showAlert("Error", "Failed to add subject to lecturer: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void clearFields() {
        txtLecturerId.clear();
        txtLecturerName.clear();
        txtSubjectIdToAdd.clear();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
