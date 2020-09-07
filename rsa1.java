import java.util.Scanner;
import java.math.BigInteger;
import java.util.Random;

public class rsa1
{
	
	private static BigInteger p,q,n,phi,e,d;
	private static int bitLength = 1024;
	private static Random r = new Random();
		
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		rsa1 rsa = new rsa1();
		System.out.println("Enter text:");
		String s = sc.nextLine();
		sc.close();
		byte[] b = s.getBytes();
		System.out.println("String in bytes:" + byte_string(b));
		
		//CALCULATIONS
		p = BigInteger.probablePrime(bitLength, r);
		q = BigInteger.probablePrime(bitLength, r);
		n = p.multiply(q);
		phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
		e = BigInteger.probablePrime(bitLength, r);
        while (phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0)
        {
            e.add(BigInteger.ONE);
        }
		d = e.modInverse(phi);
        System.out.println("P:" + p);
        System.out.println("Q:" + q);
        System.out.println("n:" + n);
        System.out.println("Phi:" + phi);
        System.out.println("e:" + e);
        System.out.println("d:" + d);
        
        // encrypt
        byte[] encryption = rsa.encrypt(s.getBytes());
        System.out.println("Encrypting Bytes: " + byte_string(encryption));
        System.out.println("Encrypted String: " + new String(encryption));
        
        // decrypt
        byte[] decryption = rsa.decrypt(encryption);
        System.out.println("Decrypting Bytes: " + byte_string(decryption));
        System.out.println("Decrypted String: " + new String(decryption));
	}

	//byte-string
	private static String byte_string(byte[] value)
    {
        String test = "";
        for (byte b : value)
        {
            test += Byte.toString(b);
        }
        return test;
    }
	
	// Encrypt message
    public byte[] encrypt(byte[] message)
    {
        return (new BigInteger(message)).modPow(e, n).toByteArray();
    }
 
    // Decrypt message
    public byte[] decrypt(byte[] message)
    {
        return (new BigInteger(message)).modPow(d, n).toByteArray();
    }
	

}

/* Reference : 
 https://introcs.cs.princeton.edu/java/99crypto/RSA.java.html
 https://www.javatpoint.com/java-biginteger*/