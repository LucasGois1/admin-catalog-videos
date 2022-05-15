package com.lucasgois.admin.catalog.domain.category;

import com.lucasgois.admin.catalog.domain.category.Category;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {
    @Test
    void getId() {
        Category category = new Category();
        category.setId("1");
        assertEquals("1", category.getId());
    }
}