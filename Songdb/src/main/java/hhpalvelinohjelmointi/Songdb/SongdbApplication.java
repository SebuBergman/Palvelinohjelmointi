package hhpalvelinohjelmointi.Songdb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hhpalvelinohjelmointi.Songdb.domain.Album;
import hhpalvelinohjelmointi.Songdb.domain.AlbumRepository;
import hhpalvelinohjelmointi.Songdb.domain.Song;
import hhpalvelinohjelmointi.Songdb.domain.SongRepository;
import hhpalvelinohjelmointi.Songdb.domain.User;
import hhpalvelinohjelmointi.Songdb.domain.UserRepository;

@SpringBootApplication
public class SongdbApplication {
	private static final Logger log = LoggerFactory.getLogger(SongdbApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(SongdbApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner Songdatabase(SongRepository songrepository, AlbumRepository albumrepository, UserRepository userrepository) {
		return (args) -> {
			log.info("save a couple of songs");
			Album sighnomore = new Album("Sigh No More", "Mumford & Sons", "Indie Folk, Folk Rock, Bluegrass", 2009);
			albumrepository.save(sighnomore);
			
			Album dawnfm = new Album("Dawn FM", "The Weeknd", "Dance-pop, synth-pop", 2022);
			albumrepository.save(dawnfm);
			
			songrepository.save(new Song("Little Lion Man", "Indie Folk", 4.06, "Mumford & Sons", sighnomore));
			songrepository.save(new Song("Take My Breath", "Dance-Pop", 5.39, "Multiple writers", dawnfm));
			//songrepository.save(new Song("What a Man Gotta Do", "Pop Rock", 3.00, "Jonas Brothers"));
			
			// Create a couple of users: admin and user, sebu with corresponding passwords
			User user1 = new User("user", "$2a$10$Xeh2yIAIZxTK0smH6Ct1UOeblzLjuMBqG0JoS8RJ1w8x0qBX5gUHy", "USER");
			User user2 = new User("admin", "$2a$10$4NEuiOObZgkcfAqA2SgZLOboYvtWZ.cLhXwYOYiwEBr22mUDTqS2a", "ADMIN");
			User user3 = new User("sebu", "$2a$10$2iFAlUL2tzxXRFGp6qfqB.us6EQu322F7ZQVzacQOYRdFihjW9JKC", "SEBU");
			
			userrepository.save(user1);
			userrepository.save(user2);
			userrepository.save(user3);
			
			log.info("fetch all songs");
			for (Song song : songrepository.findAll()) {
				log.info(song.toString());
			}
		};
	}
}