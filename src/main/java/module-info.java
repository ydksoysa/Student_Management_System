module edu.ijse.sms {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;



    exports edu.ijse.sms;
    exports edu.ijse.sms.controller;
    opens edu.ijse.sms.controller to javafx.fxml;
}