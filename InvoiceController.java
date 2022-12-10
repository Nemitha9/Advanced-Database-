package com.mgmt.controller;

import java.text.ParseException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mgmt.model.Application;
import com.mgmt.model.Deposit;
import com.mgmt.model.Invoice;
import com.mgmt.model.Requirement;
import com.mgmt.repository.ApplicationRepository;
import com.mgmt.repository.DepositRepository;
import com.mgmt.repository.InvoiceRepository;
import com.mgmt.repository.RequirementRepository;

@RequestMapping("/invoice")
@Controller
public class InvoiceController {

	@Autowired
	InvoiceRepository repo;

	@Autowired
	RequirementRepository reqRepo;

	@Autowired
	DepositRepository depRepo;
	
	@Autowired
	ApplicationRepository apRepo;

	@RequestMapping("/list")
	public String list(Model model) {
		model.addAttribute("datalist", repo.findAll());
		return "invoice";
	}

	@RequestMapping("/list/cust")
	public String listcust(Model model, HttpServletRequest req) {
		model.addAttribute("datalist", repo.findAllByCustomerId(req.getSession().getAttribute("userid").toString()).get());
		return "invoice_cust";
	}

	@RequestMapping("/list/trans")
	public String listtrans(Model model, HttpServletRequest req) {
		model.addAttribute("datalist", repo.findAllByTransId(req.getSession().getAttribute("userid").toString()).get());
		return "invoice_trans";
	}

	@RequestMapping("/create")
	public String create(Model model, @RequestParam("reqId") String reqId, @RequestParam("transId") String transId ) {
		model.addAttribute("reqId", reqId);
		model.addAttribute("transId", transId);
		Requirement req = reqRepo.findByReqId(reqId).get();
		model.addAttribute("obj", req);
		return "invoice_create";
	}

	@RequestMapping("/save")
	public String save(Invoice obj) {
		Optional<Invoice> idobj = repo.findTopByOrderByIdDesc();
		String id = null;
		if (idobj.isPresent()) {
			int idnum = Integer.parseInt(idobj.get().getInvId().substring(5));
			idnum++;
			id = "INV00" + idnum;
		} else {
			id = "INVO064901";
		}

		obj.setInvId(id);
		obj.setStatus("Generated");
		System.out.println(obj);
		repo.save(obj);
		
		
		Application app = apRepo.findTopByOrderByReqIdDesc(obj.getReqId()).get();
		app.setStatus("Generated");
		apRepo.save(app);
		
		return "redirect:/application/list";
	}

	@RequestMapping("/show/{id}")
	public String show(@PathVariable String id, Model model) {
		model.addAttribute("obj", repo.findById(id).get());
		return "invoice_show";
	}


	
	@RequestMapping("/deposit/{reqId}")
	public String save(@PathVariable String reqId) {
		Optional<Deposit> idobj = depRepo.findTopByOrderByIdDesc();
		String id = null;
		if (idobj.isPresent()) {
			int idnum = Integer.parseInt(idobj.get().getDepId().substring(5));
			idnum++;
			id = "DEPO0" + idnum;
		} else {
			id = "DEPO064901";
		}
		Deposit obj = new Deposit();
		obj.setDepId(id);
		
		Invoice inv = repo.findByReqId(reqId).get();
		obj.setInvId(inv.getInvId());
		obj.setTransId(inv.getTransId());
		obj.setTotalAmount(inv.getAmount());
		obj.setAmountPaid(inv.getAmount());
		obj.setDepositedDate(java.time.LocalDate.now().toString());
		depRepo.save(obj);
		
		Requirement req = reqRepo.findByReqId(reqId).get();
		req.setStatus("Deposited");
		reqRepo.save(req);
		System.out.println("Completed");

		return "redirect:/req/list";
	}
	
	
	@RequestMapping("/payment/{invId}")
	public String payment(@PathVariable String invId) {
		
		
		Invoice inv = repo.findByInvId(invId).get();
		inv.setPaymentDate(java.time.LocalDate.now().toString());
		inv.setStatus("Paid");
		repo.save(inv);
		
		return "redirect:/req/mylist";
	}
	
	
	
	static String ymd_dmy(String date) throws ParseException {
		String arr[] = date.split("-");
		return arr[2] + "-" + arr[1] + "-" + arr[0];
	}
}
