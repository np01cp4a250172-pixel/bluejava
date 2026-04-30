package week21.week21;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class EmployeeManagementTemplate extends JFrame {

    // UI Components
    private JLabel headerLabel;
    private JTextField empIdField, nameField, salaryField;
    private JRadioButton fullTime, partTime, contract;
    private JCheckBox healthInsurance, dentalInsurance, retirementPlan;
    private JComboBox<String> departmentBox;
    private JTextArea displayArea;
    private JLabel charCountLabel, mousePositionLabel;
    private JLabel statusLabel;

    // Employee data storage
    private ArrayList<String> employeeRecords = new ArrayList<>();

    public EmployeeManagementTemplate() {
        setTitle("Employee Management System");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 700);

        // ===== Header =====
        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT));
        headerLabel = new JLabel("<html><h1>Employee Management System</h1></html>");
        header.add(headerLabel);
        header.setBackground(new Color(70, 130, 200));
        headerLabel.setForeground(Color.WHITE);
        add(header, BorderLayout.NORTH);

        // TODO 1: MouseListener on header for hover effects
        header.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                headerLabel.setText("<html><h1>Employee Records View</h1></html>");
                header.setBackground(new Color(255, 140, 0)); // orange
            }

            @Override
            public void mouseExited(MouseEvent e) {
                headerLabel.setText("<html><h1>Employee Management System</h1></html>");
                header.setBackground(new Color(70, 130, 200));
            }
        });

        // ===== Sidebar =====
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setPreferredSize(new Dimension(200, 0));
        sidebar.setBackground(new Color(240, 240, 240));
        sidebar.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        JButton addBtn      = new JButton("Add Employee");
        JButton viewBtn     = new JButton("View Employees");
        JButton updateBtn   = new JButton("Update Employee");
        JButton deleteBtn   = new JButton("Delete Employee");
        JButton searchBtn   = new JButton("Search Employee");

        sidebar.add(Box.createRigidArea(new Dimension(0, 10)));
        sidebar.add(addBtn);
        sidebar.add(Box.createRigidArea(new Dimension(0, 10)));
        sidebar.add(viewBtn);
        sidebar.add(Box.createRigidArea(new Dimension(0, 10)));
        sidebar.add(updateBtn);
        sidebar.add(Box.createRigidArea(new Dimension(0, 10)));
        sidebar.add(deleteBtn);
        sidebar.add(Box.createRigidArea(new Dimension(0, 10)));
        sidebar.add(searchBtn);
        sidebar.add(Box.createVerticalGlue());

        add(sidebar, BorderLayout.WEST);

        // ===== Form Panel =====
        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        form.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        form.setBackground(Color.WHITE);

        // TODO 2: MouseMotionListener on form panel
        form.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                mousePositionLabel.setText(
                    "<html><h3>Mouse Position: (" + e.getX() + ", " + e.getY() + ")</h3></html>"
                );
            }
        });

        // Name Field
        nameField = new JTextField(20);
        nameField.setText("Enter full name");
        nameField.setForeground(Color.GRAY);
        JPanel namePanel = createFieldPanel("Full Name:", nameField);
        form.add(namePanel);
        form.add(Box.createRigidArea(new Dimension(0, 10)));

        // TODO 3: FocusListener on name field
        nameField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                try {
                    // Guard against null text (defensive programming)
                    String current = nameField.getText();
                    if (current == null || current.equals("Enter full name")) {
                        nameField.setText("");
                        nameField.setForeground(Color.BLACK);
                    }
                } catch (Exception ex) {
                    // Unexpected error — log and reset field safely
                    nameField.setText("");
                    nameField.setForeground(Color.BLACK);
                    updateStatus("Warning: name field error — " + ex.getMessage());
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    String text = nameField.getText();
                    if (text == null || text.trim().isEmpty()) {
                        nameField.setText("Enter full name");
                        nameField.setForeground(Color.GRAY);
                    }
                } catch (Exception ex) {
                    nameField.setText("Enter full name");
                    nameField.setForeground(Color.GRAY);
                    updateStatus("Warning: name field error — " + ex.getMessage());
                }
            }
        });

        // Salary Field
        salaryField = new JTextField(20);
        JPanel salaryPanel = createFieldPanel("Salary:", salaryField);
        form.add(salaryPanel);
        form.add(Box.createRigidArea(new Dimension(0, 10)));

        // ---- EXCEPTION HANDLING: KeyListener on salary to block non-numeric input ----
        salaryField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                // Allow digits, one decimal point, and control keys
                if (!Character.isDigit(c) && c != '.' && !Character.isISOControl(c)) {
                    e.consume(); // block the character
                    updateStatus("Salary: only numeric values are allowed.");
                } else if (c == '.' && salaryField.getText().contains(".")) {
                    e.consume(); // block a second decimal point
                    updateStatus("Salary: only one decimal point is allowed.");
                }
            }
        });

        // Employment Type
        JPanel employmentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        employmentPanel.add(new JLabel("Employment Type:"));
        fullTime = new JRadioButton("Full-Time");
        partTime = new JRadioButton("Part-Time");
        contract = new JRadioButton("Contract");
        ButtonGroup empGroup = new ButtonGroup();
        empGroup.add(fullTime);
        empGroup.add(partTime);
        empGroup.add(contract);
        employmentPanel.add(fullTime);
        employmentPanel.add(partTime);
        employmentPanel.add(contract);
        form.add(employmentPanel);
        form.add(Box.createRigidArea(new Dimension(0, 10)));

        // Benefits
        JPanel benefitsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        benefitsPanel.add(new JLabel("Benefits:"));
        healthInsurance  = new JCheckBox("Health Insurance");
        dentalInsurance  = new JCheckBox("Dental Insurance");
        retirementPlan   = new JCheckBox("Retirement Plan");
        benefitsPanel.add(healthInsurance);
        benefitsPanel.add(dentalInsurance);
        benefitsPanel.add(retirementPlan);
        form.add(benefitsPanel);
        form.add(Box.createRigidArea(new Dimension(0, 10)));

        // Department
        JPanel deptPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        deptPanel.add(new JLabel("Department:"));
        String[] departments = {"IT", "HR", "Finance", "Marketing", "Operations"};
        departmentBox = new JComboBox<>(departments);
        deptPanel.add(departmentBox);
        form.add(deptPanel);
        form.add(Box.createRigidArea(new Dimension(0, 10)));

        // Action Buttons
        JButton registerBtn  = new JButton("Register Employee");
        JButton clearFormBtn = new JButton("Clear Form");
        JButton clearAllBtn  = new JButton("Clear All Records");

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnPanel.add(registerBtn);
        btnPanel.add(clearFormBtn);
        btnPanel.add(clearAllBtn);
        form.add(btnPanel);

        add(form, BorderLayout.CENTER);

        // ===== Display Area =====
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setPreferredSize(new Dimension(400, 0));

        displayArea = new JTextArea(20, 30);
        displayArea.setEditable(false);
        displayArea.setWrapStyleWord(true);
        displayArea.setLineWrap(true);
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        displayArea.setBackground(new Color(252, 252, 252));
        rightPanel.add(new JScrollPane(displayArea), BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);

        // ===== Footer =====
        JPanel footer = new JPanel(new GridLayout(1, 2));
        footer.setBackground(new Color(60, 60, 60));
        footer.setPreferredSize(new Dimension(0, 60));

        charCountLabel     = new JLabel("<html><h3>Characters: 0</h3></html>");
        mousePositionLabel = new JLabel("<html><h3>Mouse Position: (0, 0)</h3></html>");
        statusLabel        = new JLabel("<html><h3>Status: Ready</h3></html>");

        charCountLabel.setForeground(Color.WHITE);
        mousePositionLabel.setForeground(Color.WHITE);
        statusLabel.setForeground(Color.WHITE);

        JPanel leftFooter = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftFooter.setBackground(new Color(60, 60, 60));
        leftFooter.add(charCountLabel);
        leftFooter.add(mousePositionLabel);

        JPanel rightFooter = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightFooter.setBackground(new Color(60, 60, 60));
        rightFooter.add(statusLabel);

        footer.add(leftFooter);
        footer.add(rightFooter);
        add(footer, BorderLayout.SOUTH);

        // ===== Button Action Listeners =====

        // Register Employee — main exception handling zone
        registerBtn.addActionListener(e -> registerEmployee());

        // Clear Form
        clearFormBtn.addActionListener(e -> clearForm());

        // Clear All Records
        clearAllBtn.addActionListener(e -> {
            try {
                int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to delete ALL records?",
                    "Confirm Clear All",
                    JOptionPane.YES_NO_OPTION
                );
                if (confirm == JOptionPane.YES_OPTION) {
                    employeeRecords.clear();
                    displayArea.setText("");
                    updateStatus("All records cleared.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                    "Error clearing records: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
                updateStatus("Error: " + ex.getMessage());
            }
        });

        // View Employees
        viewBtn.addActionListener(e -> {
            try {
                if (employeeRecords.isEmpty()) {
                    displayArea.setText("No employee records found.");
                    updateStatus("No records to display.");
                } else {
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < employeeRecords.size(); i++) {
                        sb.append("--- Record #").append(i + 1).append(" ---\n");
                        sb.append(employeeRecords.get(i)).append("\n\n");
                    }
                    displayArea.setText(sb.toString());
                    updateStatus("Displaying " + employeeRecords.size() + " record(s).");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                    "Error loading records: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
                updateStatus("Error: " + ex.getMessage());
            }
        });

        // Search Employee — exception handling for empty/invalid search input
        searchBtn.addActionListener(e -> {
            try {
                String query = JOptionPane.showInputDialog(this,
                    "Enter employee name to search:", "Search", JOptionPane.QUESTION_MESSAGE);

                if (query == null) return; // user cancelled

                if (query.trim().isEmpty()) {
                    throw new IllegalArgumentException("Search query cannot be empty.");
                }

                boolean found = false;
                StringBuilder results = new StringBuilder();
                for (String record : employeeRecords) {
                    if (record.toLowerCase().contains(query.toLowerCase())) {
                        results.append(record).append("\n\n");
                        found = true;
                    }
                }

                if (found) {
                    displayArea.setText(results.toString());
                    updateStatus("Search complete for: " + query);
                } else {
                    displayArea.setText("No records found for: " + query);
                    updateStatus("No match found.");
                }

            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this,
                    ex.getMessage(), "Validation Error", JOptionPane.WARNING_MESSAGE);
                updateStatus("Search failed: " + ex.getMessage());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                    "Search error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                updateStatus("Error: " + ex.getMessage());
            }
        });

        // Delete — exception handling for invalid ID input
        deleteBtn.addActionListener(e -> {
            try {
                String input = JOptionPane.showInputDialog(this,
                    "Enter record number to delete (1-based):", "Delete", JOptionPane.QUESTION_MESSAGE);

                if (input == null) return; // user cancelled

                if (input.trim().isEmpty()) {
                    throw new IllegalArgumentException("Record number cannot be empty.");
                }

                // ---- EXCEPTION HANDLING: NumberFormatException on ID parse ----
                int index;
                try {
                    index = Integer.parseInt(input.trim()) - 1;
                } catch (NumberFormatException nfe) {
                    throw new IllegalArgumentException("Invalid record number: \"" + input + "\". Please enter a whole number.");
                }

                if (index < 0 || index >= employeeRecords.size()) {
                    throw new IndexOutOfBoundsException(
                        "Record #" + (index + 1) + " does not exist. Valid range: 1–" + employeeRecords.size()
                    );
                }

                employeeRecords.remove(index);
                displayArea.setText("Record #" + (index + 1) + " deleted successfully.");
                updateStatus("Record deleted.");

            } catch (IllegalArgumentException | IndexOutOfBoundsException ex) {
                JOptionPane.showMessageDialog(this,
                    ex.getMessage(), "Validation Error", JOptionPane.WARNING_MESSAGE);
                updateStatus("Delete failed: " + ex.getMessage());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                    "Unexpected error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                updateStatus("Error: " + ex.getMessage());
            }
        });

        // Update Employee — exception handling for missing/invalid input
        updateBtn.addActionListener(e -> {
            try {
                if (employeeRecords.isEmpty()) {
                    throw new IllegalStateException("No records exist to update.");
                }
                String input = JOptionPane.showInputDialog(this,
                    "Enter record number to update (1-based):", "Update", JOptionPane.QUESTION_MESSAGE);

                if (input == null) return;

                int index;
                try {
                    index = Integer.parseInt(input.trim()) - 1;
                } catch (NumberFormatException nfe) {
                    throw new IllegalArgumentException("Invalid record number: \"" + input + "\". Please enter a whole number.");
                }

                if (index < 0 || index >= employeeRecords.size()) {
                    throw new IndexOutOfBoundsException(
                        "Record #" + (index + 1) + " does not exist. Valid range: 1–" + employeeRecords.size()
                    );
                }

                // Pre-fill form with existing data then ask user to re-register
                displayArea.setText("Record #" + (index + 1) + " selected for update.\n\n"
                    + employeeRecords.get(index)
                    + "\n\nEdit the form and click 'Register Employee' to overwrite.");
                employeeRecords.remove(index);
                updateStatus("Record #" + (index + 1) + " loaded for update.");

            } catch (IllegalArgumentException | IllegalStateException | IndexOutOfBoundsException ex) {
                JOptionPane.showMessageDialog(this,
                    ex.getMessage(), "Validation Error", JOptionPane.WARNING_MESSAGE);
                updateStatus("Update failed: " + ex.getMessage());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                    "Unexpected error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                updateStatus("Error: " + ex.getMessage());
            }
        });

        // Character count listener on name field
        nameField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            private void update() {
                try {
                    int len = nameField.getText().length();
                    charCountLabel.setText("<html><h3>Characters: " + len + "</h3></html>");
                } catch (Exception ex) {
                    charCountLabel.setText("<html><h3>Characters: ?</h3></html>");
                }
            }
            public void insertUpdate(javax.swing.event.DocumentEvent e) { update(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { update(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { update(); }
        });

        setVisible(true);
    }

    // ===== Register Employee — central validation with exception handling =====
    private void registerEmployee() {
        try {
            // --- Validate Name ---
            String name = nameField.getText().trim();
            if (name.isEmpty() || name.equals("Enter full name")) {
                throw new IllegalArgumentException("Full name is required.");
            }
            if (!name.matches("[a-zA-Z\\s'-]+")) {
                throw new IllegalArgumentException(
                    "Name can only contain letters, spaces, hyphens, or apostrophes.");
            }

            // --- Validate Salary ---
            String salaryText = salaryField.getText().trim();
            if (salaryText.isEmpty()) {
                throw new IllegalArgumentException("Salary is required.");
            }

            double salary;
            try {
                salary = Double.parseDouble(salaryText);
            } catch (NumberFormatException e) {
                
                // ---- EXCEPTION HANDLING: non-numeric salary ----
                
                throw new IllegalArgumentException("Salary must be a valid number (e.g. 50000 or 45000.50).\n"
                    + "You entered: \"" + salaryText + "\"");
            }

            if (salary < 0) {
                throw new IllegalArgumentException("Salary cannot be negative.");
            }
            if (salary > 10_000_000) {
                throw new IllegalArgumentException("Salary seems unrealistically high. Please verify.");
            }

            // --- Validate Employment Type ---
            String empType;
            if (fullTime.isSelected())      empType = "Full-Time";
            else if (partTime.isSelected()) empType = "Part-Time";
            else if (contract.isSelected()) empType = "Contract";
            else {
                // ---- EXCEPTION HANDLING: no employment type selected ----
                throw new IllegalStateException("Please select an employment type.");
            }

            // --- Validate Department ---
            Object selectedDept = departmentBox.getSelectedItem();
            if (selectedDept == null) {
                throw new IllegalStateException("Please select a department.");
            }
            String department = selectedDept.toString();

            // --- Build Benefits String ---
            StringBuilder benefits = new StringBuilder();
            if (healthInsurance.isSelected())  benefits.append("Health Insurance, ");
            if (dentalInsurance.isSelected())  benefits.append("Dental Insurance, ");
            if (retirementPlan.isSelected())   benefits.append("Retirement Plan, ");
            String benefitStr = benefits.length() > 0
                ? benefits.substring(0, benefits.length() - 2)
                : "None";

            // --- Build Record ---
            String record = String.format(
                "Name       : %s%n" +
                "Salary     : $%,.2f%n" +
                "Type       : %s%n" +
                "Department : %s%n" +
                "Benefits   : %s",
                name, salary, empType, department, benefitStr
            );

            employeeRecords.add(record);
            displayArea.setText("Employee registered successfully!\n\n" + record);
            updateStatus("Registered: " + name);
            clearForm();

        } catch (IllegalArgumentException e) {
            // ---- Validation errors — show friendly warning ----
            JOptionPane.showMessageDialog(this,
                e.getMessage(), "Validation Error", JOptionPane.WARNING_MESSAGE);
            updateStatus("Registration failed: " + e.getMessage());

        } catch (IllegalStateException e) {
            // ---- State errors (no radio selected, etc.) ----
            JOptionPane.showMessageDialog(this,
                e.getMessage(), "Incomplete Form", JOptionPane.WARNING_MESSAGE);
            updateStatus("Registration failed: " + e.getMessage());

        } catch (Exception e) {
            // ---- Catch-all for unexpected errors ----
            JOptionPane.showMessageDialog(this,
                "An unexpected error occurred:\n" + e.getMessage(),
                "Unexpected Error", JOptionPane.ERROR_MESSAGE);
            updateStatus("Unexpected error: " + e.getMessage());
        }
    }

    // ===== Clear Form =====
    private void clearForm() {
        try {
            nameField.setText("Enter full name");
            nameField.setForeground(Color.GRAY);
            salaryField.setText("");
            fullTime.setSelected(false);
            partTime.setSelected(false);
            contract.setSelected(false);
            healthInsurance.setSelected(false);
            dentalInsurance.setSelected(false);
            retirementPlan.setSelected(false);
            if (departmentBox.getItemCount() > 0) {
                departmentBox.setSelectedIndex(0);
            }
            charCountLabel.setText("<html><h3>Characters: 0</h3></html>");
            updateStatus("Form cleared.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error clearing form: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            updateStatus("Error clearing form.");
        }
    }

    // ===== Status Label Helper =====
    private void updateStatus(String message) {
        try {
            statusLabel.setText("<html><h3>Status: " + message + "</h3></html>");
        } catch (Exception e) {
            // Status update itself should never fail silently
            System.err.println("Status update failed: " + e.getMessage());
        }
    }

    // ===== Field Panel Helper =====
    private JPanel createFieldPanel(String label, JTextField field) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel jlabel = new JLabel(label);
        jlabel.setPreferredSize(new Dimension(120, 25));
        panel.add(jlabel);
        field.setPreferredSize(new Dimension(300, 30));
        panel.add(field);
        panel.setBackground(Color.WHITE);
        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EmployeeManagementTemplate());
    }
}