package com.marcusfeitosa.orderservice.service;

import com.marcusfeitosa.orderservice.dto.OrderLineItemsDTO;
import com.marcusfeitosa.orderservice.dto.OrderRequest;
import com.marcusfeitosa.orderservice.model.Order;
import com.marcusfeitosa.orderservice.model.OrderLineItems;
import com.marcusfeitosa.orderservice.repository.OrderRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static java.util.Arrays.stream;

@Service

public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    public void placeOrder(OrderRequest orderRequest){
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDTOList()
                .stream()
                .map(this::mapToDTO)
                .toList();

            order.setOrderLineItems(orderLineItems);
            orderRepository.save(order);
    }

    private OrderLineItems mapToDTO(OrderLineItemsDTO orderLineItemsDTO) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDTO.getPrice());
        orderLineItems.setQuantity(orderLineItemsDTO.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDTO.getSkuCode());
        return orderLineItems;

    }
}
