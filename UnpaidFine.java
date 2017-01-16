import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import javax.swing.JLayeredPane;
import java.awt.Insets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class UnpaidFine extends JFrame {
	
	static Connection conn = null;
	
	static String cid;

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UnpaidFine frame = new UnpaidFine(cid);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public UnpaidFine(String cid) throws SQLException {
		this.cid=cid;
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
		
		JLayeredPane layeredPane = new JLayeredPane();
		GridBagConstraints gbc_layeredPane = new GridBagConstraints();
		gbc_layeredPane.insets = new Insets(0, 0, 5, 0);
		gbc_layeredPane.fill = GridBagConstraints.BOTH;
		gbc_layeredPane.gridx = 0;
		gbc_layeredPane.gridy = 0;
		contentPane.add(layeredPane, gbc_layeredPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 24, 389, 189);
		layeredPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library", "root", "mojojojo8");
		Statement stmt = conn.createStatement();
		ResultSet rs=null;
		
		
		if(cid.equals(""))
		{
			rs = stmt.executeQuery("select f.Loan_id, isbn, card_id, fine_amt from fines f, book_loans b where b.loan_id=f.loan_id and paid=0;");
		}
		else
		{
			rs=stmt.executeQuery("select f.Loan_id, isbn, card_id, fine_amt from fines f, book_loans b where Card_id='"+cid+"' and b.loan_id=f.loan_id and paid=0;");
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
						new FinePaid(Loan_id).setVisible(true);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		            setVisible(false); 
					dispose();
		            
			        }
			    }
			});
		
		JButton btnNewButton = new JButton("Close");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 1;
		contentPane.add(btnNewButton, gbc_btnNewButton);
	}
}
