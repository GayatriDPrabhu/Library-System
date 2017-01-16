import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;

public class CheckIn extends JFrame {

	private JPanel contentPane;
	private JTextField Isbn;
	private JTextField Cid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CheckIn frame = new CheckIn();
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
	public CheckIn() {
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
		
		JLabel heading = new JLabel("Check In");
		heading.setHorizontalAlignment(SwingConstants.CENTER);
		heading.setForeground(Color.WHITE);
		heading.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 20));
		heading.setBackground(Color.BLACK);
		heading.setOpaque(true);
		GridBagConstraints gbc_heading = new GridBagConstraints();
		gbc_heading.insets = new Insets(0, 0, 5, 0);
		gbc_heading.fill = GridBagConstraints.HORIZONTAL;
		gbc_heading.gridx = 0;
		gbc_heading.gridy = 0;
		contentPane.add(heading, gbc_heading);
		
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
		
		JLabel lblIsbn = new JLabel("Isbn:");
		lblIsbn.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblIsbn.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIsbn.setBounds(105, 47, 61, 16);
		layeredPane.add(lblIsbn);
		
		JLabel lblCardid = new JLabel("Card_id:");
		lblCardid.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCardid.setBounds(105, 81, 61, 16);
		layeredPane.add(lblCardid);
		
		Isbn = new JTextField();
		Isbn.setBounds(175, 43, 130, 26);
		layeredPane.add(Isbn);
		Isbn.setColumns(10);
		
		Cid = new JTextField();
		Cid.setBounds(175, 76, 130, 26);
		layeredPane.add(Cid);
		Cid.setColumns(10);
		
		JButton btnAll = new JButton("Search All");
		btnAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String isbn=Isbn.getText();
				String cid=Cid.getText();
				AllTable t1;
				try {
					t1 = new AllTable(isbn,cid);
					t1.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnAll.setBounds(68, 127, 117, 29);
		layeredPane.add(btnAll);
		
		JButton btnActive = new JButton("Search active records");
		btnActive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String isbn=Isbn.getText();
				String cid=Cid.getText();
				ActiveTable t2;
				try {
					t2 = new ActiveTable(isbn,cid);
					t2.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnActive.setBounds(226, 127, 164, 29);
		layeredPane.add(btnActive);
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCancel.gridx = 0;
		gbc_btnCancel.gridy = 2;
		contentPane.add(btnCancel, gbc_btnCancel);
	}
}
