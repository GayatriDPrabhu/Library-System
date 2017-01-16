import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.Connection;

import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class CheckedIn extends JFrame {
	
	static Connection conn = null;
	
	String dueDate;
	
	static int Loan_id;

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CheckedIn frame = new CheckedIn(Loan_id);
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
	public CheckedIn(int Loan_id) throws SQLException {
		this.Loan_id=Loan_id;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLayeredPane layeredPane = new JLayeredPane();
		GridBagConstraints gbc_layeredPane = new GridBagConstraints();
		gbc_layeredPane.insets = new Insets(0, 0, 5, 0);
		gbc_layeredPane.fill = GridBagConstraints.BOTH;
		gbc_layeredPane.gridx = 0;
		gbc_layeredPane.gridy = 0;
		contentPane.add(layeredPane, gbc_layeredPane);
		
		JLabel lblCardid = new JLabel("Card_id:");
		lblCardid.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCardid.setFont(new Font("Lucida Grande", Font.ITALIC, 14));
		lblCardid.setBounds(31, 37, 61, 16);
		layeredPane.add(lblCardid);
		
		JLabel lblCid = new JLabel("New label");
		lblCid.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblCid.setBounds(104, 37, 79, 16);
		layeredPane.add(lblCid);
		
		JLabel lblNewLabel = new JLabel("ISBN:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.ITALIC, 14));
		lblNewLabel.setBounds(195, 39, 61, 15);
		layeredPane.add(lblNewLabel);
		
		JLabel lblIsbn = new JLabel("New label");
		lblIsbn.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblIsbn.setBounds(268, 38, 166, 16);
		layeredPane.add(lblIsbn);
		
		JLabel lblNewLabel_1 = new JLabel("Date In:");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.ITALIC, 14));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(122, 80, 79, 16);
		layeredPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Due Date:");
		lblNewLabel_2.setFont(new Font("Lucida Grande", Font.ITALIC, 14));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(122, 126, 79, 16);
		layeredPane.add(lblNewLabel_2);
		
		JLabel lblOut = new JLabel("New label");
		lblOut.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblOut.setBounds(222, 80, 128, 16);
		layeredPane.add(lblOut);
		
		JLabel lblDue = new JLabel("New label");
		lblDue.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblDue.setBounds(222, 126, 136, 16);
		layeredPane.add(lblDue);
		
		JButton btnCheckIn = new JButton("Check In");
		btnCheckIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Timestamp stamp = new Timestamp(System.currentTimeMillis());
				Date date = new Date(stamp.getTime());
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String dateIn=dateFormat.format(date);
				Statement stmt1;
				Statement stmt2;
				Statement stmt3;
				try {
					stmt1 = conn.createStatement();
					stmt1.executeUpdate("update book_loans set Date_in='"+dateIn+"' where Loan_id='"+Loan_id+"';");
					stmt2 = conn.createStatement();
					ResultSet rs=stmt2.executeQuery("select * from book_loans where Loan_id='"+Loan_id+"' and Due_date<Date_in;");
                    if(rs.next())
                    {
                    	JOptionPane.showMessageDialog(null, "Checked in. Overdue book. Fines applied");
                    	java.util.Date d1 = new SimpleDateFormat("yyyy-MM-dd").parse(dueDate);
                		java.util.Date d2 = new SimpleDateFormat("yyyy-MM-dd").parse(dateIn);


                	long x =  (d2.getTime() - d1.getTime());
                	x=x / (1000 * 60 * 60 * 24);
                	double fine=0.25*x;
                	
                	stmt3 = conn.createStatement();
					stmt3.executeUpdate("INSERT INTO Fines (Loan_id,Fine_amt) VALUES ('"+Loan_id+"', '"+fine+"');");

                	
                    }
                    else
                    {
                    	JOptionPane.showMessageDialog(null, "Checked in. No fines applied");
                    }

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				setVisible(false); 
				dispose();
				
				
			}
		});
		GridBagConstraints gbc_btnCheckIn = new GridBagConstraints();
		gbc_btnCheckIn.insets = new Insets(0, 0, 5, 0);
		gbc_btnCheckIn.gridx = 0;
		gbc_btnCheckIn.gridy = 1;
		contentPane.add(btnCheckIn, gbc_btnCheckIn);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false); 
				dispose();
			}
		});
		
	
		conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/Library", "root", "mojojojo8");
		Statement stmt = conn.createStatement();
		ResultSet rs=stmt.executeQuery("select isbn, card_id, Date_Out, Due_Date from book_loans where Loan_id='"+Loan_id+"';");
		
		rs.next();
		lblIsbn.setText(rs.getString(1));
		lblCid.setText(rs.getString(2));
		lblOut.setText(rs.getString(3));
		lblDue.setText(rs.getString(4));
		dueDate=rs.getString(4);
		
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCancel.gridx = 0;
		gbc_btnCancel.gridy = 2;
		contentPane.add(btnCancel, gbc_btnCancel);
	}
}
