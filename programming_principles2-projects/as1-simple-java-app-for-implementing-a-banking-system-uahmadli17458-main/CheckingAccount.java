import java.math.BigDecimal;

public class CheckingAccount extends Account {
    private final BigDecimal overDraftLimit;

    public CheckingAccount(BigDecimal balance, BigDecimal overDraftLimit) {
        super(balance);
        this.overDraftLimit = overDraftLimit;
    }

    @Override
    public void withdraw(BigDecimal amount) {
        BigDecimal availableFunds = balance.add(overDraftLimit);
        if (amount.compareTo(availableFunds) <= 0) {
            balance = balance.subtract(amount);
        } else {
            throw new IllegalArgumentException("Withdrawal amount exceeds available funds");
        }
    }

    @Override
    public String getType() {
        return "checking";
    }
}
