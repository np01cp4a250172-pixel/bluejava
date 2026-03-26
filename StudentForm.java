import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;


public class StudentForm extends JFrame implements ActionListener
{
    private JButton addBtn, editBtn, deleteBtn, viewBtn;
    
    private String[] btnList = {"Add", "Edit", "Delete", "View"};

    private JPanel titlePanel, sidebarPanel, formPanel, resultPanel;
    private JPanel row1, row2, row3, row4, row5;

    private JLabel label, nameLbl, courseLabel, genderLabel, hobbiesLabel;

    private JTextField txtField;
    private JComboBox<String> courseBox;
    

    private ButtonGroup genderGrp;
    private JRadioButton male, female, others;
    

    private JCheckBox readBox, travelBox, sportsBox;

    private JButton submitBtn, clearFormBtn, clearDisplayBtn;

    private JTextArea displayArea;
    
    private ArrayList<Student> students= new ArrayList<>();
    


    public StudentForm()
    {
        setTitle("Student Registration");
        setSize(600,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // TITLE PANEL
        titlePanel = new JPanel();
        label = new JLabel("Student Registration Details");
        label.setFont(new Font("Arial", Font.BOLD, 18));
        label.setForeground(Color.RED);
        titlePanel.add(label);

        add(titlePanel, BorderLayout.NORTH);


        // SIDEBAR PANEL
        sidebarPanel = new JPanel();
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));
        sidebarPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        sidebarPanel.add(Box.createVerticalGlue());

        addBtn = new JButton("Add");
        editBtn = new JButton("Edit");
        deleteBtn = new JButton("Delete");
        viewBtn = new JButton("View");

      JButton[] buttons = {addBtn, editBtn, deleteBtn, viewBtn};

      for(JButton button : buttons)
      {
       button.setMaximumSize(new Dimension(100,40));
       button.addActionListener(this); // IMPORTANT
       sidebarPanel.add(button);
       sidebarPanel.add(Box.createVerticalStrut(10));
      }

        sidebarPanel.add(Box.createVerticalGlue());

        add(sidebarPanel, BorderLayout.WEST);


        // FORM PANEL
        formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));

        // ROW 1
        row1 = new JPanel();
        row1.setLayout(new BoxLayout(row1, BoxLayout.X_AXIS));
        row1.setAlignmentX(Component.LEFT_ALIGNMENT);

        nameLbl = new JLabel("Name:");
        txtField = new JTextField(15);

        row1.add(nameLbl);
        row1.add(Box.createHorizontalStrut(10));
        row1.add(txtField);


        // ROW 2
        row2 = new JPanel();
        row2.setLayout(new BoxLayout(row2, BoxLayout.X_AXIS));
        row2.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        courseLabel = new JLabel("Course:");

        String[] courses={"Computing","AI", "Multimedia", "Networking"};
        courseBox = new JComboBox<>(courses);
        row2.add(courseLabel);
        row2.add(Box.createHorizontalStrut(10));
        row2.add(courseBox);


        // ROW 3
        row3 = new JPanel();
        row3.setLayout(new BoxLayout(row3, BoxLayout.X_AXIS));
        row3.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        genderLabel = new JLabel("Gender:");

        male = new JRadioButton("Male");
        female = new JRadioButton("Female");
        others = new JRadioButton("Others");

        genderGrp = new ButtonGroup();
        genderGrp.add(male);
        genderGrp.add(female);
        genderGrp.add(others);

        row3.add(genderLabel);
        row3.add(Box.createHorizontalStrut(10));
        row3.add(male);
        row3.add(female);
        row3.add(others);
        


        // ROW 4
        row4 = new JPanel();
        row4.setLayout(new BoxLayout(row4, BoxLayout.X_AXIS));
        row4.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        hobbiesLabel = new JLabel("Hobbies:");

        readBox = new JCheckBox("Reading");
        travelBox = new JCheckBox("Travelling");
        sportsBox = new JCheckBox("Sports");

        row4.add(hobbiesLabel);
        row4.add(Box.createHorizontalStrut(10));
        row4.add(readBox);
        row4.add(travelBox);
        row4.add(sportsBox);


        // ROW 5 BUTTONS
        row5 = new JPanel();
        row5.setLayout(new BoxLayout(row5, BoxLayout.X_AXIS));
        row5.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        submitBtn = new JButton("Submit");
        submitBtn.setForeground(Color.RED);
        submitBtn.setBackground(new Color(255,200,0));
        
        submitBtn.addActionListener(this);
        
        clearFormBtn = new JButton("Clear Form");
        clearFormBtn.setForeground(Color.WHITE);
        clearFormBtn.setBackground(Color.RED);
        
        clearDisplayBtn = new JButton("Clear Display");
        clearDisplayBtn.setForeground(Color.BLACK);
        clearDisplayBtn.setBackground(Color.green);
        
        
        row5.add(submitBtn);
        row5.add(Box.createHorizontalStrut(10));
        row5.add(clearFormBtn);
        row5.add(Box.createHorizontalStrut(10));
        row5.add(clearDisplayBtn);


        // ADD ROWS
        formPanel.add(row1);
        formPanel.add(Box.createVerticalStrut(15));
        formPanel.add(row2);
        formPanel.add(Box.createVerticalStrut(15));
        formPanel.add(row3);
        formPanel.add(Box.createVerticalStrut(15));
        formPanel.add(row4);
        formPanel.add(Box.createVerticalStrut(15));
        formPanel.add(row5);

        add(formPanel, BorderLayout.CENTER);


        // RESULT PANEL
        resultPanel = new JPanel();
        resultPanel.setLayout(new BorderLayout());
        resultPanel.setBorder(BorderFactory.createTitledBorder("Student Details"));

        displayArea = new JTextArea(6,40);
        displayArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(displayArea);

        resultPanel.add(scrollPane, BorderLayout.CENTER);

        add(resultPanel, BorderLayout.SOUTH);
        
        pack();
    }


    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() ->
        {
            new StudentForm().setVisible(true);
        });
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
      if(e.getSource() == submitBtn)
     {
        handleSubmit();
     }
    else if(e.getSource() == addBtn)
    {
        clearForm();
    }
    else if(e.getSource() == viewBtn)
    {
        viewStudents();
    }
    
    else if(e.getSource() == editBtn)
    {
        JOptionPane.showMessageDialog(this, "Edit feature coming soon");
    }
   }  
    public void viewStudents()
   {
     displayArea.setText("");

     for(Student s : students)
     {
        displayArea.append(s.toString() + "\n");
     }
   }

   
     public void clearForm()
     {
        txtField.setText("");
        courseBox.setSelectedIndex(0);
        // readbox, travelBox, sportsBox;
       readBox.setSelected(false);
       travelBox.setSelected(false);
       sportsBox.setSelected(false);
       
       submitBtn.setEnabled(true);
        
     }
    
     public void handleSubmit()
    {
        ArrayList<String> hobbiesList = new ArrayList<>();
       String name =txtField.getText().trim();
       String gender = "";
       if(male.isSelected()) gender = "Male";
       else if(female.isSelected()) gender = "Female";
       else if(others.isSelected()) gender = "Others";
       String course = courseBox.getSelectedItem().toString();
       
       // readbox, travelBox, sportsBox;
       
       if(readBox.isSelected())
       {
           hobbiesList.add(readBox.getText());   
        }
       
        if(travelBox.isSelected())
        {
           hobbiesList.add(travelBox.getText()); 
        }
       
       if(sportsBox.isSelected())
       {
           hobbiesList.add(sportsBox.getText());
       }
       
       //String Name ,String course,ArrayList<string> hobbies
       if(name.isEmpty() || gender.isEmpty() || course.isEmpty() || hobbiesList.isEmpty())
       {
           JOptionPane.showMessageDialog(this,"Please fill all the fields ","Empty Fields",JOptionPane.ERROR_MESSAGE);
       }
       else
       {
       Student std =new Student(name ,gender ,course ,hobbiesList);
       students.add(std);
       
       displayArea.append(std.toString()+"\n");
       JOptionPane.showMessageDialog(this,"Successfully Submitted","Registered Success",JOptionPane.INFORMATION_MESSAGE);
       clearForm();
          
       }
       
    }
    
    
}