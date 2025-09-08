package acme.oop;

import acme.oop.crm.domain.model.aggregates.Customer;
import acme.oop.sales.domain.model.aggregates.SalesOrder;
import acme.oop.sales.domain.model.valueobjects.ProductId;
import acme.oop.shared.domain.model.valueobjects.Address;
import acme.oop.shared.domain.model.valueobjects.Money;

import java.math.BigDecimal;
import java.util.Currency;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello and welcome to the Acme OOP example project!");
        //Shared context
        Address address = new Address("Av. La Marina 1234", "Lima", "1234", "Peru");
        System.out.println("First Address: " + address);

        Address anotherAddress = new Address("Av. Salaverry 1234", "Lima", "2345", "Peru");
        System.out.println("Second Address: " + anotherAddress);

        //CRM context
        System.out.println("Creating a customer.....");
        Customer customer = new Customer("John", "john@upc.edupe", address);
        System.out.println("Customer Contact Info: " + customer.getContactInfo());

        System.out.println("Updating customer info.....");
        customer.updateContactInfo(customer.getEmail(), anotherAddress);
        System.out.println("Second Customer Contact Info: " + customer.getContactInfo());

        //Sales context
        System.out.println("Creating a sales order.....");
        SalesOrder salesOrder = new SalesOrder(customer.getCustomerId());
        Money price = new Money(new BigDecimal("29.99"), Currency.getInstance("USD"));
        ProductId productId = new ProductId();
        salesOrder.addItem(productId, 2, price);
        System.out.println("Order Id: " + salesOrder.getId());
        System.out.println("Order Date: " + salesOrder.getOrderDate());
        System.out.println("Customer Id: " + salesOrder.getCustomerId());
        System.out.println("Order Total Amount: " + salesOrder.getTotalAmount());
    }
}
