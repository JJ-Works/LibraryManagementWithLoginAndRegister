package guis;

import constants.CommonConstants;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LibraryManagementGUI extends guis.Form {
    private JPanel rightPanel;
    private JButton viewAllButton;
    private JScrollPane scrollPane;

    public LibraryManagementGUI() {
        super("Book Management");
        setSize(1140, 740);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JLabel titleLabel = new JLabel("Book Management System", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setBounds(0, 50, 1200, 40);
        titleLabel.setForeground(CommonConstants.TEXT_COLOR);
        add(titleLabel);

        JPanel contentPanel = new JPanel();
        contentPanel.setBounds(10, 120, 1100, 560);
        contentPanel.setLayout(null);
        contentPanel.setBorder(new LineBorder(Color.BLACK, 2, true));
        contentPanel.setBackground(CommonConstants.SECONDARY_COLOR);
        add(contentPanel);

        JPanel leftPanel = new JPanel();
        leftPanel.setBounds(15, 10, 400, 540);
        leftPanel.setBorder(new LineBorder(Color.BLACK, 2, true));
        leftPanel.setLayout(null);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(rightPanel);

        JLabel addBookLabel = new JLabel("Add Book");
        addBookLabel.setFont(new Font("Arial", Font.BOLD, 20));
        addBookLabel.setBounds(20, 10, 200, 30);
        leftPanel.add(addBookLabel);



        JLabel bookIdLabel = new JLabel("Book ID:");
        bookIdLabel.setBounds(20, 50, 100, 25);
        JTextField bookIdField = new JTextField();
        bookIdField.setBounds(20, 75, 360, 25);

        //Book name start --------------------------------
        JLabel bookNameLabel = new JLabel("Book Name:");
        bookNameLabel.setBounds(20, 110, 100, 25);

        JTextField bookNameField = new JTextField();
        bookNameField.setBounds(20, 135, 360, 25);


        JLabel bookAuthorLabel = new JLabel("Book Author:");
        bookAuthorLabel.setBounds(20, 170, 100, 25);
        JTextField bookAuthorField = new JTextField();
        bookAuthorField.setBounds(20, 195, 360, 25);

        JLabel bookGenreLabel = new JLabel("Book Genre:");
        bookGenreLabel.setBounds(20, 230, 100, 25);
        JTextField bookGenreField = new JTextField();
        bookGenreField.setBounds(20, 255, 360, 25);

        JLabel bookDescriptionLabel = new JLabel("Book Description:");
        bookDescriptionLabel.setBounds(20, 290, 150, 25);
        JTextField bookDescriptionField = new JTextField();
        bookDescriptionField.setBounds(20, 315, 360, 25);

        JButton addButton = new JButton("Add");
        addButton.setBounds(20, 350, 360, 30);
        addButton.setBorder(BorderFactory.createLineBorder(null,1,true));
        addButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int bookId = Integer.parseInt(bookIdField.getText().trim());
                    String name = bookNameField.getText().trim();
                    String author = bookAuthorField.getText().trim();
                    String genre = bookGenreField.getText().trim();
                    String description = bookDescriptionField.getText().trim();

                    boolean inserted = db.MyJDBC.insertBook(bookId, name, author, genre, description);
                    if (inserted) {
                        JOptionPane.showMessageDialog(LibraryManagementGUI.this, "Book added successfully!");

                    } else {
                        JOptionPane.showMessageDialog(LibraryManagementGUI.this, "Failed to add book.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(LibraryManagementGUI.this, "Invalid Book ID.");
                }
            }
        });

        JButton viewAllButton = new JButton("View All Books");
        viewAllButton.setBounds(20, 410, 360, 30);
        viewAllButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        viewAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rightPanel.removeAll();
                JLabel inventoryLabel = new JLabel("Inventory");
                inventoryLabel.setBorder(BorderFactory.createEmptyBorder(4, 10, 0, 0));
                inventoryLabel.setHorizontalAlignment(SwingConstants.CENTER);
                inventoryLabel.setFont(new Font("Arial",Font.BOLD,20));
                rightPanel.add(inventoryLabel);

                java.util.List<model.Book> books = db.MyJDBC.getAllBooks();
                for (model.Book book : books) {
                    JLabel bookLabel = new JLabel(
                            book.id + ": " + book.name + " by " + book.author + " (" + book.genre + ")"
                    );
                    bookLabel.setFont(new Font("Arial", Font.PLAIN, 16));
                    rightPanel.add(bookLabel);
                    bookIdField.setText("");
                    bookNameField.setText("");
                    bookGenreField.setText("");
                    bookAuthorField.setText("");
                    bookDescriptionField.setText("");
                }
                rightPanel.revalidate();
                rightPanel.repaint();
            }
        });

        JButton editButton = new JButton("Edit Book");
        editButton.setBounds(20, 450, 360, 30);
        editButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int bookId = Integer.parseInt(bookIdField.getText().trim());
                    String name = bookNameField.getText().trim();
                    String author = bookAuthorField.getText().trim();
                    String genre = bookGenreField.getText().trim();
                    String description = bookDescriptionField.getText().trim();

                    boolean updated = db.MyJDBC.updateBook(bookId, name, author, genre, description);
                    if (updated) {
                        JOptionPane.showMessageDialog(LibraryManagementGUI.this, "Book updated successfully!");
                        // Refresh the right panel
                        viewAllButton.doClick();
                        bookIdField.setText("");
                        bookNameField.setText("");
                        bookGenreField.setText("");
                        bookAuthorField.setText("");
                        bookDescriptionField.setText("");
                    } else {
                        JOptionPane.showMessageDialog(LibraryManagementGUI.this, "Failed to update book.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(LibraryManagementGUI.this, "Invalid Book ID.");
                }
            }
        });

        JButton deleteButton = new JButton("Delete Book");
        deleteButton.setBounds(20, 490, 360, 30);
        deleteButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int bookId = Integer.parseInt(bookIdField.getText().trim());
                    boolean deleted = db.MyJDBC.deleteBook(bookId);
                    if (deleted) {
                        JOptionPane.showMessageDialog(LibraryManagementGUI.this, "Book deleted successfully!");
                        viewAllButton.doClick();
                        bookIdField.setText("");
                        bookNameField.setText("");
                        bookGenreField.setText("");
                        bookAuthorField.setText("");
                        bookDescriptionField.setText("");
                    } else {
                        JOptionPane.showMessageDialog(LibraryManagementGUI.this, "Failed to delete book.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(LibraryManagementGUI.this, "Invalid Book ID.");
                }
            }
        });


        leftPanel.add(bookIdLabel);
        leftPanel.add(bookIdField);
        leftPanel.add(bookNameLabel);
        leftPanel.add(bookNameField);
        leftPanel.add(bookAuthorLabel);
        leftPanel.add(bookAuthorField);
        leftPanel.add(bookGenreLabel);
        leftPanel.add(bookGenreField);
        leftPanel.add(bookDescriptionLabel);
        leftPanel.add(bookDescriptionField);
        leftPanel.add(addButton);
        leftPanel.add(viewAllButton);
        leftPanel.add(editButton);
        leftPanel.add(deleteButton);

        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        scrollPane.setBounds(420, 10, 670, 540);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.black,2,true));


        JLabel inventoryLabel = new JLabel("Inventory");
        inventoryLabel.setBorder(BorderFactory.createEmptyBorder(4, 10, 0, 0)); // Top and bottom padding
        inventoryLabel.setHorizontalAlignment(SwingConstants.CENTER);
        inventoryLabel.setFont(new Font("Arial",Font.BOLD,20));


        rightPanel.add(inventoryLabel);
        contentPanel.add(leftPanel);
        contentPanel.add(scrollPane);

    }
}