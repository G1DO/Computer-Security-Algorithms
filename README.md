# Computer Security Algorithms in Java

Java implementations of classical and modern cryptographic algorithms in a single repository, with accompanying test cases.

## Contents

* `src/main/java/`:

  * `RSA.java` – Public‑key cryptosystem (key generation, encryption, decryption)
  * `DES.java` – Symmetric block cipher implementation (key schedule, S‑boxes, Feistel rounds)
  * `ElGamal.java` – Public‑key cryptosystem based on discrete logarithms (key generation, encryption, decryption)
  * `DiffieHellman.java` – Key‑exchange protocol (prime modulus generation, shared secret computation)
  * `ColumnarCipher.java` – Classical transposition cipher (keyed columnar transposition)
  * `MonoalphabeticCipher.java` – Simple substitution cipher (letter‑to‑letter mapping)
  * `PlayfairCipher.java` – Digraph substitution cipher (5×5 key square)

* `src/test/java/`:

  * `RSATest.java`
  * `DESTest.java`
  * `ElGamalTest.java`
  * `DiffieHellmanTest.java`
  * `ColumnarCipherTest.java`
  * `MonoalphabeticCipherTest.java`
  * `PlayfairCipherTest.java`

* `README.md` – This documentation

* `.gitignore` – Standard Java ignore rules

## Description

This repository demonstrates how to implement various cryptographic algorithms from scratch in Java. Each implementation in `src/main/java/` contains a standalone class with its own `main` method, input parsing, and algorithm logic. Unit tests in `src/test/java/` verify correctness of encryption, decryption, and key exchange operations for each algorithm.

## Prerequisites

* Java Development Kit (JDK) 8 or higher
* [JUnit 5](https://junit.org/junit5/) or a compatible test runner
* (Optional) An IDE such as IntelliJ IDEA or Eclipse
* Basic familiarity with command‑line tools and unit testing

## Setup

1. **Clone this repository:**

   ```bash
   git clone https://github.com/YourUsername/Computer-Security-Algorithms.git
   cd Computer-Security-Algorithms
   ```

2. **Compile main classes:**

   ```bash
   javac src/main/java/*.java -d out/production
   ```

   This produces `.class` files under `out/production`.

3. **Compile test classes:**

   ```bash
   javac -cp .;path/to/junit5.jar;out/production src/test/java/*.java -d out/test
   ```

   Replace `path/to/junit5.jar` with your local JUnit 5 JAR path. Output `.class` files go under `out/test`.

## Running Algorithms

Use the compiled classes in `out/production`. For example:

```bash
java -cp out/production RSA <prime_p> <prime_q> <plaintext>
```

Replace `<ClassName>` and arguments accordingly:

```
RSA
DES
ElGamal
DiffieHellman
ColumnarCipher
MonoalphabeticCipher
PlayfairCipher
```

Each class’s `main` method parses input parameters, performs encryption/decryption (or key exchange), and prints results to standard output.

### Example Usage

* **RSA** (generate keys, encrypt, decrypt):

  ```bash
  java -cp out/production RSA 61 53 1234
  ```

  * `61` and `53` are prime numbers.
  * `1234` is the integer plaintext.
  * Output: public key `(n, e)`, ciphertext, and decrypted plaintext.

* **DES** (encrypt plaintext string):

  ```bash
  java -cp out/production DES "HELLOWORLD" "0123456789ABCDEF"
  ```

  * `"HELLOWORLD"` is plaintext.
  * `"0123456789ABCDEF"` is 64-bit key in hex.
  * Output: hexadecimal ciphertext.

* **ElGamal** (encrypt/decrypt):

  ```bash
  java -cp out/production ElGamal 467 2 1234
  ```

  * `467` is prime modulus.
  * `2` is generator.
  * `1234` is integer plaintext.
  * Output: public key `(p, g, h)`, ciphertext pair `(c1, c2)`, decrypted message.

* **DiffieHellman** (key exchange):

  ```bash
  java -cp out/production DiffieHellman 467 5 45 67
  ```

  * `467` is prime modulus.
  * `5` is generator.
  * `45` and `67` are private keys for parties A and B.
  * Output: shared secret for both parties.

* **ColumnarCipher** (classical transposition):

  ```bash
  java -cp out/production ColumnarCipher "ATTACKATDAWN" "4312567"
  ```

  * `"ATTACKATDAWN"` is uppercase plaintext.
  * `"4312567"` is numeric key.
  * Output: ciphertext string.

* **MonoalphabeticCipher** (simple substitution):

  ```bash
  java -cp out/production MonoalphabeticCipher "HELLOWORLD" "QWERTYUIOPASDFGHJKLZXCVBNM"
  ```

  * Substitution alphabet is a 26-letter permutation of A–Z.
  * Output: encrypted text.

* **PlayfairCipher** (digraph substitution):

  ```bash
  java -cp out/production PlayfairCipher "HELLOWORLD" "KEYPHRASE"
  ```

  * `"KEYPHRASE"` builds the 5×5 key square.
  * Output: ciphertext digraphs.

Refer to each `.java` file’s header comments for precise argument formats and behavior.

## Running Tests

Ensure JUnit 5 is on your classpath. From project root:

```bash
java -cp out/production;out/test;path/to/junit5.jar org.junit.platform.console.ConsoleLauncher --scan-classpath
```

This command discovers and runs all tests in `src/test/java/`.

Alternatively, import the project into IntelliJ IDEA or Eclipse, add JUnit 5 as a library, and run each `*Test.java` class directly from the IDE.

## File Structure

```
Computer-Security-Algorithms/
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── ColumnarCipher.java
│   │       ├── DES.java
│   │       ├── DiffieHellman.java
│   │       ├── ElGamal.java
│   │       ├── MonoalphabeticCipher.java
│   │       ├── PlayfairCipher.java
│   │       └── RSA.java
│   └── test/
│       └── java/
│           ├── ColumnarCipherTest.java
│           ├── DESTest.java
│           ├── DiffieHellmanTest.java
│           ├── ElGamalTest.java
│           ├── MonoalphabeticCipherTest.java
│           ├── PlayfairCipherTest.java
│           └── RSATest.java
├── README.md
└── .gitignore
```

