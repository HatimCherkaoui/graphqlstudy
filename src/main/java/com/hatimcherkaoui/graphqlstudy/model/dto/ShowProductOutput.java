package com.hatimcherkaoui.graphqlstudy.model.dto;

import java.time.LocalDateTime;

public record ShowProductOutput(Long id, String title, String description, Float price, LocalDateTime createdAt,
                                LocalDateTime updatedAt) {
}
