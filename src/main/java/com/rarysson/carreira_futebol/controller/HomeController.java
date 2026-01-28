package com.rarysson.carreira_futebol.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping(value = "/", produces = MediaType.TEXT_PLAIN_VALUE)
    public String home() {
        return "API carreira-futebol em execução. Endpoints disponíveis:\n" +
               "- GET /api/jogadores\n" +
               "- POST /api/jogadores\n" +
               "- outros endpoints em /api/...";
    }
}
