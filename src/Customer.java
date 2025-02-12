public class Customer extends Person{
    private String customerID;
    public Customer(String name, int age, String email, String address, String phoneNumber) {
        super(name, age, email, address, phoneNumber);
        this.customerID = "C" + (int)(Math.random() * 1000);
    }
    public String getCustomerID() {
        return customerID;
    }


}
