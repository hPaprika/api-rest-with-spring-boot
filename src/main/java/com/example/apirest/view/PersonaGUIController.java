package com.example.apirest.view;

import com.example.apirest.model.Persona;
import com.example.apirest.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/personas")
public class PersonaGUIController {

    @Autowired
    private PersonaService personaService;

    @GetMapping
    public String listarPersonas(Model model) {
        List<Persona> personas = personaService.listarPersonas();
        model.addAttribute("personas", personas);
        return "index";  // redirige a index.html
    }

    @GetMapping("/create")
    public String crearPersonaForm(Model model) {
        model.addAttribute("persona", new Persona());
        return "create";  // redirige a create.html
    }

    @PostMapping("/create")
    public String crearPersona(@ModelAttribute Persona persona) {
        personaService.guardarPersona(persona);
        return "redirect:/personas";  // redirige de vuelta a la lista
    }

    @GetMapping("/edit/{id}")
    public String editarPersonaForm(@PathVariable Long id, Model model) {
        Persona persona = personaService.obtenerPersonaPorId(id).orElseThrow(() -> new IllegalArgumentException("Invalid persona ID:" + id));
        model.addAttribute("persona", persona);
        return "edit";  // redirige a edit.html
    }

    @PostMapping("/edit/{id}")
    public String editarPersona(@PathVariable Long id, @ModelAttribute Persona personaDetalles) {
        personaService.actualizarPersona(id, personaDetalles);
        return "redirect:/personas";  // redirige de vuelta a la lista
    }

    @GetMapping("/delete/{id}")
    public String eliminarPersona(@PathVariable Long id) {
        personaService.eliminarPersona(id);
        return "redirect:/personas";  // redirige de vuelta a la lista
    }
}