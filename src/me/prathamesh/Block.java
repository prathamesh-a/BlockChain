package me.prathamesh;

import java.util.Date;

public class Block {
    public String hash;
    public String previousHash;
    private final String data;
    private final long timeStamp;

    public Block(String data, String previousHash){
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }

    public String calculateHash(){
        return StringUtils.applyHash(previousHash+Long.toString(timeStamp)+data);
    }
}
