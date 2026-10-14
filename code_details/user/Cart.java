package code_details.user;
import code_details.interfaces.*;
import code_details.common.*;
import code_details.admin.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Cart {
    private JFrame cartFrame;
    private JPanel cartPanel;
    private JLabel totalLabel;
    private double totalPrice = 0.0;
    private ArrayList<CartItem> cartItems;
    private JButton checkoutButton, clearButton;

    public Cart() {
        cartItems = new ArrayList<>();
        setupCartFrame();
    }

    private void setupCartFrame() {
        cartFrame = new JFrame("Shopping Cart");
        cartFrame.setSize(400, 500);
        cartFrame.setResizable(false);
        cartFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        cartFrame.setLayout(new BorderLayout());

        cartPanel = new JPanel();
        cartPanel.setLayout(new BoxLayout(cartPanel, BoxLayout.Y_AXIS)); // Using BoxLayout for vertical arrangement
        cartPanel.setBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(cartPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        totalLabel = new JLabel("Total: TK 0.0");
        totalLabel.setFont(new Font("Arial", Font.BOLD, 18));
        totalLabel.setForeground(Color.BLUE);
        totalLabel.setHorizontalAlignment(JLabel.CENTER);
        totalLabel.setBorder(null);
        
        JPanel bottomPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        checkoutButton = new JButton("Checkout");
        checkoutButton.setBackground(new Color(34, 177, 76));
        checkoutButton.setForeground(Color.WHITE);
        checkoutButton.setFont(new Font("Arial", Font.BOLD, 14));
        checkoutButton.addActionListener(e ->new Payment());

        clearButton = new JButton("Clear Cart");
        clearButton.setBackground(new Color(200, 0, 0));
        clearButton.setForeground(Color.WHITE);
        clearButton.setFont(new Font("Arial", Font.BOLD, 14));
        clearButton.addActionListener(e -> clearCart());
        
        bottomPanel.add(clearButton);
        bottomPanel.add(checkoutButton);

        cartFrame.add(scrollPane, BorderLayout.CENTER);
        cartFrame.add(totalLabel, BorderLayout.NORTH);
        cartFrame.add(bottomPanel, BorderLayout.SOUTH);
    }

    public void addToCart(String name, double price, int quantity) {
        if (quantity <= 0) {
            JOptionPane.showMessageDialog(null, "Invalid quantity!");
            return;
        }

        CartItem item = new CartItem(name, price, quantity);
        cartItems.add(item);
        totalPrice += price * quantity;

        refreshCart(); // Refresh the cart display after adding an item
    }

    private void refreshCart() {
        cartPanel.removeAll(); // Remove all previous items from the panel

        // Add each item as a new panel in the cart
        for (CartItem item : cartItems) {
            JPanel itemPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
            itemPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
            itemPanel.setBackground(Color.WHITE);

            JLabel itemLabel = new JLabel(item.toString());
            itemLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            itemPanel.add(itemLabel);

            cartPanel.add(itemPanel);
        }

        totalLabel.setText("Total: TK " + totalPrice); // Update total price
         cartPanel.add(new JPanel()); // Fill the remaining space in the panel

        cartPanel.revalidate(); // Automatically handled by BoxLayout
        cartPanel.repaint(); // Redraw the cart panel
    }

    private void clearCart() {
        cartItems.clear(); // Clear all items from the cart
        totalPrice = 0.0; // Reset total price
        refreshCart(); // Refresh the cart display
    }

    public void showCart() {
        cartFrame.setVisible(true); // Show the cart window
    }
}

class CartItem {
    private String name;
    private double price;
    private int quantity;

    public CartItem(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String toString() {
        return name + " TK " + price + " x " + quantity;
    }
}