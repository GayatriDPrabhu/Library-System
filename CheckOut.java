import java.awt.BorderLayout;
import java.awt.EventQueue;

import java.sql.*;
import java.sql.Date;
import java.text.*;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;

public class CheckOut extends JFrame {
	
	static Connection conn = null;

	private JPanel contentPane;
	private JTextField Isbn;
	private JTextField Card_id;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CheckOut frame = new CheckOut();
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
	public CheckOut() {
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
		
		JLabel heading = new JLabel("Check Out");
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
		
		JLabel lblIsbn = new JLabel("Isbn*:");
		lblIsbn.setFont(new Font("KufiStandardGK", Font.PLAIN, 16));
		lblIsbn.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIsbn.setBounds(83, 43, 61, 16);
		layeredPane.add(lblIsbn);
		
		JLabel lblCardid = new JLabel("Card_id*:");
		lblCardid.setFont(new Font("KufiStandardGK", Font.PLAIN, 16));
		lblCardid.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCardid.setBounds(58, 77, 86, 16);
		layeredPane.add(lblCardid);
		
		Isbn = new JTextField();
		Isbn.setBounds(156, 37, 177, 26);
		layeredPane.add(Isbn);
		Isbn.setColumns(10);
		
		Card_id = new JTextField();
		Card_id.setBounds(156, 71, 177, 26);
		layeredPane.add(Card_id);
		Card_id.setColumns(10);
		
		JLabel Msg = new JLabel("New label");
		Msg.setForeground(Color.RED);
		Msg.setHorizontalAlignment(SwingConstants.CENTER);
		Msg.setBounds(51, 125, 345, 16);
		layeredPane.add(Msg);
		Msg.setVisible(false);
		
		JButton btnEnter = new JButton("Enter");
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					
					String isbn=Isbn.getText();
					int cardId=Integer.parseInt(Card_id.getText());
					Timestamp stamp = new Timestamp(System.currentTimeMillis());
					Date date = new Date(stamp.getTime());
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					String dateIn=dateFormat.format(date);
					Calendar c = Calendar.getInstance();
					c.add(Calendar.DATE, 14);
					String dueDate=dateFormat.format(c.getTime());
					
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library", "root", "mojojojo8");
					Statement stmt1 = conn.createStatement();
					ResultSet rs1 = stmt1.executeQuery("select * from book where Isbn='"+isbn+"';");
					Statement stmt2 = conn.createStatement();
					ResultSet rs2 = stmt2.executeQuery("select * from borrower where Card_id='"+cardId+"';");
					
					if(rs1.next()&&rs2.next())
					{
						Statement stmt3 = conn.createStatement();
						ResultSet rs3 = stmt3.executeQuery("select * from book_loans where Isbn='"+isbn+"' and Date_in is null;");
						if(rs3.next())
						{
							Msg.setVisible(true);
							Msg.setText("The book is already issued");	
							Isbn.setText(""); 
						}
						else
						{
							Statement stmt4 = conn.createStatement();
							ResultSet rs4 = stmt4.executeQuery("select * from book_loans where Card_id='"+cardId+"' and Date_in is null and Due_date<CAST(CURRENT_TIMESTAMP AS DATE);");
							if(rs4.next())
							{
								Msg.setVisible(true);
								Msg.setText("The borrower already has an overdue book");	
								Isbn.setText(""); 
								Card_id.setText("");
							}
							else
							{
								Statement stmt5 = conn.createStatement();
								ResultSet rs5 = stmt5.executeQuery("select count(*) from book_loans where Card_id='"+cardId+"' and Date_in is null;");
								rs5.next();
								if(rs5.getInt(1)>=3)
								{
									Msg.setVisible(true);
									Msg.setText("The borrower already has 3 active book loans");	
									Isbn.setText(""); 
									Card_id.setText("");
								}
								else
								{
									Statement stmt6 = conn.createStatement();
									ResultSet rs6 = stmt6.executeQuery("select * from book_loans b,fines f where Card_id="+cardId+" and b.Loan_id=f.Loan_id and paid=0;");	
									if(rs6.next())
									{
										Msg.setVisible(true);
										Msg.setText("The borrower yet has to pay some fine");	
										Isbn.setText(""); 
										Card_id.setText("");
									}
									else{
										Statement stmt = conn.createStatement();
										ResultSet rs= stmt.executeQuery("select max(Loan_id) from book_loans");
										int id=0;
										if(rs.next())
										{				 	
										 id=rs.getInt(1)+1;
										}
										
										String sql="INSERT INTO Book_loans (Loan_id, Isbn, Card_id, Date_out, Due_date) VALUES ("+id+", '"+isbn+"', "+cardId+", '"+dateIn+"', '"+dueDate+"');";
										Statement stmt7 = conn.createStatement();
										stmt7.executeUpdate(sql);
										Msg.setVisible(true);
										Msg.setText("Done");	
										Isbn.setText(""); 
										Card_id.setText("");
									}
								}
							}
						}
					}
					else
					{
						Msg.setVisible(true);
						Msg.setText("Invalid Isbn or Card_id");	
						Isbn.setText("");
						Card_id.setText("");
					}
					
					
			    }
				catch(SQLException ex) {
					System.out.println("Error in connection: " + ex.getMessage());
				}
			}
		});
		btnEnter.setBounds(166, 154, 117, 29);
		layeredPane.add(btnEnter);
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCancel.gridx = 0;
		gbc_btnCancel.gridy = 2;
		contentPane.add(btnCancel, gbc_btnCancel);
	}
}
