/*
 * CRITTERS InvalidCritterException.java
 * EE422C Project 4 submission by
 * Replace <...> with your actual data.
 * <Analaura Rodriguez>
 * <ar55665>
 * <16225>
 * <Kevin Han>
 * <kdh2789>
 * <16190>
 * Slip days used: <0>
 * Spring 2019
 */

package assignment4;

public class InvalidCritterException extends Exception {

    String offending_class;

    public InvalidCritterException(String critter_class_name) {
        offending_class = critter_class_name;
    }

    public String toString() {
        return "Invalid Critter Class: " + offending_class;
    }
}
