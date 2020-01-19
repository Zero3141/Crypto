package tests;

import de.heiko.algos.RSA;
import javafx.util.Pair;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RSATests {

    @Test
    void generateKeys() {

        RSA rsa = new RSA();
        Pair<RSA.PublicKey, RSA.PrivateKey> keys = rsa.generateKeys(5);

        assertNotNull(keys);

        assertNotNull(keys.getKey().n);
        assertNotNull(keys.getKey().e);

        assertNotNull(keys.getValue().n);
        assertNotNull(keys.getValue().e);
        assertNotNull(keys.getValue().d);

    }

    @Test
    void encrypt() {

        BigInteger m = BigInteger.valueOf(8);

        RSA rsa = new RSA();
        RSA.PublicKey publicKey = new RSA.PublicKey(BigInteger.valueOf(11), BigInteger.valueOf(899));

        assertEquals(BigInteger.valueOf(380), rsa.encrypt(m, publicKey));
    }

    @Test
    void decrypt() {

        BigInteger c = BigInteger.valueOf(722);

        RSA rsa = new RSA();
        RSA.PrivateKey privateKey = new RSA.PrivateKey(BigInteger.valueOf(13), BigInteger.valueOf(899), BigInteger.valueOf(-323));

        assertEquals(BigInteger.valueOf(10), rsa.decrypt(c, privateKey));

    }

}
