package Security;

import java.util.Arrays;
import java.util.List;

public class ElGamal {
    /**
     * Encrypts a message m using ElGamal:
     *
     *   c1 = g^k mod p
     *   c2 = (m * y^k) mod p
     *
     * where:
     *   - p is a prime modulus
     *   - g is the generator
     *   - y = g^x mod p is the recipient’s public key
     *   - k is the random ephemeral exponent
     *   - m is the plaintext (0 <= m < p)
     *
     * Returns List<Long> { c1, c2 }.
     */
    public List<Long> encrypt(int p, int g, int y, int k, int m) {
        // Compute c1 = g^k mod p
        long c1 = modPow(g, k, p);

        // Compute c2 = (m * (y^k mod p)) mod p
        long yk = modPow(y, k, p);
        long c2 = ( (long) m * yk ) % p;

        return Arrays.asList(c1, c2);
    }

    /**
     * Decrypts a ciphertext (c1, c2) under private key x:
     *
     *   s = c1^x mod p
     *   s^{-1} = modular inverse of s mod p  (i.e. s^{p-2} mod p)
     *   m = (c2 * s^{-1}) mod p
     *
     * Returns the recovered plaintext m (as int).
     */
    public int decrypt(int c1, int c2, int x, int p) {
        // Compute shared secret s = c1^x mod p
        long s = modPow(c1, x, p);

        // Compute s^{-1} mod p via Fermat’s little theorem (p is prime):
        //    s^{-1} ≡ s^{p-2} (mod p)
        long sInv = modPow(s, p - 2, p);

        // Recover m = (c2 * sInv) mod p
        long m = ( (long) c2 * sInv ) % p;
        return (int) m;
    }

    /**
     * Fast modular exponentiation: (base^exp) mod mod.
     */
    private long modPow(long base, long exp, long mod) {
        long result = 1;
        base %= mod;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                result = (result * base) % mod;
            }
            base = (base * base) % mod;
            exp >>= 1;
        }
        return result;
    }
}
