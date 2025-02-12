public interface PaymentMethod {
    public void pay(double amount);
    public Invoice getInvoice(String customerName,String paymentDetails,Order currentOrder,String email); //payment class calls this method to get invoice
    public boolean paymentIsSuccessful();

}
