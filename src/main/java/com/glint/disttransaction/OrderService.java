package com.glint.disttransaction;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class OrderService {

    private final PaymentRepository paymentRepository;
    private final InventoryRepository inventoryRepository;

    public OrderService(PaymentRepository paymentRepository, InventoryRepository inventoryRepository) {
        this.paymentRepository = paymentRepository;
        this.inventoryRepository = inventoryRepository;
    }

    @Transactional
    public void placeOrder(boolean fail) {
        paymentRepository.deduct();
        inventoryRepository.reserve();

        if (fail) {
            throw new RuntimeException("Failed to place order");
        }
    }

}
