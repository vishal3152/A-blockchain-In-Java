package blockchain;

import java.util.LinkedList;

/**
 *
 */
public final class Blockchain {
    private LinkedList<Block> chain;

    private Integer difficulty;

    /**
     * @param difficulty
     */
    private Blockchain(Integer difficulty) {
        // initiliaze genesis block
        this.chain = new LinkedList<>();
        this.difficulty = difficulty;
        this.chain.add(new Block(0, "Genesis block"
                , new TransactionData.Builder()
                .setTransactionType(TransactionType.CREDIT)
                .setTransactionValue(0.0)
                .setDestinationAccount("")
                .build()));
    }

    /**
     * @param difficulty
     * @return
     */
    public static Blockchain getInstance(Integer difficulty) {
        return new Blockchain(difficulty);
    }

    /**
     * @return
     */
    public Integer getDifficulty() {
        return difficulty;
    }

    /**
     * @param difficulty
     */
    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;

    }

    /**
     * @param index
     * @return
     */
    public Block getBlockByIndex(Integer index) {
        return this.chain.get(index);
    }

    public Block getLatestBlock() {
        return this.chain.getLast();
    }

    /**
     * @param destinationAccount
     * @param value
     * @param transactionType
     */
    public void addNewBlock(String destinationAccount, double value, TransactionType transactionType) {
        Block newBlock = new Block(this.chain.size()
                , getLatestBlock().getCurrentBlockHash()
                , new TransactionData.Builder()
                .setTransactionType(transactionType)
                .setDestinationAccount(destinationAccount)
                .setTransactionValue(value)
                .build());

        newBlock.mineBlock(this.difficulty);
        this.chain.add(newBlock);
        System.out.println("Added new block to chain: " + newBlock.toString());
        verifyChainIntegrity();
    }

    /**
     * @return
     */
    public boolean verifyChainIntegrity() {
        for (Integer k = 1; k < this.chain.size(); k++) {
            //verify pervious block hash ==hash of previous block
            if (!(this.chain.get(k).getPreviousBlockHash().equals(this.chain.get(k - 1).getCurrentBlockHash()))) {
                System.out.println("Invalid block found in chain at index = " + k);
                return false;

            }
        }
        System.out.println("Chain is valid!!");
        return true;
    }


}
