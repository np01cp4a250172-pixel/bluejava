import javax.swing.*;
import java.awt.*;

public class StudentDashboardUI extends JFrame {

    public StudentDashboardUI() {
        /* =========================
           Part A: Frame Setup
        ========================= */
        setTitle("Admin Dashboard");
        setSize(900,600);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        /* =========================
           Part B: Header Panel
        ========================= */
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        headerPanel.setBackground(Color.LIGHT_GRAY);

        ImageIcon logo = new ImageIcon("C:\\Users\\Suman Kapri\\Downloads\\java.png");
        JLabel logoLabel = new JLabel(logo);

        JLabel titleLabel = new JLabel("<html><h1>Student Admin Panel</h1></html>");

        headerPanel.add(logoLabel);
        headerPanel.add(titleLabel);

        add(headerPanel, BorderLayout.NORTH);


        /* =========================
           Part C: Footer Panel
        ========================= */
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        footerPanel.setBackground(Color.LIGHT_GRAY);

        JLabel footerLabel = new JLabel("© 2025 Student Management System");

        footerPanel.add(footerLabel);

        add(footerPanel, BorderLayout.SOUTH);

        /* =========================
           Part D: Sidebar Panel
        ========================= */
        JPanel sidebarPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,10,20));
        sidebarPanel.setPreferredSize(new Dimension(180,0));
        sidebarPanel.setBackground(new Color(220,220,220));

        JButton addBtn = new JButton("Add Student");
        JButton viewBtn = new JButton("View Student");
        JButton updateBtn = new JButton("Update Student");
        JButton deleteBtn = new JButton("Delete Student");

        Dimension btnSize = new Dimension(150,30);

        addBtn.setPreferredSize(btnSize);
        viewBtn.setPreferredSize(btnSize);
        updateBtn.setPreferredSize(btnSize);
        deleteBtn.setPreferredSize(btnSize);

        sidebarPanel.add(addBtn);
        sidebarPanel.add(viewBtn);
        sidebarPanel.add(updateBtn);
        sidebarPanel.add(deleteBtn);

        add(sidebarPanel, BorderLayout.WEST);

        /* =========================
           Part E: Registration Form
        ========================= */
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));

        formPanel.add(new JLabel("Name"));
        formPanel.add(new JTextField());

        formPanel.add(new JLabel("Age"));
        formPanel.add(new JTextField());

        formPanel.add(new JLabel("Gender"));

        JRadioButton male = new JRadioButton("Male");
        JRadioButton female = new JRadioButton("Female");

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(male);
        genderGroup.add(female);

        formPanel.add(male);
        formPanel.add(female);

        formPanel.add(new JLabel("Skills"));

        formPanel.add(new JCheckBox("Java"));
        formPanel.add(new JCheckBox("Python"));
        formPanel.add(new JCheckBox("C++"));

        formPanel.add(new JLabel("Course"));

        String courses[] = {"B.Tech","M.Tech","B.Sc","M.Sc"};
        JComboBox<String> courseBox = new JComboBox<>(courses);

        formPanel.add(courseBox);

        JButton registerBtn = new JButton("Register");
        formPanel.add(registerBtn);

        add(formPanel, BorderLayout.CENTER);
    }


    public static void main(String[] args) {

        StudentDashboardUI ui = new StudentDashboardUI();
        ui.setVisible(true);

    }
}