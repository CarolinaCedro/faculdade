package com.example.auth.domain.cart;

import com.example.auth.domain.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemCarrinho {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    private Cart cart; // Relação com o carrinho

    @OneToOne
    private Product produto;
    private int quantidade;
    private double precoUnitario;
    private double precoTotal;
}
