package com.example.auth.domain.Review;

import com.example.auth.domain.product.Product;
import com.example.auth.domain.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "reviews")
@Data
@AllArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product produto;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User usuario;

    private int classificacao;
    private String comentario;
    private LocalDateTime dataReview;

    public Review() {
        this.dataReview = LocalDateTime.now();
    }
}
