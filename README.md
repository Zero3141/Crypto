# Cryptography Playground
The raw implementation of some encryption and key exchange algorithms.
## Algorithms

### Symmetric 
- Caesar

### Asymmetric 
- RSA
- ElGamal

### Key Exchange 
- Diffie Hellman

## Caesar
TODO

## RSA
The security of the RSA cryptosystem is based on two mathematical problems: 
the problem of factoring large numbers and the RSA problem.

### Key Generation
1. Randomly choose two primes `p, q` with `p ≠ q`
2. Calculate `N=p*q` and `phi(N)=(p-1)*(q-1)`
3. Choose `e=65537`
4. Calculate `d` such as `ed mod phi(N) = 1`

Which produces PublicKey (e, n) and PrivateKey (d, n)

### Encryption
Encrypt a message `m` with a PublicKey `(e,n)` with `c=m^e mod n`

### Decryption
Decrypt a cipher `c` with a PrivateKey `(d,n)` with `m=c^d mod n`

## ElGamal
The security of the ElGamal scheme depends on the properties 
of the underlying group as well as any padding scheme used on the messages.

### Key Generation
1. Randomly choose a cyclic group `G=(G,°,e)` with a generator `g`
2. Choose `a ∈ {2,...,ord(G)-1}`
3. Calculate `A = g^a`

Which produces PublicKey (G, g, A) and PrivateKey (G, g, a)

### Encryption
Encrypt a message m with a PublicKey (G, g, A)
1. Randomly choose `r ∈ {2,...,ord(G)-1}`
2. Calculate `K=A^r`
3. Calculate `C=m*K mod G`

Which produces the chiffre (R,C)

### Decryption
Decrypt a chiffre (R,C) with a PrivateKey (G, g, a)
1. Calculate `K=R^a`
2. Find `K^-1` in `G`
3. Calculate `m=C*K^-1 mod G`

Which decrypts to origin message

## Diffie Hellman
TODO
