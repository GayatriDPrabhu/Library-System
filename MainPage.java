import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.TextUI;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class MainPage extends JFrame {

	private JPanel contentPane;
	private JTextField search;
	private JButton btnSearch;
	private JButton btnCheckOut;
	private JButton btnCheckIn;
	private JButton btnFines;
	private JButton btnAddBorrower;
	private JButton btnClose;
	private JLabel lblA;
	private JLabel lblB;
	private JLabel lblC;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPage frame = new MainPage();
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
	public MainPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 467, 406);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		
		
		JLabel heading = new JLabel("Library System");
		heading.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 22));
		heading.setForeground(Color.WHITE);
		heading.setBackground(new Color(0, 0, 0));
		heading.setOpaque(true);
		heading.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_heading = new GridBagConstraints();
		gbc_heading.insets = new Insets(0, 0, 5, 0);
		gbc_heading.fill = GridBagConstraints.BOTH;
		gbc_heading.gridx = 0;
		gbc_heading.gridy = 0;
		contentPane.add(heading, gbc_heading);
		
		search = new JTextField();
		search.setHorizontalAlignment(SwingConstants.LEFT);
		search.setFont(new Font("Arial", Font.PLAIN, 9));
		search.setForeground(new Color(0, 0, 0));
		search.setText("Search for the book....");
		GridBagConstraints gbc_search = new GridBagConstraints();
		gbc_search.insets = new Insets(0, 0, 5, 0);
		gbc_search.gridx = 0;
		gbc_search.gridy = 2;
		contentPane.add(search, gbc_search);
		search.setColumns(63);
		
		btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new Search(search.getText()).setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		GridBagConstraints gbc_btnSearch = new GridBagConstraints();
		gbc_btnSearch.insets = new Insets(0, 0, 5, 0);
		gbc_btnSearch.gridx = 0;
		gbc_btnSearch.gridy = 3;
		contentPane.add(btnSearch, gbc_btnSearch);
		
		btnCheckOut = new JButton("Check Out");
		btnCheckOut.setFont(new Font("Lucida Bright", Font.PLAIN, 13));
		btnCheckOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CheckOut().setVisible(true);
			}
		});
		
		lblA = new JLabel("a");
		lblA.setForeground(SystemColor.window);
		lblA.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		GridBagConstraints gbc_lblA = new GridBagConstraints();
		gbc_lblA.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblA.insets = new Insets(0, 0, 5, 0);
		gbc_lblA.gridx = 0;
		gbc_lblA.gridy = 4;
		contentPane.add(lblA, gbc_lblA);
		GridBagConstraints gbc_btnCheckOut = new GridBagConstraints();
		gbc_btnCheckOut.insets = new Insets(0, 0, 5, 0);
		gbc_btnCheckOut.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCheckOut.gridx = 0;
		gbc_btnCheckOut.gridy = 5;
		contentPane.add(btnCheckOut, gbc_btnCheckOut);
		
		btnCheckIn = new JButton("Check In");
		btnCheckIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CheckIn().setVisible(true);
			}
		});
		btnCheckIn.setFont(new Font("Lucida Bright", Font.PLAIN, 13));
		GridBagConstraints gbc_btnCheckIn = new GridBagConstraints();
		gbc_btnCheckIn.insets = new Insets(0, 0, 5, 0);
		gbc_btnCheckIn.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCheckIn.gridx = 0;
		gbc_btnCheckIn.gridy = 6;
		contentPane.add(btnCheckIn, gbc_btnCheckIn);
		
		btnFines = new JButton("Fines");
		btnFines.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Fines().setVisible(true);			}
		});
		btnFines.setFont(new Font("Lucida Bright", Font.PLAIN, 13));
		GridBagConstraints gbc_btnFines = new GridBagConstraints();
		gbc_btnFines.insets = new Insets(0, 0, 5, 0);
		gbc_btnFines.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnFines.gridx = 0;
		gbc_btnFines.gridy = 7;
		contentPane.add(btnFines, gbc_btnFines);
		
		lblB = new JLabel("b");
		lblB.setForeground(SystemColor.window);
		GridBagConstraints gbc_lblB = new GridBagConstraints();
		gbc_lblB.insets = new Insets(0, 0, 5, 0);
		gbc_lblB.gridx = 0;
		gbc_lblB.gridy = 8;
		contentPane.add(lblB, gbc_lblB);
		
		btnAddBorrower = new JButton("Add Borrower");
		btnAddBorrower.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddBorrower().setVisible(true);
			}
		});
		btnAddBorrower.setFont(new Font("Lucida Bright", Font.PLAIN, 13));
		GridBagConstraints gbc_btnAddBorrower = new GridBagConstraints();
		gbc_btnAddBorrower.insets = new Insets(0, 0, 5, 0);
		gbc_btnAddBorrower.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAddBorrower.gridx = 0;
		gbc_btnAddBorrower.gridy = 9;
		contentPane.add(btnAddBorrower, gbc_btnAddBorrower);
		
		lblC = new JLabel("c");
		lblC.setForeground(SystemColor.window);
		GridBagConstraints gbc_lblC = new GridBagConstraints();
		gbc_lblC.insets = new Insets(0, 0, 5, 0);
		gbc_lblC.gridx = 0;
		gbc_lblC.gridy = 10;
		contentPane.add(lblC, gbc_lblC);
		
		btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false); 
				dispose();
			}
		});
		GridBagConstraints gbc_btnClose = new GridBagConstraints();
		gbc_btnClose.anchor = GridBagConstraints.SOUTHEAST;
		gbc_btnClose.gridx = 0;
		gbc_btnClose.gridy = 11;
		contentPane.add(btnClose, gbc_btnClose);
	}

}
