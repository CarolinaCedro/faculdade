package com.example.auth.domain.pedido;

import com.example.auth.domain.cart.Cart;
import com.example.auth.domain.cepView.CepView;
import com.example.auth.domain.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "pedidos")
@AllArgsConstructor
@Data
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User cliente;


    @Column(name = "data_pedido")
    private LocalDateTime dataPedido;

    @OneToOne
    private Cart cart;

    @OneToOne
    private CepView enderecoEntrega;


    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    public Pedido() {
        this.dataPedido = LocalDateTime.now();
        this.status = StatusPedido.PENDENTE;
    }
}
