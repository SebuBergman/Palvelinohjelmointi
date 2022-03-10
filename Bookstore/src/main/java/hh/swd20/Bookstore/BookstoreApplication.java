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

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner bookDemo(BookRepository brepository, CategoryRepository crepository) {
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

			log.info("fetch all books");
			for (Book book : brepository.findAll()) {
				log.info(book.toString());
			}
		};
	}
}
