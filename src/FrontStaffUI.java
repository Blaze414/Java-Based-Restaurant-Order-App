import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrontStaffUI extends JFrame {

    public FrontStaffUI() {
        setTitle("Front Staff");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLayout(new BorderLayout());
        setResizable(false);

        JPanel mainPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(mainPanel, BorderLayout.CENTER);

        // Title Label
        JLabel titleLabel = new JLabel("Front Staff Dashboard");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(titleLabel);

        // Take Order Button
        JButton takeOrderButton = new JButton("Take Order");
        takeOrderButton.setFont(new Font("Arial", Font.PLAIN, 14));
        takeOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the OnlineMenu UI
                new OnlineMenu();
            }
        });
        mainPanel.add(takeOrderButton);

        JButton makeReservationsButton = new JButton("Make Reservations");
        makeReservationsButton.setFont(new Font("Arial", Font.PLAIN, 14));
        makeReservationsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a dummy FrontStaff object
                FrontStaff frontStaff = new FrontStaff("Jane Smith", 25, "jane@example.com", "123 Main St, Springfield", "555-1234");

                // Open the ReservationUI with the dummy FrontStaff
                new ReservationUI(frontStaff);
            }
        });
        mainPanel.add(makeReservationsButton);

        // Make the UI visible
        setVisible(true);
    }

    public static void main(String[] args) {
        // Create and display the FrontStaffUI
        new FrontStaffUI();
    }
}
