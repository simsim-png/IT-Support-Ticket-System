package com.itsupport.ui;

import com.itsupport.model.Ticket;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class TicketTableModel extends AbstractTableModel {
    private final String[] columnNames = {"ID", "Title", "Priority", "Category", "Status", "Created Date"};
    private List<Ticket> tickets = new ArrayList<>();
    
    @Override
    public int getRowCount() {
        return tickets.size();
    }
    
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
    
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Ticket ticket = tickets.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> ticket.getId();
            case 1 -> ticket.getTitle();
            case 2 -> ticket.getPriority();
            case 3 -> ticket.getCategory();
            case 4 -> ticket.getStatus();
            case 5 -> ticket.getCreatedDate();
            default -> null;
        };
    }
    
    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
        fireTableDataChanged();
    }
} 