package com.example.auth.domain.pedido;

import com.example.auth.domain.cart.Cart;
import com.example.auth.domain.cepView.CepView;
import com.example.auth.domain.user.User;

import java.time.LocalDateTime;

public record PedidoResponseDto(String id, User clienteId, LocalDateTime dataPedido, Cart cartId,
                                CepView enderecoEntrega, StatusPedido status) {
}
