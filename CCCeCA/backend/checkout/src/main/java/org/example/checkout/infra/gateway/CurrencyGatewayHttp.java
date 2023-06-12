package org.example.checkout.infra.gateway;

import org.example.checkout.application.gateway.CurrencyGateway;

public class CurrencyGatewayHttp implements CurrencyGateway {
	public Object getCurrencies() {
		Object response = "Implementação da obtenção de moedas sem HttpClient";
		return response;
	}
}
