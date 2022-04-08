package hhpalvelinohjelmointi.Songdb.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hhpalvelinohjelmointi.Songdb.domain.Album;
import hhpalvelinohjelmointi.Songdb.domain.AlbumRepository;
import hhpalvelinohjelmointi.Songdb.domain.Song;
import hhpalvelinohjelmointi.Songdb.domain.SongRepository;

@Controller
public class SongController {
	@Autowired
	private SongRepository songrepository;
	
	@Autowired
	AlbumRepository albumrepository;
	
	//Login and main page
		//Login mapping for Songdb
		@RequestMapping(value="/login")
		public String login() {
			return "login";
		}
		
		//Login mapping for the song database
		@RequestMapping(value = "/index", method = RequestMethod.GET)
		public String showMainpage(Model model) {
			return "welcome";
		}
	
	//Restful services
		//Restful service. Show all songs aka FindAll songs
		@RequestMapping(value="/songs", method = RequestMethod.GET)
		public @ResponseBody List<Song> songListRest() {
			return (List<Song>) songrepository.findAll();
		}
		
		//Restful service for song database, FindById
		@RequestMapping(value = "/songs/{id}", method = RequestMethod.GET)
		public @ResponseBody Optional<Song> findSongRest(@PathVariable("id") Long songId) {
			return songrepository.findById(songId);
		}
		
		//Restful service for song database, FindById and also get album details
		@RequestMapping(value = "/songs/{id}/albums", method = RequestMethod.GET)
		public @ResponseBody Album findSongsandAlbumRest(@PathVariable("id") Long songId) {
			return songrepository.findById(songId).get().getAlbum();
		}
		
		//Restful service, Save Song
		@RequestMapping(value="/songs", method = RequestMethod.POST)
		public @ResponseBody Song saveSongRest(@RequestBody Song song) {
			return songrepository.save(song);
		}
	
	//All of the endpoints
		//Songs, Show all songs
		@RequestMapping(value="/listsongs")
		public String songlist(Model model) {
			model.addAttribute("songs", songrepository.findAll());
			model.addAttribute("albums", albumrepository.findAll());
			return "songlist";
		}
		
		//Song database addSong
		@RequestMapping(value= "/addsong")
		public String addSong(Model model) {
			model.addAttribute("song", new Song());
			model.addAttribute("albums", albumrepository.findAll());
			return "addsong";
		}
		
		//Song database saveSong
		@RequestMapping(value ="savesong", method = RequestMethod.POST)
		public String save(Song song) {
			songrepository.save(song);
			return "redirect:listsongs";
		}
		
		//Song database editSong details
		@RequestMapping(value = "/songedit{id}", method = RequestMethod.GET)
		public String editSong(@PathVariable("id") Long songId, Model model) {
			model.addAttribute("song", songrepository.findById(songId));
			model.addAttribute("albums", albumrepository.findAll());
			return "editsong";
		}
		
		//Song database deleteSong from database
		//@PreAuthorize("hasAuthority('admin')")
		@RequestMapping(value = "/songdelete/{id}", method = RequestMethod.GET)
		public String deleteSong(@PathVariable("id") Long songId, Model model) {
			songrepository.deleteById(songId);
			return"redirect:../listsongs";
		}
}