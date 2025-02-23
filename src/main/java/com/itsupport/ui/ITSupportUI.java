package com.itsupport.ui;

import com.itsupport.ui.service.TicketUIService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import javax.swing.*;

@SpringBootApplication
public class ITSupportUI {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(ITSupportUI.class)
            .headless(false)
            .run(args);

        SwingUtilities.invokeLater(() -> {
            TicketUIService ticketUIService = context.getBean(TicketUIService.class);
            MainWindow mainWindow = new MainWindow(ticketUIService);
            mainWindow.setVisible(true);
        });
    }
} 