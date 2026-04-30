package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import model.Student;
import service.FeeCalculator;

public class MainFormController {

    // Input Fields (FXML elements injected from MainForm.fxml)
    @FXML private JFXTextField txtName; // Text field for student name
    @FXML private JFXTextField txtId; // Text field for student ID
    @FXML private JFXComboBox<String> cmbProgramme; // Combo box for programme selection (UG/PG)
    @FXML private JFXComboBox<Integer> cmbLevel; // Combo box for level selection (4, 5, 6, 7)
    @FXML private JFXTextField txtModules; // Text field for number of modules registered
    @FXML private JFXComboBox<String> cmbStatus; // Combo box for student status (Local/International)
    @FXML private JFXTextField txtTutoring; // Text field for tutoring hours
    @FXML private JFXButton btnCalculate; // Button to trigger fee calculation
    @FXML private JFXButton btnDownload; // Button to simulate invoice download

    // Output Labels (Right Side - FXML elements for displaying results)
    @FXML private Label lblOutName; // Label to display student name in output
    @FXML private Label lblBadge; // Label to display module load validity status
    @FXML private Label lblOutIdLevel; // Label to display student ID and level
    @FXML private Label lblOutBase; // Label to display base module fee
    @FXML private Label lblOutSurcharge; // Label to display international surcharge
    @FXML private Label lblOutTutoring; // Label to display tutoring fee
    @FXML private Label lblOutReg; // Label to display registration fee
    @FXML private Label lblOutTotal; // Label to display grand total payable

    @FXML
    public void initialize() {
        // Initialize combo box options
        cmbProgramme.getItems().addAll("Undergraduate", "Postgraduate"); // Add programme options
        cmbLevel.getItems().addAll(4, 5, 6, 7); // Add level options
        cmbStatus.getItems().addAll("Local", "International"); // Add status options

        // Set action for the calculate button
        btnCalculate.setOnAction(this::handleCalculateAction);

        // Setup dummy action for the Download button
        btnDownload.setOnAction(e -> {
            // Create an information alert
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Download Invoice"); // Set alert title
            alert.setHeaderText("PDF Generation Started"); // Set alert header
            alert.setContentText("In a full production environment, this would save a PDF of the invoice to your desktop."); // Set alert content
            alert.showAndWait(); // Display the alert and wait for user interaction
        });
    }

    private void handleCalculateAction(ActionEvent event) {

        // EVENT-DRIVEN PARADIGM:
        // This method is triggered by a button click event (btnCalculate)
        try {
            // OOP PARADIGM:
            // Creating a Student object to encapsulate data and behavior
            Student student = new Student(); // Instantiate a new Student object

            // Set student properties from UI input fields, providing defaults if empty
            student.setFullName(txtName.getText().isEmpty() ? "Unknown Student" : txtName.getText());
            student.setStudentId(txtId.getText().isEmpty() ? "N/A" : txtId.getText());

            // Validate if Level and Status are selected
            if (cmbLevel.getValue() == null || cmbStatus.getValue() == null) {
                showAlert("Missing Input", "Please select Level and Status."); // Show error if not selected
                return; // Stop further processing
            }

            student.setLevel(cmbLevel.getValue()); // Set student level

            // Defensive parsing for numeric inputs to prevent NumberFormatException
            int modules = Integer.parseInt(txtModules.getText()); // Parse modules registered
            int tutoring = Integer.parseInt(txtTutoring.getText()); // Parse tutoring hours

            student.setModulesRegistered(modules); // Set modules registered
            student.setInternational("International".equals(cmbStatus.getValue())); // Set international status
            student.setTutoringHours(tutoring); // Set tutoring hours (OOP rule enforcement for max 10 hours happens in Student class)

            // PROCEDURAL PARADIGM:
            // Sequential validation and calculation using static methods from FeeCalculator
            boolean isValid = FeeCalculator.validateModuleLoad(student.getLevel(), student.getModulesRegistered()); // Validate module load

            if (!isValid) {
                lblBadge.setText("Load: INVALID"); // Update badge text
                lblBadge.setStyle("-fx-background-color: #FCE8E6; -fx-text-fill: #D93025;"); // Style badge for invalid
                showAlert("Invalid Module Load", "Check module limits."); // Show error alert
                return; // Stop further processing
            }

            lblBadge.setText("Load: VALID"); // Update badge text
            lblBadge.setStyle("-fx-background-color: #E6F4EA; -fx-text-fill: #1E8E3E;"); // Style badge for valid

            // Calculate individual fee components using FeeCalculator's static methods
            double baseFee = FeeCalculator.calculateModuleFee(student); // Calculate base module fee
            double surcharge = FeeCalculator.calculateSurcharge(student, baseFee); // Calculate international surcharge
            double tutoringFee = FeeCalculator.calculateTutoringFee(student); // Calculate tutoring fee
            double regFee = FeeCalculator.getRegistrationFee(student); // Calculate registration fee

            double total = baseFee + surcharge + tutoringFee + regFee; // Calculate grand total

            // EVENT-DRIVEN UI UPDATE:
            // Update output labels with calculated values and student information
            lblOutName.setText(student.getFullName()); // Display student's full name
            lblOutIdLevel.setText(student.getStudentId() + " • Level " + student.getLevel()); // Display student ID and level

            // Format and display individual fee components
            lblOutBase.setText(String.format("LKR %,.2f", baseFee));
            lblOutSurcharge.setText(String.format("LKR %,.2f", surcharge));
            lblOutTutoring.setText(String.format("LKR %,.2f", tutoringFee));
            lblOutReg.setText(String.format("LKR %,.2f", regFee));
            lblOutTotal.setText(String.format("LKR %,.2f", total)); // Display grand total

        } catch (NumberFormatException e) {
            // Handle cases where non-numeric input is provided for number fields
            showAlert("Input Error", "Enter valid numbers."); // Show error alert for invalid number format
        }
    }

    // Helper method to display an alert dialog
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR); // Create an error alert
        alert.setTitle(title); // Set alert title
        alert.setHeaderText(null); // No header text
        alert.setContentText(message); // Set alert content message
        alert.showAndWait(); // Display the alert and wait for user interaction
    }
}