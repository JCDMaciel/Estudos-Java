package org.example.checkout.application.usecase;

import java.util.Date;

import org.example.checkout.application.repository.CouponRepository;
import org.example.checkout.domain.entity.Coupon;

public class ValidateCoupon {

    private final CouponRepository couponRepository;

    public ValidateCoupon(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    public boolean execute(String code) {
        Coupon coupon = couponRepository.getCoupon(code);
        return !coupon.isExpired(new Date());
    }
}
