package hhpalvelinohjelmointi.Songdatabase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hhpalvelinohjelmointi.Songdatabase.domain.Album;
import hhpalvelinohjelmointi.Songdatabase.domain.AlbumRepository;
import hhpalvelinohjelmointi.Songdatabase.domain.Song;
import hhpalvelinohjelmointi.Songdatabase.domain.SongRepository;

@SpringBootApplication
public class SongdatabaseApplication {
	private static final Logger log = LoggerFactory.getLogger(SongdatabaseApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SongdatabaseApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookDemo(SongRepository songrepository, AlbumRepository albumrepository) {
		return (args) -> {
			log.info("save a couple of songs");
			Album sighnomore = new Album("Sigh No More", "Mumford & Sons", "Indie Folk, Folk Rock, Bluegrass", 2009);
			albumrepository.save(sighnomore);
			
			Album dawnfm = new Album("Dawn FM", "The Weeknd", "Dance-pop, synth-pop", 2022);
			albumrepository.save(dawnfm);
			
			songrepository.save(new Song("Little Lion Man", "Indie Folk", 4.06, "Mumford & Sons", sighnomore));
			songrepository.save(new Song("Take My Breath", "Dance-Pop", 5.39, "Multiple writers", dawnfm));
			songrepository.save(new Song("What a Man Gotta Do", "Pop Rock", 3.00, "Jonas Brothers"));
			
			log.info("fetch all songs");
			for (Song song : songrepository.findAll()) {
				log.info(song.toString());
			}
		};
	}
}