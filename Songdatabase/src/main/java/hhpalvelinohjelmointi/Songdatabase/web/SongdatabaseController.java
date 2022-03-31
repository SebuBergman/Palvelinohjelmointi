package hhpalvelinohjelmointi.Songdatabase.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;

import hhpalvelinohjelmointi.Songdatabase.domain.AlbumRepository;
import hhpalvelinohjelmointi.Songdatabase.domain.Song;
import hhpalvelinohjelmointi.Songdatabase.domain.SongRepository;

@Controller
public class SongdatabaseController {
	@Autowired
	private SongRepository songrepository;
	
	@Autowired
	AlbumRepository albumrepository;
	
	//Songs, Show all songs
	@RequestMapping(value="/listsongs")
	public String songlist(Model model) {
		model.addAttribute("songs", songrepository.findAll());
		model.addAttribute("album", albumrepository.findAll());
		return "songlist";
	}
	
	//Login mapping for the song database
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String showMainpage(Model model) {
		return "welcomepage";
	}
	
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
	
	//Restful service, Save Book
	@RequestMapping(value="/song", method = RequestMethod.POST)
	public @ResponseBody Song saveSongRest(@RequestBody Song song) {
		return songrepository.save(song);
	}
	
	//Song database addSong
	@RequestMapping(value= "/add")
	public String addSong(Model model) {
		model.addAttribute("song", new Song());
		model.addAttribute("albums", albumrepository.findAll());
		return "addsong";
	}
	
	//Song database saveSong
	@RequestMapping(value ="savesong", method = RequestMethod.POST)
	public String save(Song song) {
		songrepository.save(song);
		return "redirect:songlist";
	}
	
	//Song database editSong details
	@RequestMapping(value = "/edit{id}", method = RequestMethod.GET)
	public String editSong(@PathVariable("id") Long songId, Model model) {
		model.addAttribute("song", songrepository.findById(songId));
		model.addAttribute("albums", albumrepository.findAll());
		return "editsong";
	}
	
	//Song database deleteBook from database
	@PreAuthorize("hasAuthority('admin')")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteSong(@PathVariable("id") Long songId, Model model) {
		songrepository.deleteById(songId);
		return"redirect:../songlist";
	}
}