package edu.ijse.sms.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminDashboardController {



    private void openPage(String fxmlFile, String title) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/" + fxmlFile));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void handleManageCourse(ActionEvent event) throws IOException {
        openPage("ManCourse.fxml", "Course Management");
    }

    public void handleManageStudents(ActionEvent event) throws IOException {
        openPage("ManStu.fxml", "Student Management");
    }

    public void handleManageLecturers(ActionEvent event) throws IOException {
        openPage("ManLec.fxml", "Lecturer Management");
    }

    public void handleManageClass(ActionEvent event) throws IOException {
        openPage("ManClass.fxml", "Class Management");
    }

    public void handleManageAttendance(ActionEvent event) throws IOException {
        openPage("ManAttend.fxml", "Attendance Reports");
    }
}

