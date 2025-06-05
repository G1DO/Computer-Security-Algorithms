package Security;

import java.util.Arrays;
import java.util.List;

public class DiffieHellman {
    public List<Integer> getKeys(int q, int alpha, int xa, int xb) {
        // Compute public values
        int ya = modPow(alpha, xa, q);
        int yb = modPow(alpha, xb, q);

        // Compute shared secrets
        int Ka = modPow(yb, xa, q);
        int Kb = modPow(ya, xb, q);

        return Arrays.asList(Ka, Kb);
    }

    private int modPow(int base, int exp, int mod) {
        long result = 1;
        long b = base % mod;

        while (exp > 0) {
            if ((exp & 1) == 1) {
                result = (result * b) % mod;
            }
            b = (b * b) % mod;
            exp >>= 1;
        }

        return (int) result;
    }
}
