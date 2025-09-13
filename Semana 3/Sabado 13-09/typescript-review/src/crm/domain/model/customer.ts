import {Money} from "../../../shared/domain/model/money";

/**
 * Represents a customer in the CRM system.
 * @remarks
 * Each customer has a unique identifier, a name, and an optional last order price.
 */
export class Customer {
    private readonly _id: string;
    private readonly _name: string;
    private _lastOrderPrice: Money | null;

    /**
     * Creates a new Customer instance.
     * @remarks
     * The customer ID is automatically generated as a UUID.
     * The last order price is initialized to null.
     * @param name - The name of the customer.
     * @throws Error if the name is empty or consists only of whitespace.
     */
    constructor(name: string) {
        if (!name || name.trim().length === 0) {
            throw new Error("Customer name cannot be empty");
        }
        this._id = crypto.randomUUID();
        this._name = name;
        this._lastOrderPrice = null;
    }

    /**
     * Gets the unique identifier of the customer.
     * @returns The unique identifier of the customer.
     */
    public get id(): string { return this._id;    }

    /**
     * Gets the name of the customer.
     * @returns The name of the customer.
     */
    public get name(): string { return this._name; }

    /**
     * Gets the price of the last order made by the customer.
     * @returns The price of the last order, or null if no orders have been made.
     */
    public get lastOrderPrice(): Money | null { return this._lastOrderPrice; }

    /**
     * Sets the price of the last order made by the customer.
     * @param newLastOrderPrice - The price of the last order.
     */
    public set lastOrderPrice(newLastOrderPrice: Money) {
        this._lastOrderPrice = newLastOrderPrice;
    }
}