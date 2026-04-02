package com.project.view;

import com.project.controller.StudentController;
import com.project.model.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * StudentView (GUI)
 * Provides a graphical user interface for the Student Management System.
 * Built using Java Swing with a modern, responsive layout.
 */
public class StudentView extends JFrame {

    private final StudentController controller;
    private JTable studentTable;
    private DefaultTableModel tableModel;

    private JTextField txtName, txtUid, txtCourse;
    private JButton btnAdd, btnUpdate, btnDelete, btnClear;

    public StudentView() {
        this.controller = new StudentController();
        initializeUI();
        refreshTable();
    }

    private void initializeUI() {
        // --- 1. Basic Window Setup ---
        setTitle("Student Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 500);
        setLocationRelativeTo(null); // Center on screen

        // Use Nimbus Look and Feel for a modern look
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // Default to system L&F if Nimbus fails
        }

        // --- 2. Main Layout (Three Panels) ---
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // --- A. Left Side: Form Panel ---
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Student Information"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Name
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        txtName = new JTextField(15);
        formPanel.add(txtName, gbc);

        // UID
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("UID:"), gbc);
        gbc.gridx = 1;
        txtUid = new JTextField(15);
        formPanel.add(txtUid, gbc);

        // Course
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Course:"), gbc);
        gbc.gridx = 1;
        txtCourse = new JTextField(15);
        formPanel.add(txtCourse, gbc);

        // --- B. Right Side: Table Panel ---
        String[] columnNames = {"UID", "Name", "Course"};
        tableModel = new DefaultTableModel(columnNames, 0);
        studentTable = new JTable(tableModel);
        studentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane tableScrollPane = new JScrollPane(studentTable);
        
        // Add row selection listener to populate fields
        studentTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = studentTable.getSelectedRow();
                if (row != -1) {
                    txtUid.setText(tableModel.getValueAt(row, 0).toString());
                    txtName.setText(tableModel.getValueAt(row, 1).toString());
                    txtCourse.setText(tableModel.getValueAt(row, 2).toString());
                    txtUid.setEditable(false); // UID cannot be edited during update
                }
            }
        });

        // --- C. Bottom: Button Panel ---
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        btnAdd = new JButton("Add Student");
        btnUpdate = new JButton("Update Student");
        btnDelete = new JButton("Delete Student");
        btnClear = new JButton("Clear Form");

        // Styling Buttons
        btnAdd.setBackground(new Color(40, 167, 69)); // Success Green
        btnDelete.setBackground(new Color(220, 53, 69)); // Danger Red
        
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnClear);

        // Add to main layout
        mainPanel.add(formPanel, BorderLayout.WEST);
        mainPanel.add(tableScrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        // --- 3. Event Listeners ---
        btnAdd.addActionListener(e -> addStudent());
        btnUpdate.addActionListener(e -> updateStudent());
        btnDelete.addActionListener(e -> deleteStudent());
        btnClear.addActionListener(e -> clearForm());
    }

    // ═════════════════════════════════════════════════════════
    //  Actions
    // ═════════════════════════════════════════════════════════

    private void addStudent() {
        String name = txtName.getText();
        String uid = txtUid.getText();
        String course = txtCourse.getText();

        if (controller.addStudent(name, uid, course)) {
            JOptionPane.showMessageDialog(this, "Student added successfully!");
            clearForm();
            refreshTable();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to add student. Check unique UID.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateStudent() {
        String name = txtName.getText();
        String uid = txtUid.getText();
        String course = txtCourse.getText();

        if (controller.updateStudent(name, uid, course)) {
            JOptionPane.showMessageDialog(this, "Student updated successfully!");
            clearForm();
            refreshTable();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to update. UID not found.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteStudent() {
        String uid = txtUid.getText();
        if (uid.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a student from the table.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete student: " + uid + "?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            if (controller.deleteStudent(uid)) {
                JOptionPane.showMessageDialog(this, "Student deleted successfully!");
                clearForm();
                refreshTable();
            } else {
                JOptionPane.showMessageDialog(this, "Deletion failed.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void clearForm() {
        txtName.setText("");
        txtUid.setText("");
        txtCourse.setText("");
        txtUid.setEditable(true);
        studentTable.clearSelection();
    }

    private void refreshTable() {
        tableModel.setRowCount(0); // Clear current rows
        List<Student> students = controller.getAllStudents();
        for (Student s : students) {
            tableModel.addRow(new Object[]{s.getUid(), s.getName(), s.getCourse()});
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new StudentView().setVisible(true);
        });
    }
}
