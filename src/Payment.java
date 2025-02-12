
public class Payment {
    private Order currentOrder;
    private  double amount;
    private PaymentMethodEnum paymentMethod;
    private PaymentMethod paymentMethodObject;
    private String customerName;
    private String paymentDetails;
    private String email;
    private String paymentID;

    // Constructor
    public Payment(Order currentOrder, PaymentMethodEnum paymentMethod,String customerName,String paymentDetails,String email) {
        this.currentOrder = currentOrder;
        this.amount = currentOrder.getTotalPrice();
        this.paymentMethod = paymentMethod;
        this.customerName = customerName;
        this.paymentDetails = paymentDetails;
        this.email = email;
        this.paymentID = "P" + (int)(Math.random() * 1000);


        switch (paymentMethod) {
            case CARD:
                paymentMethodObject = new CardPayment();
                break;
            case ACCOUNT:
                paymentMethodObject = new AccountPayment();
                break;
            case CHEQUE:
                paymentMethodObject = new ChequePayment();
                break;
        }

    }

    public void pay() {
        paymentMethodObject.pay(amount);
    }

    public String getInvoice() {
        // Call the getInvoice method of the paymentMethodObject
        return paymentMethodObject.getInvoice(customerName,paymentDetails,currentOrder,email).getInvoice();
    }

    public boolean paymentSuccessful() {
        return paymentMethodObject.paymentIsSuccessful();
    }

    public String getPaymentID() {
        return paymentID;
    }


}
