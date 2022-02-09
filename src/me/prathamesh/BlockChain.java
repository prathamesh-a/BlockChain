package me.prathamesh;

import java.security.Security;
import java.util.ArrayList;
import com.google.gson.GsonBuilder;

public class BlockChain {
    public static ArrayList<Block> blockchain = new ArrayList<>();
    public static int diff = 5;
    public static Wallet walletA;
    public static Wallet walletB;

    public static void main(String[] args){
        blockchain.add(new Block("Genesis", "0"));
        System.out.println("Mining Block 1...");
        blockchain.get(0).mine(diff);

        blockchain.add(new Block("Block 2",blockchain.get(blockchain.size()-1).hash));
        System.out.println("Mining Block 2...");
        blockchain.get(1).mine(diff);

        blockchain.add(new Block("Block 3",blockchain.get(blockchain.size()-1).hash));
        System.out.println("Mining Block 3...");
        blockchain.get(2).mine(diff);

        System.out.println("Is Blockchain Valid? :" + isValid());

        String json = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println("Final Blockchain:");
        System.out.println(json);

        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());

        walletA = new Wallet();
        walletB = new Wallet();

        System.out.println("Private and public keys:");
        System.out.println(StringUtil.getStringFromKey(walletA.privateKey));
        System.out.println(StringUtil.getStringFromKey(walletA.publicKey));

        Transaction transaction = new Transaction(walletA.publicKey, walletB.publicKey, 5, null);
        transaction.generateSignature(walletA.privateKey);

        System.out.println("Is signature verified? :" + transaction.verifySignature());
    }

    public static boolean isValid(){
        Block current;
        Block prev;
        String hashTarget = new String(new char[diff]).replace('\0', '0');
        for (int i=1; i<blockchain.size(); i++){
            current = blockchain.get(1);
            prev = blockchain.get(i-1);
            if (!current.hash.equals(current.calculateHash())) {
                System.out.println("Current Hashes not valid!");
                return false;
            }
            if (!current.hash.substring(0, diff).equals(hashTarget)){
                System.out.println("This block is not mined!");
                return false;
            }
        }
        return true;
    }

}
