package br.edu.ifsuldeminas.mch.webii.crudmanager.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.edu.ifsuldeminas.mch.webii.crudmanager.model.ClassType;
import br.edu.ifsuldeminas.mch.webii.crudmanager.model.User;
import br.edu.ifsuldeminas.mch.webii.crudmanager.repo.ClassRepository;
import jakarta.validation.Valid;

@Controller
public class ClassTypeController {

	@Autowired
	private ClassRepository classRepo;

	

	@GetMapping("/class")
	public String listClass(Model model) {
		List<ClassType> classtype= classRepo.findAll();
		model.addAttribute("class",classtype);
		return "class-type";
	}
	
	@GetMapping("/class/form")
	public String classForm(@ModelAttribute("class") ClassType classtype ) {
		//Estudar o mecanismo de binding do String		
		
		return "class_form";
	}
		
	@PostMapping("/class/register")
	public String classNew(@Valid
			              @ModelAttribute("class") 
	                      ClassType classtype,
	                      BindingResult erros) {
		
		if (erros.hasErrors()) {
			return "class_form";
		}
		
		classRepo.save(classtype);		
		return "redirect:/class";
		
	}
	
	@GetMapping("/class/update/{id}")
	public String classUpdate(@PathVariable("id") Integer id, Model model) {
		
		Optional<ClassType> classOpt = classRepo.findById(id);
		ClassType classtype;		
		if(!classOpt.isPresent()) {
			classtype = new ClassType();
		} else {
			classtype = classOpt.get();
		}
		
		model.addAttribute("class",classtype);
		return "class_form";
	}
		
	@GetMapping("/class/delete/{id}")
	public String classDelete(@PathVariable("id") Integer id) {
		classRepo.delete(new ClassType(id));
		return "redirect:/class";
	}
}
