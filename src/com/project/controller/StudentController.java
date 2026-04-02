package com.project.controller;

import com.project.dao.StudentDAO;
import com.project.model.Student;

import java.util.List;

/**
 * Student Controller
 * Acts as the intermediary between the DAO layer and the UI (View) layer.
 * Follows the MVC pattern — the Controller receives user actions,
 * delegates data operations to the DAO, and returns results to the View.
 */
public class StudentController {

    private final StudentDAO studentDAO;

    // ── Constructor ──────────────────────────────────────────
    public StudentController() {
        this.studentDAO = new StudentDAO();
    }

    // ═════════════════════════════════════════════════════════
    //  ADD – Add a new student
    // ═════════════════════════════════════════════════════════
    /**
     * Adds a new student to the system.
     *
     * @param name   student's full name
     * @param uid    unique alphanumeric ID (e.g., "STU001")
     * @param course course name
     * @return true if the student was added successfully
     */
    public boolean addStudent(String name, String uid, String course) {
        // Validate inputs
        if (name == null || name.trim().isEmpty()) {
            System.out.println("[Controller] Error: Student name cannot be empty.");
            return false;
        }
        if (uid == null || uid.trim().isEmpty()) {
            System.out.println("[Controller] Error: Student UID cannot be empty.");
            return false;
        }
        if (course == null || course.trim().isEmpty()) {
            System.out.println("[Controller] Error: Course cannot be empty.");
            return false;
        }

        Student student = new Student(name.trim(), uid.trim(), course.trim());
        boolean success = studentDAO.insertStudent(student);

        if (success) {
            System.out.println("[Controller] Student added successfully: " + uid);
        } else {
            System.out.println("[Controller] Failed to add student. UID may already exist.");
        }

        return success;
    }

    // ═════════════════════════════════════════════════════════
    //  LIST – Get all students
    // ═════════════════════════════════════════════════════════
    /**
     * Retrieves all students from the database.
     *
     * @return List of all Student objects
     */
    public List<Student> getAllStudents() {
        List<Student> students = studentDAO.listAllStudents();
        System.out.println("[Controller] Retrieved " + students.size() + " student(s).");
        return students;
    }

    // ═════════════════════════════════════════════════════════
    //  FIND – Get a student by UID
    // ═════════════════════════════════════════════════════════
    /**
     * Finds a student by their unique ID.
     *
     * @param uid the student's unique identifier
     * @return Student object if found, null otherwise
     */
    public Student findStudent(String uid) {
        if (uid == null || uid.trim().isEmpty()) {
            System.out.println("[Controller] Error: UID cannot be empty.");
            return null;
        }

        Student student = studentDAO.getStudentByUid(uid.trim());

        if (student != null) {
            System.out.println("[Controller] Student found: " + student);
        } else {
            System.out.println("[Controller] No student found with UID: " + uid);
        }

        return student;
    }

    // ═════════════════════════════════════════════════════════
    //  UPDATE – Update student details
    // ═════════════════════════════════════════════════════════
    /**
     * Updates the name and course of an existing student.
     *
     * @param name   updated name
     * @param uid    the UID of the student to update (cannot be changed)
     * @param course updated course
     * @return true if the update was successful
     */
    public boolean updateStudent(String name, String uid, String course) {
        if (uid == null || uid.trim().isEmpty()) {
            System.out.println("[Controller] Error: UID cannot be empty for update.");
            return false;
        }
        if (name == null || name.trim().isEmpty()) {
            System.out.println("[Controller] Error: Student name cannot be empty.");
            return false;
        }
        if (course == null || course.trim().isEmpty()) {
            System.out.println("[Controller] Error: Course cannot be empty.");
            return false;
        }

        Student student = new Student(name.trim(), uid.trim(), course.trim());
        boolean success = studentDAO.updateStudent(student);

        if (success) {
            System.out.println("[Controller] Student updated successfully: " + uid);
        } else {
            System.out.println("[Controller] Failed to update. No student found with UID: " + uid);
        }

        return success;
    }

    // ═════════════════════════════════════════════════════════
    //  DELETE – Remove a student
    // ═════════════════════════════════════════════════════════
    /**
     * Deletes a student from the database by UID.
     *
     * @param uid the UID of the student to delete
     * @return true if the deletion was successful
     */
    public boolean deleteStudent(String uid) {
        if (uid == null || uid.trim().isEmpty()) {
            System.out.println("[Controller] Error: UID cannot be empty for deletion.");
            return false;
        }

        boolean success = studentDAO.deleteStudent(uid.trim());

        if (success) {
            System.out.println("[Controller] Student deleted successfully: " + uid);
        } else {
            System.out.println("[Controller] Failed to delete. No student found with UID: " + uid);
        }

        return success;
    }
}
