package acme.oop.shared.domain.model.valueobjects;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Objects;

/**
 * Represents a monetary value with an amount and currency.
 * The amount must be non-null and non-negative, and the currency must be non-null.
 * @param amount the monetary amount, it must not be null or negative.
 * @param currency the currency of the amount, it must not be null.
 * @author Open Source Application Development Team
 */
public record Money(BigDecimal amount, Currency currency) {
    /**
     * Constructs a Money object with validation.
     * @param amount the monetary amount, it must not be null or negative.
     * @param currency the currency of the amount, it must not be null.
     * @throws IllegalArgumentException if the amount is null or negative, or if the currency
     */
    public Money {
        if (Objects.isNull(amount) || amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Amount cannot be null or negative");
        }
        if (Objects.isNull(currency)) {
            throw new IllegalArgumentException("Currency cannot be null");
        }
        if (amount.scale() > currency.getDefaultFractionDigits()) {
            throw new IllegalArgumentException("Amount scale exceeds currency default fraction digits");
        }
    }

    /**
     * Adds another Money instance to this one, ensuring both have the same currency.
     * @param other the other Money instance to add, it must not be null and must have the same currency.
     * @return a new Money instance with the summed amount.
     * @throws IllegalArgumentException if the other Money is null or has a different currency.
     */
    public Money add(Money other) {
        if (!this.currency.equals(other.currency)) {
            throw new IllegalArgumentException("Cannot add Money with different currencies");
        }
        return new Money(this.amount.add(other.amount), this.currency);
    }

    /**
     * Multiplies the monetary amount by a given multiplier.
     * @param multiplier the multiplier to apply, it must not be null.
     * @return a new Money instance with the multiplied amount.
     * @throws IllegalArgumentException if the multiplier is null.
     */
    public Money multiply(BigDecimal multiplier) {
        return new Money(this.amount.multiply(multiplier), this.currency);
    }
}
