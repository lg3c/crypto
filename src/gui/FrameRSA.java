package src.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import src.algorithms.RSA;

public class FrameRSA extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JButton bt_generatePairKey = null;
	private JButton bt_encrypt = null;
	private JButton bt_decrypt = null;
	private JLabel jLabel_privatekey;
	private JLabel jLabel_publickey;
	private JLabel jLabel_plaintext;
	private JLabel jLabel_cyphertext;
	
	private JPanel jPanel_Input = null;
	private JTextArea ta_privatekey;
	private JTextArea ta_publickey;
	private JTextArea ta_plaintext;
	private JTextArea ta_cyphertext;
	
	private RSA rsa;
	
	/**
	 * This is the default constructor
	 */
	public FrameRSA() {
		super();
		initialize();
		rsa = new RSA();
	}
	
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(620, 500);
		this.setContentPane(getJContentPane());
		this.setTitle("RSA Encryption");
	}

	/**
	 * Updates RSA class variables with UI values.
	 */
	private void updateRsa() {
		rsa.publicKey = ta_publickey.getText();
		rsa.privateKey = ta_privatekey.getText();		
		rsa.message = ta_plaintext.getText();
		rsa.encrypted = ta_cyphertext.getText();
	}
	
	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			
			jContentPane.add(getBt_encrypt(), null);
			jContentPane.add(getBt_decrypt(), null);
			jContentPane.add(getJPanel_Input(), null);
						
		}
		return jContentPane;
	}

	/**
	 * Generates RSA key pair and updates public and private keys text areas.
	 */
	private void rsa_generatePairKey() {
		try {
			rsa.generateKeys();
			ta_publickey.setText(rsa.publicKey);
			ta_privatekey.setText(rsa.privateKey);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(jContentPane, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}			
	}
	
	/**
	 * Encrypts the message and updates the cipher text area.
	 */
	private void rsa_encrypt() {		
		try {
			updateRsa();
			rsa.encrypt();
			ta_cyphertext.setText(rsa.encrypted);			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(jContentPane, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Decrypts the ciphered text and updates the plain text area.
	 */
	private void rsa_decrypt() {
		try {
			updateRsa();
			rsa.decrypt();
			ta_plaintext.setText(rsa.message);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(jContentPane, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * This method initializes bt_generatePairKey
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBt_generateKeyPair() {
		if (bt_generatePairKey == null) {
			bt_generatePairKey = new JButton();
			bt_generatePairKey.setText("Generate a Key Pair");
			bt_generatePairKey.setHorizontalAlignment(SwingConstants.LEADING);
			bt_generatePairKey.setFont(new Font("Tahoma", Font.BOLD, 14));
			bt_generatePairKey.setBounds(new Rectangle(170, 402, 113, 29));
			bt_generatePairKey.setBounds(12, 18, 169, 29);
			bt_generatePairKey.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					rsa_generatePairKey();
				}
			});
		}
		return bt_generatePairKey;
	}
	
	
	/**
	 * This method initializes bt_encript
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBt_encrypt() {
		if (bt_encrypt == null) {
			bt_encrypt = new JButton();
			bt_encrypt.setFont(new Font("Tahoma", Font.BOLD, 14));
			bt_encrypt.setBounds(new Rectangle(170, 402, 113, 29));
			bt_encrypt.setText("Encrypt");
			bt_encrypt.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					rsa_encrypt();
				}
			});
		}
		return bt_encrypt;
	}

	
	/**
	 * This method initializes bt_decript	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBt_decrypt() {
		if (bt_decrypt == null) {
			bt_decrypt = new JButton();
			bt_decrypt.setFont(new Font("Tahoma", Font.BOLD, 14));
			bt_decrypt.setBounds(new Rectangle(423, 402, 113, 29));
			bt_decrypt.setText("Decrypt");
			bt_decrypt.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					rsa_decrypt();
				}
			});
		}
		return bt_decrypt;
	}
	
	/**
	 * This method initializes jPanel1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel_Input() {
		if (jPanel_Input == null) {
			jPanel_Input = new JPanel();
			jPanel_Input.setBorder(new TitledBorder(new LineBorder(new Color(171, 173, 179)), "RSA Parametes", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
			jPanel_Input.setToolTipText("");
			jPanel_Input.setLayout(null);
			jPanel_Input.setBounds(new Rectangle(12, 25, 578, 354));

			jLabel_privatekey = new JLabel();
			jLabel_privatekey.setFont(new Font("Tahoma", Font.BOLD, 14));
			jLabel_privatekey.setBounds(12, 54, 90, 28);
			jPanel_Input.add(jLabel_privatekey);
			jLabel_privatekey.setText("Private Key:");
			
			jLabel_publickey = new JLabel();
			jLabel_publickey.setFont(new Font("Tahoma", Font.BOLD, 14));
			jLabel_publickey.setBounds(12, 101, 90, 28);
			jPanel_Input.add(jLabel_publickey);
			jLabel_publickey.setText("Public Key:");

			jLabel_plaintext = new JLabel();
			jLabel_plaintext.setFont(new Font("Tahoma", Font.BOLD, 14));
			jLabel_plaintext.setBounds(12, 173, 81, 28);
			jPanel_Input.add(jLabel_plaintext);
			jLabel_plaintext.setText("Plain Text:");
			
			jLabel_cyphertext = new JLabel();
			jLabel_cyphertext.setFont(new Font("Tahoma", Font.BOLD, 14));
			jLabel_cyphertext.setBounds(12, 274, 90, 28);
			jPanel_Input.add(jLabel_cyphertext);
			jLabel_cyphertext.setText("Cypher Text:");

			jPanel_Input.add(getTa_privatekey());
			jPanel_Input.add(getTa_publickey());
			jPanel_Input.add(getTa_plaintext());
			jPanel_Input.add(getTa_cyphertext());
			jPanel_Input.add(getBt_generateKeyPair());

		}
		return jPanel_Input;
	}
	
	private JTextArea getTa_privatekey() {
		if (ta_privatekey == null) {
			ta_privatekey = new JTextArea();
			ta_privatekey.setAutoscrolls(false);
			ta_privatekey.setLineWrap(true);
			ta_privatekey.setFont(new Font("Tahoma", Font.PLAIN, 12));
			ta_privatekey.setBounds(116, 60, 450, 28);
		}
		return ta_privatekey;
	}
	
	private JTextArea getTa_publickey() {
		if (ta_publickey == null) {
			ta_publickey = new JTextArea();
			ta_publickey.setLineWrap(true);
			ta_publickey.setFont(new Font("Tahoma", Font.PLAIN, 12));
			ta_publickey.setBounds(116, 107, 450, 28);
		}
		return ta_publickey;
	}
	
	private JTextArea getTa_plaintext() {
		if (ta_plaintext == null) {
			ta_plaintext = new JTextArea();
			ta_plaintext.setLineWrap(true);
			ta_plaintext.setFont(new Font("Tahoma", Font.PLAIN, 12));
			ta_plaintext.setBounds(116, 148, 450, 90);
		}
		return ta_plaintext;
	}
	
	private JTextArea getTa_cyphertext() {
		if (ta_cyphertext == null) {
			ta_cyphertext = new JTextArea();
			ta_cyphertext.setLineWrap(true);
			ta_cyphertext.setFont(new Font("Tahoma", Font.PLAIN, 12));
			ta_cyphertext.setBounds(116, 251, 450, 90);
		}
		return ta_cyphertext;
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"
