package com.getechnologiesMx.parking.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartRunner implements CommandLineRunner {
    @Autowired
    private Menu menuService;

    @Override
    public void run(String... args) throws Exception {
        menuService.showMenu(); // Esto iniciará automáticamente el menú al arrancar la aplicación
    }
}
