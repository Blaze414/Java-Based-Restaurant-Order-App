import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
// Class for invoice
//called from payment class to generate invoice
public class Invoice {
    private String customerName;
    private String paymentDetails;
    private double amount;
    private String email;
    private PaymentMethodEnum paymentMethod;

    private Integer invoiceNumber;
    private Order currentOrder;

    public Invoice(String customerName, String paymentDetails,Order currentOrder,String email,PaymentMethodEnum paymentMethod) {
        this.customerName = customerName;
        this.paymentDetails = paymentDetails;
        this.currentOrder = currentOrder;
        this.amount = currentOrder.getTotalPrice();
        this.email = email;
        this.paymentMethod = paymentMethod;
        // Generate a random invoice number
        Random rand = new Random();
        this.invoiceNumber = rand.nextInt(1000);
    }

    public String getInvoice() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();

        StringBuilder invoiceBuilder = new StringBuilder();
        invoiceBuilder.append("<html>")
                .append("<head>")
                .append("<style>")
                .append("body { font-family: Arial, sans-serif; margin: 20px; }")
                .append(".invoice-box { max-width: 800px; margin: auto; padding: 30px; border: 1px solid #eee; box-shadow: 0 0 10px rgba(0, 0, 0, 0.15); }")
                .append(".invoice-title { font-size: 28px; margin-bottom: 20px; text-align: center; }")
                .append(".invoice-details { font-size: 16px; margin-bottom: 20px; }")
                .append(".invoice-details p { margin: 5px 0; }")
                .append(".invoice-items { margin-bottom: 20px; }")
                .append(".invoice-items table { width: 100%; border-collapse: collapse; }")
                .append(".invoice-items th, .invoice-items td { border: 1px solid #ddd; padding: 8px; }")
                .append(".invoice-items th { background-color: #f2f2f2; text-align: left; }")
                .append("</style>")
                .append("</head>")
                .append("<body>")
                .append("<div class='invoice-box'>")
                .append("<div class='invoice-title'>Invoice</div>")
                .append("<div class='invoice-details'>")
                .append("<p>Date: <strong>").append(formatter.format(date)).append("</strong></p>")
                .append("<p>Invoice Number: <strong>").append(invoiceNumber).append("</strong></p>")
                .append("<p>Customer Name: <strong>").append(customerName).append("</strong></p>")
                .append("<p>Email: <strong>").append(email).append("</strong></p>")
                .append("<p>Payment Method: <strong>").append(paymentMethod).append("</strong></p>")
                .append("<p>Payment Details: <strong>").append(paymentDetails).append("</strong></p>")
                .append("</div>")
                .append("<div class='invoice-items'>")
                .append("<table>")
                .append("<tr><th>Item</th><th>Price</th></tr>");

        for (MenuItem item : currentOrder.getItems()) {
            invoiceBuilder.append("<tr>")
                    .append("<td>").append(item.getName()).append("</td>")
                    .append("<td>$").append(String.format("%.2f", item.getPrice())).append("</td>")
                    .append("</tr>");
        }

        invoiceBuilder.append("<tr>")
                .append("<td><strong>Total</strong></td>")
                .append("<td><strong>$").append(String.format("%.2f", amount)).append("</strong></td>")
                .append("</tr>")
                .append("</table>")
                .append("</div>")
                .append("</div>")
                .append("</body>")
                .append("</html>");

        return invoiceBuilder.toString();
    }



}
