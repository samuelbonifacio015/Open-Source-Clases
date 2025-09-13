import {Money} from "../../../shared/domain/model/money";
import {ProductId} from "./product-id";

/**
 * SalesOrderItem represents an item in a sales order, aggregate within the Sales bounded context.
 */
export class SalesOrderItem {
    private readonly _orderId: string;
    private readonly _itemId: string;
    private readonly _productId: ProductId;
    private readonly _quantity: number;
    private readonly _unitPrice: Money;

    /**
     * Creates a new SalesOrderItem.
     * @param orderId   - ID of the order this item belongs to
     * @param productId - ID of the product
     * @param quantity - Quantity of the product
     * @param unitPrice - Unit price of the product
     * @throws Error if quantity is less than or equal to zero
     */
    constructor(orderId: string, productId: ProductId, quantity: number, unitPrice: Money) {
        if (quantity <= 0) throw Error("Quantity must be greater than zero");
        this._orderId = orderId;
        this._itemId = crypto.randomUUID();
        this._productId = productId;
        this._quantity = quantity;
        this._unitPrice = unitPrice;
    }

    public get orderId(): string {  return this._orderId;     }

    public get itemId(): string {        return this._itemId;    }

    public get productId(): ProductId {        return this._productId;    }

    public get quantity(): number {        return this._quantity;    }

    public get unitPrice(): Money {        return this._unitPrice;    }

    /**
     * Calculates the total price for this item (unit price * quantity).
     * @returns Money representing the total price for this item
     */
    public calculateItemTotal(): Money {
        return new Money(this._unitPrice.amount * this._quantity, this._unitPrice.currency);
    }
}