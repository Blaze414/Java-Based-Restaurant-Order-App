import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class OnlineMenu extends JFrame {
    private JPanel menuPanel;
    private JList<String> orderList;
    private DefaultListModel<String> orderListModel;
    private JLabel totalLabel;
    private Order currentOrder;
    private List<MenuItem> menuItems;
    private JComboBox<String> categoryComboBox;
    private JTextField searchField;
    private JLabel userLabel;

    public OnlineMenu() {
        currentOrder = new Order();
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Relaxing Koala Online Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLayout(new BorderLayout());
        setResizable(false);

        // Top Panel for Search, Category Filter, and User Name
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchField = new JTextField(20);
        searchField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        searchField.setFont(new Font("Arial", Font.PLAIN, 14));
        JButton searchButton = createFlatButton("Search", new Color(33, 150, 243), Color.WHITE);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterMenuItems();
            }
        });
        searchPanel.add(new JLabel("Search:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        JPanel categoryPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        String[] categories = {"All", "Beverages", "Desserts", "Snacks"};
        categoryComboBox = new JComboBox<>(categories);
        categoryComboBox.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        categoryComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterMenuItems();
            }
        });
        categoryPanel.add(new JLabel("Category:"));
        categoryPanel.add(categoryComboBox);

        // Display random user name
        JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        userLabel = new JLabel("Welcome, " + generateRandomUserName());
        userPanel.add(userLabel);

        topPanel.add(searchPanel, BorderLayout.CENTER);
        topPanel.add(categoryPanel, BorderLayout.EAST);
        topPanel.add(userPanel, BorderLayout.NORTH);

        // Menu Panel
        menuPanel = new JPanel(new GridLayout(0, 3, 10, 10));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        populateMenuItems();

        // Order Panel
        JPanel orderPanel = createOrderPanel();

        add(topPanel, BorderLayout.NORTH);
        add(menuPanel, BorderLayout.CENTER);
        add(orderPanel, BorderLayout.EAST);

        setVisible(true);
    }

    private JPanel createOrderPanel() {
        JPanel orderPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int shadowSize = 5;
                int panelWidth = getWidth();
                int panelHeight = getHeight();
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new Color(0, 0, 0, 50));
                g2d.fillRoundRect(0, 0, panelWidth, panelHeight, 20, 20);
                g2d.dispose();
            }
        };
        orderPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        orderPanel.setBackground(Color.WHITE);
        orderPanel.setOpaque(false);

        orderListModel = new DefaultListModel<>();
        orderList = new JList<>(orderListModel);
        orderList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(orderList);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        totalLabel = new JLabel("Total: $0.00");
        totalLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        JButton checkoutButton = createFlatButton("Checkout", new Color(0, 150, 136), Color.WHITE);
        checkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int orderNumber = generateRandomOrderNumber(); // Generate order number
//                new CheckoutDialog(OnlineMenu.this, currentOrder, orderNumber).setVisible(true);
                new PaymentUI(currentOrder).setVisible(true);
            }
        });

        JButton removeButton = createFlatButton("Remove Item", new Color(233, 30, 99), Color.WHITE);
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = orderList.getSelectedIndex();
                if (selectedIndex >= 0) {
                    currentOrder.getItems().remove(selectedIndex);
                    updateOrderList();
                }
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(removeButton);
        buttonPanel.add(checkoutButton);

        orderPanel.add(new JLabel("Your Order"), BorderLayout.NORTH);
        orderPanel.add(scrollPane, BorderLayout.CENTER);
        orderPanel.add(totalLabel, BorderLayout.SOUTH);
        orderPanel.add(buttonPanel, BorderLayout.PAGE_END);

        return orderPanel;
    }

    private void populateMenuItems() {
        menuItems = new ArrayList<>();
        menuItems.add(new MenuItem("Coffee", 2.50, "src/img/coffee.jpg", "Beverages"));
        menuItems.add(new MenuItem("Tea", 2.00, "src/img/tea.jpg", "Beverages"));
        menuItems.add(new MenuItem("Croissant", 3.50, "src/img/croissant.jpg", "Snacks"));
        menuItems.add(new MenuItem("Sandwich", 6.00, "src/img/sandwich.jpg", "Snacks"));
        menuItems.add(new MenuItem("Salad", 7.50, "src/img/salad.jpg", "Snacks"));
        menuItems.add(new MenuItem("Cake", 4.50, "src/img/cake.jpg", "Desserts"));

        displayMenuItems(menuItems);
    }

    private void displayMenuItems(List<MenuItem> items) {
        menuPanel.removeAll();
        for (MenuItem item : items) {
            JPanel itemPanel = new JPanel(new BorderLayout()) {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    int shadowSize = 5;
                    int panelWidth = getWidth();
                    int panelHeight = getHeight();
                    Graphics2D g2d = (Graphics2D) g.create();
                    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2d.setColor(new Color(0, 0, 0, 50));
                    g2d.fillRoundRect(0, 0, panelWidth, panelHeight, 20, 20);
                    g2d.dispose();
                }
            };
            itemPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
            itemPanel.setBackground(Color.WHITE);
            itemPanel.setPreferredSize(new Dimension(200, 250));

            JLabel imageLabel = new JLabel();
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
            resizeImageIcon(item.getImagePath(), imageLabel);
            itemPanel.add(imageLabel, BorderLayout.CENTER);

            JPanel infoPanel = new JPanel(new GridLayout(2, 1));
            infoPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
            JLabel nameLabel = new JLabel(item.getName());
            nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
            JLabel priceLabel = new JLabel("$" + item.getPrice());
            priceLabel.setHorizontalAlignment(SwingConstants.CENTER);
            infoPanel.add(nameLabel);
            infoPanel.add(priceLabel);
            itemPanel.add(infoPanel, BorderLayout.NORTH);

            JButton addButton = createFlatButton("Add to Order", new Color(0, 150, 136), Color.WHITE);
            addButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    currentOrder.addItem(item);
                    updateOrderList();
                }
            });
            itemPanel.add(addButton, BorderLayout.SOUTH);

            menuPanel.add(itemPanel);
        }
        menuPanel.revalidate();
        menuPanel.repaint();
    }

    private void resizeImageIcon(String imagePath, JLabel label) {
        ImageIcon icon = new ImageIcon(imagePath);
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH);
        label.setIcon(new ImageIcon(newImage));
    }

    private void filterMenuItems() {
        String searchText = searchField.getText().toLowerCase();
        String selectedCategory = (String) categoryComboBox.getSelectedItem();

        List<MenuItem> filteredItems = menuItems.stream()
                .filter(item -> item.getName().toLowerCase().contains(searchText) &&
                        (selectedCategory.equals("All") || item.getCategory().equals(selectedCategory)))
                .collect(Collectors.toList());

        displayMenuItems(filteredItems);
    }

    private void updateOrderList() {
        orderListModel.clear();
        for (MenuItem item : currentOrder.getItems()) {
            orderListModel.addElement(item.getName() + " - $" + item.getPrice());
        }
        totalLabel.setText("Total: $" + currentOrder.getTotalPrice());
    }

    private JButton createFlatButton(String text, Color bgColor, Color fgColor) {
        JButton button = new JButton(text);
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setOpaque(true);
        return button;
    }

    private String generateRandomUserName() {
        String[] names = {"Alice", "Bob", "Charlie", "David", "Emma", "Frank", "Grace", "Henry"};
        Random random = new Random();
        return names[random.nextInt(names.length)];
    }

    private int generateRandomOrderNumber() {
        Random random = new Random();
        return 100000 + random.nextInt(900000);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new OnlineMenu();
            }
        });
    }
}

