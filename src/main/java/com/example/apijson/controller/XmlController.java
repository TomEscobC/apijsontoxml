package com.example.apijson.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.apijson.service.XmlService;

@Slf4j
@RestController
@RequestMapping("/api/xml")
@RequiredArgsConstructor
public class XmlController {

    private final XmlService xmlService;

    @PostMapping(consumes = "application/json", produces = "application/xml")
    public ResponseEntity<?> convertirJsonAXml(@RequestBody String jsonInput) {
        try {
            log.info("Recibiendo solicitud de conversión JSON a XML");
            
            if (jsonInput == null || jsonInput.trim().isEmpty()) {
                return ResponseEntity.badRequest().body("JSON input no puede estar vacío");
            }
            
            String xml = xmlService.convertirJsonAXml(jsonInput);
            return ResponseEntity.ok(xml);
            
        } catch (IllegalArgumentException e) {
            log.warn("JSON inválido: {}", e.getMessage());
            return ResponseEntity.badRequest().body("JSON inválido: " + e.getMessage());
        } catch (Exception e) {
            log.error("Error al convertir JSON a XML", e);
            return ResponseEntity
                    .internalServerError()
                    .body("Error al convertir JSON a XML: " + e.getMessage());
        }
    }
}