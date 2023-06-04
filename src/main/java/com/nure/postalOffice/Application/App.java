package com.nure.postalOffice.Application;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.nure.postalOffice"})
public class App {
    public static void main(String[] args) {
        Application.launch(JavaFxApp.class, args);
    }

}
