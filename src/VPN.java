import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

public class VPN extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField serverIP;
    private JTextField serverPort;
    private JTextField username;
    private JTextField password;
    private JButton connectButton;
    private JTextArea statusArea;

    public VPN() {
        super("VPN");

        // Create the GUI elements
        serverIP = new JTextField(15);
        serverPort = new JTextField(5);
        username = new JTextField(15);
        password = new JPasswordField(15);
        connectButton = new JButton("Connect");
        statusArea = new JTextArea(10, 20);

        // Add the GUI elements to the frame
        JPanel mainPanel = new JPanel(new GridLayout(0, 1));
        mainPanel.add(new JLabel("Server IP:"));
        mainPanel.add(serverIP);
        mainPanel.add(new JLabel("Server Port:"));
        mainPanel.add(serverPort);
        mainPanel.add(new JLabel("Username:"));
        mainPanel.add(username);
        mainPanel.add(new JLabel("Password:"));
        mainPanel.add(password);
        mainPanel.add(connectButton);
        mainPanel.add(statusArea);

        getContentPane().add(mainPanel);

        // Set the frame's properties
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Register a listener for the connect button
        connectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Connect to the VPN server
                try {
                    Socket socket = new Socket(serverIP.getText(), Integer.parseInt(serverPort.getText()));
                    PrintWriter out = new PrintWriter(socket.getOutputStream());
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                    // Send the username and password to the server
                    out.println(username.getText());
                    out.println(password.getText());

                    // Check if the login was successful
                    String response = in.readLine();
                    if (response.equals("OK")) {
                        statusArea.append("Connected to VPN server.\n");

                        // Start a new thread to listen for data from the server
                        new Thread(new Runnable() {
                            public void run() {
                                while (true) {
                                    String data = null;
									try {
										data = in.readLine();
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
                                    if (data != null) {
                                        statusArea.append(data + "\n");
                                    }
                                }
                            }
                        }).start();
                    } else {
                        statusArea.append("Login failed.\n");
                    }
                } catch (IOException e1) {
                    statusArea.append("Error connecting to VPN server.\n");
                }
            }
        });
    }

    public static void main(String[] args) {
        new VPN().setVisible(true);
    }
}
