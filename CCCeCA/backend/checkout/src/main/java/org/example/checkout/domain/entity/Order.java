package org.example.checkout.domain.entity;

import javax.persistence.*;
import java.time.Year;
import java.util.*;

@Entity
@Table(name = "orders")
public class Order {

    private static int sequence = 0;

    @Id
    @Column(name = "id_order")
    private String idOrder;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cpf_id")
    private Cpf cpf;

    @Column(name = "code")
    private String code;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;

    @Column(name = "freight")
    private double freight;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private List<Item> items;

    @Column(name = "total")
    private double total;

    @Transient
    private CurrencyTable currencyTable;

    public Order(UUID uuid, String cpf) {
        if (uuid == null) {
            throw new IllegalArgumentException("UUID cannot be null");
        }
        if (cpf == null || cpf.isEmpty()) {
            throw new IllegalArgumentException("CPF cannot be null or empty");
        }
        this.idOrder = uuid.toString();
        this.cpf = new Cpf(cpf);
        this.items = new ArrayList<>();
        this.code = Year.now().getValue() + String.format("%08d", generateSequence());
    }

    public Order(UUID uuid, String cpf, CurrencyTable currencyTable) {
        this(uuid, cpf);
        this.currencyTable = currencyTable;
    }

    public Order(String uuid, String cpf, CurrencyTable currencyTable, int sequence, Date date) {
        this(UUID.fromString(uuid), cpf, currencyTable);
        if (currencyTable == null) {
            throw new IllegalArgumentException("CurrencyTable cannot be null");
        }
        this.code = Year.now().getValue() + String.format("%08d", sequence);
    }

    public Order(String idOrder, String cpf, String code, double freight, Date date) {
        this(idOrder, cpf, code, freight, date, 0.0);
    }

    public Order(String idOrder, String cpf, String code, double freight, Date date, double total) {
        if (idOrder == null) {
            idOrder = UUID.randomUUID().toString();
        }
        this.idOrder = idOrder;
        this.cpf = new Cpf(cpf);
        this.items = new ArrayList<>();
        this.code = code;
        this.freight = freight;
        this.total = total;
    }

    public Order() {

    }

    private int generateSequence() {
        synchronized (Order.class) {
            sequence++;
            return sequence;
        }
    }

    public void addItem(Product product, int quantity) {
        for (Item item : items) {
            if (item.getProduct().getIdProduct() == product.getIdProduct()) {
                throw new IllegalArgumentException("Duplicate item");
            }
        }

        if (quantity <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero.");
        }

        for (Item item : items) {
            if (item.getProduct().getIdProduct() == product.getIdProduct()) {
                throw new IllegalArgumentException("Item duplicado.");
            }
        }

        Item newItem = new Item(product, quantity);
        items.add(newItem);
    }

    public void addCoupon(Coupon coupon) {
        if (!coupon.isExpired(new Date())) {
            this.coupon = coupon;
        }
    }

    public String getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(String idOrder) {
        this.idOrder = idOrder;
    }

    public Cpf getCpf() {
        return cpf;
    }

    public void setCpf(Cpf cpf) {
        this.cpf = cpf;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Coupon getCoupon() {
        return coupon;
    }

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

    public double getFreight() {
        return freight;
    }

    public void setFreight(double freight) {
        this.freight = freight;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public double getTotal() {
        if (total == 0.0) {
            double calculatedTotal = 0;
            for (Item item : items) {
                double itemTotal = item.getProduct().getPrice() * item.getQuantity();
                calculatedTotal += itemTotal;
            }
            if (coupon != null) {
                calculatedTotal -= coupon.calculateDiscount(calculatedTotal);
            }
            calculatedTotal += freight;
            return calculatedTotal;
        } else {
            return total;
        }
    }

    public double getTotalInBRL() {
        double total = 0;
        for (Item item : items) {
            double itemPrice = item.getProduct().getPrice();
            int quantity = item.getQuantity();
            String currency = item.getProduct().getCurrency();

            if (currency.equals("USD")) {
                double conversionRate = currencyTable.getCurrency(currency);
                itemPrice *= conversionRate;
            }

            total += itemPrice * quantity;
        }

        if (coupon != null) {
            double discount = coupon.calculateDiscount(total);
            total -= discount;
        }

        total += freight;

        return total;
    }
}
