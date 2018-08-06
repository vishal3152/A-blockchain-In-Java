package blockchain;

public class Test {

    public static void main(String[] args) {
        Blockchain bc = Blockchain.getInstance(5);
        bc.addNewBlock("31520", 232.2, TransactionType.CREDIT);

        bc.addNewBlock("31520", 232.2, TransactionType.CREDIT);

    }
}
