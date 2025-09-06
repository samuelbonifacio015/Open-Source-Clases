package acme.opp.sales.domain.model.aggregates;

import acme.opp.crm.domain.model.valueobjects.ProductId;
import acme.opp.shared.domain.model.valueobjects.Money;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

/*
 * Represents the SalesOrderItem
 */

public class SalesOrderItem {
    private final ProductId productId;
    @Setter private int quantity;
    @Setter private Money unitPrice;

    /**
     * Constructs a SalesOrderItem with the specified product ID, quantity, and unit price.
     * @param productId the unique identifier of the product.
     * @param quantity the quantity of the product ordered, must be greater than zero.
     * @param unitPrice the quantity of the product ordered, must be greater than zero.
     * @throws IllegalArgumentException if quantity is less than or equal to zero,
     *                                  if unitprice is not positive,
     *                                  or if unitPrice does not have a valid currency.
     */
    SalesOrderItem(ProductId productId, int quantity, Money unitPrice) {
        if (quantity <= 0){
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
        if (unitPrice.amount().compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("Unit price must be greater than zero");
        }
        if (Objects.isNull(unitPrice.currency()) || Objects.isNull(unitPrice.currency().getCurrencyCode())
            || unitPrice.currency().getCurrencyCode().isBlank()) {
               throw new IllegalArgumentException("Unit price currency code must not be blank");
        }
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    /**
     * Calculates the total price for this sales order item
     * @return the total price as a Money object, which is the product of unit price and quantity.
     */

    public Money calculateItemAmount() {
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }
}
