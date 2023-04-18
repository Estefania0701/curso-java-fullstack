package com.cursojava.curso.controllers;

// los controladores sirven para manejar las URL
// para que las URL devuelvan cosas
// manejan las direcciones de URL

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {

    @RequestMapping(value = "prueba")
    public String prueba(){
        return "prueba";
    }
}
