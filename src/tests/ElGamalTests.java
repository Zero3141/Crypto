package tests;

import de.heiko.algos.ElGamal;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ElGamalTests {

    @Test
    void generateKeys() {

        ElGamal elGamal = new ElGamal();
        ElGamal.Keys keys = elGamal.generateKeys(512);

        BigInteger m = BigInteger.valueOf(10);
        ElGamal.Chiffre chiffre = elGamal.encrypt(m, 512, keys.publicKey);

        BigInteger r = elGamal.decrypt(chiffre, keys.privateKey);
        assertEquals(m, r);

    }



}
