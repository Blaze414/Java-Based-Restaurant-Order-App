import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<MenuItem> items;
    private String orderID;

    public Order() {
        items = new ArrayList<>();
        orderID = "O" + (int)(Math.random() * 1000);
    }

    public void addItem(MenuItem item) {
        items.add(item);
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public double getTotalPrice() {
        double total = 0.0;
        for (MenuItem item : items) {
            total += item.getPrice();
        }
        return total;
    }
    public String getOrderID() {
        return orderID;
    }
}
