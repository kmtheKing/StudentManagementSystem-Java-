package com.project.dao;

import com.project.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Student Data Access Object (DAO)
 * Handles all CRUD operations for the 'students' table.
 * Uses PreparedStatement for all queries to prevent SQL injection.
 */
public class StudentDAO {

    // ── SQL Queries ──────────────────────────────────────────
    private static final String INSERT_SQL = "INSERT INTO students (name, uid, course) VALUES (?, ?, ?)";
    private static final String SELECT_ALL_SQL = "SELECT * FROM students";
    private static final String UPDATE_SQL = "UPDATE students SET name = ?, course = ? WHERE uid = ?";
    private static final String DELETE_SQL = "DELETE FROM students WHERE uid = ?";
    private static final String SELECT_BY_UID_SQL = "SELECT * FROM students WHERE uid = ?";

    // ═════════════════════════════════════════════════════════
    //  CREATE – Insert a new student
    // ═════════════════════════════════════════════════════════
    /**
     * Inserts a new student record into the database.
     *
     * @param student the Student object to insert
     * @return true if the insertion was successful, false otherwise
     */
    public boolean insertStudent(Student student) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(INSERT_SQL)) {

            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getUid());
            pstmt.setString(3, student.getCourse());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error inserting student: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // ═════════════════════════════════════════════════════════
    //  READ – List all students
    // ═════════════════════════════════════════════════════════
    /**
     * Retrieves all student records from the database.
     *
     * @return List of Student objects
     */
    public List<Student> listAllStudents() {
        List<Student> students = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL_SQL);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Student student = new Student();
                student.setName(rs.getString("name"));
                student.setUid(rs.getString("uid"));
                student.setCourse(rs.getString("course"));
                students.add(student);
            }

        } catch (SQLException e) {
            System.err.println("Error listing students: " + e.getMessage());
            e.printStackTrace();
        }

        return students;
    }

    // ═════════════════════════════════════════════════════════
    //  READ – Get a student by UID
    // ═════════════════════════════════════════════════════════
    /**
     * Retrieves a single student record by UID.
     *
     * @param uid the unique identifier of the student
     * @return Student object if found, null otherwise
     */
    public Student getStudentByUid(String uid) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SELECT_BY_UID_SQL)) {

            pstmt.setString(1, uid);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Student student = new Student();
                    student.setName(rs.getString("name"));
                    student.setUid(rs.getString("uid"));
                    student.setCourse(rs.getString("course"));
                    return student;
                }
            }

        } catch (SQLException e) {
            System.err.println("Error fetching student: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    // ═════════════════════════════════════════════════════════
    //  UPDATE – Update an existing student
    // ═════════════════════════════════════════════════════════
    /**
     * Updates the name and course of an existing student identified by UID.
     *
     * @param student the Student object with updated values
     * @return true if the update was successful, false otherwise
     */
    public boolean updateStudent(Student student) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(UPDATE_SQL)) {

            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getCourse());
            pstmt.setString(3, student.getUid());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error updating student: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // ═════════════════════════════════════════════════════════
    //  DELETE – Delete a student by UID
    // ═════════════════════════════════════════════════════════
    /**
     * Deletes a student record from the database by UID.
     *
     * @param uid the unique identifier of the student to delete
     * @return true if the deletion was successful, false otherwise
     */
    public boolean deleteStudent(String uid) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(DELETE_SQL)) {

            pstmt.setString(1, uid);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error deleting student: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
