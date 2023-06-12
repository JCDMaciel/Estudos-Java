package org.example.checkout.infra.cli;

import java.util.ArrayList;
import java.util.List;

import org.example.checkout.application.usecase.Checkout;

public class CLIController {

	private final CLIHandler handler;
	private final Checkout checkout;
	private final Checkout.Input input;

	public CLIController(CLIHandler handler, Checkout checkout) {
		this.handler = handler;
		this.checkout = checkout;
		this.input = new Checkout.Input("", new ArrayList<>());

		handler.on("set-cpf", (params) -> {
			input.setCpf(params);
		});

		handler.on("add-item", (params) -> {
			String[] splitParams = params.split(" ");
			int idProduct = Integer.parseInt(splitParams[0]);
			int quantity = Integer.parseInt(splitParams[1]);
			input.getItems().add(new Item(idProduct, quantity));
		});

		handler.on("checkout", (params) -> {
			try {
				Checkout.Output output = checkout.execute(input);
				handler.write(output.toString());
			} catch (Exception e) {
				handler.write(e.getMessage());
			}
		});
	}

	private static class Input {
		private String cpf;
		private List<Item> items;

		public Input(String cpf, List<Item> items) {
			this.cpf = cpf;
			this.items = items;
		}

		public String getCpf() {
			return cpf;
		}

		public void setCpf(String cpf) {
			this.cpf = cpf;
		}

		public List<Item> getItems() {
			return items;
		}
	}

	private static class Item extends Checkout.Item {
		private int idProduct;
		private int quantity;

		public Item(int idProduct, int quantity) {
			this.idProduct = idProduct;
			this.quantity = quantity;
		}

		public int getIdProduct() {
			return idProduct;
		}

		public int getQuantity() {
			return quantity;
		}
	}
	private static class Output {
		private double total;
		private double discount;
		private double finalTotal;

		public Output(double total, double discount, double finalTotal) {
			this.total = total;
			this.discount = discount;
			this.finalTotal = finalTotal;
		}

		public double getTotal() {
			return total;
		}

		public double getDiscount() {
			return discount;
		}

		public double getFinalTotal() {
			return finalTotal;
		}

		@Override
		public String toString() {
			return "Total: " + total + "\nDiscount: " + discount + "\nFinal Total: " + finalTotal;
		}
	}
}
