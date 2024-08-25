package br.edu.ifsuldeminas.mch.webii.crudmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/principal")
    public String index(Model model) {
        
        
        return "principal"; 
    }
}
