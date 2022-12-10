package com.mgmt.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mgmt.model.Customer;
import com.mgmt.model.Login;
import com.mgmt.repository.CustomerRepository;
import com.mgmt.repository.RequirementRepository;

@RequestMapping("/customer")
@Controller
public class CustomerController {

	@Autowired
	CustomerRepository repo;
	
	@Autowired
	RequirementRepository reqRepo;
	
	@RequestMapping()
    public String login(Model model) {
        return "cust_login";
    }	
	
	
	@RequestMapping("index")
    public String index(Model model) {
        return "index";
    }	
	
	@RequestMapping("/home")
    public String home(Model model, HttpServletRequest req) {

		model.addAttribute("datalist", reqRepo.findAll());
		return "cust_home";
    }
	
	@RequestMapping("/logout")
    public String logout(Model model, HttpServletRequest req) {
        req.getSession().invalidate();
        return "logout";
    }
	
	@PostMapping("/login")
	public String show(Login login, Model model, HttpServletRequest request) {
		Optional<Customer> obj = repo.findByEmailIdAndPassword(login.getEmail(),login.getPassword());
		if(obj.isPresent())
		{
			model.addAttribute("name",obj.get().getName());
			request.getSession().setAttribute("id", obj.get().getId());
			request.getSession().setAttribute("userid", obj.get().getCustomerId());
			request.getSession().setAttribute("usertype", "customer");
			request.getSession().setAttribute("name", obj.get().getName());
			return "redirect:/customer/home";
		}
		else
		{
			model.addAttribute("msg","Invalid Login Credentials");
			return "cust_login";
		}
	}
	
	@RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("datalist", repo.findAll());
        return "cust";
    }
	
	@RequestMapping("/create")
	public String create(Model model) {
		return "cust_create";
	}
	
	@RequestMapping("/save")
	public String save( Customer obj){
		Optional<Customer> idobj = repo.findTopByOrderByIdDesc();
		String id = null;
		if(idobj.isPresent())
		{
			int idnum = Integer.parseInt(idobj.get().getCustomerId().substring(5));
			idnum++;
			id = "CUST0"+idnum;
		}
		else
		{
			id = "CUST064901";
		}
		
		obj.setCustomerId(id);
		repo.save(obj);		
		return "redirect:/customer";
	}
	
	@RequestMapping("/show/{id}")
	public String show(@PathVariable String id, Model model) {
		model.addAttribute("obj",repo.findById(id).get());
		return "cust_show";
	}
	
	 @RequestMapping("/delete")
	    public String delete(@RequestParam String id) {
	        Optional<Customer> obj = repo.findById(id);
	        repo.delete(obj.get());

	        return "redirect:/customer/list";
	    }
	    
	    @RequestMapping("/edit")
	    public String edit( Model model, HttpServletRequest req) {
	        model.addAttribute("obj", repo.findByCustomerId(req.getSession().getAttribute("userid").toString()).get());
	       System.out.println( repo.findByCustomerId(req.getSession().getAttribute("userid").toString()).get());
	        return "cust_edit";
	    }
	    
	    @RequestMapping("/update")
	    public String update(Customer obj) {
	        repo.save(obj);
	        return "redirect:/customer/home/";
	    }
}
