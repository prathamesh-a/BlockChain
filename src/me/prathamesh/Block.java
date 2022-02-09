package me.prathamesh;

import java.util.Date;

public class Block {
    public String hash;
    public String previousHash;
    private final String data;
    private final long timeStamp;
    private int nonce;

    public Block(String data, String previousHash){
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }

    public String calculateHash(){
        return StringUtil.applyHash(previousHash + timeStamp + nonce + data);
    }

    public void mine(int diff){
        String target = new String(new char[diff]).replace('\0', '0');
        while (!hash.substring(0, diff).equals(target)){
            nonce++;
            hash = calculateHash();
        }
        System.out.println("New Block Mined with Hash: " + hash);
    }
}
