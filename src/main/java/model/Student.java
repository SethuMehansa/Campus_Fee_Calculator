package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * PARADIGM DEMONSTRATION: OBJECT-ORIENTED PROGRAMMING (OOP)
 * This class demonstrates encapsulation by keeping fields private
 * and providing controlled access through public getters and setters.
 * Lombok annotations (@Getter, @Setter) are used here to auto-generate these methods.
 */
@Getter // Lombok annotation to generate all getter methods automatically.
@Setter // Lombok annotation to generate all setter methods automatically.
@AllArgsConstructor // Lombok annotation to generate a constructor with all fields.
@NoArgsConstructor // Lombok annotation to generate a no-argument constructor.
public class Student {

    // --- Private Fields: Encapsulated data representing the student's state ---

    private String fullName; // Stores the student's full name.
    private String studentId; // Stores the unique student identifier.
    private String programmeType; // Stores the type of programme (e.g., "Undergraduate").
    private int level; // Stores the student's academic level (e.g., 4, 5, 6, 7).
    private int modulesRegistered; // Stores the number of modules the student is taking.
    private boolean isInternational; // Flag to indicate if the student is international (true) or local (false).
    private int tutoringHours; // Stores the number of requested tutoring hours.

    /**
     * Custom getter for the boolean 'isInternational' field.
     * This follows the standard Java naming convention for boolean getters.
     * @return true if the student is international, false otherwise.
     */
    public boolean isInternational() {
        return isInternational;
    }

    /**
     * Custom setter for tutoringHours that enforces a business rule.
     * @param tutoringHours The number of hours requested by the user.
     */
    public void setTutoringHours(int tutoringHours) {
        // --- OOP Business Rule Enforcement ---
        // This logic is encapsulated within the object itself.
        // If the requested hours exceed the maximum allowed (10), cap it at 10.
        if (tutoringHours > 10) {
            this.tutoringHours = 10; // Enforce the maximum limit.
        } else {
            this.tutoringHours = tutoringHours; // Otherwise, accept the provided value.
        }
    }
}