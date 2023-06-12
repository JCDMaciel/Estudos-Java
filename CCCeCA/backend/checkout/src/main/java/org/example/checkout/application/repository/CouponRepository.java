package org.example.checkout.application.repository;

import org.example.checkout.domain.entity.Coupon;

public interface CouponRepository {
    Coupon getCoupon(String code);
}
