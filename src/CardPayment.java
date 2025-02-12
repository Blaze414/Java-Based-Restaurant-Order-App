import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class CardPayment implements PaymentMethod {
    public void pay(double amount) {
        System.out.println("Paying " + amount + " using card");
    }

    public Invoice getInvoice(String customerName,String paymentDetails,Order currentOrder,String email) {
        return new Invoice(customerName,paymentDetails,currentOrder , email, PaymentMethodEnum.CARD);
    }



    public boolean paymentIsSuccessful() {
        return true;
    }
}
