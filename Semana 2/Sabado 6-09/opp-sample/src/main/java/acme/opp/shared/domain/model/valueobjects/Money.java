package acme.opp.shared.domain.model.valueobjects;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Objects;

public record Money(BigDecimal amount, Currency currency) {
    /**
     * Constructs a Money object with validation
     * @param amount the monetary amount,, it must be null or negative
     * @param currency the currency of amount, it must not be null
     * @throws IllegalArgumentException if the amount is null or negative, or if the currency is null
     */
    public Money{
        if (Objects.isNull(amount) || amount.compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
        if (Objects.isNull(currency)){
            throw new IllegalArgumentException("Currency must be greater than zero");
        }
        if (amount.scale() > currency.getDefaultFractionDigits()){
            throw new IllegalArgumentException("Amount must be less than currency.getDefaultFractionDigits");
        }
    }

    /**
     * Adds another Money instance to this one, ensuring both have the same currency
     * @param other
     * @return
     */
    public Money add(Money other){
        if(!this.currency.equals(other.currency)){
            throw new IllegalArgumentException("Currency must be same");
        }
        return new Money(amount.add(other.amount), other.currency);
    }

    /**
     * Multiplier the monetary amount by a given multiplier
     * @param multiplier
     * @return
     */
    public Money multiply(BigDecimal multiplier){
        return new Money(amount.multiply(multiplier), currency);
    }

}
