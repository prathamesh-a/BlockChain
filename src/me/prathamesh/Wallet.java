package me.prathamesh;

import java.security.*;
import java.security.spec.ECGenParameterSpec;

public class Wallet {
    public PrivateKey privateKey;
    public PublicKey publicKey;

    public Wallet(){
        generateKeyPais();
    }

    public void generateKeyPais(){
        try {
            KeyPairGenerator generator = KeyPairGenerator.getInstance("ECDSA", "BC");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            ECGenParameterSpec spec = new ECGenParameterSpec("prime192v1");
            generator.initialize(spec, random);
            KeyPair keyPair = generator.generateKeyPair();
            privateKey = keyPair.getPrivate();
            publicKey = keyPair.getPublic();
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
