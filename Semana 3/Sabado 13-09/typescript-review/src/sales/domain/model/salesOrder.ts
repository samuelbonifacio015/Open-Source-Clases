import {SalesOrderItem} from "./salesOrderItem";
import {DateTime} from "../../../shared/domain/model/date-time";
import {Currency} from "../../../shared/domain/model/currency";
import {ProductId} from "./product-id";
import {Money} from "../../../shared/domain/model/money";

export type SalesOrderState = 'PENDING' | 'CONFIRMED' | 'SHIPPED' | 'CANCELLED';

/**
 * SalesOrder represents a sales order, aggregate within the Sales bounded context.
 */
export class SalesOrder {
    private readonly _customerId: string;
    private readonly _id: string;
    private readonly _items: SalesOrderItem[];
    private readonly _orderedAt: DateTime;
    private readonly _currency: Currency;
    private _state: SalesOrderState;


    /**
     * Creates a new SalesOrder.
     * @param customerId - ID of the customer placing the order
     * @param currency - Currency of the order
     * @param orderedAt - Date and time when the order was placed (optional, defaults to now)
     */
    constructor(customerId: string, currency: Currency, orderedAt?: Date | string) {
        if (!customerId || customerId.trim() === '') throw Error("Customer ID is required");
        this._customerId = customerId;
        this._id = crypto.randomUUID();
        this._items = [];
        this._currency = currency;
        this._orderedAt = new DateTime(orderedAt);
        this._state = 'PENDING';
    }

    public get customerId(): string { return this._customerId;     }

    public get id(): string {         return this._id;    }

    public get orderedAt(): DateTime {        return this._orderedAt;    }

    public get currency(): Currency {        return this._currency;    }

    public get state(): SalesOrderState {        return this._state;    }

    public set state(newState: SalesOrderState) {        this._state = newState;    }

    private canAddItem(): boolean {
        return this._state !== 'CANCELLED' && this._state !== 'SHIPPED';
    }

    /**
     * Adds an item to the order if the order is in a state that allows adding items.
     * @param productId       - ID of the product
     * @param quantity        - Quantity of    the product
     * @param unitPriceAmount - Unit price of the product as a number
     * @throws Error if the order is in a state that does not allow adding items
     * @throws Error if quantity is less than or equal to zero
     * @throws Error if unit price is less than or equal to zero
     * @throws Error if productId is empty
     */
    public addItem(productId: ProductId, quantity: number, unitPriceAmount: number): void {
        if (!this.canAddItem()) {
            throw new Error(`Cannot add items to a ${this._state} order.`);
        }
        if (quantity <= 0) throw Error("Quantity must be greater than zero");
        if (unitPriceAmount <= 0) throw Error("Unit price must be greater than zero");
        if (!productId || productId.id.trim() === '') throw Error("Product ID is required");
        const unitPrice = new Money(unitPriceAmount, this._currency);
        const item = new SalesOrderItem(this._id, productId, quantity, unitPrice);
        this._items.push(item);
    }

    /**
     * Calculates the total amount of the order by summing up the total of each item.
     * @returns Money representing the total amount of the order
     */
    public calculateTotalAmount(): Money {
        return this._items.reduce((total, item) =>
            total.add(item.calculateItemTotal()),new Money(0, this._currency));
    }

    /**
     * States changes
     */
    public confirm(): void {
        if (this._state === 'PENDING') this._state = 'CONFIRMED';
        else throw new Error(`Cannot confirm an order in ${this._state} state.`);
        }

    public ship(): void {
        if (this._state === 'CONFIRMED') this._state = 'SHIPPED';
        else throw new Error(`Cannot ship an order in ${this._state} state.`);
    }

    public cancel(): void {
        if (this._state === 'PENDING' || this._state === 'CANCELLED')
          throw new Error(`Cannot cancel an order in ${this._state} state.`);
        this._state = 'CANCELLED';
    }
}