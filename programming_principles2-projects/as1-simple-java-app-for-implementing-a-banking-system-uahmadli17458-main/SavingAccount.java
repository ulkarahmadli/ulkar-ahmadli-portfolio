import java.math.BigDecimal;

public class SavingAccount extends Account {
    private final BigDecimal interestRate;

    public SavingAccount(BigDecimal balance, BigDecimal interestRate) {
        super(balance);
        this.interestRate = interestRate;
    }

    public void withdraw(BigDecimal amount) {
        if (balance.compareTo(amount) >= 0) {
            balance = balance.subtract(amount);
        } else {
            throw new IllegalArgumentException("Withdrawal amount exceeds available funds");
        }
    }

    @Override
    public String getType() {
        return "saving";
    }
}
