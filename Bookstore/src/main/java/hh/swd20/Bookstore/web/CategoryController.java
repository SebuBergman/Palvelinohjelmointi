package hh.swd20.Bookstore.web;

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

import hh.swd20.Bookstore.domain.Category;
import hh.swd20.Bookstore.domain.CategoryRepository;

@CrossOrigin
@Controller
public class CategoryController {
	@Autowired
	private CategoryRepository crepository;
	
	//Categorylist findAll
	@RequestMapping("/categorylist")
	public String categorylist(Model model) {
		model.addAttribute("categories", crepository.findAll());
		return "categorylist";
	}
	
	//Categorylist addCategory
	@RequestMapping(value = "/addcategory")
	public String addCategory(Model model) {
		model.addAttribute("category", new Category());
		return "addcategory";
	}
	
	//Categorylist saveCategory
	@RequestMapping(value = "/savecategory", method = RequestMethod.POST)
	public String save(Category category) {
		crepository.save(category);
		return "redirect:categorylist";
	}
	
	// RESTful service, Category findAll
    @RequestMapping(value="/categories", method = RequestMethod.GET)
    public @ResponseBody List<Category> getDepartmentsRest() {	
        return (List<Category>) crepository.findAll();
    }
    
    // RESTful service, Category findById
    @RequestMapping(value="/categories/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Category> findDepartmentRest(@PathVariable("id") Long dId) {	
    	return crepository.findById(dId);
    }
    
    // RESTful service, Save category
    @RequestMapping(value="/categories", method = RequestMethod.POST)
    public @ResponseBody Category saveStudentRest(@RequestBody Category category) {	
    	return crepository.save(category);
    }
}