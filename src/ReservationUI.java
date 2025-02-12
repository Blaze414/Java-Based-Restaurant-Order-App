import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
//UI class for reservation. Takes in frontstaff object and creates a reservation.

public class ReservationUI extends JFrame {
    private JTextField customerNameField;
    private JTextField customerPhoneNumberField;
    private JTextField reservationDateField;
    private JTextField numberOfPeopleField;
    private JTextField specialRequestsField;
    private JTextField tableNumberField;
    private JLabel resultLabel;
    private FrontStaff frontStaff;

    public ReservationUI(FrontStaff frontStaff) {
        this.frontStaff = frontStaff;
        setTitle("Reservation System");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 700);
        setLayout(new BorderLayout());
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(mainPanel, BorderLayout.CENTER);

        // Title Label
        JLabel titleLabel = new JLabel("Reservation");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Reservation Form Panel
        JPanel reservationPanel = new JPanel();
        reservationPanel.setLayout(new BoxLayout(reservationPanel, BoxLayout.Y_AXIS));
        reservationPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Customer Name Field
        customerNameField = new JTextField();
        customerNameField.setFont(new Font("Arial", Font.PLAIN, 14));
        customerNameField.setBorder(BorderFactory.createTitledBorder("Customer Name"));
        reservationPanel.add(customerNameField);
        reservationPanel.add(Box.createVerticalStrut(10));

        // Customer Phone Number Field
        customerPhoneNumberField = new JTextField();
        customerPhoneNumberField.setFont(new Font("Arial", Font.PLAIN, 14));
        customerPhoneNumberField.setBorder(BorderFactory.createTitledBorder("Customer Phone Number"));
        reservationPanel.add(customerPhoneNumberField);
        reservationPanel.add(Box.createVerticalStrut(10));

        // Reservation Date Field
        reservationDateField = new JTextField();
        reservationDateField.setFont(new Font("Arial", Font.PLAIN, 14));
        reservationDateField.setBorder(BorderFactory.createTitledBorder("Reservation Date (dd-MM-yy HH:mm)"));
        reservationPanel.add(reservationDateField);
        reservationPanel.add(Box.createVerticalStrut(10));

        // Number of People Field
        numberOfPeopleField = new JTextField();
        numberOfPeopleField.setFont(new Font("Arial", Font.PLAIN, 14));
        numberOfPeopleField.setBorder(BorderFactory.createTitledBorder("Number of People"));
        reservationPanel.add(numberOfPeopleField);
        reservationPanel.add(Box.createVerticalStrut(10));

        // Special Requests Field
        specialRequestsField = new JTextField();
        specialRequestsField.setFont(new Font("Arial", Font.PLAIN, 14));
        specialRequestsField.setBorder(BorderFactory.createTitledBorder("Special Requests"));
        reservationPanel.add(specialRequestsField);
        reservationPanel.add(Box.createVerticalStrut(10));

        // Table Number Field
        tableNumberField = new JTextField();
        tableNumberField.setFont(new Font("Arial", Font.PLAIN, 14));
        tableNumberField.setBorder(BorderFactory.createTitledBorder("Table Number"));
        reservationPanel.add(tableNumberField);
        reservationPanel.add(Box.createVerticalStrut(10));

        // Submit Button
        JButton submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Arial", Font.PLAIN, 14));
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSubmitButton();
            }
        });
        reservationPanel.add(submitButton);

        mainPanel.add(reservationPanel, BorderLayout.CENTER);


        resultLabel = new JLabel();
        resultLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(resultLabel, BorderLayout.SOUTH);


        setVisible(true);
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

    private void handleSubmitButton() {
        try {
            String customerName = customerNameField.getText();
            String customerPhoneNumber = customerPhoneNumberField.getText();
            String reservationDateStr = reservationDateField.getText();
            int numberOfPeople = Integer.parseInt(numberOfPeopleField.getText());
            String specialRequests = specialRequestsField.getText();
            int tableNumber = Integer.parseInt(tableNumberField.getText());

            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yy HH:mm");
            Date reservationDate = formatter.parse(reservationDateStr);
            // Create a reservation using the FrontStaff object's createReservation method
            Reservation reservation = frontStaff.createReservation(
                    customerName,
                    customerPhoneNumber,
                    reservationDateStr,
                    numberOfPeople,
                    specialRequests,
                    tableNumber
            );

            if (reservation != null) {
                showToast("Reservation created successfully!");
                resultLabel.setText("<html>Reservation created successfully!<br/>"
                        + "Reservation ID: " + reservation.getReservationID() + "<br/>"
                        + "Customer Name: " + customerName + "<br/>"
                        + "Phone Number: " + customerPhoneNumber + "<br/>"
                        + "Date: " + reservationDateStr + "<br/>"
                        + "Number of People: " + numberOfPeople + "<br/>"
                        + "Special Requests: " + specialRequests + "<br/>"
                        + "Table Number: " + tableNumber + "</html>");
            } else {
                showToast("Reservation failed. Please check the details and try again.");
                resultLabel.setText("Invalid reservation details");
            }
        } catch (NumberFormatException ex) {
            resultLabel.setText("Please enter valid numbers for Number of People and Table Number.");
        } catch (ParseException ex) {
            resultLabel.setText("Please enter the date in the correct format (dd-MM-yy HH:mm).");
        } catch (Exception ex) {
            resultLabel.setText("An error occurred: " + ex.getMessage());
        }
    }


}
