package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	// display list of employees
	@GetMapping("/")
	public String viewHomePage(Model model) {
		
		//model.addAttribute("listEmployees", employeeService.getAllEmployees());
        //return "index";
    	//Added for pagination logic
        //return findPaginated(1, model);
    	//Added for pagination and sorting logic
        return findPaginated(1, "firstName", "asc", model);
        	
	}
	
	@GetMapping("/showNewEmployeeForm")
	public String showNewEmployeeForm(Model model) {
		// create model attribute to bind form data
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		return "new_employee";
	}
	
	@PostMapping("/saveEmployee")
	public String saveEmployee(Model model,@Valid @ModelAttribute("employee") Employee employee,BindingResult bindResult) {
		if(bindResult.hasErrors())
    	{
			return "new_employee";
    	}
		else
    	{
        try {
        	employeeService.saveEmployee(employee);
        }
        catch (Exception e) {
			 model.addAttribute("errorMessage", e.getMessage());
			 return "new_employee";
		}	
		return "redirect:/";
    	}
	}	
    
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable ( value = "id") long id, Model model) {
		
		// get employee from the service
		Employee employee = employeeService.getEmployeeById(id);
		
		// set employee as a model attribute to pre-populate the form
		model.addAttribute("employee", employee);
		return "update_employee";
	}
	
	@GetMapping("/deleteEmployee/{id}")
	public String deleteEmployee(@PathVariable (value = "id") long id) {
		
		// call delete employee method 
		this.employeeService.deleteEmployeeById(id);
		return "redirect:/";
	}
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5;
		
		Page<Employee> page = employeeService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Employee> listEmployees = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		//Added sorting logic
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listEmployees", listEmployees);
		return "index";
	}
	
	   @RequestMapping("/search")
	    public String search(Model model) {
		   Employee employee = new Employee();
	        model.addAttribute("employee", employee);
	        return "search_employee";
	    }
	    
	    @RequestMapping("/searchEmployee")
	    public String searchEmployee(@RequestParam(name = "id", defaultValue = "1") int id,Model model) {
	    	Employee employee=employeeService.getEmployeeById(id);
	        List<Employee> employees =new ArrayList<>();
	        Optional<Employee> employeeExists = Optional.ofNullable(employee);
	        if(employeeExists.isPresent()) {
	        	employees.add(employee);
	        	model.addAttribute("listEmployees", employees);
	        	return "index";
	        }
	        else {
	        	Employee employee1 = new Employee();
	            model.addAttribute("employee", employee1);
	        	model.addAttribute("errorMessage","Employee does not exist !! ");
	        	return "search_employee";
	        }
	    }
	    

}
