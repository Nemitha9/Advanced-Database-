package com.mgmt.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mgmt.model.Application;
import com.mgmt.model.Requirement;
import com.mgmt.repository.ApplicationRepository;
import com.mgmt.repository.RequirementRepository;
import com.mgmt.repository.SettingsRepository;
import com.mgmt.service.FileUploadUtil;

@RequestMapping("/req")
@Controller
public class RequirementController {

	@Autowired
	RequirementRepository repo;
	@Autowired
	ApplicationRepository appRepo;
	@Autowired
	SettingsRepository setRepo;

	@RequestMapping("/list")
	public String list(Model model, HttpServletRequest request) {
		List<Application> apps = appRepo.findAllByTransId(request.getSession().getAttribute("userid").toString()).get();
		List<String> appliedReqIds = new ArrayList<>();
		for (Application app : apps) {
			appliedReqIds.add(app.getReqId());
		}

		model.addAttribute("datalist", repo.findAllByReqIdNotIn(appliedReqIds).get());
		return "req";
	}

	@RequestMapping("/mylist")
	public String mylist(Model model, HttpServletRequest request) {
		model.addAttribute("datalist",
				repo.findAllByCustomerId(request.getSession().getAttribute("userid").toString()).get());
		return "reqmy";
	}

	@RequestMapping("/create")
	public String create(Model model) {

		if(setRepo.findTopByOrderByIdDesc().isPresent())
			model.addAttribute("costPerPage", setRepo.findTopByOrderByIdDesc().get().getCostPerPage());
		else
			model.addAttribute("costPerPage", 0);
		return "req_create";
	}

	@RequestMapping("/save")
	public String save(Requirement obj, HttpServletRequest request, @RequestParam("upload") MultipartFile multipartFile)
			throws IOException {

		Optional<Requirement> idobj = repo.findTopByOrderByIdDesc();
		String id = null;
		if (idobj.isPresent()) {
			int idnum = Integer.parseInt(idobj.get().getReqId().substring(5));
			idnum++;
			id = "REQ03" + idnum;
		} else {
			id = "REQ0362353";
		}

		String docUrl = id + multipartFile.getOriginalFilename();
		obj.setReqId(id);
		obj.setCustomerId(request.getSession().getAttribute("userid").toString());
		obj.setCustConfirmation("UnConfirmed");
		obj.setTranslatedDoc("");
		obj.setUploadedDoc(docUrl);
		obj.setStatus("Not Started");
		repo.save(obj);

		String uploadDir = "uploads";

		FileUploadUtil.saveFile(uploadDir, docUrl, multipartFile);
		return "redirect:/req/mylist";
	}

	@RequestMapping("/upload")
	public String upload(@RequestParam("appId") String appId, HttpServletRequest request,
			@RequestParam("upload") MultipartFile multipartFile) throws IOException {

		Optional<Application> appobj = appRepo.findByAppId(appId);
		String reqId = appobj.get().getReqId();
		String docUrl = reqId + "_trans" + multipartFile.getOriginalFilename();

		Optional<Requirement> reqobj = repo.findByReqId(reqId);
		reqobj.get().setTranslatedDoc(docUrl);
		repo.save(reqobj.get());

		String uploadDir = "uploads";

		FileUploadUtil.saveFile(uploadDir, docUrl, multipartFile);
		return "redirect:/req/list";
	}

	@GetMapping("/download/{fileName:.+}")
	public void downloadPDFResource(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("fileName") String fileName) throws IOException {
		System.out.println(fileName);
		File file = new File("uploads/" + fileName);
		System.out.println(file.exists());
		if (file.exists()) {

			// get the mimetype
			String mimeType = URLConnection.guessContentTypeFromName(file.getName());
			if (mimeType == null) {
				// unknown mimetype so set the mimetype to application/octet-stream
				mimeType = "application/octet-stream";
			}

			response.setContentType(mimeType);

			response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));

			// Here we have mentioned it to show as attachment
			// response.setHeader("Content-Disposition", String.format("attachment;
			// filename=\"" + file.getName() + "\""));

			response.setContentLength((int) file.length());

			InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

			FileCopyUtils.copy(inputStream, response.getOutputStream());

		}
	}

	@RequestMapping("/show/{id}")
	public String show(@PathVariable String id, Model model) {
		Requirement req = repo.findById(id).get();
		model.addAttribute("obj", req);
		model.addAttribute("appdatalist", appRepo.findAllByReqId(req.getReqId()).get());

		return "req_show";
	}

	@RequestMapping("/myshow/{id}")
	public String myshow(@PathVariable String id, Model model) {
		Requirement req = repo.findById(id).get();
		model.addAttribute("obj", req);
		model.addAttribute("appdatalist", appRepo.findAllByReqId(req.getReqId()).get());

		return "req_myshow";
	}

	@RequestMapping("/show/reqid/{id}")
	public String reqshow(@PathVariable String id, Model model, HttpServletRequest request) {
		Requirement req = repo.findByReqId(id).get();
		model.addAttribute("obj", req);
		model.addAttribute("appdatalist", appRepo.findAllByReqId(req.getReqId()).get());

		if (request.getSession().getAttribute("usertype").equals("customer"))
			return "req_myshow";
		else
			return "req_show";

	}

	@RequestMapping("/delete")
	public String delete(@RequestParam String id) {
		Optional<Requirement> obj = repo.findById(id);
		repo.delete(obj.get());
		return "redirect:/req/mylist";
	}

	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable String id, Model model) {
		model.addAttribute("costPerPage", setRepo.findTopByOrderByIdDesc().get().getCostPerPage());
		model.addAttribute("obj", repo.findById(id).get());
		return "req_edit";
	}

	@RequestMapping("/update")
	public String update(Requirement obj) {
		repo.save(obj);
		return "redirect:/req/myshow/" + obj.getId();
	}

	@RequestMapping("/status/{id}/{status}")
	public String updateStatus(@PathVariable String id, @PathVariable String status, Model model) {
		Requirement obj = repo.findById(id).get();
		obj.setStatus(status);
		repo.save(obj);

		return "redirect:/req/list";
	}

}
