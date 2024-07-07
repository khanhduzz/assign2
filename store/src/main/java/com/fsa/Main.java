package com.fsa;

import com.fsa.entities.Customer;
import com.fsa.entities.Movie;
import com.fsa.entities.Order;
import com.fsa.entities.OrderLine;
import com.fsa.entities.Song;
import com.fsa.repositories.CustomerRepository;
import com.fsa.repositories.EbookRepository;
import com.fsa.repositories.MovieRepository;
import com.fsa.repositories.OrderRepository;
import com.fsa.repositories.SongRepository;
import com.fsa.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;

public class Main {

    public static final String LINE = "---------------------------------------";

    public static void main(String[] args) {
        crudNoHQLCase();
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

    public static void crudNoHQLCase () {
        Customer customer = new Customer();
        customer.setEmail("david_malan@gmail.com");
        customer.setName("David Mala");
        customer.setPhone("029373523");

        Song song = new Song();
        song.setTitle("Runaway");
        song.setDuration(4.5);
        song.setLyrics("a thousand years before...");
        song.setDescription("Aurora Grammy 2016");
        song.setLanguage("English");
        song.setReleaseDate(LocalDate.now());
        song.setUnitPrice(45.0);
        song.setGenre("Country");

        Movie movie = new Movie();
        movie.setTitle("Runaway");
        movie.setDuration(4.5);
        movie.setReleaseDate(LocalDate.now());
        movie.setLanguage("English");
        movie.setGenre("Detective");
        movie.setDescription("a good movie...");
        movie.setReviews("Just a review...");
        movie.setUnitPrice(5832.5);

        OrderLine orderSong = new OrderLine();
        orderSong.setUnitPrice(45.0);
        orderSong.setQuantity(10);
        orderSong.setProduct(song);

        OrderLine orderMovie = new OrderLine();
        orderMovie.setUnitPrice(45.0);
        orderMovie.setQuantity(10);
        orderMovie.setProduct(movie);

        Order order = new Order();
        order.setOrderDate(LocalDate.now());
        order.addOrderLine(orderSong);
        order.addOrderLine(orderMovie);

        customer.addOrder(order);
        CustomerRepository.addCustomer(customer);

        System.out.println(LINE);
        System.out.println("Get all customers");
        System.out.println(CustomerRepository.getAllCustomers());

        System.out.println(LINE);
        System.out.println("Get all products");
        System.out.println(SongRepository.getAllSongsWithDetails());
        System.out.println(MovieRepository.getAllMoviesWithDetails());
        System.out.println(EbookRepository.getAllEbooksWithDetails());

        System.out.println(LINE);
        System.out.println("Get all orders");
        System.out.println(OrderRepository.getAllOrders());

        System.out.println(LINE);
        System.out.println("Update product, then check order");
        song.setTitle("Song update");
        SongRepository.updateSong(song);
        System.out.println(OrderRepository.getAllOrders());

        System.out.println(LINE);
        System.out.println("Delete customer, then all orders belong to customer will be deleted");
        CustomerRepository.deleteCustomer(customer);
        System.out.println(OrderRepository.getAllOrders());

    }
}
