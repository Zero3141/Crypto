package tests;

import de.heiko.algos.DiffieHellman;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DiffieHellmanTests {


    @Test
    void exchange() {

        DiffieHellman.Public pub = DiffieHellman.generatePublic(512);

        DiffieHellman alice = new DiffieHellman(pub);
        DiffieHellman bob = new DiffieHellman(pub);

        BigInteger sharedAlice = alice.generateSecretAndCalculate(512);
        BigInteger sharedBob = bob.generateSecretAndCalculate(512);

        assertEquals(alice.calculateKey(sharedBob), bob.calculateKey(sharedAlice));

    }


}
