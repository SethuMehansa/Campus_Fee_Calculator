package service;

import model.Student;

/**
 * PARADIGM DEMONSTRATION: PROCEDURAL PROGRAMMING
 * This class contains step-by-step static methods (procedures) that take inputs,
 * process them sequentially based on business rules, and return a result.
 */
public class FeeCalculator {

    /**
     * Validates if the number of registered modules is within the allowed range for the student's level.
     * @param level The student's academic level.
     * @param modules The number of modules registered.
     * @return True if the module load is valid, otherwise false.
     */
    public static boolean validateModuleLoad(int level, int modules) {
        // For Levels 4 and 5, students must register between 3 and 5 modules.
        if (level == 4 || level == 5) {
            return modules >= 3 && modules <= 5;
            // For Levels 6 and 7, students must register between 2 and 4 modules.
        } else if (level == 6 || level == 7) {
            return modules >= 2 && modules <= 4;
        }
        // If the level is not recognized, the module load is considered invalid.
        return false;
    }

    /**
     * Calculates the total fee for all registered modules based on the student's level and status.
     * @param student The student object containing all necessary data.
     * @return The total cost for the registered modules.
     */
    public static double calculateModuleFee(Student student) {
        double costPerModule = 0; // Initialize cost per module.
        boolean isInt = student.isInternational(); // Check if the student is international for rate lookup.

        // Use a switch statement to determine the cost per module based on the student's level.
        switch (student.getLevel()) {
            case 4: costPerModule = isInt ? 62000.00 : 35000.00; break; // Level 4 rates.
            case 5: costPerModule = isInt ? 67000.00 : 38000.00; break; // Level 5 rates.
            case 6: costPerModule = isInt ? 72000.00 : 42000.00; break; // Level 6 rates.
            case 7: costPerModule = isInt ? 95000.00 : 55000.00; break; // Level 7 rates.
        }
        // Multiply the cost per module by the number of registered modules to get the total.
        return costPerModule * student.getModulesRegistered();
    }

    /**
     * Calculates the 10% surcharge on module fees for international students.
     * @param student The student object.
     * @param baseModuleFee The pre-calculated total module fee.
     * @return The surcharge amount, or 0.0 if the student is local.
     */
    public static double calculateSurcharge(Student student, double baseModuleFee) {
        // Apply a 10% surcharge if the student is international; otherwise, the surcharge is zero.
        return student.isInternational() ? baseModuleFee * 0.10 : 0.0;
    }

    /**
     * Calculates the total fee for tutoring based on the student's status.
     * @param student The student object.
     * @return The total cost for the requested tutoring hours.
     */
    public static double calculateTutoringFee(Student student) {
        // Determine the hourly rate based on whether the student is international or local.
        double rate = student.isInternational() ? 2500.00 : 1500.00;
        // Multiply the hourly rate by the number of tutoring hours.
        return rate * student.getTutoringHours();
    }

    /**
     * Retrieves the standard registration fee based on the student's status.
     * @param student The student object.
     * @return The registration fee amount.
     */
    public static double getRegistrationFee(Student student) {
        // Return the appropriate registration fee: 8000.00 for international, 5000.00 for local.
        return student.isInternational() ? 8000.00 : 5000.00;
    }
}