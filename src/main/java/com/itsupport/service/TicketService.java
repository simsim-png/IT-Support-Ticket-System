package com.itsupport.service;

import com.itsupport.exception.TicketNotFoundException;
import com.itsupport.model.Ticket;
import com.itsupport.model.enums.Status;
import com.itsupport.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;

    public Ticket createTicket(Ticket ticket) {
        ticket.setStatus(Status.NEW);
        ticket.setCreatedDate(LocalDateTime.now());
        return ticketRepository.save(ticket);
    }

    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    public List<Ticket> findByStatus(Status status) {
        return ticketRepository.findByStatus(status);
    }

    public Ticket findById(Long id) {
        return ticketRepository.findById(id)
            .orElseThrow(() -> new TicketNotFoundException(id));
    }
} 