import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JLayeredPane;
import java.awt.Insets;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class Search extends JFrame {
	
	static Connection conn = null;
	
	static String keyword;

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Search frame = new Search(keyword);
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
	public Search(String keyword) throws SQLException {
		
		this.keyword=keyword;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100,100,1100,1000);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JButton btnNewButton = new JButton("Close");
		btnNewButton.addActionListener(new ActionListener() {
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
		scrollPane.setBounds(6, 6, 1200, 600);
		layeredPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 1;
		contentPane.add(btnNewButton, gbc_btnNewButton);
		
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library", "root", "mojojojo8");
		Statement stmt = conn.createStatement();
		ResultSet rs=null;
		if(keyword.equals(""))
		{
			rs=stmt.executeQuery("SELECT b.isbn , b.title, GROUP_CONCAT(a.name) as authors, (case when b.isbn in (select isbn from Book_loans where Date_in is null) then 'no' else 'yes' end) as available FROM book b left outer join  book_authors ba on b.isbn=ba.isbn left outer join authors a  on ba.author_id=a.author_id group by b.isbn;");
	
		}
		else
		{
		rs=stmt.executeQuery("SELECT b.isbn , b.title, GROUP_CONCAT(a.name) as authors, (case when b.isbn in (select isbn from Book_loans where Date_in is null) then 'no' else 'yes' end) as available FROM book b left outer join  book_authors ba on b.isbn=ba.isbn left outer join authors a  on ba.author_id=a.author_id group by b.isbn having b.Isbn like '%"+keyword+"%' or b.Title like '%"+keyword+"%' or authors like '%"+keyword+"%';");
		}
		
		table.setModel(DbUtils.resultSetToTableModel(rs));
		table.setEnabled(false);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(150);
		table.getColumnModel().getColumn(1).setPreferredWidth(500);
		table.getColumnModel().getColumn(2).setPreferredWidth(350);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
	}
}
