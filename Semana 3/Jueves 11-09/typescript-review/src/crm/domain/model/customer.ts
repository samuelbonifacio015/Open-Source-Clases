import { Money } from '../../../shared/domain/model/money';

export class Customer {
    private readonly _id: string;
    private readonly _name: string;
    private _lastOrderPrice: Money | null;

    constructor(name : string) {
        if (!name || name.trim().length === 0) throw new Error("Customer name cannot be empty");

    this._id = crypto.randomUUID();
    this._name = name;
    this._lastOrderPrice = null;

    }

    public get id(): string { return this._id; }

    public get name(): string { return this._name; }

    public get lastOrderPrice(): Money | null { return this._lastOrderPrice; }

    public setLastOrderPrice(newLastOderPrice: Money) {
        this._lastOrderPrice = newLastOderPrice;
    }
}