package com.itsupport.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "audit_logs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;
    
    @Column(name = "action_type")
    private String actionType;
    
    @Column(name = "action_details")
    private String actionDetails;
    
    @Column(name = "timestamp")
    private LocalDateTime timestamp = LocalDateTime.now();
    
    @ManyToOne
    @JoinColumn(name = "performed_by")
    private User performedBy;
} 