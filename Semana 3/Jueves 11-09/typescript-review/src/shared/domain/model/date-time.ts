/**
 *
 */

export class DateTime {
    private readonly _date: Date;

    /**
     * Creates a new DateTime instance.
     * @remarks
     * If no value is provided, it defaults to the current date and time.
     * Throws an error if the provided date is invalid or in the future.
     * @param value
     */

    constructor(value? : Date | string) {
        const now = new Date();
        if (value) {
            const parsedDate = new Date(value);
            if (isNaN(parsedDate.getTime())) throw Error (`Invalid date: ${parsedDate}`);
            if (parsedDate > now) throw Error(`Date cannot be in the future: ${parsedDate}`);
            this._date = parsedDate;
        }else
            this._date = now;
    }

    /**
     * Gets the underlying Date object.
     * @returns {Date} the Date object.
     */

    public get value(): Date {
        return this._date;
    }

    /**
     * Formats the date to a human-readable string in the specified locale.
     * Defaults to 'en-US' if no locale is provided.
     * @returns {string} the formatted date string.
     * @param locale
     */

    public format (locale: string = 'en-US') : string {
        return this._date.toLocaleDateString(locale,
            {
                year: 'numeric',
                month: '2-digit',
                day: '2-digit',
                hour: '2-digit',
                minute: '2-digit',
                second: '2-digit'
            });
    }

    /**
     * Returns the ISO 8601 string representation of the date.
     * @returns {string} the ISO 8601 formatted date string.
     */
    public toString(): string {
        return this._date.toISOString();
    }
}