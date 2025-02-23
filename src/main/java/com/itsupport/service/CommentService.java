package com.itsupport.service;

import com.itsupport.model.Ticket;
import com.itsupport.model.TicketComment;
import com.itsupport.model.User;
import com.itsupport.repository.TicketCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final TicketCommentRepository commentRepository;
    private final TicketService ticketService;

    @Transactional
    public TicketComment addComment(Long ticketId, String commentText, User user) {
        Ticket ticket = ticketService.findById(ticketId);
        
        TicketComment comment = new TicketComment();
        comment.setTicket(ticket);
        comment.setCommentText(commentText);
        comment.setCreatedBy(user);
        
        return commentRepository.save(comment);
    }

    public List<TicketComment> getComments(Long ticketId) {
        return commentRepository.findByTicketId(ticketId);
    }
} 