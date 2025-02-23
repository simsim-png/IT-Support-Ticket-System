package com.itsupport.ui;

import com.itsupport.model.Ticket;
import com.itsupport.model.enums.Category;
import com.itsupport.model.enums.Priority;
import com.itsupport.ui.service.TicketUIService;

import javax.swing.*;
import java.awt.*;

public class NewTicketDialog extends JDialog {
    private final JTextField titleField;
    private final JTextArea descriptionArea;
    private final JComboBox<Priority> priorityCombo;
    private final JComboBox<Category> categoryCombo;
    private final TicketUIService ticketUIService;
    
    public NewTicketDialog(Frame owner, TicketUIService ticketUIService) {
        super(owner, "Create New Ticket", true);
        this.ticketUIService = ticketUIService;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Create components
        titleField = new JTextField(30);
        descriptionArea = new JTextArea(5, 30);
        priorityCombo = new JComboBox<>(Priority.values());
        categoryCombo = new JComboBox<>(Category.values());
        
        // Add components with GridBagLayout
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Title:"), gbc);
        
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        add(titleField, gbc);
        
        gbc.gridy = 2;
        add(new JLabel("Description:"), gbc);
        
        gbc.gridy = 3;
        add(new JScrollPane(descriptionArea), gbc);
        
        gbc.gridy = 4;
        add(new JLabel("Priority:"), gbc);
        
        gbc.gridy = 5;
        add(priorityCombo, gbc);
        
        gbc.gridy = 6;
        add(new JLabel("Category:"), gbc);
        
        gbc.gridy = 7;
        add(categoryCombo, gbc);
        
        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton submitButton = new JButton("Submit");
        JButton cancelButton = new JButton("Cancel");
        
        submitButton.addActionListener(e -> submitTicket());
        cancelButton.addActionListener(e -> dispose());
        
        buttonPanel.add(submitButton);
        buttonPanel.add(cancelButton);
        
        gbc.gridy = 8;
        add(buttonPanel, gbc);
        
        pack();
        setLocationRelativeTo(owner);
    }
    
    private void submitTicket() {
        try {
            Ticket ticket = new Ticket();
            ticket.setTitle(titleField.getText());
            ticket.setDescription(descriptionArea.getText());
            ticket.setPriority((Priority) priorityCombo.getSelectedItem());
            ticket.setCategory((Category) categoryCombo.getSelectedItem());
            
            // Submit ticket through service
            ticketUIService.createTicket(ticket);
            JOptionPane.showMessageDialog(this, "Ticket created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, 
                "Error creating ticket: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
} 