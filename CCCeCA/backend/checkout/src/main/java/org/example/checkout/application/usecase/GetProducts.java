package org.example.checkout.application.usecase;

import org.example.checkout.application.repository.ProductRepository;
import org.example.checkout.domain.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class GetProducts {

    private final ProductRepository productRepository;

    public GetProducts(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Output> execute() {
        List<Output> output = new ArrayList<>();
        List<Product> products = (List<Product>) this.productRepository.getProducts();
        for (Product product : products) {
            Output item = new Output(product.getIdProduct(), product.getDescription(), product.getPrice());
            output.add(item);
        }
        return output;
    }

    public static class Output {
        private final int idProduct;
        private final String description;
        private final double price;

        public Output(int idProduct, String description, double price) {
            this.idProduct = idProduct;
            this.description = description;
            this.price = price;
        }

        public int getIdProduct() {
            return idProduct;
        }

        public String getDescription() {
            return description;
        }

        public double getPrice() {
            return price;
        }
    }
}
