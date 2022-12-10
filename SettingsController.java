package com.mgmt.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mgmt.model.Customer;
import com.mgmt.model.Settings;
import com.mgmt.repository.SettingsRepository;

@RequestMapping("/settings")
@Controller
public class SettingsController {

	@Autowired
	SettingsRepository repo;

	@RequestMapping
	public String edit(Model model) {

		Optional<Settings> settings = repo.findTopByOrderByIdDesc();
		if (settings.isPresent())
			model.addAttribute("obj", settings.get());
	
		return "settings_edit";
	}

	@RequestMapping("/update")
	public String update(Model model, Settings obj) {
		repo.save(obj);
		model.addAttribute("msg","Price Updated Successfully !!!");
		return "redirect:/settings";
	}
}
