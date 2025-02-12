import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ChequePayment implements PaymentMethod {
    @Override
    public void pay(double amount) {
        System.out.println("Paying $" + amount + " by cheque");
    }

    public Invoice getInvoice(String customerName,String paymentDetails,Order currentOrder,String email) {
        return new Invoice(customerName,paymentDetails,currentOrder , email, PaymentMethodEnum.CHEQUE);
    }


    @Override
    public boolean paymentIsSuccessful() {
        return true;
    }
}
