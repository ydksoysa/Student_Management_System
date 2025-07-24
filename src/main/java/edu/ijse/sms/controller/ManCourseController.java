package edu.ijse.sms.controller;

import edu.ijse.sms.dto.CourseDto;
import edu.ijse.sms.dto.SubjectDto;
import edu.ijse.sms.service.custom.CourseService;
import edu.ijse.sms.service.custom.SubjectService;
import edu.ijse.sms.service.impl.CourseServiceImpl;
import edu.ijse.sms.service.impl.SubjectServiceImpl;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class ManCourseController {
    // Subject UI Components
    @FXML private TextField txtSubjectId;
    @FXML private TextField txtSubjectName;
    @FXML private TableView<SubjectDto> tblSubjects;
    @FXML private TableColumn<SubjectDto, String> colSubjectId;
    @FXML private TableColumn<SubjectDto, String> colSubjectName;

    // Course UI Components
    @FXML private TextField txtCourseId;
    @FXML private TextField txtCourseName;
    @FXML private ComboBox<String> cmbSubjects;
    @FXML private TableView<CourseDto> tblCourses;
    @FXML private TableColumn<CourseDto, String> colCourseId;
    @FXML private TableColumn<CourseDto, String> colCourseName;
    @FXML private TableColumn<CourseDto, String> colCourseSubjects;

    private ObservableList<SubjectDto> subjectList = FXCollections.observableArrayList();
    private ObservableList<CourseDto> courseList = FXCollections.observableArrayList();
    private ObservableList<String> subjectComboList = FXCollections.observableArrayList();

    private SubjectService subjectService = new SubjectServiceImpl();
    private CourseService courseService = new CourseServiceImpl();

    @FXML
    public void initialize() {
        // Initialize Subject Table
        colSubjectId.setCellValueFactory(new PropertyValueFactory<>("subjectId"));
        colSubjectName.setCellValueFactory(new PropertyValueFactory<>("subjectName"));

        // Initialize Course Table
        colCourseId.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        colCourseName.setCellValueFactory(new PropertyValueFactory<>("courseName"));

        colCourseSubjects.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSubjectsFormatted()));
        // Load initial data
        loadAllSubjects();
        loadAllCourses();
        loadSubjectComboBox();
    }

    // Subject Management Methods
    @FXML
    public void saveSubject() {
        try {
            SubjectDto dto = new SubjectDto(
                    txtSubjectId.getText(),
                    txtSubjectName.getText()
            );

            if (subjectService.addSubject(dto)) {
                clearSubjectFields();
                loadAllSubjects();
                loadSubjectComboBox();
                showAlert("Success", "Subject added successfully!");
            } else {
                showAlert("Error", "Failed to add subject!");
            }
        } catch (Exception e) {
            showAlert("Error", "added -: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void deleteSubject() {
        try {
            String subjectId = txtSubjectId.getText();
            if (subjectId.isEmpty()) {
                showAlert("Error", "Please enter a Subject ID to delete");
                return;
            }

            if (subjectService.deleteSubject(subjectId)) {
                clearSubjectFields();
                loadAllSubjects();
                loadSubjectComboBox();
                showAlert("Success", "Subject deleted successfully!");
            } else {
                showAlert("Error", "Failed to delete subject!");
            }
        } catch (Exception e) {
            showAlert("Error", "Failed to delete subject: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void loadAllSubjects() {
        try {
            subjectList.clear();
            List<SubjectDto> subjects = subjectService.getAllSubjects();
            subjectList.addAll(subjects);
            tblSubjects.setItems(subjectList);
        } catch (Exception e) {
            showAlert("Error", "Failed to load subjects: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void loadSubjectComboBox() {
        try {
            subjectComboList.clear();
            List<SubjectDto> subjects = subjectService.getAllSubjects();
            for (SubjectDto subject : subjects) {
                subjectComboList.add(subject.getSubjectId() + " - " + subject.getSubjectName());
            }
            cmbSubjects.setItems(subjectComboList);
        } catch (Exception e) {
            showAlert("Error", "Failed to load subjects for combo box: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void clearSubjectFields() {
        txtSubjectId.clear();
        txtSubjectName.clear();
    }

    // Course Management Methods
    @FXML
    public void saveCourse() {
        try {
            CourseDto dto = new CourseDto(
                    txtCourseId.getText(),
                    txtCourseName.getText()
            );

            if (courseService.addCourse(dto)) {
                clearCourseFields();
                loadAllCourses();
                showAlert("Success", "Course added successfully!");
            } else {
                showAlert("Error", "Failed to add course!");
            }
        } catch (Exception e) {
            showAlert("Error", "Failed to add course: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void updateCourse() {
        try {
            CourseDto dto = new CourseDto(
                    txtCourseId.getText(),
                    txtCourseName.getText()
            );

            if (courseService.updateCourse(dto)) {
                clearCourseFields();
                loadAllCourses();
                showAlert("Success", "Course updated successfully!");
            } else {
                showAlert("Error", "Failed to update course!");
            }
        } catch (Exception e) {
            showAlert("Error", "Failed to update course: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void deleteCourse() {
        try {
            String courseId = txtCourseId.getText();
            if (courseId.isEmpty()) {
                showAlert("Error", "Please enter a Course ID to delete");
                return;
            }

            if (courseService.deleteCourse(courseId)) {
                clearCourseFields();
                loadAllCourses();
                showAlert("Success", "Course deleted successfully!");
            } else {
                showAlert("Error", "Failed to delete course!");
            }
        } catch (Exception e) {
            showAlert("Error", "Failed to delete course: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void addSubjectToCourse() {
        try {
            String courseId = txtCourseId.getText();
            if (courseId.isEmpty()) {
                showAlert("Error", "Please select a course first");
                return;
            }

            String selectedSubject = cmbSubjects.getSelectionModel().getSelectedItem();
            if (selectedSubject == null) {
                showAlert("Error", "Please select a subject to add");
                return;
            }

            // Extract subject ID from combo box item (format: "ID - Name")
            String[] parts = selectedSubject.split(" - ");
            String subjectId = parts[0];

            // Check if subject is already assigned
            CourseDto currentCourse = courseService.findCourse(courseId);
            if (currentCourse.getSubjectIds().contains(subjectId)) {
                showAlert("Warning", "This subject is already assigned to the course");
                return;
            }

            if (courseService.addSubjectToCourse(courseId, subjectId)) {
                loadAllCourses(); // Refresh the course table
                showAlert("Success", "Subject added to course successfully!");
            } else {
                showAlert("Error", "Failed to add subject to course!");
            }
        } catch (Exception e) {
            showAlert("Error", "Failed to add subject to course: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void loadAllCourses() {
        try {
            courseList.clear();
            List<CourseDto> courses = courseService.getAllCourses();
            courseList.addAll(courses);
            tblCourses.setItems(courseList);
        } catch (Exception e) {
            showAlert("Error", "go to " );
            e.printStackTrace();
        }
    }

    private void clearCourseFields() {
        txtCourseId.clear();
        txtCourseName.clear();
    }

    // Utility Methods
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Update the course table selection handler
    @FXML
    private void handleCourseSelection() {
        CourseDto selectedCourse = tblCourses.getSelectionModel().getSelectedItem();
        if (selectedCourse != null) {
            txtCourseId.setText(selectedCourse.getCourseId());
            txtCourseName.setText(selectedCourse.getCourseName());
        }
    }
}
