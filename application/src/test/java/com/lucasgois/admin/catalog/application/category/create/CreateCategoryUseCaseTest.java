package com.lucasgois.admin.catalog.application.category.create;

import com.lucasgois.admin.catalog.domain.category.CategoryGateway;
import com.lucasgois.admin.catalog.domain.shared.exceptions.DomainException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateCategoryUseCaseTest {

    @Mock
    private CategoryGateway gatewayMock;

    @InjectMocks
    private CreateCategoryUseCase useCase;

    @Test
    @DisplayName("Should create and return a category")
    void testCreateCategory() {
        final var expectedName = "name";
        final var expectedDescription = "description";
        final var expectedIsActive = true;

        final var command = new CategoryInput(expectedName, expectedDescription, expectedIsActive);

        when(gatewayMock.create(any()))
                .thenAnswer(returnsFirstArg());

        final var categoryOutput = useCase.execute(command);

        assertNotNull(categoryOutput);
        assertNotNull(categoryOutput.id());

        verify(gatewayMock, times(1))
                .create(
                        argThat(category -> Objects.equals(expectedName, category.getName())
                                                && Objects.equals(expectedDescription, category.getDescription())
                                                && Objects.equals(expectedIsActive, category.isActive())
                                                && Objects.nonNull(category.getId())
                                                && Objects.nonNull(category.getCreatedAt())
                                                && Objects.nonNull(category.getUpdatedAt())
                                                && Objects.isNull(category.getDeletedAt()))
                );
    }

    @Test
    @DisplayName("Should throw DomainException when name is null")
    void testCreateCategoryWithNullName() {
        final var expectedDescription = "description";
        final var expectedIsActive = true;

        final var expectedErrorMessage = "Category Name cannot be null";
        final var expectedErrorCount = 1;

        final var command = new CategoryInput(null, expectedDescription, expectedIsActive);

        final var actualException = assertThrows(DomainException.class, () -> useCase.execute(command));

        assertEquals(expectedErrorMessage, actualException.getMessage());
        assertEquals(expectedErrorCount, actualException.getErrors().size());

        verify(gatewayMock, never())
                .create(any());
    }

    @Test
    @DisplayName("Should create and return a category")
    void testCreateInactiveCategory() {
        final var expectedName = "name";
        final var expectedDescription = "description";
        final var expectedIsActive = false;

        final var command = new CategoryInput(expectedName, expectedDescription, expectedIsActive);

        when(gatewayMock.create(any()))
                .thenAnswer(returnsFirstArg());

        final var categoryOutput = useCase.execute(command);

        assertNotNull(categoryOutput);
        assertNotNull(categoryOutput.id());

        verify(gatewayMock, times(1))
                .create(
                        argThat(category -> Objects.equals(expectedName, category.getName())
                                                && Objects.equals(expectedDescription, category.getDescription())
                                                && Objects.equals(expectedIsActive, category.isActive())
                                                && Objects.nonNull(category.getId())
                                                && Objects.nonNull(category.getCreatedAt())
                                                && Objects.nonNull(category.getUpdatedAt())
                                                && Objects.nonNull(category.getDeletedAt()))
                );
    }

    @Test
    @DisplayName("Should return an exception when gateway throws an any exception")
    void testCreateCategoryWithAnyException() {
        final var expectedName = "name";
        final var expectedDescription = "description";
        final var expectedIsActive = true;

        final var expectedErrorMessage = "Gateway error";

        final var command = new CategoryInput(expectedName, expectedDescription, expectedIsActive);

        when(gatewayMock.create(any()))
                .thenThrow(new IllegalStateException(expectedErrorMessage));

        final var actualException = assertThrows(IllegalStateException.class, () -> useCase.execute(command));

        assertEquals("Gateway error", actualException.getMessage());

        verify(gatewayMock, times(1))
                .create(
                        argThat(category -> Objects.equals(expectedName, category.getName())
                                                && Objects.equals(expectedDescription, category.getDescription())
                                                && Objects.equals(expectedIsActive, category.isActive())
                                                && Objects.nonNull(category.getId())
                                                && Objects.nonNull(category.getCreatedAt())
                                                && Objects.nonNull(category.getUpdatedAt())
                                                && Objects.isNull(category.getDeletedAt()))
                );
    }
}