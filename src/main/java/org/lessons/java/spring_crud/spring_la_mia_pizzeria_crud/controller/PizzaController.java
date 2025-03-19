package org.lessons.java.spring_crud.spring_la_mia_pizzeria_crud.controller;

import java.util.List;

import org.lessons.java.spring_crud.spring_la_mia_pizzeria_crud.model.Pizza;
import org.lessons.java.spring_crud.spring_la_mia_pizzeria_crud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("/pizze")
public class PizzaController {

    @Autowired
    private PizzaRepository repository;
    
    @GetMapping("/{id}")
    public String getPizzaDetails(@PathVariable("id") Integer id, Model model) {
        Pizza pizza = repository.findById(id).get();
    
        model.addAttribute("pizza", pizza);
        return "pizze/show";
    }

    @GetMapping
    public String getMethodName(@RequestParam(name ="nome", required = false) String nome, Model model) {
        List<Pizza> pizze;
        
        if (nome != null && !nome.isEmpty()) {
            pizze = repository.findByNomeContaining(nome); 
        } else {
            pizze = repository.findAll(); 
        }

        model.addAttribute("pizze", pizze);
        return "pizze/index";
    }
    
}
