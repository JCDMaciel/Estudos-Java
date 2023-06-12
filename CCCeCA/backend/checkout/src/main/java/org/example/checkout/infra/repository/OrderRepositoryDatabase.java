package org.example.checkout.infra.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.example.checkout.application.repository.OrderRepository;
import org.example.checkout.domain.entity.Item;
import org.example.checkout.domain.entity.Order;

public class OrderRepositoryDatabase implements OrderRepository {

    private final Connection connection;

    public OrderRepositoryDatabase(Connection connection) {
        this.connection = connection;
    }

    @Override
    public CompletableFuture<Void> save(Order order) {
        String orderInsertSql = "INSERT INTO cccat10.order (id_order, cpf, code, total, freight) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement orderStatement = connection.prepareStatement(orderInsertSql)) {
            orderStatement.setString(1, order.getIdOrder());
            orderStatement.setString(2, order.getCpf().getValue());
            orderStatement.setString(3, order.getCode());
            orderStatement.setDouble(4, order.getTotal());
            orderStatement.setDouble(5, order.getFreight());
            orderStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save order to the database.", e);
        }

        String itemInsertSql = "INSERT INTO cccat10.item (id_order, id_product, price, quantity, currency) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement itemStatement = connection.prepareStatement(itemInsertSql)) {
            for (Item item : order.getItems()) {
                itemStatement.setString(1, order.getIdOrder());
                itemStatement.setInt(2, item.getProduct().getIdProduct());
                itemStatement.setDouble(3, item.getProduct().getPrice());
                itemStatement.setInt(4, item.getQuantity());
                itemStatement.setString(5, item.getProduct().getCurrency());
                itemStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save items to the database.", e);
        }
        return CompletableFuture.completedFuture(null);
    }

    @Override
    public Order getById(String id) {
        String orderQuerySql = "SELECT * FROM cccat10.order WHERE id_order = ?";
        Order order = null;
        try (PreparedStatement orderStatement = connection.prepareStatement(orderQuerySql)) {
            orderStatement.setString(1, id);
            try (ResultSet resultSet = orderStatement.executeQuery()) {
                if (resultSet.next()) {
                    order = mapOrder(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve order from the database.", e);
        }

        if (order != null) {
            String itemQuerySql = "SELECT * FROM cccat10.item WHERE id_order = ?";
            try (PreparedStatement itemStatement = connection.prepareStatement(itemQuerySql)) {
                itemStatement.setString(1, id);
                try (ResultSet resultSet = itemStatement.executeQuery()) {
                    List<Item> items = new ArrayList<>();
                    while (resultSet.next()) {
                        Item item = mapItem(resultSet);
                        items.add(item);
                    }
                    order.setItems(items);
                }
            } catch (SQLException e) {
                throw new RuntimeException("Failed to retrieve items from the database.", e);
            }
        }

        return order;
    }

    @Override
    public int count() {
        String countSql = "SELECT COUNT(*) FROM cccat10.order";
        try (PreparedStatement countStatement = connection.prepareStatement(countSql)) {
            try (ResultSet resultSet = countStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve order count from the database.", e);
        }
        return 0;
    }

    private Order mapOrder(ResultSet resultSet) throws SQLException {
        String idOrder = resultSet.getString("id_order");
        String cpf = resultSet.getString("cpf");
        String code = resultSet.getString("code");
        double total = resultSet.getDouble("total");
        double freight = resultSet.getDouble("freight");
        return new Order(idOrder, cpf, code, total, new Date(), freight);
    }

    private Item mapItem(ResultSet resultSet) throws SQLException {
        int idProduct = resultSet.getInt("id_product");
        double price = resultSet.getDouble("price");
        int quantity = resultSet.getInt("quantity");
        String currency = resultSet.getString("currency");
        return new Item(idProduct, price, quantity, currency);
    }
}
