package hh.swd20.Bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.swd20.Bookstore.domain.Book;
import hh.swd20.Bookstore.domain.BookRepository;
import hh.swd20.Bookstore.domain.Category;
import hh.swd20.Bookstore.domain.CategoryRepository;
import hh.swd20.Bookstore.domain.User;
import hh.swd20.Bookstore.domain.UserRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner bookDemo(BookRepository brepository, CategoryRepository crepository, UserRepository userepository) {
		return (args) -> {
			log.info("save a couple of books");
			Category category1 = new Category("Scifi");
			crepository.save(category1);
			Category category2 = new Category("Epic");
			crepository.save(category2);
			Category category3 = new Category("Action & Adventure");
			crepository.save(category3);
			
			brepository.save(new Book("Shadow of the Conqueror", "Shad M. Brooks", 2019, "945-3-16-14-15", 32, category2));
			brepository.save(new Book("The Way of Kings", "Brandon Sanderson", 2010, "9564-17-15-16", 35, category3));
			
			// Create a couple of users: admin and user with corresponding passwords
			User user1 = new User("user", "$2a$10$yYjJovK1WZ2kDMqp11RtbuKoGTpJ3Dn017w8KWy6X38Se3uSe4kP6", "USER");
			User user2 = new User("admin", "$2a$10$kKWgk/FN9a4EMdaN7q3xeu5f.DElxL4E/.wsSsCfQ4.ol3mtlF5M.", "ADMIN");
			userepository.save(user1);
			userepository.save(user2);
			

			log.info("fetch all books");
			for (Book book : brepository.findAll()) {
				log.info(book.toString());
			}
		};
	}
}
