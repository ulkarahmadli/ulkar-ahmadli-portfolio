import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Customer {
    private final String id;
    private final String fullName;
    private final ArrayList<Account> accounts;

    public Customer(String fullName) {
        this.id = Util.getRandomString();
        this.fullName = fullName;
        this.accounts = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public void removeAccount(Account account) {
        if (!accounts.contains(account)) {
            throw new IllegalArgumentException("Account not found");
        }
        accounts.remove(account);
    }

    public void removeAccount(String accountId) {
        Account accountToRemove = null;
        for (Account account : accounts) {
            if (account.getId().equals(accountId)) {
                accountToRemove = account;
                break;
            }
        }
        if (accountToRemove != null) {
            accounts.remove(accountToRemove);
        } else {
            throw new IllegalArgumentException("Account not found");
        }
    }

    public BigDecimal getTotalBalance() {
        BigDecimal totalBalance = BigDecimal.ZERO;
        for (Account account : accounts) {
            totalBalance = totalBalance.add(account.getBalance());
        }
        return totalBalance;
    }

    public List<Account> getAccounts(String type) {
        List<Account> filteredAccounts = new ArrayList<>();
        for (Account account : accounts) {
            if (account.getType().equalsIgnoreCase(type)) {
                filteredAccounts.add(account);
            }
        }
        return filteredAccounts;
    }
}