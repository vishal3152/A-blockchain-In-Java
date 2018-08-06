package blockchain;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;

/**
 *
 */
final class Block implements Serializable {

    private Integer index;
    private String previousBlockHash;
    private String currentBlockHash;
    private Date timeStamp;
    private TransactionData transactionData;
    private Integer nonce;


    Block(Integer index, String previousBlockHash, TransactionData transactionData) {
        this.index = index;
        this.previousBlockHash = previousBlockHash;
        this.transactionData = transactionData;
        this.timeStamp = new Date();
        this.nonce = 0;
        this.currentBlockHash = calculateHash(this);

    }

    /**
     * @param block
     * @return
     */
    private String calculateHash(Block block) {

        return Utility.getSHA256Hash(block);
    }

    /**
     * @param difficulty
     */
    public void mineBlock(Integer difficulty) {
        System.out.println("--------------------------- Block is being mined ------------------------------------------");

        String difficultyString = String.join("", Collections.nCopies(difficulty, "0"));
        Date start = new Date();
        while (!this.currentBlockHash.substring(0, difficulty).equals(difficultyString)) {
            this.nonce++;
            this.currentBlockHash = calculateHash(this);
            // System.out.println(difficultyString+"  "+this.nonce+"   "+this.currentBlockHash);
        }

        System.out.print("Block mined!!!    Time taken(in seconds) = ");
        System.out.println((new Date().getTime() - start.getTime()) / 1000);
    }

    /**
     * @return
     */
    public String getCurrentBlockHash() {
        return this.currentBlockHash;
    }

    public String getPreviousBlockHash() {
        return this.previousBlockHash;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return "Index= " + this.index + ", PreviousBlockHash= " + this.previousBlockHash + ", BlockHash= " + this.currentBlockHash
                + ", Timestamp= " + this.timeStamp;
    }
}
