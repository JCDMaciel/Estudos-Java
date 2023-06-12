package org.example.checkout.application.repository;

import org.example.checkout.domain.entity.Product;
import java.util.List;

public interface ProductRepository {
    Product getProduct(int idProduct);
    List<Product> getProducts();
}
