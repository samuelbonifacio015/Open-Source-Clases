/**
 * Value object representing a date and time.
 * Ensures the date is valid and not in the future.
 * @remarks
 * This class provides methods to format the date in a human-readable way
 * and to get the ISO 8601 string representation.
 * 
 * Example usage:
 * ```typescript
 * const dateTime = new DateTime('2023-10-05T14:48:00.000Z');
 * console.log(dateTime.format('en-US')); // "10/05/2023, 02:48:00 PM"
 * console.log(dateTime.toString()); // "2023-10-05T14:48:00.000Z"
 * ```
 */
export class DateTime {
    private readonly _date: Date;

    /**
     * Creates a new DateTime instance.
     * @remarks
     * If no value is provided, it defaults to the current date and time.
     * If a string is provided, it attempts to parse it into a Date object.
     * An error is throw if the date is invalid or in the future.
     * @param value - the date value, either as a Date object or a string.
     * @throws {Error} If the date is invalid or in the future.
     */
    constructor(value?: Date | string) {
        const now = new Date();
        if (value) {
            const parsedDate = new Date(value);
            if (isNaN(parsedDate.getTime())) throw Error(`Invalid date: ${parsedDate}`);
            if (parsedDate > now) throw Error(`Date cannot be in the future: ${parsedDate}`);
            this._date = parsedDate;
        } else 
            this._date = now;
    }

    /**
     * Gets the underlying Date object.
     * @returns {Date} The Date object.
     */
    public get value(): Date {
        return this._date;
    }

    /**
     * Formats the date to a human-readable string in the specified locale.
     * Defaults to 'en-US' locale if none is provided.
     * @param locale The locale string (e.g., 'en-US', 'fr-FR').
     * @returns {string} The formatted date string.
     */
    public format(locale: string = 'en-US'): string {
        return this._date.toLocaleDateString(locale,
            {
                year:  'numeric', month: '2-digit', day:   '2-digit',
                hour:  '2-digit', minute:'2-digit', second:'2-digit'
            });
    }

    /**
     * Returns the ISO 8601 string representation of the date.
     * @returns {string} The ISO 8601 formatted date string.
     */
    public toString(): string {
        return this._date.toISOString();
    }
}