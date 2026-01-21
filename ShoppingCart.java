import java.util.*;

public class ShoppingCart {

    List<CartItem> cart = new ArrayList<>();
    final double TAX_RATE = 0.05;

    void addItem(Product product, int qty) {
        if (qty <= 0) {
            System.out.println("Invalid quantity");
            return;
        }

        if (qty > product.stock) {
            System.out.println("Cannot add item beyond available stock");
            return;
        }

        for (CartItem item : cart) {
            if (item.product.name.equalsIgnoreCase(product.name)) {
                item.quantity += qty;
                product.stock -= qty;
                System.out.println("Item added to cart successfully");
                return;
            }
        }

        cart.add(new CartItem(product, qty));
        product.stock -= qty;
        System.out.println("Item added to cart successfully");
    }

    void removeItem(String productName) {
        Iterator<CartItem> iterator = cart.iterator();

        while (iterator.hasNext()) {
            CartItem item = iterator.next();
            if (item.product.name.equalsIgnoreCase(productName)) {
                item.product.stock += item.quantity;
                iterator.remove();
                System.out.println("Item removed from cart successfully");
                return;
            }
        }
        System.out.println("Item not found in cart");
    }

    void increaseQuantity(String productName, int qty) {
        for (CartItem item : cart) {
            if (item.product.name.equalsIgnoreCase(productName)) {
                if (qty > item.product.stock) {
                    System.out.println("Cannot increase quantity beyond available stock");
                    return;
                }
                item.quantity += qty;
                item.product.stock -= qty;
                System.out.println("Cart quantity increased successfully");
                return;
            }
        }
        System.out.println("Item not found in cart");
    }

    void decreaseQuantity(String productName, int qty) {
        Iterator<CartItem> it = cart.iterator();

        while (it.hasNext()) {
            CartItem item = it.next();
            if (item.product.name.equalsIgnoreCase(productName)) {

                if (qty >= item.quantity) {
                    item.product.stock += item.quantity;
                    it.remove();
                    System.out.println("Item removed from cart successfully");
                } else {
                    item.quantity -= qty;
                    item.product.stock += qty;
                    System.out.println("Cart quantity decreased successfully");
                }
                return;
            }
        }
        System.out.println("Item not found in cart");
    }

    void viewCartAndBill() {
        if (cart.isEmpty()) {
            System.out.println("Cart is empty");
            return;
        }

        double subtotal = 0;

        System.out.println("\n========================================");
        System.out.println("Cart Products");
        System.out.println("========================================");
        System.out.printf("%-15s %-10s %-10s%n", "Product", "Quantity", "Total");

        for (CartItem item : cart) {
            double total = item.getTotalPrice();
            subtotal += total;
            System.out.printf("%-15s %-10d %-10.2f%n",
                    item.product.name,
                    item.quantity,
                    total);
        }

        double tax = subtotal * TAX_RATE;
        double finalAmount = subtotal + tax;

        System.out.println("----------------------------------------");
        System.out.printf("%-25s %.2f%n", "Subtotal", subtotal);
        System.out.printf("%-25s %.2f%n", "Tax 5 percent", tax);
        System.out.println("----------------------------------------");
        System.out.printf("%-25s %.2f%n", "Final Amount", finalAmount);
        System.out.println("----------------------------------------");
    }
}

