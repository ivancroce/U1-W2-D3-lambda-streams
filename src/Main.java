import com.github.javafaker.Faker;
import entities.Customer;
import entities.Order;
import entities.Product;
import enums.OrderStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        Faker faker = new Faker(Locale.ITALY);

        // --------------- Customers -------------

        Customer customer1 = new Customer(001L, faker.dragonBall().character(), 2);
        Customer customer2 = new Customer(002L, faker.dragonBall().character(), 3);
        Customer customer3 = new Customer(003L, faker.dragonBall().character(), 1);
        Customer customer4 = new Customer(004L, faker.dragonBall().character(), 3);
        Customer customer5 = new Customer(005L, faker.dragonBall().character(), 1);

        System.out.println(" --- Customers --- ");
        // allCustomers List
        List<Customer> allCustomers = List.of(customer1, customer2, customer3, customer4, customer5);

        // classic foreach
        /* for (Customer c : allCustomers) {
            System.out.println(c);
        }*/

        // functional foreach
        allCustomers.forEach(c -> System.out.println(c));

        // --------------- Products -------------

        Product book1 = new Product(101L, faker.book().title(), "Books", 110.0);
        Product book2 = new Product(102L, faker.book().title(), "Books", 90.0);
        Product book3 = new Product(201L, faker.book().title(), "Baby", 25.5);
        Product book4 = new Product(301L, faker.book().title(), "Boys", 19.9);
        Product book5 = new Product(302L, faker.book().title(), "Boys", 29.9);

        System.out.println(" --- Products --- ");

        List<Product> allProducts = List.of(book1, book2, book3, book4, book5);
        allProducts.forEach(b -> System.out.println(b));


        // --------------- Orders -------------

        Order order1 = new Order(1001L, OrderStatus.COMPLETED, LocalDate.of(2021, 3, 5), LocalDate.of(2021, 3, 10), List.of(book1), customer1);
        Order order2 = new Order(1002L, OrderStatus.CANCELLED, LocalDate.of(2021, 4, 3), LocalDate.of(2021, 4, 12), List.of(book2), customer2);
        Order order3 = new Order(1003L, OrderStatus.SHIPPED, LocalDate.of(2021, 5, 1), LocalDate.of(2021, 5, 5), List.of(book3), customer3);
        Order order4 = new Order(1004L, OrderStatus.PENDING, LocalDate.of(2021, 3, 15), LocalDate.of(2021, 3, 20), List.of(book1), customer4);

        System.out.println(" --- Orders --- ");

        List<Order> allOrders = List.of(order1, order2, order3, order4);
        allOrders.forEach(o -> System.out.println(o));

        // --------------- Exersice 1 Books > 100 -------------

        System.out.println("--------------- Exersice 1 -------------");

        Predicate<Product> isCategoryBooks = product -> product.getCategory().equals("Books");
        Predicate<Product> isPriceGreaterThan100 = product -> product.getPrice() > 100;

        List<Product> expensiveBooks = allProducts.stream()
                .filter(isCategoryBooks.and(isPriceGreaterThan100)).toList();

        expensiveBooks.forEach(b -> System.out.println(b));

        // --------------- Exersice 2 Orders with Baby -------------
        System.out.println("--------------- Exersice 2 -------------");
        // stream allOrders =>
        // filter For each order, perform a check. The order passes if the check returns true. =>
        // anyMatch check if at least one product in the inner stream belongs to the Baby category. It returns true if at least one product has the category "Baby". =>
        // toList Collect all the orders that passed the filter into a new list.

        List<Order> babyOrders = allOrders.stream()
                .filter(order -> order.getProducts().stream().anyMatch(product -> product.getCategory().equals("Baby")))
                .toList();

        babyOrders.forEach(b -> System.out.println(b));

        // --------------- Exersice 3 Orders with Boys + 10% discount -------------
        System.out.println("--------------- Exersice 3 -------------");

        Predicate<Product> isCategoryBoys = product -> product.getCategory().equals("Boys");

        // to continue
    }

}