package acme.opp.sales;

import java.util.Objects;
import java.util.UUID;

/**
 * Reoresents a unique Identifier for a product within Sales bounded context.
 * @param value the product identifier value, it must not be null for sure.
 * @author Samuel Bonifacio (Open Source Application Development Team)
 */

public record ProductId(UUID value) {

    /**
     * COnstructs a ProductId object with validation.
     * @param value the product identifier value.
     * @throws IllegalArgumentException if the value is null.
     */
    public ProductId {
        if (Objects.isNull(value)) {
            throw new IllegalArgumentException("Product id cannot be null");
        }
    }

    /**
     * Constructs a ProductId with a random UUID value.
     */

    public ProductId() {
        this (UUID.randomUUID());
    }
}
