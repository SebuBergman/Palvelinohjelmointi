package hhpalvelinohjelmointi.Songdb.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hhpalvelinohjelmointi.Songdb.domain.Album;
import hhpalvelinohjelmointi.Songdb.domain.AlbumRepository;

@CrossOrigin
@Controller
public class AlbumController {
	@Autowired
	private AlbumRepository albumrepository;
	
	//Albumlist findAll
	@RequestMapping("/albumlist")
	public String albumlist(Model model) {
		model.addAttribute("albums", albumrepository.findAll());
		return "albumlist";
	}
	
	//Albumlist addAlbum
	@RequestMapping(value = "/addalbum")
	public String addAlbum(Model model) {
		model.addAttribute("album", new Album());
		return "addalbum";
	}
	
	//Albumlist saveCategory
	@RequestMapping(value = "/savealbum", method = RequestMethod.POST)
	public String save(Album album) {
		albumrepository.save(album);
		return "redirect:albumlist";
	}
		
	// RESTful service, Album findAll
	@RequestMapping(value="/albums", method = RequestMethod.GET)
	public @ResponseBody List<Album> getAlbumRest() {	
		return (List<Album>) albumrepository.findAll();
	}
	
	// RESTful service, Album findById
	@RequestMapping(value="/albums/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Album> findAlbumRest(@PathVariable("id") Long albumId) {	
		return albumrepository.findById(albumId);
	}
	
	// RESTful service, Save albums
	@RequestMapping(value="/albums", method = RequestMethod.POST)
	public @ResponseBody Album saveAlbumRest(@RequestBody Album album) {	
		return albumrepository.save(album);
	}
}