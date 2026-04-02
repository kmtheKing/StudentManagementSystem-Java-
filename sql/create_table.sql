-- =============================================================
-- Student Management System - Database Setup Script
-- =============================================================
-- Database: MySQL via XAMPP (localhost:3306)
-- DB Name:  Student_Management_System
-- User:     root | Password: (empty)
-- =============================================================

-- Step 1: Create the database if it doesn't exist
CREATE DATABASE IF NOT EXISTS Student_Management_System;

-- Step 2: Use the database
USE Student_Management_System;

-- Step 3: Create the students table
CREATE TABLE IF NOT EXISTS students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    uid VARCHAR(50) NOT NULL UNIQUE,
    course VARCHAR(100) NOT NULL
);

-- Step 4: (Optional) Insert sample data for testing
INSERT INTO students (name, uid, course) VALUES
    ('Kamal Kumar', 'STU001', 'Computer Science'),
    ('Anita Sharma', 'STU002', 'Information Technology'),
    ('Rahul Verma', 'STU003', 'Electronics');
