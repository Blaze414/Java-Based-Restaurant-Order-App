
public class Manager extends Person{
    private String managerID;

    public Manager(String name, int age, String email, String address, String phoneNumber) {
        super(name, age, email, address, phoneNumber);
        this.managerID = "M" + (int)(Math.random() * 1000);
    }
    public String getManagerID() {
        return managerID;
    }
    public String addStaff(String name, int age, String email, String address, String phoneNumber){
        return "Staff added by " + getName() + " with staff ID " + new FrontStaff(name, age, email, address, phoneNumber).getStaffID();
    }
    public String removeStaff(FrontStaff staff){
        return "Staff removed by " + getName() + " with staff ID " + staff.getStaffID();
    }
    public String addMenuItem(String name, double price, String imagePath, String category){
        return "Menu item added by " + getName() + " with item ID " + new MenuItem(name, price , imagePath , category).getItemID();
    }
    public String removeMenuItem(MenuItem item){
        return "Menu item removed by " + getName() + " with item ID " + item.getItemID();
    }
    public String updateMenuItem(MenuItem item, String name, double price){
        item.setName(name);
        item.setPrice(price);
        return "Menu item updated by " + getName() + " with item ID " + item.getItemID();
    }
    public String viewMenu(){
        return "Menu viewed by " + getName();
    }
    public String viewOrder(Order order){
        return "Order viewed by " + getName() + " with order ID " + order.getOrderID();
    }
    public String viewStaff(FrontStaff staff){
        return "Staff viewed by " + getName() + " with staff ID " + staff.getStaffID();
    }
    public String viewCustomer(Customer customer){
        return "Customer viewed by " + getName() + " with customer ID " + customer.getCustomerID();
    }
    public String viewPayment(Payment payment){
        return "Payment viewed by " + getName() + " with payment ID " + payment.getPaymentID();
    }
    public String viewInvoice(Payment payment){
        return "Invoice viewed by " + getName() + " with payment ID " + payment.getPaymentID();
    }

}
