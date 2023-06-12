package org.example.checkout.application.usecase;

import org.example.checkout.application.repository.OrderRepository;
import org.example.checkout.domain.entity.Order;

public class GetOrder {

    private final OrderRepository orderRepository;

    public GetOrder(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Output execute(String id) {
        Order order = this.orderRepository.getById(id);
        Output output = new Output(order.getCode(), order.getTotal(), order.getFreight());
        return output;
    }

    public static class Output {
        private final String code;
        private final double total;
        private final double freight;

        public Output(String code, double total, double freight) {
            this.code = code;
            this.total = total;
            this.freight = freight;
        }

        public String getCode() {
            return code;
        }

        public double getTotal() {
            return total;
        }

        public double getFreight() {
            return freight;
        }
    }
}
