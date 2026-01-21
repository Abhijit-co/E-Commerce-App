import java.util.*;

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
            System.out.println("\n----------------------------------------");
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

            switch (choice) {
                case 1:
                    System.out.printf("%-5s %-15s %-10s %-10s%n",
                            "No", "Product", "Price", "Stock");
                    for (int i = 0; i < products.length; i++) {
                        System.out.printf("%-5d %-15s %-10.2f %-10d%n",
                                i + 1,
                                products[i].name,
                                products[i].price,
                                products[i].stock);
                    }
                    break;

                case 2:
                    System.out.print("Enter product number: ");
                    int index = sc.nextInt() - 1;
                    System.out.print("Enter quantity: ");
                    int qty = sc.nextInt();

                    if (index >= 0 && index < products.length) {
                        cart.addItem(products[index], qty);
                    } else {
                        System.out.println("Invalid product selection");
                    }
                    break;

                case 3:
                    sc.nextLine();
                    System.out.print("Enter product name: ");
                    cart.removeItem(sc.nextLine());
                    break;

                case 4:
                    sc.nextLine();
                    System.out.print("Enter product name: ");
                    String incName = sc.nextLine();
                    System.out.print("Enter quantity to increase: ");
                    cart.increaseQuantity(incName, sc.nextInt());
                    break;

                case 5:
                    sc.nextLine();
                    System.out.print("Enter product name: ");
                    String decName = sc.nextLine();
                    System.out.print("Enter quantity to decrease: ");
                    cart.decreaseQuantity(decName, sc.nextInt());
                    break;

                case 6:
                    cart.viewCartAndBill();
                    break;

                case 7:
                    System.out.println("Thank you for using the shopping cart system");
                    return;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}

