package src.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import src.algorithms.HMAC;

public class FrameHMAC extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel  jContentPane   = null;
	private JButton bt_computeHmac = null;
	private JButton bt_generateKey = null;
	
	private JLabel jLabel_message;
	private JLabel jLabel_key;
	private JLabel jLabel_mac;
	private JLabel jLabel_mode;
	
	private JPanel jPanel_Input = null;

	private JTextArea ta_message;
	private JTextArea ta_key;
	private JTextArea ta_mac;
	private JComboBox cbox_mode;
	
	private HMAC hmac;
	
	/**
	 * This is the default constructor
	 */
	public FrameHMAC() {
		super();
		initialize();
		hmac = new HMAC();
	}
	
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(620, 500);
		this.setContentPane(getJContentPane());
		this.setTitle("HMAC Authentication");			
	}
	
	/**
	 * Updates HMAC class variables with UI values.
	 */
	private void updateHmac() {				
		hmac.message = ta_message.getText();
		hmac.key = ta_key.getText();
		hmac.mac = ta_mac.getText();		
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
			
			jContentPane.add(getBt_computeHmac(), null);
			jContentPane.add(getJPanel_Input(), null);

			jLabel_mode = new JLabel();
			jLabel_mode.setBounds(22, 402, 105, 28);
			jContentPane.add(jLabel_mode);
			jLabel_mode.setFont(new Font("Tahoma", Font.BOLD, 14));
			jLabel_mode.setText("Hash:");
			jContentPane.add(getCbox_mode());
						
		}
		return jContentPane;
	}


	private void hmac_generateKey() {		
		try {
			hmac.generateKey();
			ta_key.setText(hmac.key);			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(jContentPane, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}		
	}
	
	private void hmac_compute() {
		try {
			updateHmac();
			hmac.compute();
			ta_mac.setText(hmac.mac);			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(jContentPane, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}	
	
	/**
	 * This method initializes bt_generateKey
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBt_generateKey() {
		if (bt_generateKey == null) {
			bt_generateKey = new JButton();
			bt_generateKey.setBounds(12, 26, 134, 28);
			bt_generateKey.setText("Generate Key");
			bt_generateKey.setHorizontalAlignment(SwingConstants.LEADING);
			bt_generateKey.setFont(new Font("Tahoma", Font.BOLD, 14));
			bt_generateKey.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					hmac_generateKey();
				}
			});
		}
		return bt_generateKey;
	}


	/**
	 * This method initializes bt_encript
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBt_computeHmac() {
		if (bt_computeHmac == null) {
			bt_computeHmac = new JButton();
			bt_computeHmac.setFont(new Font("Tahoma", Font.BOLD, 14));
			bt_computeHmac.setBounds(new Rectangle(303, 402, 150, 29));
			bt_computeHmac.setText("Compute HMAC");
			bt_computeHmac.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					hmac_compute();
				}
			});
		}
		return bt_computeHmac;
	}
	
	/**
	 * This method initializes jPanel1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel_Input() {
		if (jPanel_Input == null) {
			jPanel_Input = new JPanel();
			jPanel_Input.setBorder(new TitledBorder(new LineBorder(new Color(171, 173, 179)), "HMAC Parametes", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
			jPanel_Input.setToolTipText("");
			jPanel_Input.setLayout(null);
			jPanel_Input.setBounds(new Rectangle(12, 25, 578, 354));

			jLabel_message = new JLabel();
			jLabel_message.setFont(new Font("Tahoma", Font.BOLD, 14));
			jLabel_message.setBounds(12, 148, 81, 28);
			jPanel_Input.add(jLabel_message);
			jLabel_message.setText("Message:");

			jLabel_key = new JLabel();
			jLabel_key.setFont(new Font("Tahoma", Font.BOLD, 14));
			jLabel_key.setBounds(12, 78, 90, 28);
			jPanel_Input.add(jLabel_key);
			jLabel_key.setText("Key:");
			
			jLabel_mac = new JLabel();
			jLabel_mac.setFont(new Font("Tahoma", Font.BOLD, 14));
			jLabel_mac.setBounds(12, 241, 90, 28);
			jPanel_Input.add(jLabel_mac);
			jLabel_mac.setText("MAC:");
			
			jPanel_Input.add(getTa_message());
			jPanel_Input.add(getTa_key());
			jPanel_Input.add(getTa_mac());
			jPanel_Input.add(getBt_generateKey());
		}
		return jPanel_Input;
	}
	
	private JTextArea getTa_message() {
		if (ta_message == null) {
			ta_message = new JTextArea();
			ta_message.setLineWrap(true);
			ta_message.setFont(new Font("Tahoma", Font.PLAIN, 12));
			ta_message.setBounds(116, 138, 450, 51);
		}
		return ta_message;
	}

	private JTextArea getTa_key() {
		if (ta_key == null) {
			ta_key = new JTextArea();
			ta_key.setLineWrap(true);
			ta_key.setFont(new Font("Tahoma", Font.PLAIN, 12));
			ta_key.setBounds(116, 74, 450, 38);;
		}
		return ta_key;
	}
	
	private JTextArea getTa_mac() {
		if (ta_mac == null) {
			ta_mac = new JTextArea();
			ta_mac.setLineWrap(true);
			ta_mac.setFont(new Font("Tahoma", Font.PLAIN, 12));
			ta_mac.setBounds(116, 218, 450, 90);
		}
		return ta_mac;
	}

	private JComboBox getCbox_mode() {
		if (cbox_mode == null) {
			cbox_mode = new JComboBox();
			cbox_mode.setModel(new DefaultComboBoxModel(new String[] {"SHA-256", "SHA-1"}));
			cbox_mode.setFont(new Font("Tahoma", Font.BOLD, 14));
			cbox_mode.setBounds(90, 402, 91, 29);
			cbox_mode.addActionListener(new java.awt.event.ActionListener () {
				public void actionPerformed(java.awt.event.ActionEvent event) {
					if (hmac != null) {
						hmac.setMode(cbox_mode.getSelectedIndex());
					}									
				}
			});
		}
		return cbox_mode;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
