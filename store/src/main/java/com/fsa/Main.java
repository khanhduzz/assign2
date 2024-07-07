package com.fsa;

import com.fsa.entities.Customer;
import com.fsa.entities.Order;
import com.fsa.entities.OrderLine;
import com.fsa.entities.Song;
import com.fsa.repositories.CustomerRepository;
import com.fsa.repositories.SongRepository;
import com.fsa.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
//        createConnection();
//        createSong();
//        createProduct();
//        createCustomer();
        System.out.println(CustomerRepository.findById(1L));
    }

    public static void createConnection () {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            System.out.println("Session open");
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Exception caught");
        }
    }

    public static void createProduct () {
//        Product product = new Product();
//        product.setTitle("Product");
//        product.setDescription("Product Description");
//
//        ProductRepository.createProduct(product);
    }

//    public static void createProduct () {
//        Product product = Product.builder()
//                .title("Product")
//                .description("Description")
//                .unitPrice(45.0)
//                .genre("Comedy")
//                .language("English")
//                .build();
//
//        Song song = Song.builder()
//                .duration(4.5)
//                .lyrics("This is lyrics")
//                .build();
//
//        song.setProduct(product);
//        ProductRepository.createProduct(product);
//        System.out.println(ProductRepository.findByName("Product"));
//    }

    public static void createSong () {
        Song song = new Song();
        song.setTitle("Song");
        song.setDuration(4.5);
        song.setLyrics("This is lyrics");

        System.out.println(SongRepository.createSong(song));
    }

    public static void createCustomer () {
        Customer customer = new Customer();
        customer.setEmail("customer@gmail.com");
        customer.setName("Customer Name");
        customer.setPhone("029373523");

        Song song = new Song();
        song.setTitle("Song");
        song.setDuration(4.5);
        song.setLyrics("This is lyrics");
        song.setDescription("This is description");
        song.setLanguage("English");
        song.setReleaseDate(LocalDate.now());
        song.setUnitPrice(45.0);
        song.setGenre("Country");

        Order order = new Order();
        order.setOrderDate(LocalDate.now());

        OrderLine orderLine = new OrderLine();
        orderLine.setUnitPrice(45.0);
        orderLine.setQuantity(10);

        OrderLine orderLine2 = new OrderLine();
        orderLine2.setUnitPrice(45.0);
        orderLine2.setQuantity(10);

        order.addOrderLine(orderLine);
        order.addOrderLine(orderLine2);

        customer.addOrder(order);

        System.out.println(CustomerRepository.addCustomer(customer));
        System.out.println(SongRepository.createSong(song));

        song.addOrderLine(orderLine);
        song.addOrderLine(orderLine2);

        System.out.println(SongRepository.updateSong(song));
    }
}
