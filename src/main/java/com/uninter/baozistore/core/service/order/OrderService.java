package com.uninter.baozistore.core.service.order;

import com.uninter.baozistore.core.common.Messages;
import com.uninter.baozistore.core.model.Customer;
import com.uninter.baozistore.core.model.Order;
import com.uninter.baozistore.core.model.Product;
import com.uninter.baozistore.core.repository.customer.CustomerRepository;
import com.uninter.baozistore.core.repository.order.OrderRepository;
import com.uninter.baozistore.core.repository.product.ProductRepository;
import com.uninter.baozistore.core.service.customer.CustomerService;
import com.uninter.baozistore.infrastructure.controller.order.OrderOutput;
import com.uninter.baozistore.infrastructure.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
    }

    public OrderOutput createOrder(Long customerId, Long productId, Integer quantity) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new NotFoundException(Messages.CUSTOMER_NOT_FOUND + " " + customerId));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException(Messages.PRODUCT_NOT_FOUND + " " + productId));
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }

        Order order = Order.builder()
                .customer(customer)
                .product(product)
                .quantity(quantity)
                .build();
        order = orderRepository.save(order);
        BigDecimal price = order.calculatePrice();
        return new OrderOutput(order.getId(), order.getCustomer().getId(), order.getProduct().getId(), order.getQuantity(), price);
    }

    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Messages.ORDER_NOT_FOUND + " " + id));
        orderRepository.delete(order);
    }

    public List<OrderOutput> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(order -> new OrderOutput(order.getId(), order.getCustomer().getId(), order.getProduct().getId(), order.getQuantity(), order.calculatePrice()))
                .toList();
    }

    public OrderOutput getOrderById(Long id) {
        return orderRepository.findById(id)
                .map(order -> new OrderOutput(order.getId(), order.getCustomer().getId(), order.getProduct().getId(), order.getQuantity(), order.calculatePrice()))
                .orElseThrow(() -> new NotFoundException(Messages.ORDER_NOT_FOUND + " " + id));
    }
}
