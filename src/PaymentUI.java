import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//UI class for payment. Takes in an order and processes payment for it.
public class PaymentUI extends JFrame {

    private JComboBox<String> paymentMethodComboBox;
    private JTextField customerNameField;
    private JTextField paymentDetailsField;
    private JTextField emailField;
    private JLabel resultLabel;

    private PaymentMethodEnum paymentMethod;
    private Payment payment;
    private Order currentOrder;

    public PaymentUI(Order currentOrder) {
        this.currentOrder = currentOrder;

        setTitle("Payment System");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 400);
        setLayout(new BorderLayout());
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(mainPanel, BorderLayout.CENTER);

        // Title Label
        JLabel titleLabel = new JLabel("Payment Options");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Payment Options Panel
        JPanel paymentPanel = new JPanel();
        paymentPanel.setLayout(new BoxLayout(paymentPanel, BoxLayout.Y_AXIS));
        paymentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        paymentMethodComboBox = new JComboBox<>(new String[]{"Card", "Cheque", "Account"});
        paymentMethodComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        paymentMethodComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatePaymentDetailsField();
            }
        });
        paymentPanel.add(paymentMethodComboBox);

        paymentPanel.add(Box.createVerticalStrut(20));

        // Customer Name Field
        customerNameField = new JTextField();
        customerNameField.setFont(new Font("Arial", Font.PLAIN, 14));
        customerNameField.setBorder(BorderFactory.createTitledBorder("Customer Name"));
        paymentPanel.add(customerNameField);

        paymentPanel.add(Box.createVerticalStrut(10));

        // Payment Details Field
        paymentDetailsField = new JTextField();
        paymentDetailsField.setFont(new Font("Arial", Font.PLAIN, 14));
        paymentDetailsField.setBorder(BorderFactory.createTitledBorder("Card Number"));
        paymentPanel.add(paymentDetailsField);

        paymentPanel.add(Box.createVerticalStrut(10));

        // Email Field
        emailField = new JTextField();
        emailField.setFont(new Font("Arial", Font.PLAIN, 14));
        emailField.setBorder(BorderFactory.createTitledBorder("Email"));
        paymentPanel.add(emailField);

        paymentPanel.add(Box.createVerticalStrut(10));

        JButton payButton = createFlatButton("Pay", new Color(0, 150, 136), Color.WHITE);
        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedMethod = (String) paymentMethodComboBox.getSelectedItem();
                assert selectedMethod != null;
                paymentMethod = PaymentMethodEnum.valueOf(selectedMethod.toUpperCase());
                String customerName = customerNameField.getText();
                String paymentDetails = paymentDetailsField.getText();
                String email = emailField.getText();
                // Create a new Payment object which takes in the current order, payment method, customer name, payment details and email.
                payment = new Payment(currentOrder, paymentMethod, customerName, paymentDetails, email);
                //process payment and show invoice if successful
                if (processPayment()) {
                    showToast("Payment Successful!");
                    showInvoice();
                } else {
                    showToast("Payment Failed!");
                }
            }
        });
        paymentPanel.add(payButton);

        mainPanel.add(paymentPanel, BorderLayout.CENTER);

        // Result Label
        resultLabel = new JLabel("", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        titleLabel.setText("");
        mainPanel.add(resultLabel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void updatePaymentDetailsField() {
        String selectedMethod = (String) paymentMethodComboBox.getSelectedItem();
        paymentDetailsField.setBorder(BorderFactory.createTitledBorder(selectedMethod + " Number"));
    }

    private boolean processPayment() {
        //Add validation for empty fields and invalid email address
        if (paymentMethod == null) {
            resultLabel.setText("Please select a payment method");
            return false;
        }
        if (customerNameField.getText().isEmpty() || paymentDetailsField.getText().isEmpty() || emailField.getText().isEmpty()) {
            resultLabel.setText("Please fill in all fields");
            return false;
        }
        if(emailField.getText().indexOf('@') == -1 || emailField.getText().indexOf('.') == -1) {
            resultLabel.setText("Invalid email address");
            return false;
        }
        resultLabel.setText("");
        payment.pay();
        return payment.paymentSuccessful();
    }

    private void showToast(String message) {
        JDialog dialog = new JDialog();
        dialog.setUndecorated(true);
        dialog.setLayout(new GridBagLayout());
        JLabel label = new JLabel(message);
        label.setOpaque(true);
        label.setBackground(Color.BLACK);
        label.setForeground(Color.WHITE);
        label.setBorder(new EmptyBorder(10, 10, 10, 10));
        dialog.add(label);

        dialog.pack();
        dialog.setLocationRelativeTo(this);
        Timer timer = new Timer(2000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
        timer.setRepeats(false);
        timer.start();

        dialog.setVisible(true);
    }

    private void showInvoice() {
        // Create a new InvoiceUI object which takes in the invoice content from the payment object.
        InvoiceUI invoiceUI = new InvoiceUI(payment.getInvoice());
        invoiceUI.setVisible(true);
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

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new PaymentUI(100.00);
//            }
//        });
//    }
}


