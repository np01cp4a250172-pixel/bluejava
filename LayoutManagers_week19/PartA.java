import javax.swing.*;
import java.awt.*;

public class PartA extends JFrame 
{

    public PartA() {
        // ----------------- JFrame setup -----------------
        setTitle("Admin Dashboard");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ----------------- NORTH: Header Panel -----------------
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.LIGHT_GRAY);
        headerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel headerLabel = new JLabel("<html><h1>Admin Dashboard</h1></html>");
        headerPanel.add(headerLabel);
        add(headerPanel, BorderLayout.NORTH);
        

      // Load the icon image
      ImageIcon icon = new ImageIcon("logo.png"); 
      JLabel iconLabel = new JLabel(icon);


     // Add icon and title to header panel
     headerPanel.add(iconLabel);
     headerPanel.add(headerLabel);

    // Add the header panel to the NORTH region
    add(headerPanel, BorderLayout.NORTH);

     // ----------------- SOUTH: Footer Panel -----------------
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(Color.GRAY);
        JLabel footerLabel = new JLabel("© 2026 islington college");
        footerPanel.add(footerLabel);
        add(footerPanel, BorderLayout.SOUTH);

    // ----------------- WEST: Sidebar Panel -----------------
        JPanel sidebarPanel = new JPanel();
        sidebarPanel.setBackground(new Color(220, 220, 220));
        sidebarPanel.setLayout(new GridLayout(5, 1, 5, 5));
        sidebarPanel.setPreferredSize(new Dimension(200, 0));
        
        JButton btnDashboard = new JButton("Dashboard");
        JButton btnStudents = new JButton("Students");
        JButton btnTeachers = new JButton("Teachers");
        JButton btnSettings = new JButton("Settings");
        JButton btnLogout = new JButton("Logout");

        sidebarPanel.add(btnDashboard);
        sidebarPanel.add(btnStudents);
        sidebarPanel.add(btnTeachers);
        sidebarPanel.add(btnSettings);
        sidebarPanel.add(btnLogout);

        add(sidebarPanel, BorderLayout.WEST);

    // ----------------- CENTER: Student Registration Form -----------------
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel lblName = new JLabel("Student Name:");
        JTextField txtName = new JTextField(20);

        JLabel lblAge = new JLabel("Age:");
        JTextField txtAge = new JTextField(5);

        JLabel lblEmail = new JLabel("Email:");
        JTextField txtEmail = new JTextField(20);

        JLabel lblCourse = new JLabel("Course:");
        JTextField txtCourse = new JTextField(15);

        JButton btnSubmit = new JButton("Register");

        gbc.gridx = 0;
        gbc.gridy = 0;
        centerPanel.add(lblName, gbc);
        gbc.gridx = 1;
        centerPanel.add(txtName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        centerPanel.add(lblAge, gbc);
        gbc.gridx = 1;
        centerPanel.add(txtAge, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        centerPanel.add(lblEmail, gbc);
        gbc.gridx = 1;
        centerPanel.add(txtEmail, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        centerPanel.add(lblCourse, gbc);
        gbc.gridx = 1;
        centerPanel.add(txtCourse, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        centerPanel.add(btnSubmit, gbc);

        add(centerPanel, BorderLayout.CENTER);

        // ----------------- Final setup -----------------
        setLocationRelativeTo(null); // center the frame
        setVisible(true);
    }

    // ----------------- main method -----------------
    public static void main(String[] args) {
        new PartA(); // create and show the GUI
    }
}