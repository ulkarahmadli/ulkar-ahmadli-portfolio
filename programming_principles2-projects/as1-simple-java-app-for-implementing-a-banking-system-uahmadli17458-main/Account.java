import java.math.BigDecimal;

public abstract class Account {
    protected String id;
    protected BigDecimal balance = BigDecimal.ZERO;

    public Account(BigDecimal balance) {
        this.id = Util.getRandomString();
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void deposit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Deposit amount can't be negative");
        }
        balance = balance.add(amount);
    }

    public abstract void withdraw(BigDecimal amount);

    public abstract String getType();
}
