import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Banking {
    private final List<Customer> customers;

    public Banking() {
        customers = new ArrayList<>();
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void removeCustomer(Customer customer) {
        if (!customers.contains(customer)) {
            throw new IllegalArgumentException("Customer not found");
        }
        customers.remove(customer);
    }

    public void removeCustomer(String customerId) {
        Customer customerToRemove = null;
        for (Customer customer : customers) {
            if (customer.getId().equals(customerId)) {
                customerToRemove = customer;
                break;
            }
        }
        if (customerToRemove != null) {
            customers.remove(customerToRemove);
        } else {
            throw new IllegalArgumentException("Customer not found");
        }
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public Customer getCustomer(String customerId) {
        for (Customer customer : customers) {
            if (customer.getId().equals(customerId)) {
                return customer;
            }
        }
        throw new IllegalArgumentException("Customer not found");
    }

    public CheckingAccount createCheckingAccount(Customer customer, BigDecimal balance, BigDecimal overDraftLimit) {
        CheckingAccount checkingAccount = new CheckingAccount(balance, overDraftLimit);
        customer.addAccount(checkingAccount);
        return checkingAccount;
    }

    public SavingAccount createSavingAccount(Customer customer, BigDecimal balance, BigDecimal interestRate) {
        SavingAccount savingAccount = new SavingAccount(balance, interestRate);
        customer.addAccount(savingAccount);
        return savingAccount;
    }
}
