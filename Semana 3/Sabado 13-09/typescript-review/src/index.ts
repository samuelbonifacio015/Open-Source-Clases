import {Customer} from "./crm/domain/model/customer";
import {Currency} from "./shared/domain/model/currency";
import {SalesOrder} from "./sales/domain/model/salesOrder";
import {ProductId} from "./sales/domain/model/product-id";

console.log('Type Script with Web Storm✨')

try {
    const customer = new Customer("Luisito Carrion");

    //Scenario 1
    const usdCurrencyCode = "USD";
    const usdCurrency = new Currency(usdCurrencyCode);
    const realTimeSalesOrder = new SalesOrder(customer.id, usdCurrency);
    realTimeSalesOrder.addItem(new ProductId(), 2, 100)
    realTimeSalesOrder.addItem(new ProductId(), 20, 50)
    realTimeSalesOrder.confirm();
    customer.lastOrderPrice = realTimeSalesOrder.calculateTotalAmount();
    console.log(`Real Time Order - Customer: ${customer.name}, ID ${customer.id}, 
    Ordered At: ${realTimeSalesOrder.orderedAt.toString()},
    State: ${realTimeSalesOrder.state},
    Total Amount: ${customer.lastOrderPrice?.format()}`);


    //Scenario 1
    const penCurrencyCode = "PEN";
    const penCurrency = new Currency(usdCurrencyCode);
    const pastOrderDate = "2023-05-15T10:00:00Z";
    const manualTimeSalesOrder = new SalesOrder(customer.id, penCurrency,pastOrderDate);
    manualTimeSalesOrder.addItem(new ProductId(), 10, 100)
    manualTimeSalesOrder.addItem(new ProductId(), 20, 50)
    manualTimeSalesOrder.confirm();
    manualTimeSalesOrder.ship()
    customer.lastOrderPrice = manualTimeSalesOrder.calculateTotalAmount();
    console.log(`Manual Order - Customer: ${customer.name}, ID ${customer.id}, 
    Ordered At: ${manualTimeSalesOrder.orderedAt.toString()},
    State: ${manualTimeSalesOrder.state},
    Total Amount: ${customer.lastOrderPrice?.format()}`);

    manualTimeSalesOrder.confirm();
} catch (error) {
    if (error instanceof Error)
        console.error(`Error: ${error.message}`);
    else
        console.error("An unknown error occurred:", error);
}
