package org.projeto.adapter.javafx.application;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 *  Responsável por fazer o ponto de inicio de execução
 *  @author anderson
 *
 */
public class AdaptadorJavaFx extends Application {

    private ApplicationContext spring;

    @Override
    public void init() {
        System.out.println("iniciando spring..");
        spring = new AnnotationConfigApplicationContext(Build.class);
        //spring = new AnnotationConfigApplicationContext(Build3.class);
        //spring = new AnnotationConfigApplicationContext(Build4.class);
    }

    @Override
    public void start(Stage stage) {
    	TransferenciaFrm form = spring.getBean(TransferenciaFrm.class);
        form.mostrar(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
