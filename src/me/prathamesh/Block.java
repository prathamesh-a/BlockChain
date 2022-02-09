package me.prathamesh;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class Block {
    public String hash;
    public String previousHash;
    public String merkleRoot;
    //private final String data;
    public ArrayList<Transaction> transactions = new ArrayList<Transaction>();
    private final long timeStamp;
    private int nonce;

    public Block(String previousHash ) {
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }

    public String calculateHash() {
        return StringUtil.applyHash(previousHash + timeStamp + nonce + merkleRoot);
    }

    public void mine(int difficulty) {
        merkleRoot = StringUtil.getMerkleRoot(transactions);
        String target = StringUtil.getDificultyString(difficulty);
        while(!hash.substring( 0, difficulty).equals(target)) {
            nonce ++;
            hash = calculateHash();
        }
        System.out.println("Block Mined!!! : " + hash);
    }

    public boolean addTransaction(Transaction transaction) {
        if(transaction == null) return false;
        if((!Objects.equals(previousHash, "0"))) {
            if((!transaction.processTransaction())) {
                System.out.println("Transaction failed to process. Discarded.");
                return false;
            }
        }
        transactions.add(transaction);
        System.out.println("Transaction Successfully added to Block");
        return true;
    }
}
