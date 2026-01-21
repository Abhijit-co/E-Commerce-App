import java.util.*;

class Product {
    String name;
    double price;
    int stock;

    Product(String name, double price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }
}

class CartItem {
    Product product;
    int quantity;

    CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    double getTotalPrice() {
        return product.price * quantity;
    }
}

class ShoppingCart {
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
        for (Iterator<CartItem> it = cart.iterator(); it.hasNext();) {
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

        System.out.println();
        System.out.println("========================================");
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

public class ECommerceApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Product[] products = {
                new Product("Laptop", 999.99, 5),
                new Product("Phone", 499.50, 10),
                new Product("Headphones", 89.00, 15)
        };

        ShoppingCart cart = new ShoppingCart();

        System.out.println("========================================");
        System.out.println("        SIMPLE SHOPPING CART SYSTEM      ");
        System.out.println("========================================");

        while (true) {
            System.out.println();
            System.out.println("----------------------------------------");
            System.out.println("Main Menu");
            System.out.println("----------------------------------------");
            System.out.println("1 View Available Products");
            System.out.println("2 Add Item to Cart");
            System.out.println("3 Remove Item from Cart");
            System.out.println("4 Increase Cart Quantity");
            System.out.println("5 Decrease Cart Quantity");
            System.out.println("6 View Cart and Bill");
            System.out.println("7 Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            if (choice == 1) {
                System.out.println();
                System.out.println("----------------------------------------");
                System.out.println("Available Products");
                System.out.println("----------------------------------------");
                System.out.printf("%-5s %-15s %-10s %-10s%n",
                        "No", "Product", "Price", "Stock");

                for (int i = 0; i < products.length; i++) {
                    System.out.printf("%-5d %-15s %-10.2f %-10d%n",
                            i + 1,
                            products[i].name,
                            products[i].price,
                            products[i].stock);
                }
                System.out.println("----------------------------------------");
            }

            else if (choice == 2) {
                System.out.print("Enter product number: ");
                int index = sc.nextInt() - 1;

                System.out.print("Enter quantity: ");
                int qty = sc.nextInt();

                if (index >= 0 && index < products.length) {
                    cart.addItem(products[index], qty);
                } else {
                    System.out.println("Invalid product selection");
                }
            }

            else if (choice == 3) {
                sc.nextLine();
                System.out.print("Enter product name: ");
                String name = sc.nextLine();
                cart.removeItem(name);
            }

            else if (choice == 4) {
                sc.nextLine();
                System.out.print("Enter product name: ");
                String name = sc.nextLine();
                System.out.print("Enter quantity to increase: ");
                int qty = sc.nextInt();
                cart.increaseQuantity(name, qty);
            }

            else if (choice == 5) {
                sc.nextLine();
                System.out.print("Enter product name: ");
                String name = sc.nextLine();
                System.out.print("Enter quantity to decrease: ");
                int qty = sc.nextInt();
                cart.decreaseQuantity(name, qty);
            }

            else if (choice == 6) {
                cart.viewCartAndBill();
            }

            else if (choice == 7) {
                System.out.println("Thank you for using the shopping cart system");
                break;
            }

            else {
                System.out.println("Invalid choice");
            }
        }
    }
}
