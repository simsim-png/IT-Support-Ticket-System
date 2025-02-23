package com.itsupport.controller;

import com.itsupport.model.User;
import com.itsupport.model.TicketComment;
import com.itsupport.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets/{ticketId}/comments")
@RequiredArgsConstructor
@Tag(name = "Ticket Comments", description = "APIs for managing ticket comments")
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    @Operation(summary = "Add a comment to a ticket")
    public ResponseEntity<TicketComment> addComment(
            @PathVariable Long ticketId,
            @RequestBody String commentText,
            @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(commentService.addComment(ticketId, commentText, user));
    }

    @GetMapping
    @Operation(summary = "Get all comments for a ticket")
    public ResponseEntity<List<TicketComment>> getComments(@PathVariable Long ticketId) {
        return ResponseEntity.ok(commentService.getComments(ticketId));
    }
} 