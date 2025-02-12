import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CheckoutDialog extends JDialog {
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField emailField;
    private JTextField cardNumberField;
    private JTextField cvcField;
    private Order order;
    private int orderNumber;

    public CheckoutDialog(JFrame parent, Order order, int orderNumber) {
        super(parent, "Checkout", true);
        this.order = order;
        this.orderNumber = orderNumber;
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());
        setSize(400, 300);
        setLocationRelativeTo(getParent());

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        formPanel.add(new JLabel("First Name:"));
        firstNameField = new JTextField();
        formPanel.add(firstNameField);

        formPanel.add(new JLabel("Last Name:"));
        lastNameField = new JTextField();
        formPanel.add(lastNameField);

        formPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        formPanel.add(emailField);

        formPanel.add(new JLabel("Card Number:"));
        cardNumberField = new JTextField();
        formPanel.add(cardNumberField);

        formPanel.add(new JLabel("CVC:"));
        cvcField = new JTextField();
        formPanel.add(cvcField);

        JButton confirmButton = createFlatButton("Confirm Order", new Color(0, 150, 136), Color.WHITE);
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle order confirmation
                if (validateInput()) {
                    String firstName = firstNameField.getText().trim();
                    String lastName = lastNameField.getText().trim();
                    String email = emailField.getText().trim();

                    // Construct message with order details and order number
                    String message = "Order Confirmed!\n\n" +
                            "Order Number: " + orderNumber + "\n" +
                            "Customer Name: " + firstName + " " + lastName + "\n" +
                            "Email: " + email + "\n" +
                            "Total Amount: $" + order.getTotalPrice();

                    // Show message dialog with order details
                    JOptionPane.showMessageDialog(CheckoutDialog.this, message, "Order Confirmation", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                }
            }
        });

        add(formPanel, BorderLayout.CENTER);
        add(confirmButton, BorderLayout.SOUTH);
    }

    private boolean validateInput() {
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String email = emailField.getText().trim();
        String cardNumber = cardNumberField.getText().trim();
        String cvc = cvcField.getText().trim();

        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || cardNumber.isEmpty() || cvc.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Validate email format
        if (!isValidEmail(email)) {
            JOptionPane.showMessageDialog(this, "Please enter a valid email address.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Validate card number format (assume a simple check for 16 digits)
        if (!isValidCardNumber(cardNumber)) {
            JOptionPane.showMessageDialog(this, "Please enter a valid 16-digit card number.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Validate CVC format (assume a simple check for 3 or 4 digits)
        if (!isValidCVC(cvc)) {
            JOptionPane.showMessageDialog(this, "Please enter a valid 3 or 4-digit CVC.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Perform additional validation if needed

        return true;
    }

    private boolean isValidEmail(String email) {
        // Simple email validation using regular expression
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }

    private boolean isValidCardNumber(String cardNumber) {
        // Simple card number validation (16 digits)
        return cardNumber.matches("^\\d{16}$");
    }

    private boolean isValidCVC(String cvc) {
        // Simple CVC validation (3 or 4 digits)
        return cvc.matches("^\\d{3,4}$");
    }

    private JButton createFlatButton(String text, Color bgColor, Color fgColor) {
        JButton button = new JButton(text);
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setOpaque(true);
        return button;
    }
}
