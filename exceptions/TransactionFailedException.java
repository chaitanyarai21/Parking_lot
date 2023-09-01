package src.main.exceptions;

public class TransactionFailedException extends Exception{
    public TransactionFailedException() {
        super("Transaction Failed!");
    }
}
