package blockchain;

import java.io.Serializable;

/**
 *
 */
enum TransactionType {
    CREDIT, DEBIT
}

/**
 *
 */
public final class TransactionData implements Serializable {

    private TransactionType transactionType;// credit or debit
    private double transactionValue;
    private String destinationAccount;

    /**
     * @param builder
     */
    private TransactionData(Builder builder) {

        this.transactionType = builder.transactionType;
        this.transactionValue = builder.transactionValue;
        this.destinationAccount = builder.destinationAccount;

    }

    /**
     *
     */
    public static class Builder

    {
        private TransactionType transactionType;// credit or debit
        private double transactionValue;
        private String destinationAccount;

        public Builder setTransactionType(TransactionType transactionType) {

            this.transactionType = transactionType;
            return this;
        }

        public Builder setTransactionValue(double transactionValue) {
            this.transactionValue = transactionValue;
            return this;
        }


        public Builder setDestinationAccount(String destinationAccount) {
            this.destinationAccount = destinationAccount;
            return this;
        }

        public TransactionData build() {
            return new TransactionData(this);
        }
    }


}
