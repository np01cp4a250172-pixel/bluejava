import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class TodoApp implements ActionListener {

    private JFrame frame;
    private JTextField taskField;
    private JButton addBtn, doneBtn, clearBtn;
    private JButton allBtn, completedBtn, pendingBtn, importantBtn;
    private JPanel taskPanel;

    private ArrayList<JCheckBox> checkBoxes;
    private Color primary = new Color(30, 144, 255);     // Blue
    private Color success = new Color(46, 204, 113);     // Green
    private Color danger = new Color(231, 76, 60);       // Red
    private Color warning = new Color(241, 196, 15);     // Yellow
    private Color dark = new Color(44, 62, 80);          // Dark background
    private Color light = new Color(236, 240, 241);      // Light background

    public TodoApp() {
        checkBoxes = new ArrayList<>();

        // Frame
        frame = new JFrame("My todo Manager");
        frame.setSize(500, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(light);

        // Top Panel
        JPanel topPanel = new JPanel(new BorderLayout());
        taskField = new JTextField();
        addBtn = new JButton("Add Task");

        topPanel.add(taskField, BorderLayout.CENTER);
        topPanel.add(addBtn, BorderLayout.EAST);
        topPanel.setBackground(light);
        taskField.setFont(new Font("Arial", Font.PLAIN, 14));

        // Task Panel
        taskPanel = new JPanel();
        taskPanel.setLayout(new BoxLayout(taskPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(taskPanel);
        taskPanel.setBackground(light);

        // Bottom Panel
        JPanel bottomPanel = new JPanel();
        doneBtn = new JButton("Done All");
        clearBtn = new JButton("Clear All");

        bottomPanel.add(doneBtn);
        bottomPanel.add(clearBtn);

        // Side Panel
        JPanel sidePanel = new JPanel(new GridLayout(4,1));
        allBtn = new JButton("All Tasks");
        completedBtn = new JButton("Completed");
        pendingBtn = new JButton("Pending");
        importantBtn = new JButton("Important");

        sidePanel.add(allBtn);
        sidePanel.add(completedBtn);
        sidePanel.add(pendingBtn);
        sidePanel.add(importantBtn);
        sidePanel.setBackground(dark);
        
        // Button Colors
        addBtn.setBackground(primary);
        addBtn.setForeground(Color.WHITE);

        doneBtn.setBackground(success);
        doneBtn.setForeground(Color.WHITE);

        clearBtn.setBackground(danger);
        clearBtn.setForeground(Color.WHITE);

        importantBtn.setBackground(warning);
        importantBtn.setForeground(Color.BLACK);

        allBtn.setBackground(dark);
        allBtn.setForeground(Color.WHITE);

        completedBtn.setBackground(dark);
        completedBtn.setForeground(Color.WHITE);

        pendingBtn.setBackground(dark);
        pendingBtn.setForeground(Color.WHITE);
        JButton[] allButtons = {addBtn, doneBtn, clearBtn, allBtn, completedBtn, pendingBtn, importantBtn};

        for (JButton btn : allButtons) {
         btn.setFocusPainted(false);
         btn.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        }
        // Fix visibility
        addBtn.setFocusPainted(false);
        doneBtn.setFocusPainted(false);
        clearBtn.setFocusPainted(false);
        importantBtn.setFocusPainted(false);

        // Add components
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);
        frame.add(sidePanel, BorderLayout.WEST);

        // Register ActionListener
        addBtn.addActionListener(this);
        doneBtn.addActionListener(this);
        clearBtn.addActionListener(this);
        allBtn.addActionListener(this);
        completedBtn.addActionListener(this);
        pendingBtn.addActionListener(this);
        importantBtn.addActionListener(this);

        frame.setVisible(true);
    }

     // ActionListener Method
    
    public void actionPerformed(ActionEvent e) {

        // Question 1:PART A: Add Task
        if (e.getSource() == addBtn) {
            String task = taskField.getText().trim();

            if (task.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Task cannot be empty!");
            } else {
                addTask(task);
                taskField.setText("");
            }
        }

        // PART B: Done All
        else if (e.getSource() == doneBtn) {
            for (JCheckBox cb : checkBoxes) {
                cb.setSelected(true);
                cb.setText("<html><strike>" + removeHtml(cb.getText()) + "</strike></html>");
            }
            refreshUI();
        }

        // PART C: Clear All
        else if (e.getSource() == clearBtn) {
            int confirm = JOptionPane.showConfirmDialog(
                    frame,
                    "Are you sure you want to delete all tasks?",
                    "Confirm",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                taskPanel.removeAll();
                checkBoxes.clear();
                refreshUI();
            }
        }

        // PART D: All Tasks
        else if (e.getSource() == allBtn) {
            taskPanel.removeAll();
            for (JCheckBox cb : checkBoxes) {
                taskPanel.add(cb);
            }
            refreshUI();
        }

        // Completed
        else if (e.getSource() == completedBtn) {
            taskPanel.removeAll();
            for (JCheckBox cb : checkBoxes) {
                if (cb.isSelected()) {
                    taskPanel.add(cb);
                }
            }
            refreshUI();
        }

        // Pending
        else if (e.getSource() == pendingBtn) {
            taskPanel.removeAll();
            for (JCheckBox cb : checkBoxes) {
                if (!cb.isSelected()) {
                    taskPanel.add(cb);
                }
            }
            refreshUI();
        }

        // Important
        else if (e.getSource() == importantBtn) {
            taskPanel.removeAll();
            for (JCheckBox cb : checkBoxes) {
                if (removeHtml(cb.getText()).contains("High")) {
                    taskPanel.add(cb);
                }
            }
            refreshUI();
        }
    }

    // Add Task
    private void addTask(String task) {

    JPanel taskRow = new JPanel(new FlowLayout(FlowLayout.LEFT));

    JCheckBox cb = new JCheckBox(task);
    JButton deleteBtn = new JButton("Delete");

    // Optional color
     cb.setFont(new Font("Arial", Font.PLAIN, 14));
     cb.setBackground(light);
     cb.setForeground(dark);

    // Delete button action
    deleteBtn.addActionListener(e ->
    {
        taskPanel.remove(taskRow);
        checkBoxes.remove(cb);
        refreshUI();
      });
      
    deleteBtn.setBackground(danger);
    deleteBtn.setForeground(Color.WHITE);
    deleteBtn.setFocusPainted(false);
    taskRow.add(cb);
    taskRow.add(deleteBtn);

    checkBoxes.add(cb);
    taskPanel.add(taskRow);

    refreshUI();
   }

    // Refresh UI
    private void refreshUI() {
        taskPanel.revalidate();
        taskPanel.repaint();
    }

    // Remove HTML
    private String removeHtml(String text) {
        return text.replaceAll("<[^>]*>", "");
    }

    public static void main(String[] args) {
        new TodoApp();
    }
}