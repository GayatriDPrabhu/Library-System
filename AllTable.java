import java.awt.BorderLayout;


import java.sql.*;
import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JLayeredPane;
import java.awt.Insets;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;

public class AllTable extends JFrame {
	
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
					AllTable frame = new AllTable(Isbn, Cid);
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
	public AllTable(String Isbn, String Cid) throws SQLException {
		this.Isbn=Isbn;
		this.Cid=Cid;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 543, 303);
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
		scrollPane.setBounds(6, 6, 521, 225);
		layeredPane.add(scrollPane,BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library", "root", "mojojojo8");
		Statement stmt = conn.createStatement();
		ResultSet rs=null;
		
		if(Isbn.equals("")&&Cid.equals(""))
		{
			rs = stmt.executeQuery("select isbn, card_id, Date_Out, Due_Date, date_in from book_loans;");

		}
		else if(Isbn.equals(""))
		{
			rs = stmt.executeQuery("select isbn, card_id, Date_Out, Due_Date, date_in from book_loans where Card_id='"+Cid+"';");

		}
		else if(Cid.equals(""))
		{
			rs = stmt.executeQuery("select isbn, card_id, Date_Out, Due_Date, date_in from book_loans where Isbn='"+Isbn+"';");

		}
	    else
		{
			rs = stmt.executeQuery("select isbn, card_id, Date_Out, Due_Date, date_in from book_loans where Isbn='"+Isbn+"' and Card_id='"+Cid+"';");
		}
		
		table.setModel(DbUtils.resultSetToTableModel(rs));
		table.setEnabled(false);
		
		
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(50);
		
		for(int i=0;i<table.getRowCount();i++)
		{
			 Object ob = table.getModel().getValueAt(i, 4);
        if (ob == null) {
            table.getModel().setValueAt("Not yet", i, 4);
        }
		}
		
		
		
		
		GridBagConstraints gbc_btnClose = new GridBagConstraints();
		gbc_btnClose.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnClose.gridx = 0;
		gbc_btnClose.gridy = 1;
		contentPane.add(btnClose, gbc_btnClose);
	}
}
