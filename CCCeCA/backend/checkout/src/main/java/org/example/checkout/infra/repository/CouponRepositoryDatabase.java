package org.example.checkout.infra.repository;

import org.example.checkout.domain.entity.Coupon;
import org.example.checkout.application.repository.CouponRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CouponRepositoryDatabase implements CouponRepository {

    private final Connection connection;

    public CouponRepositoryDatabase(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Coupon getCoupon(String code) {
        String sql = "SELECT * FROM cccat10.coupon WHERE code = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, code);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapCoupon(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Coupon mapCoupon(ResultSet resultSet) throws SQLException {
        String code = resultSet.getString("code");
        float percentage = resultSet.getFloat("percentage");
        java.util.Date expireDate = resultSet.getDate("expire_date");
        return new Coupon(code, percentage, expireDate);
    }
}
