package com.project;

import com.project.view.StudentView;

import javax.swing.*;

/**
 * Main Application Entry Point
 * Launches the Student Management System GUI.
 */
public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StudentView view = new StudentView();
            view.setVisible(true);
        });
    }
}
