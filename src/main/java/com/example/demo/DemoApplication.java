package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@RestController 
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    // Web Service para resolver la fórmula de Bhaskara
    @CrossOrigin(origins = "*")
    @GetMapping("/api/bhaskara")
    
    public Map<String, Object> resolverBhaskara(
            @RequestParam(name = "a") double a,
            @RequestParam(name = "b") double b,
            @RequestParam(name = "c") double c) {

        Map<String, Object> resultado = new HashMap<>();

        // Validación: 'a' no puede ser 0 en una ecuación cuadrática
        if (a == 0) {
            resultado.put("error", "El coeficiente 'a' no puede ser cero.");
            return resultado;
        }

        // Cálculo del discriminante: b^2 - 4ac
        double discriminante = (b * b) - (4 * a * c);

        if (discriminante < 0) {
            resultado.put("error", "La ecuación no tiene raíces reales (discriminante negativo).");
        } else {
            // Fórmula: (-b ± √discriminante) / 2a
            double x1 = (-b + Math.sqrt(discriminante)) / (2 * a);
            double x2 = (-b - Math.sqrt(discriminante)) / (2 * a);

            resultado.put("a", a);
            resultado.put("b", b);
            resultado.put("c", c);
            resultado.put("x1", x1);
            resultado.put("x2", x2);
        }

        return resultado; // Spring convierte este Map automáticamente a un JSON limpio
    }
}