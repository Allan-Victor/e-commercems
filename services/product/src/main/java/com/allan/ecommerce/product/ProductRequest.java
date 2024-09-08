package com.allan.ecommerce.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequest(
        Integer id,
        @NotNull(message = "Product name is required")
        String name,
        @NotNull(message = "Product description is required")
        String description,
        @Positive(message = "Available Quantity should be a positive number")
        double availableQuantity,
        @Positive(message = "Price should be a positive number")
        BigDecimal price,
        @NotNull(message = "price Category is required" )
        Integer categoryId
) {
}
