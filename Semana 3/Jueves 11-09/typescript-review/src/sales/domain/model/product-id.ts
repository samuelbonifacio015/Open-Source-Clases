/**
 * ProductId value object representing a unique identifier for a product.
 */
export class ProductId{
    private readonly _id: string;

    /**
     * Creates a new ProductId instance.
     * @remarks
     * If no ID is provided, it generates a new UUID.
     * @param id
     */

    constructor(id?: string) {
        this._id = id ?? crypto.randomUUID();
    }

    /**
     * Gets the unique identifier of the product.
     * @returns {string} the product ID.
     */

    public get id(): string { return this._id;}
}