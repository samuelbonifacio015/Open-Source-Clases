package acme.oop.shared.domain.model.valueobjects;

import java.util.Objects;
import java.util.UUID;

/**
 * Representa un identificador unico de un cliente.
 * @param value
 * @author Open Source Application Development Team
 */
public record CustomerId(UUID value) {

    /**
     * Construye un CustomerId con un valor dado.
     * @param value
     */
    public CustomerId {
        if (Objects.isNull(value)) {
            throw new IllegalArgumentException("CustomerId cannot be null");
        }
    }

    /**
     * Construye un CustomerId aleatorio con UUID.
     */
    public CustomerId(){
        this(UUID.randomUUID());
    }

    public String toString() {
        return value.toString();
    }
}
