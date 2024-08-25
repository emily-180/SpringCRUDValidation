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
import br.edu.ifsuldeminas.mch.webii.crudmanager.model.Enrollment;
import br.edu.ifsuldeminas.mch.webii.crudmanager.model.User;
import br.edu.ifsuldeminas.mch.webii.crudmanager.repo.ClassRepository;
import br.edu.ifsuldeminas.mch.webii.crudmanager.repo.EnrollmentRepository;
import br.edu.ifsuldeminas.mch.webii.crudmanager.repo.UserRepository;
import jakarta.validation.Valid;

@Controller
public class EnrollmentController {
	
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private ClassRepository classRepo;
	@Autowired
	private EnrollmentRepository enrollmentsRepo;
	
	@GetMapping("/enrollment")
	public String listEnrollment(Model model) {
		List<Enrollment> enrollments = enrollmentsRepo.findAll();
		model.addAttribute("enrollments",enrollments);
		return "enrollment";
	}
	
	@GetMapping("enrollment/form")
	public String classForm(@ModelAttribute("enrollment") Enrollment enrollment, Model model) {
	    List<User> users = userRepo.findAll();
	    List<ClassType> classes = classRepo.findAll();

	    model.addAttribute("users", users);
	    model.addAttribute("classes", classes);

	    return "enrollment_form";
	}

	@PostMapping("/enrollment/register")
	public String enrollmentNew(@Valid
			              @ModelAttribute("enrollment") 
						  Enrollment enrollment,
	                      BindingResult erros, Model model) {
		
		if (erros.hasErrors()) {
			List<User> users = userRepo.findAll();
	        List<ClassType> classes = classRepo.findAll();
	        model.addAttribute("users", users);
	        model.addAttribute("classes", classes);
			return "enrollment_form";
		}
		
		enrollmentsRepo.save(enrollment);
		
		return "redirect:/enrollment";
	}


	
	@GetMapping("/enrollment/update/{id}")
	public String enrollmentUpdate(@PathVariable("id") Integer id, Model model) {
	    // Obtém a matrícula com o ID fornecido
	    Optional<Enrollment> enrollmentOpt = enrollmentsRepo.findById(id);
	    if (!enrollmentOpt.isPresent()) {
	        return "redirect:/enrollment"; // Redireciona se a matrícula não for encontrada
	    }
	    // Obtém a matrícula existente
	    Enrollment enrollment = enrollmentOpt.get();
	    
	    // Obtém a lista de usuários e cursos
	    List<User> users = userRepo.findAll();
	    List<ClassType> classes = classRepo.findAll();
	    
	    // Adiciona a matrícula e as listas ao modelo
	    model.addAttribute("enrollment", enrollment);
	    model.addAttribute("users", users);
	    model.addAttribute("classes", classes);
	    
	    return "enrollment_form";
	}
	
	@GetMapping("/enrollment/delete/{id}")
	public String enrollmentDelete(@PathVariable("id") Integer id) {
		enrollmentsRepo.delete(new Enrollment(id));
		return "redirect:/enrollment";
	}

}
