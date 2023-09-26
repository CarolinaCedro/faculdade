package com.example.auth.controllers;

import com.example.auth.domain.cepView.CepView;
import com.example.auth.domain.exchangeRateAPI.ResponseExchangeRate;
import com.example.auth.domain.pedido.Pedido;
import com.example.auth.services.AbstractService;
import com.example.auth.services.impl.PedidoImpl;
import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("pedido")
public class PedidoController extends RestControllerImpl<Pedido> {

    private final PedidoImpl service;

    public PedidoController(PedidoImpl service) {
        this.service = service;
    }

    @Override
    protected AbstractService<Pedido> getService() {
        return this.service;
    }


    @GetMapping(value = "/getCepf/{cep}")
    public ResponseEntity<Optional<CepView>> showCep(@PathVariable String cep) {
        return ResponseEntity.ok(this.service.getCep(cep));
    }

    @GetMapping(value = "/handleConversion/{moeda}")
    public ResponseEntity<ResponseExchangeRate> handleConvertion(@PathVariable String moeda, @PathParam("value") BigDecimal value) {
        return ResponseEntity.ok(this.service.getConversion(moeda, value).getBody());
    }

    @GetMapping(value = "/finalizarCompra/{idCarrinho}")
    public ResponseEntity<Object> finalizarCompra(@PathVariable String idCarrinho, @RequestParam("cep") String cep, @RequestParam("moeda") String moeda, @RequestParam("valor") BigDecimal valor) {
        try {
            // Verifica se o CEP é válido
            Optional<CepView> cepData = this.service.getCep(cep);
            if (!cepData.isPresent()) {
                return ResponseEntity.badRequest().body("CEP inválido ou inexistente.");
            }

            // Realiza a conversão de moeda
            ResponseEntity<ResponseExchangeRate> conversionResponse = this.service.getConversion(moeda, valor);
            if (conversionResponse.getStatusCode() != HttpStatus.OK) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro na conversão de moeda.");
            }

            // Aqui você pode adicionar lógica adicional, como salvar os dados da compra, etc.

            // Retorne a resposta final com os dados do CEP e a conversão de moeda
            Map<String, Object> responseData = new HashMap<>();
            responseData.put("cepData", cepData.get());
            responseData.put("conversionData", conversionResponse.getBody());

            return ResponseEntity.ok(responseData);
        } catch (RestClientException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("CEP Inexistente.");
        }
    }


}

