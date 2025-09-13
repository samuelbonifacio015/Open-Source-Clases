/**
 * Represents an uppercase letter from A to Z.
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

    /**
     * Creates a new Currency instance.
     * @param code - The three-letter currency code in uppercase (ISO 4217).
     */
    constructor(code: CurrencyCode) {
        this._code = code;
    }

    /**
     * Gets the currency code.
     * @returns {string} The currency code. ISO 4217 standard.
     */
    public get code(): string {
        return this._code;
    }

    /**
     * Formats a number as a currency string in the specified locale.
     * @param amount - The amount to format.
     * @param locale - The locale string (e.g., 'en-US', 'fr-FR'). Defaults to 'en-US'.
     * @returns {string} The formatted currency string.
     */
    public formatAmount = (amount: number, locale: string = 'en-US'): string => {
        return amount.toLocaleString(locale, {
            style: 'currency',
            currency: this._code,
            minimumFractionDigits: 2,
            maximumFractionDigits: 2
        });
    }
    
    /**
     * Returns the ISO 4217 currency code as a string.
     * @returns {string} The currency code.
     */
    public toString = (): string => this._code;
}        