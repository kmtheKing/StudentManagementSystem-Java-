package com.project.model;

/**
 * Student Model Class
 * Represents a student entity in the Student Management System.
 * Maps directly to the 'students' table in the database.
 */
public class Student {

    private String name;
    private String uid;
    private String course;

    // ── Default Constructor ──────────────────────────────────
    public Student() {
    }

    // ── Parameterized Constructor ────────────────────────────
    public Student(String name, String uid, String course) {
        this.name = name;
        this.uid = uid;
        this.course = course;
    }

    // ── Getters & Setters ────────────────────────────────────

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    // ── toString ─────────────────────────────────────────────
    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", uid='" + uid + '\'' +
                ", course='" + course + '\'' +
                '}';
    }
}
