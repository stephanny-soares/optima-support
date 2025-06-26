package com.optima.support.controller;

import com.optima.support.dto.TicketDTO;
import com.optima.support.model.Ticket;
import com.optima.support.model.Usuario;
import com.optima.support.repository.UsuarioRepository;
import com.optima.support.service.TicketService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private Usuario getUsuarioLogado(UserDetails userDetails) {
        return usuarioRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    @GetMapping
    public List<Ticket> listarTickets(@AuthenticationPrincipal UserDetails userDetails) {
        Usuario usuario = getUsuarioLogado(userDetails);
        return ticketService.listarTickets()
                .stream()
                .filter(t -> t.getAutor().getId().equals(usuario.getId()))
                .toList();
    }

    @PostMapping
    public ResponseEntity<Ticket> criarTicket(@Valid @RequestBody TicketDTO dto,
                                              @AuthenticationPrincipal UserDetails userDetails) {
        Usuario usuario = getUsuarioLogado(userDetails);

        Ticket ticket = new Ticket();
        ticket.setTitulo(dto.getTitulo());
        ticket.setDescricao(dto.getDescricao());
        ticket.setCategoria(dto.getCategoria());
        ticket.setAutor(usuario);

        Ticket salvo = ticketService.salvar(ticket);
        return ResponseEntity.ok(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ticket> atualizarTicket(@PathVariable Long id,
                                                  @Valid @RequestBody TicketDTO dto,
                                                  @AuthenticationPrincipal UserDetails userDetails) {
        Ticket ticket = ticketService.buscarPorIdOuFalhar(id);

        if (!ticket.getAutor().getEmail().equals(userDetails.getUsername())) {
            return ResponseEntity.status(403).build();
        }

        ticket.setTitulo(dto.getTitulo());
        ticket.setDescricao(dto.getDescricao());
        ticket.setCategoria(dto.getCategoria());

        Ticket atualizado = ticketService.salvar(ticket);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTicket(@PathVariable Long id,
                                              @AuthenticationPrincipal UserDetails userDetails) {
        Ticket ticket = ticketService.buscarPorIdOuFalhar(id);

        if (!ticket.getAutor().getEmail().equals(userDetails.getUsername())) {
            return ResponseEntity.status(403).build();
        }

        ticketService.deletar(ticket);
        return ResponseEntity.noContent().build();
    }
}

