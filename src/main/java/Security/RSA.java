package Security;

public class RSA {
    /**
     * Encrypts message M under RSA with primes p, q and public exponent e:
     *
     *   n = p * q
     *   C = M^e mod n
     *
     * @param p first prime
     * @param q second prime
     * @param M plaintext (0 ≤ M < p*q)
     * @param e public exponent (gcd(e, (p−1)(q−1)) = 1)
     * @return ciphertext C = M^e mod (p*q)
     */
    public int encrypt(int p, int q, int M, int e) {
        long n = (long) p * q;
        return (int) modPow(M, e, n);
    }

    /**
     * Decrypts ciphertext C under RSA with primes p, q and public exponent e:
     *
     *   φ(n) = (p−1)(q−1)
     *   d = e^(−1) mod φ(n)
     *   M = C^d mod n
     *
     * @param p first prime
     * @param q second prime
     * @param C ciphertext
     * @param e public exponent
     * @return recovered plaintext M
     */
    public int decrypt(int p, int q, int C, int e) {
        // Compute φ(n)
        int phi = (p - 1) * (q - 1);
        // Compute private exponent d = e^(−1) mod phi
        int d = modInverse(e, phi);
        long n = (long) p * q;
        return (int) modPow(C, d, n);
    }

    /**
     * Fast modular exponentiation: (base^exp) mod mod.
     * Uses repeated squaring to run in O(log exp).
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

    /**
     * Computes modular inverse of a modulo m, i.e. x such that a·x ≡ 1 (mod m).
     * Assumes gcd(a, m) = 1 and m > 1. Uses the extended Euclidean algorithm.
     */
    private int modInverse(int a, int m) {
        int m0 = m;
        int x0 = 0, x1 = 1;

        if (m == 1) {
            return 0;
        }

        while (a > 1) {
            int q = a / m;
            int t = m;

            // m = a mod m
            m = a % m;
            a = t;

            // update x0, x1
            t = x0;
            x0 = x1 - q * x0;
            x1 = t;
        }

        // x1 is now the inverse; make it positive
        if (x1 < 0) {
            x1 += m0;
        }

        return x1;
    }
}
