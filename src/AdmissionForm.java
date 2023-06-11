import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AdmissionForm implements ActionListener {

    // Declare form components
    private JFrame frame;
    private JLabel nameLabel, emailLabel, phoneLabel, courseLabel;
    private JTextField nameField, emailField, phoneField;
    private JComboBox<String> courseCombo;
    private JButton submitButton;

    public AdmissionForm() {
        // Create form components
        nameLabel = new JLabel("Name:");
        emailLabel = new JLabel("Email:");
        phoneLabel = new JLabel("Phone:");
        courseLabel = new JLabel("Course:");
        nameField = new JTextField(20);
        emailField = new JTextField(20);
        phoneField = new JTextField(10);
        courseCombo = new JComboBox<>(new String[] {"Java Programming", "Python Programming", "Web Development", "Database Management"});
        submitButton = new JButton("Submit");

        // Create form panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(4, 2));
        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(emailLabel);
        formPanel.add(emailField);
        formPanel.add(phoneLabel);
        formPanel.add(phoneField);
        formPanel.add(courseLabel);
        formPanel.add(courseCombo);

        // Create button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(submitButton);

        // Create frame
        frame = new JFrame("Admission Form");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(formPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Add action listener to submit button
        submitButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        // Get form values
        String name = nameField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();
        String course = (String) courseCombo.getSelectedItem();

        // Output form values to console
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phone);
        System.out.println("Course: " + course);

        // Clear form fields
        nameField.setText("");
        emailField.setText("");
        phoneField.setText("");
        courseCombo.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        // Create menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu admissionMenu = new JMenu("Admission");
        admissionMenu.add(new JMenuItem("New Form")).addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AdmissionForm();
            }
        });
        admissionMenu.addSeparator();
        admissionMenu.add(new JMenuItem("Exit")).addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        menuBar.add(admissionMenu);

        // Create frame with menu bar
        JFrame frame = new JFrame("Admission");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setJMenuBar(menuBar);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
