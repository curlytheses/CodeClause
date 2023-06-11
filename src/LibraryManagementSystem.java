import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LibraryManagementSystem extends JFrame implements ActionListener {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField titleTextField, authorTextField, searchTextField;
    private JTextArea bookListTextArea;

    public LibraryManagementSystem() {
        setTitle("Library Management System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel addPanel = new JPanel(new GridLayout(3, 2));
        JLabel titleLabel = new JLabel("Title:");
        titleTextField = new JTextField();
        JLabel authorLabel = new JLabel("Author:");
        authorTextField = new JTextField();
        JButton addButton = new JButton("Add");
        addButton.addActionListener(this);
        addPanel.add(titleLabel);
        addPanel.add(titleTextField);
        addPanel.add(authorLabel);
        addPanel.add(authorTextField);
        addPanel.add(new JLabel());
        addPanel.add(addButton);

        JPanel searchPanel = new JPanel(new BorderLayout());
        JLabel searchLabel = new JLabel("Search:");
        searchTextField = new JTextField();
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(this);
        searchPanel.add(searchLabel, BorderLayout.WEST);
        searchPanel.add(searchTextField, BorderLayout.CENTER);
        searchPanel.add(searchButton, BorderLayout.EAST);

        bookListTextArea = new JTextArea();
        bookListTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(bookListTextArea);

        mainPanel.add(addPanel, BorderLayout.NORTH);
        mainPanel.add(searchPanel, BorderLayout.CENTER);
        mainPanel.add(scrollPane, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        if (actionCommand.equals("Add")) {
            String title = titleTextField.getText();
            String author = authorTextField.getText();
            if (!title.isEmpty() && !author.isEmpty()) {
                String book = title + " - " + author;
                bookListTextArea.append(book + "\n");
                titleTextField.setText("");
                authorTextField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Please enter both title and author.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (actionCommand.equals("Search")) {
            String searchTerm = searchTextField.getText();
            String[] books = bookListTextArea.getText().split("\n");
            bookListTextArea.setText("");
            for (String book : books) {
                if (book.toLowerCase().contains(searchTerm.toLowerCase())) {
                    bookListTextArea.append(book + "\n");
                }
            }
            searchTextField.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LibraryManagementSystem();
            }
        });
    }
}
