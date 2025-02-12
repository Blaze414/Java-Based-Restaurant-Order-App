import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

// FrontStaff class
public class FrontStaff extends Person {
    private String staffID;

    public FrontStaff(String name, int age, String email, String address, String phoneNumber) {
        super(name, age, email, address, phoneNumber);
        this.staffID = "F" + (int)(Math.random() * 1000);
    }
    public String getStaffID() {
        return staffID;
    }
    public String takeOrder(Customer customer,Order order,Payment payment){
        return "Order taken by " + getName() + " for " + customer.getName() + " with order ID " + order.getOrderID() + " and payment ID " + payment.getPaymentID();
    }
    public String serveOrder(Order order){
        return "Order served by " + getName() + " with order ID " + order.getOrderID();
    }
    public String getInvoice(Payment payment){
        return payment.getInvoice();
    }
    public String sendOrderToKitchen(Order order){
        return "Order sent to kitchen by " + getName() + " with order ID " + order.getOrderID();
    }
    // Method to create a reservation , called from ReservationUI class.

    public Reservation createReservation(String customerName, String customerPhoneNumber, String reservationDate, int numberOfPeople, String specialRequests, int tableNumber) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yy HH:mm");
        Date reservationDateObject = null;
        try {
            reservationDateObject = formatter.parse(reservationDate);
        } catch (ParseException e) {
            e.printStackTrace();

        }

        if (reservationDateObject != null) {
            return new Reservation(customerName, customerPhoneNumber, reservationDateObject, numberOfPeople, specialRequests, tableNumber);
        } else {
            return null;
        }
    }


}
