package org.projeto.adapter.javafx.application;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// Responsável por configurar os serviços do spring
@Configuration
@ComponentScan({
        "org.projeto.adapter.javafx.application",
        "org.projeto"})
public class Build {
    // Build 2 - Adaptador JavaFX -> Sistema <- Adaptadores Mocks
}
