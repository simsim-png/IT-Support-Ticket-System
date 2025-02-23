package com.itsupport.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "ticket_comments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;
    
    @Column(name = "comment_text")
    private String commentText;
    
    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;
    
    @Column(name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();
} 