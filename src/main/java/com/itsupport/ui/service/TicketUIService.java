package com.itsupport.ui.service;

import com.itsupport.model.Ticket;
import com.itsupport.model.User;
import com.itsupport.model.enums.Status;
import com.itsupport.service.TicketService;
import com.itsupport.service.CurrentUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketUIService {
    private final TicketService ticketService;
    private final CurrentUserService currentUserService;

    public Ticket createTicket(Ticket ticket) {
        try {
            User currentUser = currentUserService.getCurrentUser();
            ticket.setCreatedBy(currentUser);
            return ticketService.createTicket(ticket);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to create ticket: " + e.getMessage());
        }
    }

    public List<Ticket> getAllTickets() {
        return ticketService.findAll();
    }

    public List<Ticket> getTicketsByStatus(Status status) {
        return ticketService.findByStatus(status);
    }

    public List<Ticket> getMyTickets() {
        return ticketService.findAll(); // For now, return all tickets
    }

    public Ticket updateTicketStatus(Long ticketId, Status newStatus) {
        Ticket ticket = ticketService.findById(ticketId);
        ticket.setStatus(newStatus);
        return ticketService.createTicket(ticket); // Reuse create method to update
    }
} 