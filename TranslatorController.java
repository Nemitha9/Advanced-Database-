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

import com.mgmt.model.Login;
import com.mgmt.model.Translator;
import com.mgmt.repository.TranslatorRepository;

@RequestMapping("/translator")
@Controller
public class TranslatorController {

	@Autowired
	TranslatorRepository repo;

	@RequestMapping()
	public String login(Model model) {
		return "translator_login";
	}

	@RequestMapping("/logout")
	public String logout(Model model, HttpServletRequest req) {
		req.getSession().invalidate();
		return "logout";
	}

	@PostMapping("/login")
	public String show(Login login, Model model, HttpServletRequest request) {
		if(login.getEmail().equals("admin") && login.getPassword().equals("admin"))
		{
			request.getSession().setAttribute("id", "1001");
			request.getSession().setAttribute("userid", "STAFF00001");
			request.getSession().setAttribute("usertype", "admin");
			request.getSession().setAttribute("name", "Admin");
			return "translator_home";
			
		}
		Optional<Translator> obj = repo.findByEmailIdAndPassword(login.getEmail(),login.getPassword());		
		if(obj.isPresent())
		{
			if (obj.get().getApprovalStatus().equals("Approved")) {
			model.addAttribute("name",obj.get().getName());
			request.getSession().setAttribute("id", obj.get().getId());
			request.getSession().setAttribute("userid", obj.get().getTransId());
			request.getSession().setAttribute("usertype", "staff");
			request.getSession().setAttribute("name", obj.get().getName());
			return "translator_home";
			}
			else {
				model.addAttribute("msg", "Your Account is not yet Approved");
				return "translator_login";
			}
		}
		else
		{
			model.addAttribute("msg","Invalid Login Credentials");
			return "translator_login";
		}
	}

	@RequestMapping("/list")
	public String home(Model model, HttpServletRequest request) {
		model.addAttribute("datalist", repo.findAll());
		return "translator";
	}

	@RequestMapping("/create")
	public String create(Model model, HttpServletRequest request) {
		return "translator_create";
	}

	@RequestMapping("/save")
	public String save(Translator obj) {
		Optional<Translator> idobj = repo.findTopByOrderByIdDesc();
		String id = null;
		if (idobj.isPresent()) {
			int idnum = Integer.parseInt(idobj.get().getTransId().substring(5));
			idnum++;
			id = "EMPL0" + idnum;
		} else {
			id = "EMPL021301";
		}
		obj.setTransId(id);
		obj.setApprovalStatus("UnApproved");

		repo.save(obj);
		return "redirect:/translator";
	}

	@RequestMapping("/show/{id}")
	public String show(@PathVariable String id, Model model, HttpServletRequest request) {
		model.addAttribute("obj", repo.findById(id).get());
		return "translator_show";
	}

	@RequestMapping("/approve/{id}")
	public String approve(@PathVariable String id, Model model) {
		Translator trans = repo.findById(id).get();
		trans.setApprovalStatus("Approved");
		repo.save(trans);
		return "redirect:/translator/list";
	}

	@RequestMapping("/delete")
	public String delete(@RequestParam String id) {
		Optional<Translator> obj = repo.findById(id);
		repo.delete(obj.get());

		return "redirect:/translator/list";
	}

	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable String id, Model model) {
		model.addAttribute("obj", repo.findById(id).get());
		return "translator_edit";
	}

	@RequestMapping("/update")
	public String update(Translator obj) {
		repo.save(obj);
		return "redirect:/translator/show/" + obj.getId();
	}
}
