package org.example.checkout.domain.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "coupons")
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private double percentage;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date expireDate;

    public Coupon() {
    }

    public Coupon(String code, double percentage, Date expireDate) {
        this.code = code;
        this.percentage = percentage;
        this.expireDate = expireDate;
    }

    public boolean isExpired(Date today) {
        return this.expireDate.getTime() < today.getTime();
    }

    public double calculateDiscount(double amount) {
        return (amount * this.percentage) / 100;
    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }
}
