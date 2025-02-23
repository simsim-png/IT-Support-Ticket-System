package com.itsupport;

import com.itsupport.ui.MainWindow;
import com.itsupport.ui.service.TicketUIService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import javax.swing.*;

@SpringBootApplication
@EnableJpaRepositories
public class ITSupportApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(ITSupportApplication.class)
            .headless(false)
            .run(args);

        SwingUtilities.invokeLater(() -> {
            TicketUIService ticketUIService = context.getBean(TicketUIService.class);
            MainWindow mainWindow = new MainWindow(ticketUIService);
            mainWindow.setVisible(true);
        });
    }
} 