import java.awt.EventQueue;
import java.awt.TextArea;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class RSA {

	private static JFrame frmRsa;
	private static JTextField textField;
	private static JTextField textField_1, textField_2;
	private JLabel l2;
	private static BigInteger p,q,n,phi,en,d;
	private static int bitLength = 1024;
    private static Random r = new Random();
    private static String en1 ;
    private static JLabel lblNewLabel_1;
    private static JLabel lblNewLabel_2;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run()
			{
				try 
				{
					RSA window = new RSA();
					window.frmRsa.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RSA()
	{
		initialize();	
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRsa = new JFrame();
		frmRsa.setTitle("RSA");
		frmRsa.setBounds(100, 100, 450, 350);
		frmRsa.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRsa.getContentPane().setLayout(null);
		
		
		JLabel lblNewLabel = new JLabel("enter text");
		lblNewLabel.setBounds(195, 11, 58, 14);
		frmRsa.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
				
			}

		});
		textField.setBounds(154, 36, 148, 43);
		frmRsa.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(10, 150, 414, 58);
		frmRsa.getContentPane().add(textField_1);
		textField_1.setColumns(1);
		
		textField_2 = new JTextField();
		textField_2.setBounds(10, 270, 414, 20);
		frmRsa.getContentPane().add(textField_2);
		textField_2.setColumns(1);
		
		lblNewLabel_1 = new JLabel("encrypted");
		lblNewLabel_1.setBounds(195, 120, 98, 14);
		frmRsa.getContentPane().add(lblNewLabel_1);
		lblNewLabel_1.setVisible(false);
		
		lblNewLabel_2 = new JLabel("decrypted");
		lblNewLabel_2.setBounds(195, 240, 98, 14);
		frmRsa.getContentPane().add(lblNewLabel_2);
		lblNewLabel_2.setVisible(false);
		
		 l2 = new JLabel();
		l2.setBounds(206, 220, 121, 14);
		frmRsa.getContentPane().add(l2);
		
		JButton btnNewButton = new JButton("encryption");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)  {
				encryption();	
			}
		});
		btnNewButton.setBounds(181, 90, 89, 23);
		frmRsa.getContentPane().add(btnNewButton);
		
		JButton btnNewButton1 = new JButton("decryption");
		btnNewButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)  {
				decryption();	
			}
		});
		btnNewButton1.setBounds(181, 215, 89, 23);
		frmRsa.getContentPane().add(btnNewButton1);
	}
	
	public static byte[] encryption() {
		String text = textField.getText();
		byte[] b = text.getBytes();
		byte_string(b);
		
	
		
		//CALCULATIONS
		p = BigInteger.probablePrime(bitLength, r);
		q = BigInteger.probablePrime(bitLength, r);
		n = p.multiply(q);
		phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
		en = BigInteger.probablePrime(bitLength, r);
        while (phi.gcd(en).compareTo(BigInteger.ONE) > 0 && en.compareTo(phi) < 0)
        {
            en.add(BigInteger.ONE);
        }
		d = en.modInverse(phi);
		
		// encrypt
		byte[] encryption = encrypt(text.getBytes());
        byte_string(encryption);
         en1 = new String(encryption);
		
     // decrypt
        byte[] decryption = decrypt(encryption);
       byte_string(decryption);
       String de= new String(decryption);
       
       lblNewLabel_1.setVisible(true);
        textField_1.setText(en1);

        return encryption;
	}
	
	private static void decryption()
	{
		byte[] decryption = decrypt(encryption());
	       byte_string(decryption);
	       String de= new String(decryption);
	       
	       lblNewLabel_2.setVisible(true);
	        textField_2.setText(de);
	}
	
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
    public static byte[] encrypt(byte[] message)
    {
        return (new BigInteger(message)).modPow(en, n).toByteArray();
    }
    // Decrypt message
    public static byte[] decrypt(byte[] message)
    {
        return (new BigInteger(message)).modPow(d, n).toByteArray();
    }
}
