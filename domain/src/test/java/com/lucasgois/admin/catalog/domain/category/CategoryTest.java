package com.lucasgois.admin.catalog.domain.category;

import com.lucasgois.admin.catalog.domain.shared.exceptions.DomainException;
import com.lucasgois.admin.catalog.domain.shared.validation.ThrowsValidationHandler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {
    @Test
    @DisplayName("Should instance a category with newCategory method")
    void getId() {
        final var expectedName = "Films";
        final var expectedDescription = "Category most viewed";
        final var expectedIsActive = true;

        final var category = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        assertNotNull(category);
        assertNotNull(category.getId());
        assertEquals(expectedName, category.getName());
        assertEquals(expectedDescription, category.getDescription());
        assertEquals(expectedIsActive, category.isActive());
        assertNotNull(category.getCreatedAt());
        assertNotNull(category.getUpdatedAt());
        assertNull(category.getDeletedAt());
    }

    @Test
    @DisplayName("Should throw exception when name is null")
    void testThrowNameNull() {
        final var expectedDescription = "Category most viewed";
        final var expectedIsActive = true;

        final var expectedException = "Category Name cannot be null";
        final var expectedErrorCount = 1;

        final var validationResult = assertThrows(
                DomainException.class, () -> Category
                                                .newCategory(null, expectedDescription, expectedIsActive)
                                                .validate(new ThrowsValidationHandler())
        );

        assertEquals(expectedErrorCount, validationResult.getErrors().size());
        assertEquals(expectedException, validationResult.getErrors().get(0).message());
    }

    @Test
    @DisplayName("Should throw exception when name is null or empty")
    void testThrowNameEmpty() {
        final var expectedName = " ";
        final var expectedDescription = "Category most viewed";
        final var expectedIsActive = true;

        final var expectedException = "Category Name cannot be empty";
        final var expectedErrorCount = 1;

        final var validationResult = assertThrows(
                DomainException.class, () -> Category
                        .newCategory(expectedName, expectedDescription, expectedIsActive)
                        .validate(new ThrowsValidationHandler())
        );

        assertEquals(expectedErrorCount, validationResult.getErrors().size());
        assertEquals(expectedException, validationResult.getErrors().get(0).message());
    }

    @Test
    @DisplayName("Should throw exception when name has less than 3 characters")
    void testThrowNameShort() {
        final var expectedName = "ab";
        final var expectedDescription = "Category most viewed";
        final var expectedIsActive = true;

        final var expectedException = "Category Name must have at least 3 characters";
        final var expectedErrorCount = 1;

        final var validationResult = assertThrows(
                DomainException.class, () -> Category
                        .newCategory(expectedName, expectedDescription, expectedIsActive)
                        .validate(new ThrowsValidationHandler())
        );

        assertEquals(expectedErrorCount, validationResult.getErrors().size());
        assertEquals(expectedException, validationResult.getErrors().get(0).message());
    }

    @Test
    @DisplayName("Should throw exception when name has more than 255 characters")
    void testThrowNameLonger() {
        final var expectedName = "a".repeat(256);
        final var expectedDescription = "Category most viewed";
        final var expectedIsActive = true;

        final var expectedException = "Category Name must have less than 255 characters";
        final var expectedErrorCount = 1;

        final var validationResult = assertThrows(
                DomainException.class, () -> Category
                        .newCategory(expectedName, expectedDescription, expectedIsActive)
                        .validate(new ThrowsValidationHandler())
        );

        assertEquals(expectedErrorCount, validationResult.getErrors().size());
        assertEquals(expectedException, validationResult.getErrors().get(0).message());
    }

    @Test
    @DisplayName("Should not throw when description is null or empty")
    void testDescriptionBlankNotThrow() {
        final var expectedName = "Films";
        final var expectedDescription = " ";
        final var expectedIsActive = true;

        final var category = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        assertDoesNotThrow(() -> category.validate(new ThrowsValidationHandler()));

        assertNotNull(category);
        assertNotNull(category.getId());
        assertEquals(expectedName, category.getName());
        assertEquals(expectedDescription, category.getDescription());
        assertEquals(expectedIsActive, category.isActive());
        assertNotNull(category.getCreatedAt());
        assertNotNull(category.getUpdatedAt());
        assertNull(category.getDeletedAt());
    }

    @Test
    @DisplayName("Should update activate to false when call method deactivate")
    void testDeactivate() {
        final var expectedName = "Films";
        final var expectedDescription = "Category most viewed";
        final var expectedIsActive = true;

        final var category = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        assertTrue(category.isActive());
        assertNull(category.getDeletedAt());

        category.deactivate();

        assertNotNull(category);
        assertNotNull(category.getId());
        assertEquals(expectedName, category.getName());
        assertEquals(expectedDescription, category.getDescription());
        assertFalse(category.isActive());
        assertNotNull(category.getCreatedAt());
        assertNotNull(category.getUpdatedAt());
        assertNotNull(category.getDeletedAt());
    }

    @Test
    @DisplayName("Should update activate to true when call method activate")
    void testActivate() {
        final var expectedName = "Films";
        final var expectedDescription = "Category most viewed";
        final var expectedIsActive = false;

        final var category = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        final var createdAt = category.getCreatedAt();

        assertFalse(category.isActive());
        assertNotNull(category.getDeletedAt());

        category.activate();

        assertNotNull(category);
        assertNotNull(category.getId());
        assertEquals(expectedName, category.getName());
        assertEquals(expectedDescription, category.getDescription());
        assertEquals(createdAt, category.getCreatedAt());
        assertTrue(category.isActive());
        assertNotNull(category.getCreatedAt());
        assertNotNull(category.getUpdatedAt());
        assertNull(category.getDeletedAt());
    }

    @Test
    @DisplayName("Should update category values when call method update")
    void testUpdateWithInactive() {
        final var expectedName = "Films";
        final var expectedDescription = "Category most viewed";
        final var expectedIsActive = true;

        final var category = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        final var expectedNameUpdate = "Series";
        final var expectedDescriptionUpdate = "Category most rated";
        final var expectedIsActiveUpdate = false;

        category.update(expectedNameUpdate, expectedDescriptionUpdate, expectedIsActiveUpdate);

        assertDoesNotThrow(() -> category.validate(new ThrowsValidationHandler()));

        assertNotNull(category);
        assertNotNull(category.getId());
        assertEquals(expectedNameUpdate, category.getName());
        assertEquals(expectedDescriptionUpdate, category.getDescription());
        assertEquals(expectedIsActiveUpdate, category.isActive());
        assertNotNull(category.getCreatedAt());
        assertNotNull(category.getUpdatedAt());
        assertNotNull(category.getDeletedAt());
    }

    @Test
    @DisplayName("Should update category values when call method update")
    void testUpdateWithActive() {
        final var expectedName = "Films";
        final var expectedDescription = "Category most viewed";
        final var expectedIsActive = false;

        final var category = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        final var expectedNameUpdate = "Series";
        final var expectedDescriptionUpdate = "Category most rated";
        final var expectedIsActiveUpdate = true;

        category.update(expectedNameUpdate, expectedDescriptionUpdate, expectedIsActiveUpdate);

        assertDoesNotThrow(() -> category.validate(new ThrowsValidationHandler()));

        assertNotNull(category);
        assertNotNull(category.getId());
        assertEquals(expectedNameUpdate, category.getName());
        assertEquals(expectedDescriptionUpdate, category.getDescription());
        assertEquals(expectedIsActiveUpdate, category.isActive());
        assertNotNull(category.getCreatedAt());
        assertNotNull(category.getUpdatedAt());
        assertNull(category.getDeletedAt());
    }

    @Test
    @DisplayName("Should throw when call method update with invalid values")
    void testUpdateThrow() {
        final var expectedName = "Films";
        final var expectedDescription = "Category most viewed";
        final var expectedIsActive = false;

        final var category = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        final var expectedNameUpdate = " ";
        final var expectedDescriptionUpdate = "Category most rated";
        final var expectedIsActiveUpdate = true;

        category.update(expectedNameUpdate, expectedDescriptionUpdate, expectedIsActiveUpdate);

        assertThrows(
                DomainException.class, () -> category.validate(new ThrowsValidationHandler())
        );
    }
}