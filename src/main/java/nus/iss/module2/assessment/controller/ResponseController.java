package nus.iss.module2.assessment.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import nus.iss.module2.assessment.service.PizzaService;

@RestController
public class ResponseController {
    
    @Autowired
    private PizzaService pizzaService;

    @GetMapping(path = "{msId}")
    public ResponseEntity<String> getBoardGame(@PathVariable String orderDetailsID)
            throws IOException {
 
        if (orderDetailsID == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("");
        }
        return null;
    }
}
