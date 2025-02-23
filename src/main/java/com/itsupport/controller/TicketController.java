package com.itsupport.controller;

import com.itsupport.model.Ticket;
import com.itsupport.model.User;
import com.itsupport.model.enums.Status;
import com.itsupport.service.TicketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
@Tag(name = "Ticket Management")
public class TicketController {
    private final TicketService ticketService;

    @PostMapping
    @Operation(summary = "Create a new ticket")
    public ResponseEntity<Ticket> createTicket(
            @RequestBody Ticket ticket,
            @AuthenticationPrincipal User user) {
        ticket.setCreatedBy(user);
        return ResponseEntity.ok(ticketService.createTicket(ticket));
    }

    @GetMapping
    @Operation(summary = "Get tickets with filters")
    public ResponseEntity<List<Ticket>> getTickets(
            @RequestParam(required = false) Status status,
            @RequestParam(required = false) String searchId) {
        List<Ticket> tickets;
        
        // First apply status filter
        if (status != null) {
            tickets = ticketService.findByStatus(status);
        } else {
            tickets = ticketService.findAll();
        }
        
        // Then apply ID search if provided
        if (searchId != null && !searchId.trim().isEmpty()) {
            tickets = tickets.stream()
                .filter(ticket -> String.valueOf(ticket.getId()).contains(searchId.trim()))
                .collect(Collectors.toList());
        }
        
        return ResponseEntity.ok(tickets);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get ticket by ID")
    public ResponseEntity<Ticket> getTicket(@PathVariable Long id) {
        return ResponseEntity.ok(ticketService.findById(id));
    }
} 