package acme.opp.sales.domain.model.aggregates;

import acme.opp.shared.domain.model.valueobjects.CustomerId;
import acme.opp.shared.domain.model.valueobjects.Money;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Represents a sales order aggregate within the Sales bounded context.
 * This aggregate manages the lifeycle and business rules related to sales orers
 * @author Open Source Application Development Team Samuel CEO
 */
public class SalesOrder {
    private final UUID id;
    private final CustomerId customerId;
    private LocalDateTime orderDate;
    private final List<SalesOrderItem> items;
    private Money totalAmount;

    /**
     * Constructs a SalesOrder with a specific customerId
     * @param customerId
     */
    public SalesOrder(CustomerId customerId) {
        this.id = UUID.randomUUID();
        this.customerId = customerId;
        this.orderDate = LocalDateTime.now();
        this.items = new ArrayList<>();
        this.totalAmount = Money.zero();
    }
}
