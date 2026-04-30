# 🎓 Saegis Campus Student Fee Calculator

A JavaFX desktop application developed to calculate semester fees for students based on programme level, module selection, and student status.

---

## 🚀 Features

- Calculates total semester fees
- Supports Local and International students
- Applies:
    - Module-based fees
    - International surcharge (10%)
    - Tutoring fees (with max limit enforcement)
    - Registration fees
- Validates module limits based on level
- Clean UI built using JavaFX and JFoenix

---

## 🛠 Technologies Used

- Java 21
- JavaFX 21
- JFoenix (Material UI components)
- Maven

---

## 🧠 Programming Paradigms Demonstrated

### ✔ Object-Oriented Programming (OOP)
- `Student.java`
- Encapsulation using private fields and getters/setters
- Business rule enforcement (max tutoring hours)

### ✔ Procedural Programming
- `FeeCalculator.java`
- Step-by-step static methods to compute fees

### ✔ Event-Driven Programming
- `MainFormController.java`
- Handles button click events and updates UI dynamically

---

## 📦 How to Run the Application

### ✅ Option 1 (Recommended)

Make sure you have:
- Java 21 installed
- Maven installed

Run:

```bash
mvn clean javafx:run