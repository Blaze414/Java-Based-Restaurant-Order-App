import java.util.Date;

public class Reservation {
    private String customerName;
    private String customerPhoneNumber;
    private String reservationID;
    private Date reservationDate;
    private int numberOfPeople;
    private String specialRequests;
    private int tableNumber;
    // Constructor
    public Reservation(String customerName,String customerPhoneNumber, Date reservationDate, int numberOfPeople, String specialRequests,int tableNumber) {
        this.customerName = customerName;
        this.customerPhoneNumber = customerPhoneNumber;
        this.reservationDate = reservationDate;
        this.numberOfPeople = numberOfPeople;
        this.specialRequests = specialRequests;
        this.tableNumber = tableNumber;
        this.reservationID = "R" + (int)(Math.random() * 1000);
    }
    public String getReservationID() {
        return reservationID;
    }

    // Getter and setter methods
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }


    public String confirmReservation(FrontStaff frontStaff) {
        return "Reservation confirmed by " + frontStaff.getName();
    }

    public void updateReservation(Date newDate, int newNumberOfPeople, String newSpecialRequests) {
        this.reservationDate = newDate;
        this.numberOfPeople = newNumberOfPeople;
        this.specialRequests = newSpecialRequests;
    }

    @Override
    public String toString() {
        return "Reservation ID: " + reservationID + "\n" +
                "Customer Name: " + customerName + "\n" +
                "Customer Phone Number: " + customerPhoneNumber + "\n" +
                "Reservation Date: " + reservationDate + "\n" +
                "Number of People: " + numberOfPeople + "\n" +
                "Special Requests: " + specialRequests + "\n" +
                "Table Number: " + tableNumber;
    }

}
