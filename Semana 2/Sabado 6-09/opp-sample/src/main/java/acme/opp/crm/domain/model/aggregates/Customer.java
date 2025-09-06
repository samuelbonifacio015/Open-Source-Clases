package acme.opp.crm.domain.model.aggregates;

import acme.opp.shared.domain.model.valueobjects.Address;
import acme.opp.shared.domain.model.valueobjects.CustomerId;
import lombok.Getter;

import java.util.Objects;

/*
 * Represents a Customer aggregate in the CRM bounded context.
 */

public class Customer {
    private final CustomerId customerId;
    private String name;
    private String email;
    private Address address;

    /**
     * Constructs a Customer aggregate with validation.
     * @param name the customer name. it must not be null or blank.
     * @param email the customer email. same. it must not be blank.
     * @param address the customer address. same. it must not be blank.
     * @throws IllegalArgumentException if any of the parameters is null or blank
     */
    public Customer(String name, String email, Address address) {
        if (Objects.isNull(name) || name.isBlank()){
            throw new IllegalArgumentException("Customer name cannot be null or blank");
        }
        if (Objects.isNull(email) || email.isBlank()){
            throw new IllegalArgumentException("Customer email cannot be null or blank");
        }
        if (Objects.isNull(address)){
            throw new IllegalArgumentException("Customer address cannot be null");
        }
        this.customerId = new CustomerId();
        this.name = name;
        this.email = email;
        this.address = address;
    }

    /**
     * Updates the customer's contact info with validation.
     * @param email the new customer email. same. it must not be blank.
     * @param address the new customer address. same. it must not be blank.
     * @throws IllegalArgumentException if any of the parameters is null or blank
     */
    public void updateContactInfo(String email, Address address) {
        if (Objects.isNull(email) || email.isBlank()){
            throw new IllegalArgumentException("Customer email cannot be null or blank");
        }
        this.email = email;
        this.address = address;
    }

    /**
     * Returns the customer's contact information as a formatted string.
     * @return the customer's contact info.
     */
    public String getContactInfo(){
        return String.format("%s %s", this.name, this.email);
    }
}
