package com.example.auth.services.impl;

import com.example.auth.domain.cepView.CepView;
import com.example.auth.domain.exchangeRateAPI.ExchangeRate;
import com.example.auth.domain.exchangeRateAPI.ResponseExchangeRate;
import com.example.auth.domain.pedido.Pedido;
import com.example.auth.repositories.CartRepository;
import com.example.auth.repositories.PedidoRepository;
import com.example.auth.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;


@Service
public class PedidoImpl extends AbstractClassImpl<Pedido> {


    private final String viaCepUrl = "https://viacep.com.br/ws/";
    private final String exchangeRateAPI = "https://v6.exchangerate-api.com/v6/";
    private final String chave = "eff9e284625ea81d8ba8cf97";
//    private static final Map<Currency, Double> staticConversionRates = createStaticRates();


    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Override
    protected JpaRepository<Pedido, String> getRepository() {
        return this.pedidoRepository;
    }

    public Optional<CepView> getCep(String cep) {
        String viaCepEndpoint = viaCepUrl + cep + "/json/";

        try {
            ResponseEntity<CepView> response = restTemplate.getForEntity(viaCepEndpoint, CepView.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                CepView cepView = response.getBody();

                if (cepView != null) {
                    return Optional.of(cepView);
                } else {
                    throw new RuntimeException("CEP não encontrado: " + cep);
                }
            } else if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new RuntimeException("CEP Inexistente: " + cep);
            } else if (response.getStatusCode() == HttpStatus.BAD_REQUEST) {
                throw new RuntimeException("CEP está no formato inválido.: " + cep);
            } else {
                throw new RuntimeException("CEP Inválido: " + cep);
            }
        } catch (RestClientException e) {
            // Lida com exceções ao chamar o serviço REST.
            throw new RuntimeException("Erro ao buscar CEP: " + cep, e);
        }
    }


    public ResponseEntity<ResponseExchangeRate> getConversion(String moeda, BigDecimal value) {
        try {
            if (!isValidCurrency(moeda)) {
                return ResponseEntity
                        .badRequest()
                        .build();

            }

            String exchangeRateUrl = exchangeRateAPI + chave + "/latest/" + moeda;

            ResponseEntity<ExchangeRate> response = restTemplate.getForEntity(exchangeRateUrl, ExchangeRate.class);

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                ExchangeRate exchangeRate = response.getBody();
                BigDecimal convertedValue = convertCurrency(exchangeRate, moeda, "BRL", value);
                ResponseExchangeRate responseExchangeRate = new ResponseExchangeRate(convertedValue);

                return ResponseEntity.ok(responseExchangeRate);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        } catch (RestClientException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    private boolean isValidCurrency(String moeda) {
        return moeda != null && (moeda.equals("USD") || moeda.equals("EUR") || moeda.equals("GBP") /* Não coloquei todas as moedas */);
    }

    // Método para converter a moeda.
    public BigDecimal convertCurrency(ExchangeRate exchangeRate, String fromCurrency, String toCurrency, BigDecimal amount) {
        Map<String, Double> conversionRates = exchangeRate.getConversion_rates();

        // Verificando se as moedas de origem e destino são válidas.
        if (conversionRates.containsKey(fromCurrency) && conversionRates.containsKey(toCurrency)) {
            Double fromConversionRate = conversionRates.get(fromCurrency);
            Double toConversionRate = conversionRates.get(toCurrency);

            if (fromConversionRate != null && toConversionRate != null) {
                // Realizando a conversão de uma moeda para outra.
                BigDecimal convertedValue = amount.multiply(BigDecimal.valueOf(toConversionRate / fromConversionRate));
                return convertedValue;
            } else {
                throw new RuntimeException("Taxas de conversão ausentes ou inválidas.");
            }
        } else {
            throw new RuntimeException("Moeda de origem ou destino inválida.");
        }
    }




}
