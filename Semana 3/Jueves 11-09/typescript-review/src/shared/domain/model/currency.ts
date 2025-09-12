/**
 * Represents a uppercase letter from A to Z.
 */

type UpperCaseLetter =
    | 'A' | 'B' | 'C' | 'D' | 'E' | 'F' | 'G' | 'H' | 'I' | 'J' | 'K' | 'L' | 'M'
    | 'N' | 'O' | 'P' | 'Q' | 'R' | 'S' | 'T' | 'U' | 'V' | 'W' | 'X' | 'Y' | 'Z';

/**
 * Represents a three-letter currency code in uppercase (e.g., USD, EUR).
 * ISO 4217 standard.
 * Example: 'USD', 'EUR', 'JPY'
 */
export type CurrencyCode = `${UpperCaseLetter}${UpperCaseLetter}${UpperCaseLetter}`;

/**
 * Value object representing a currency code.
 */

export class Currency {
    private readonly _code: CurrencyCode;

    constructor(code: CurrencyCode){
        this._code = code;
    }

    public get code() : string { return this._code; }

    public formatAmount = (amount:number, locale:string = 'en-US'): string=> {
        return amount.toLocaleString(locale, {
            style: 'currency',
            currency: this._code,
            minimumFractionDigits: 2,
            maximumFractionDigits: 2
        });
   }

   public toString = () : string => this._code;
    public toLocaleString = (): string => this._code;

}