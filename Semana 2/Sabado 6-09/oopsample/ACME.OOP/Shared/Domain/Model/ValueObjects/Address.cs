namespace ACME.OOP.Shared.Domain.Model.ValueObjects;

// <summary>
// Representa una direccion internacional (Address)
// </summary>

public record Address
{
    public string Street { get; init; }
    public string Number { get; init; }
    public string City { get; init; }
    public string? StateorRegion { get; init; }
    public string PostalCode { get; init; }
    public string Country { get; init; }
    
    /// <summary>
    /// Creamos una instancia de address
    /// </summary>
    /// <param name = "street"> direccion de calle 
    

    public Address(string street, string number, string city, string? stateorRegion, string postalCode, string country)
    {
        if (string.IsNullOrWhiteSpace(street))
            throw new ArgumentException("Street cannot be null or empty.", nameof(street));
        if (string.IsNullOrWhiteSpace(number))
            throw new ArgumentException("Number cannot be null or empty.", nameof(number));
        if (string.IsNullOrWhiteSpace(city))
            throw new ArgumentException("City cannot be null or empty.", nameof(city));
        if(string.IsNullOrEmpty(postalCode))
            throw new ArgumentException("Postal code cannot be null or empty.", nameof(postalCode));
        if (string.IsNullOrEmpty(country))
            throw new ArgumentException("Country cannot be null or empty.", nameof(country));
        
        Street = street;
        Number = number;
        City = city;
        StateorRegion = stateorRegion;
        PostalCode = postalCode;
        Country = country;
    }
    
    public override string ToString()
     => $"{Street} {Number}, {City}, {(string.IsNullOrWhiteSpace(StateorRegion) ? "" : StateorRegion + ", ")}{PostalCode}, {Country}";
};