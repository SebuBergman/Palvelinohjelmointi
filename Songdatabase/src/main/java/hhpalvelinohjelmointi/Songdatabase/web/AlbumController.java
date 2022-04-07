package hhpalvelinohjelmointi.Songdatabase.web;

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

import hhpalvelinohjelmointi.Songdatabase.domain.Album;
import hhpalvelinohjelmointi.Songdatabase.domain.AlbumRepository;

@CrossOrigin
@Controller
public class AlbumController {
	@Autowired
	private AlbumRepository albumrepository;
	
	//Albumlist findAll
	@RequestMapping("albumlist")
	public String albumlist(Model model) {
		model.addAttribute("album", albumrepository.findAll());
		return "albumlist";
	}
	
	//Albumlist addAlbum
	@RequestMapping(value = "/addalbum")
	public String addAlbum(Model model) {
		model.addAttribute("album", new Album());
		return "addalbum";
	}
	
	//Albumlist saveCategory
	@RequestMapping(value = "/savecategory", method = RequestMethod.POST)
	public String save(Album album) {
		albumrepository.save(album);
		return "redirect:categorylist";
	}
		
	// RESTful service, Album findAll
	@RequestMapping(value="/categories", method = RequestMethod.GET)
	public @ResponseBody List<Album> getDepartmentsRest() {	
		return (List<Album>) albumrepository.findAll();
	}
	
	// RESTful service, Album findById
	@RequestMapping(value="/categories/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Album> findDepartmentRest(@PathVariable("id") Long dId) {	
		return albumrepository.findById(dId);
	}
	
	// RESTful service, Save albums
	@RequestMapping(value="/categories", method = RequestMethod.POST)
	public @ResponseBody Album saveStudentRest(@RequestBody Album album) {	
		return albumrepository.save(album);
	}
}
