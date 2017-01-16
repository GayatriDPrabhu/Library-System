import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.Connection;

import net.proteanit.sql.DbUtils;

import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JLayeredPane;
import java.awt.Insets;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class ActiveTable extends JFrame {
	
	static Connection conn = null;

	public static String Isbn;
	public static String Cid;
	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ActiveTable frame = new ActiveTable(Isbn, Cid);
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
	public ActiveTable(String Isbn, String Cid) throws SQLException{
		this.Isbn=Isbn;
		this.Cid=Cid;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
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
		gbc_layeredPane.gridy = 0;
		contentPane.add(layeredPane, gbc_layeredPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 6, 440, 222);
		layeredPane.add(scrollPane,BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/Library", "root", "mojojojo8");
		Statement stmt = conn.createStatement();
		ResultSet rs=null;
		
		if(Isbn.equals("")&&Cid.equals(""))
		{
			rs = stmt.executeQuery("select loan_id, isbn, card_id, Date_Out, Due_Date from book_loans where date_in is null;");

		}
		else if(Isbn.equals(""))
		{
			rs = stmt.executeQuery("select loan_id, isbn, card_id, Date_Out, Due_Date from book_loans where date_in is null and Card_id='"+Cid+"';");

		}
		else if(Cid.equals(""))
		{
			rs = stmt.executeQuery("select loan_id, isbn, card_id, Date_Out, Due_Date from book_loans where date_in is null and Isbn='"+Isbn+"';");

		}
	    else
		{
			rs = stmt.executeQuery("select loan_id, isbn, card_id, Date_Out, Due_Date from book_loans where date_in is null and Isbn='"+Isbn+"' and Card_id='"+Cid+"';");
		}
		
		table.setModel(DbUtils.resultSetToTableModel(rs));
		table.setEnabled(false);
		
		
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(50);
		
		table.addMouseListener(new MouseAdapter(){
			   public void mouseClicked(MouseEvent evnt) {
			       
		        if (evnt.getClickCount() == 1||evnt.getClickCount() == 2) {
		            
		            int Loan_id= (int) table.getModel().getValueAt(table.rowAtPoint(evnt.getPoint()),0);
		            
		            try {
						new CheckedIn(Loan_id).setVisible(true);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		            setVisible(false); 
					dispose();
		            
			        }
			    }
			});
		
		
		GridBagConstraints gbc_btnClose = new GridBagConstraints();
		gbc_btnClose.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnClose.gridx = 0;
		gbc_btnClose.gridy = 1;
		contentPane.add(btnClose, gbc_btnClose);
	}

}
