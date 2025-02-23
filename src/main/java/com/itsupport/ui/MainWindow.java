package com.itsupport.ui;

import com.itsupport.model.Ticket;
import com.itsupport.model.enums.Status;
import com.itsupport.service.TicketService;
import com.itsupport.ui.service.TicketUIService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainWindow extends JFrame {
    private final TicketUIService ticketUIService;
    private final JTable ticketTable;
    private final TicketTableModel tableModel;
    private final JComboBox<String> statusFilter;
    private final JTextField searchField;

    public MainWindow(TicketUIService ticketUIService) {
        this.ticketUIService = ticketUIService;
        setTitle("IT Support Ticket System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 600);

        // Create top panel with buttons and filters
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        // New Ticket button
        JButton newTicketButton = new JButton("New Ticket");
        newTicketButton.addActionListener(e -> showNewTicketDialog());
        topPanel.add(newTicketButton);

        // Status filter
        JLabel statusLabel = new JLabel("Status:");
        topPanel.add(statusLabel);
        
        statusFilter = new JComboBox<>(new String[]{"All", "NEW", "IN_PROGRESS", "RESOLVED"});
        statusFilter.addActionListener(e -> refreshTickets());
        topPanel.add(statusFilter);

        // Search field
        JLabel searchLabel = new JLabel("Search:");
        topPanel.add(searchLabel);
        
        searchField = new JTextField(20);
        topPanel.add(searchField);
        
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> refreshTickets());
        topPanel.add(searchButton);

        // Create table
        tableModel = new TicketTableModel();
        ticketTable = new JTable(tableModel);
        ticketTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        // Add table to scroll pane
        JScrollPane scrollPane = new JScrollPane(ticketTable);

        // Add components to frame
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Initial load of tickets
        refreshTickets();
    }

    private void showNewTicketDialog() {
        NewTicketDialog dialog = new NewTicketDialog(this, ticketUIService);
        dialog.setVisible(true);
        refreshTickets(); // Refresh after new ticket is created
    }

    private void refreshTickets() {
        String selectedStatus = (String) statusFilter.getSelectedItem();
        String searchText = searchField.getText().trim();
        
        List<Ticket> tickets;
        if ("All".equals(selectedStatus)) {
            tickets = ticketUIService.getAllTickets();
        } else {
            tickets = ticketUIService.getTicketsByStatus(Status.valueOf(selectedStatus));
        }
        
        // Apply search filter if text is entered
        if (!searchText.isEmpty()) {
            tickets = tickets.stream()
                .filter(ticket -> 
                    String.valueOf(ticket.getId()).contains(searchText) ||
                    ticket.getTitle().toLowerCase().contains(searchText.toLowerCase()) ||
                    ticket.getDescription().toLowerCase().contains(searchText.toLowerCase()))
                .toList();
        }
        
        tableModel.setTickets(tickets);
    }
} 