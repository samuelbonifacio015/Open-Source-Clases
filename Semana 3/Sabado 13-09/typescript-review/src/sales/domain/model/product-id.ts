/**
 * ProductId Value Object represents a unique identifier for a product in CRM system.
 */
export class ProductId {
    private readonly _id: string;

    /**
     * Creates a new ProductId instance.
     * @remarks
     * If no ID is provided, a new UUID is generated.
     * @param id - The unique identifier of the product.
     */
    constructor(id?: string) {
        this._id = id ?? crypto.randomUUID();
    }

    /**
     * Gets the unique identifier of the product.
     * @returns The unique identifier of the product.
     */
    public get id(): string { return this._id; }
    
}