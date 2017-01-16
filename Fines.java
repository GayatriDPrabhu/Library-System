import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;

public class Fines extends JFrame {

	private JPanel contentPane;
	private JTextField Cid;
	String cid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Fines frame = new Fines();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Fines() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblNewLabel = new JLabel("Fines");
		lblNewLabel.setBackground(Color.BLACK);
		lblNewLabel.setOpaque(true);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false); 
				dispose();
			}
		});
		
		JLayeredPane layeredPane = new JLayeredPane();
		GridBagConstraints gbc_layeredPane = new GridBagConstraints();
		gbc_layeredPane.insets = new Insets(0, 0, 5, 0);
		gbc_layeredPane.fill = GridBagConstraints.BOTH;
		gbc_layeredPane.gridx = 0;
		gbc_layeredPane.gridy = 1;
		contentPane.add(layeredPane, gbc_layeredPane);
		
		JLabel lblCardid = new JLabel("Card_id:");
		lblCardid.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblCardid.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCardid.setBounds(111, 23, 61, 16);
		layeredPane.add(lblCardid);
		
		Cid = new JTextField();
		Cid.setBounds(181, 19, 130, 26);
		layeredPane.add(Cid);
		Cid.setColumns(10);
		
		JButton btnAllFines = new JButton("All Fines");
		btnAllFines.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cid=Cid.getText();
				try {
					new AllFine(cid).setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnAllFines.setBounds(6, 62, 117, 29);
		layeredPane.add(btnAllFines);
		
		JButton btnUnpaidFines = new JButton("Unpaid Fines");
		btnUnpaidFines.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cid=Cid.getText();
				try {
					new UnpaidFine(cid).setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnUnpaidFines.setBounds(161, 62, 117, 29);
		layeredPane.add(btnUnpaidFines);
		
		JButton btnPaidFines = new JButton("Paid Fines");
		btnPaidFines.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cid=Cid.getText();
			try {
				new PaidFine(cid).setVisible(true);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
		});
		btnPaidFines.setBounds(317, 62, 117, 29);
		layeredPane.add(btnPaidFines);
		
		JButton btnNewButton = new JButton("Overdue Books");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cid=Cid.getText();
				try {
					new Overdue(cid).setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(140, 123, 158, 29);
		layeredPane.add(btnNewButton);
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCancel.gridx = 0;
		gbc_btnCancel.gridy = 2;
		contentPane.add(btnCancel, gbc_btnCancel);
	}
}
