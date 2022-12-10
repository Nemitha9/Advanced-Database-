package com.mgmt.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mgmt.model.Application;
import com.mgmt.model.Requirement;
import com.mgmt.repository.ApplicationRepository;
import com.mgmt.repository.RequirementRepository;

@RequestMapping("/application")
@Controller
public class ApplicationController {

	@Autowired
	ApplicationRepository repo;
	
	@Autowired
	RequirementRepository reqRepo;
	
	
	@RequestMapping("/list/trans")
    public String listtrans(Model model, HttpServletRequest req) {
        model.addAttribute("datalist", repo.findAllByTransId(req.getSession().getAttribute("userid").toString()).get());
        return "application_trans";
    }

	@RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("datalist", repo.findAll());
        return "application";
    }

	@RequestMapping("/save")
	public String save(@RequestParam String reqId,@RequestParam String msg, HttpServletRequest request ){
		
		Optional<Application> idobj = repo.findTopByOrderByIdDesc();
		String id = null;
		if(idobj.isPresent())
		{
			int idnum = Integer.parseInt(idobj.get().getAppId().substring(5));
			idnum++;
			id = "APPL0"+idnum;
		}
		else
		{
			id = "APPL064901";
		}
		Application obj = new Application();
		obj.setAppId(id);
		obj.setReqId(reqId);
		obj.setMessage(msg);
		obj.setTransId(request.getSession().getAttribute("userid").toString());
		obj.setStatus("Requested");
		repo.save(obj);		
		return "redirect:/application/list";
	}
	
	@RequestMapping("/approve/{appId}")
	public String approve(@PathVariable String appId, Model model) {
		Application app = repo.findByAppId(appId).get();
		app.setStatus("Approved");
		repo.save(app);
		
		Requirement req = reqRepo.findByReqId(app.getReqId()).get();
		req.setStatus("Approved");
		reqRepo.save(req);
		return "redirect:/req/show/reqid/"+ app.getReqId();
	}
	
	
}
