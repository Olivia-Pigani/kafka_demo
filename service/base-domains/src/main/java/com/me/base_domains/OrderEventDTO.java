package com.me.base_domains;

public record OrderEventDTO(
        String message,
        String status,
        Order order
) {
}
