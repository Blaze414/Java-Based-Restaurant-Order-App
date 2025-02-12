import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class AccountPayment implements PaymentMethod {
    public void pay(double amount) {
        System.out.println("Paying " + amount + " using account");
    }

    public Invoice getInvoice(String customerName,String paymentDetails,Order currentOrder,String email) {
        return new Invoice(customerName,paymentDetails,currentOrder , email, PaymentMethodEnum.ACCOUNT);
    }



    public boolean paymentIsSuccessful() {
        return true;
    }

}
