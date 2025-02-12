import javax.swing.*;
import java.awt.*;
//UI class for invoice which shows invoice info on screen
public class InvoiceUI extends JFrame {
    //takes in invoice content as string and displays it on screen

    public InvoiceUI(String invoiceContent) {
        setTitle("Invoice");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());
        setResizable(true);

        JLabel invoiceLabel = new JLabel(invoiceContent);
        invoiceLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        invoiceLabel.setVerticalAlignment(SwingConstants.TOP);

        JScrollPane scrollPane = new JScrollPane(invoiceLabel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(scrollPane, BorderLayout.CENTER);
    }
}
