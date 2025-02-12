public class MenuItem {
    private String name;
    private double price;
    private String imagePath;
    private String category;

    private String itemID;

    public MenuItem(String name, double price, String imagePath, String category) {
        this.name = name;
        this.price = price;
        this.imagePath = imagePath;
        this.category = category;
        this.itemID = "I" + (int)(Math.random() * 1000);
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return name + " - $" + price;
    }

    public String getItemID() {
        return itemID;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(double price) {
        this.price = price;
    }
}
