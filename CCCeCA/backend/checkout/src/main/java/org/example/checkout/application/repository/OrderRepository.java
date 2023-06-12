package org.example.checkout.application.repository;

import java.util.concurrent.CompletableFuture;

import org.example.checkout.domain.entity.Order;

public interface OrderRepository {
    CompletableFuture<Void> save(Order order);
    Order getById(String id);
    int count();
}
