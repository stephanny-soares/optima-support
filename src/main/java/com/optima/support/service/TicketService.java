package com.optima.support.service;

import com.optima.support.exception.ResourceNotFoundException;
import com.optima.support.model.Ticket;
import com.optima.support.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public List<Ticket> listarTickets() {
        return ticketRepository.findAll();
    }

    public Ticket buscarPorIdOuFalhar(Long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket não encontrado"));
    }

    public Ticket salvar(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public void deletar(Ticket ticket) {
        ticketRepository.delete(ticket);
    }
}
