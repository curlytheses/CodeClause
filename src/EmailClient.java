import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmailClient extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField recipientField;
    private JTextField subjectField;
    private JTextArea messageArea;

    public EmailClient() {
        setTitle("Email Client");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));

        JLabel recipientLabel = new JLabel("Recipient:");
        recipientField = new JTextField(20);
        JLabel subjectLabel = new JLabel("Subject:");
        subjectField = new JTextField(20);

        inputPanel.add(recipientLabel);
        inputPanel.add(recipientField);
        inputPanel.add(subjectLabel);
        inputPanel.add(subjectField);

        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(new SendButtonListener());

        panel.add(inputPanel, BorderLayout.NORTH);

        messageArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(messageArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        panel.add(sendButton, BorderLayout.SOUTH);

        add(panel);
        setVisible(true);
    }

    private class SendButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String recipient = recipientField.getText();
            String subject = subjectField.getText();
            String message = messageArea.getText();

            // Implement your email sending logic here
            System.out.println("Recipient: " + recipient);
            System.out.println("Subject: " + subject);
            System.out.println("Message: " + message);

            // Clear the input fields
            recipientField.setText("");
            subjectField.setText("");
            messageArea.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new EmailClient();
            }
        });
    }
}
