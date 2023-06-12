package org.example.checkout.application.usecase;

import org.example.checkout.domain.entity.Coupon;
import org.example.checkout.domain.entity.CurrencyTable;
import org.example.checkout.domain.entity.FreightCalculator;
import org.example.checkout.domain.entity.Order;
import org.example.checkout.domain.entity.Product;
import org.example.checkout.application.gateway.CurrencyGateway;
import org.example.checkout.application.repository.CouponRepository;
import org.example.checkout.application.repository.OrderRepository;
import org.example.checkout.application.repository.ProductRepository;

import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.List;

public class Checkout {
    private final CurrencyGateway currencyGateway;
    private final ProductRepository productRepository;
    private final CouponRepository couponRepository;
    private final OrderRepository orderRepository;

    public Checkout(CurrencyGateway currencyGateway, ProductRepository productRepository,
                    CouponRepository couponRepository, OrderRepository orderRepository) {
        this.currencyGateway = currencyGateway;
        this.productRepository = productRepository;
        this.couponRepository = couponRepository;
        this.orderRepository = orderRepository;
    }

    public Output execute(Input input) {
        List<Currency> currencies = (List<Currency>) this.currencyGateway.getCurrencies();
        CurrencyTable currencyTable = new CurrencyTable();
        for (Currency currency : currencies) {
            currencyTable.addCurrency(currency.getCurrencyCode(), 1.0);
        }
        long sequence = this.orderRepository.count();
        Order order = new Order(input.getUuid(), input.getCpf(), currencyTable, (int) sequence, new Date());
        double freight = 0;
        if (input.getItems() != null) {
            for (Item item : input.getItems()) {
                Product product = this.productRepository.getProduct(item.getIdProduct());
                order.addItem(product, item.getQuantity());
                double itemFreight = FreightCalculator.calculate(product, item.getQuantity());
                freight += itemFreight;
            }
        }
        if (input.getFrom() != null && input.getTo() != null) {
            order.setFreight(freight);
        }
        if (input.getCoupon() != null) {
            Coupon coupon = this.couponRepository.getCoupon(input.getCoupon());
            order.addCoupon(coupon);
        }
        double total = order.getTotal();
        this.orderRepository.save(order);
        return new Output(total, freight);
    }

    public static class Input {
        private String uuid;
        private String cpf;
        private List<Item> items;
        private String coupon;
        private String from;
        private String to;

        public Input() {
        }

        public Input(String s, ArrayList<Object> objects) {
            this.uuid = s;
            this.items = new ArrayList<>();
            for (Object obj : objects) {
                if (obj instanceof Item) {
                    this.items.add((Item) obj);
                }
            }
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
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

        public void setItems(List<Item> items) {
            this.items = items;
        }

        public String getCoupon() {
            return coupon;
        }

        public void setCoupon(String coupon) {
            this.coupon = coupon;
        }

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }
    }

    public static class Output {
        private double total;
        private double freight;

        public Output(double total, double freight) {
            this.total = total;
            this.freight = freight;
        }

        public double getTotal() {
            return total;
        }

        public void setTotal(double total) {
            this.total = total;
        }

        public double getFreight() {
            return freight;
        }

        public void setFreight(double freight) {
            this.freight = freight;
        }
    }

    public static class Item {
        private int idProduct;
        private int quantity;
        private double price;

        public Item() {
        }

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

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }
    }
}
