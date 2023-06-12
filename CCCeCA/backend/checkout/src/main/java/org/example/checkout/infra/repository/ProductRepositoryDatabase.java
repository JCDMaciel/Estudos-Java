package org.example.checkout.infra.repository;

import org.example.checkout.domain.entity.Product;
import org.example.checkout.application.repository.ProductRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryDatabase implements ProductRepository {
    private final Connection connection;

    public ProductRepositoryDatabase(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Product getProduct(int idProduct) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM cccat10.product WHERE id_product = ?")) {
            statement.setInt(1, idProduct);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapProduct(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Product> getProducts() {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM cccat10.product WHERE id_product IN (1, 2, 3)")) {
            ResultSet resultSet = statement.executeQuery();
            List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                Product product = mapProduct(resultSet);
                if (product != null) {
                    products.add(product);
                }
            }
            return products;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private Product mapProduct(ResultSet resultSet) throws SQLException {
        int idProduct = resultSet.getInt("id_product");
        String description = resultSet.getString("description");
        double price = resultSet.getDouble("price");
        int width = resultSet.getInt("width");
        int height = resultSet.getInt("height");
        int length = resultSet.getInt("length");
        double weight = resultSet.getDouble("weight");
        String currency = resultSet.getString("currency");
        return new Product(idProduct, description, price, width, height, length, weight, currency);
    }
}
