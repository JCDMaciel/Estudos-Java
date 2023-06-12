package org.example.checkout.application.usecase;

import org.example.checkout.application.repository.ProductRepository;
import org.example.checkout.domain.entity.FreightCalculator;
import org.example.checkout.domain.entity.Product;

public class SimulateFreight {

    private final ProductRepository productRepository;

    public SimulateFreight(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Output execute(Input input) {
        Output output = new Output();
        output.setFreight(0);
        if (input.getItems() != null) {
            for (Input.Item item : input.getItems()) {
                Product product = productRepository.getProduct(item.getIdProduct());
                double itemFreight = FreightCalculator.calculate(product, item.getQuantity());
                output.setFreight(output.getFreight() + itemFreight);
            }
        }
        return output;
    }

    public static class Input {
        private Item[] items;

        public Item[] getItems() {
            return items;
        }

        public void setItems(Item[] items) {
            this.items = items;
        }

        public static class Item {
            private int idProduct;
            private int quantity;

            public int getIdProduct() {
                return idProduct;
            }

            public void setIdProduct(int idProduct) {
                this.idProduct = idProduct;
            }

            public int getQuantity() {
                return quantity;
            }

            public void setQuantity(int quantity) {
                this.quantity = quantity;
            }
        }
    }

    public static class Output {
        private double freight;

        public double getFreight() {
            return freight;
        }

        public void setFreight(double freight) {
            this.freight = freight;
        }
    }
}
