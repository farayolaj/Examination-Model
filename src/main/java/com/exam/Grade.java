package com.exam;

import com.exam.examiner.Subject;

//import java.io.FileInputStream;
import java.io.*;
import java.util.HashMap;

/**
 * Abstracts a school class. It contains the class name and the subjects offered in the class.
 * @author FarayolaJ
 *
 */
public class Grade {
    /**
     * Maps the name of a subject to an instance of the class <code>Subject</code>.
     */
    private HashMap<String, Subject> subjects;// maps the subjects offered by the grade to their real world names
    /**
     * Holds the name of the class.
     */
    private String className;

    /**
     * Creates an instance of <code>Grade</code> with the grade name.
     *
     * @param cls Holds the class name
     */
    public Grade(String cls) {
        className = cls;
        subjects = new HashMap<>();
    }

    /**
     * Adds a subject for the class.
     * @param subname Holds the name of the subject.
     */
    public Subject addSubject(String subname) {
        Subject sub = new Subject(subname);
        subjects.put(subname, sub);
        return sub;
    }

    /**
     * Gets the instance of class <code>Subject</code> that is mapped to <code>subname</code>.
     * @param subname Refers to the name of the subject.
     * @return The <code>Subject</code> object that is mapped to <code>subname</code>.
     */
    public Subject getSubject(String subname) {
        return subjects.get(subname);
    }

    /**
     * Prints out the subject name and questions in a fixed format.
     * @param subname Holds the subject name.
     */
    public void printSubject(String subname) {
        Subject tsub = subjects.get(subname);
        tsub.print();
    }

    /**
     * This writes the questions in a subject and other related data to a JSON file encapsulated by a <code>FileWriter</code>.
     * @param subname Holds the subject name.
     * @param w Hold the <code>FileWriter</code> object.
     */
    void jsonifySubject(String subname, Writer w) {
        Subject tsub = subjects.get(subname);
        tsub.writeJSON(w);
    }

    /**
     * Gets the <code>className</code> of the current instance.
     * @return <code>className</code>
     */
    public String getClassName() {
        return className;
    }
}

