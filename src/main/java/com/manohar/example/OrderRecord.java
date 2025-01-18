package com.manohar.example;

public record OrderRecord(
        String customerName,
        String productName,
        String quantity
) {
}
