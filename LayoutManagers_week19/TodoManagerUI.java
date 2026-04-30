import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TodoManagerUI extends JFrame 
{

    public TodoManagerUI() {

        setTitle("My ToDo Manager");
        setSize(700,500);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /* =========================
           Header Panel
         */
        JLabel header = new JLabel("My ToDo Manager", JLabel.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 22));
        header.setOpaque(true);
        header.setBackground(new Color(200,210,230));
        header.setPreferredSize(new Dimension(700,50));

        add(header, BorderLayout.NORTH);

        /* =========================
           Sidebar Panel
        ========================= */
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new GridLayout(4,1,5,5));
        sidebar.setPreferredSize(new Dimension(120,0));

        JButton allBtn = new JButton("All Tasks");
        JButton completeBtn = new JButton("Completed");
        JButton pendingBtn = new JButton("Pending");
        JButton importantBtn = new JButton("Important");

        sidebar.add(allBtn);
        sidebar.add(completeBtn);
        sidebar.add(pendingBtn);
        sidebar.add(importantBtn);

        add(sidebar, BorderLayout.WEST);

        /* =========================
           Main Panel
        ========================= */
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Task input panel
        JPanel inputPanel = new JPanel();

        JLabel taskLabel = new JLabel("Task:");
        JTextField taskField = new JTextField(20);

        String priorities[] = {"High","Medium","Low"};
        JComboBox<String> priorityBox = new JComboBox<>(priorities);

        JButton addTask = new JButton("Add Task");
        addTask.setBackground(Color.GREEN);

        inputPanel.add(taskLabel);
        inputPanel.add(taskField);
        inputPanel.add(priorityBox);
        inputPanel.add(addTask);

        mainPanel.add(inputPanel, BorderLayout.NORTH);

        // Task display area
        JPanel taskArea = new JPanel();
        taskArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        mainPanel.add(taskArea, BorderLayout.CENTER);

        add(mainPanel, BorderLayout.CENTER);

        //Footer
        
        JLabel footer = new JLabel("Developed using Java Swing", JLabel.CENTER);
        footer.setPreferredSize(new Dimension(700,30));

        add(footer, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {

        TodoManagerUI ui = new TodoManagerUI();
        ui.setVisible(true);

    }
}