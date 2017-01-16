import java.awt.BorderLayout;
import java.awt.EventQueue;

import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class AddBorrower extends JFrame {
	
	static Connection conn = null;

	private JPanel contentPane;
	private JTextField Fname;
	private JTextField Lname;
	private JTextField Addr;
	private JTextField city;
	private JTextField state;
	private JTextField Phone;
	private JTextField SSNno;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddBorrower frame = new AddBorrower();
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
	public AddBorrower() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 464, 355);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{440, 0};
		gbl_contentPane.rowHeights = new int[]{24, 215, 0, 29, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false); 
				dispose();
			}
		});
		
		JLabel heading = new JLabel("Add Borrower");
		heading.setForeground(Color.WHITE);
		heading.setBackground(Color.BLACK);
		heading.setOpaque(true);
		heading.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 20));
		heading.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_heading = new GridBagConstraints();
		gbc_heading.anchor = GridBagConstraints.NORTH;
		gbc_heading.fill = GridBagConstraints.HORIZONTAL;
		gbc_heading.insets = new Insets(0, 0, 5, 0);
		gbc_heading.gridx = 0;
		gbc_heading.gridy = 0;
		contentPane.add(heading, gbc_heading);
		
		JLayeredPane layeredPane = new JLayeredPane();
		GridBagConstraints gbc_layeredPane = new GridBagConstraints();
		gbc_layeredPane.insets = new Insets(0, 0, 5, 0);
		gbc_layeredPane.fill = GridBagConstraints.BOTH;
		gbc_layeredPane.gridx = 0;
		gbc_layeredPane.gridy = 1;
		contentPane.add(layeredPane, gbc_layeredPane);
		
		Fname = new JTextField();
		Fname.setBounds(88, 19, 136, 26);
		layeredPane.add(Fname);
		Fname.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Fisrt name*:");
		lblNewLabel.setBounds(6, 24, 76, 16);
		layeredPane.add(lblNewLabel);
		
		JLabel lblLastName = new JLabel("Last Name*:");
		lblLastName.setBounds(226, 24, 82, 16);
		layeredPane.add(lblLastName);
		
		Lname = new JTextField();
		Lname.setBounds(305, 19, 144, 26);
		layeredPane.add(Lname);
		Lname.setColumns(10);
		
		JLabel lblAddress = new JLabel("Address*:");
		lblAddress.setBounds(16, 52, 61, 16);
		layeredPane.add(lblAddress);
		
		Addr = new JTextField();
		Addr.setBounds(88, 47, 360, 26);
		layeredPane.add(Addr);
		Addr.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("City*:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(6, 80, 76, 16);
		layeredPane.add(lblNewLabel_1);
		
		JLabel lblState = new JLabel("State*:");
		lblState.setHorizontalAlignment(SwingConstants.RIGHT);
		lblState.setBounds(217, 85, 76, 16);
		layeredPane.add(lblState);
		
		city = new JTextField();
		city.setBounds(88, 75, 136, 26);
		layeredPane.add(city);
		city.setColumns(10);
		
		state = new JTextField();
		state.setBounds(305, 75, 143, 26);
		layeredPane.add(state);
		state.setColumns(10);
		
		JLabel lblPhone = new JLabel("Phone:");
		lblPhone.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPhone.setBounds(21, 111, 61, 16);
		layeredPane.add(lblPhone);
		
		JLabel lblSsn = new JLabel("SSN*:");
		lblSsn.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSsn.setBounds(236, 111, 61, 16);
		layeredPane.add(lblSsn);
		
		Phone = new JTextField();
		Phone.setBounds(88, 106, 136, 26);
		layeredPane.add(Phone);
		Phone.setColumns(10);
		Phone.addKeyListener(new KeyAdapter() {
	         public void keyTyped(KeyEvent e) {
	           char c = e.getKeyChar();
	           if (!(Character.isDigit(c) ||
	              (c == KeyEvent.VK_BACK_SPACE) ||
	              (c == KeyEvent.VK_DELETE))) {
	                e.consume();
	              }
	           if (Phone.getText().length() >= 10 )
	        	   e.consume();
	         }
	       });
		
		SSNno = new JTextField();
		SSNno.setBounds(305, 106, 144, 26);
		layeredPane.add(SSNno);
		SSNno.setColumns(10);
		SSNno.addKeyListener(new KeyAdapter() {
	         public void keyTyped(KeyEvent e) {
	           char c = e.getKeyChar();
	           if (!(Character.isDigit(c) ||
	              (c == KeyEvent.VK_BACK_SPACE) ||
	              (c == KeyEvent.VK_DELETE))) {
	                e.consume();
	              }
	           if (SSNno.getText().length() >= 9 )
	        	   e.consume();
	         }
	       });
		
		JLabel lblNewLabel_2 = new JLabel("* marked fields are mandatory");
		lblNewLabel_2.setForeground(Color.RED);
		lblNewLabel_2.setFont(new Font("Avenir", Font.ITALIC, 13));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(16, 144, 433, 16);
		layeredPane.add(lblNewLabel_2);
		
		JLabel Message = new JLabel("New label");
		Message.setHorizontalAlignment(SwingConstants.CENTER);
		Message.setBounds(59, 181, 338, 16);
		layeredPane.add(Message);
		Message.setVisible(false);
		
		
		JButton btnEnter = new JButton("Enter");
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library", "root", "mojojojo8");
					Statement stmt = conn.createStatement();
				String fname,lname,addrs,cty,st,phn,ssn;
				fname=Fname.getText();
				lname=Lname.getText();
				addrs=Addr.getText();
				cty=city.getText();
				st=state.getText();
				phn=Phone.getText();
				ssn=SSNno.getText();
				if(fname.isEmpty()||lname.isEmpty()||addrs.isEmpty()||cty.isEmpty()||st.isEmpty()||ssn.isEmpty())
				{
				Message.setVisible(true);
				Message.setText("Please fill all the mandatory fields");
				}
				else if(phn.length()!=0)
				{
					if(phn.length()<10)
					{
					Message.setVisible(true);
					Message.setText("Please enter correct phone number");
					}
				}	
				else if(ssn.length()<9)
				{
					Message.setVisible(true);
					Message.setText("Please enter correct SSN");
					}	
				else 
				{
					ResultSet rs = stmt.executeQuery("select * from borrower where ssn='"+ssn+"';");
					if(rs.next())
					{
						Message.setVisible(true);
						Message.setText("Borrower with the given ssn already exists");	
						SSNno.setText("");
					}
					else{
						Statement stmt1 = conn.createStatement();
						ResultSet rs2 = stmt1.executeQuery("select max(Card_id) from borrower");
						int CardId=0;
						if(rs2.next())
						{				 	
						 CardId=rs2.getInt(1)+1;
						}
						String Sql="insert into borrower values("+CardId+",'"+ssn+"','"+fname+"','"+lname+"','"+addrs+"','"+cty+"','"+st+"','"+phn+"');";
						Statement stmt2 = conn.createStatement();
                        stmt2.executeUpdate(Sql);
						Message.setVisible(true);
						Message.setText("Done");	
						Fname.setText("");
						Lname.setText("");
						Addr.setText("");
						city.setText("");
						state.setText("");
						Phone.setText("");
						SSNno.setText("");
						JOptionPane.showMessageDialog(null, "Card_id of the new borrower is"+CardId);

						
					}
				}
				}
				catch(SQLException ex) {
					System.out.println("Error in connection: " + ex.getMessage());
				}
			}
		});
		GridBagConstraints gbc_btnEnter = new GridBagConstraints();
		gbc_btnEnter.insets = new Insets(0, 0, 5, 0);
		gbc_btnEnter.gridx = 0;
		gbc_btnEnter.gridy = 2;
		contentPane.add(btnEnter, gbc_btnEnter);
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCancel.gridx = 0;
		gbc_btnCancel.gridy = 3;
		contentPane.add(btnCancel, gbc_btnCancel);
		}
	}

