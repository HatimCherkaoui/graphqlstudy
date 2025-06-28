package com.hatimcherkaoui.graphqlstudy.model.dto;

public record UpdateProductInput(Long id, String title, String description, Float price) {
}