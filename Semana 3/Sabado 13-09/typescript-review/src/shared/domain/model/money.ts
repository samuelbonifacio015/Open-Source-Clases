import {Currency} from "./currency";

export class Money {
    private readonly _amount: number;
    private readonly _currency: Currency;
    
    constructor(amount: number, currency: Currency) {
        if (amount < 0) throw new Error("Amount must be greater than 0");
        this._amount = amount;
        this._currency = currency;
    }
    
    public get amount(): number { return this._amount;    }
    
    public get currency(): Currency { return this._currency; }
    
    public format = (locale: string = 'en-US'): string => 
        this._currency.formatAmount(this._amount, locale);
    
    public add = (other: Money): Money => {
        if (this._currency.code !== other.currency.code) {
            throw new Error(`Cannot add amounts with different currencies: ${this._currency.code} and ${other.currency.code}`);
        }
        return new Money(this._amount + other.amount, this._currency);
    }
    
    public multiply = (factor: number): Money => {
        if (factor < 0) throw new Error("Factor must be greater than 0");
        return new Money(this._amount * factor, this._currency);
    }
}