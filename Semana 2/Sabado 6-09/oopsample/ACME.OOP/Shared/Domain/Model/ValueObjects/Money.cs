namespace ACME.OOP.Shared.Domain.Model.ValueObjects;
// </summary>
// Represente una unidad monetaria
// </summary>
public record Money
{
    public decimal Amount { get; init; }
    public string Currency { get; init; }
    
    /// <summary>
    /// Se crea una nueva instancia de moneda
    /// </summary>
    /// <param name="amount">El monto monera, tiene que ser mayor a zero</param>
    /// <param name="currency">El codigo de currency que debe ser un codigo valida de 3-letras ISO code</param>
    /// <exception cref="ArgumentException">Thrown cuando la currency no es un codigo valida de letras de ISO code</exception>
    public Money(decimal amount, string currency)
    {
        if (string.IsNullOrWhiteSpace(currency) || currency.Length != 3)
            throw new ArgumentException("Invalid currency");
        if (amount <= 0)
            throw new ArgumentException("Ammount cannot be zero or negative", nameof(amount));
        Amount = amount;
        Currency = currency.ToUpper();
    }
    
    /// <summary>
    /// Retorna una representacion de un monto (ejem -> $30.0)
    /// </summary>
    /// <returns>Un string en formato correcto</returns>
    public override string ToString() => $"{Amount} {Currency}";
}