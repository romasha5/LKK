package lkk.addform;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.MatteBorder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import lkk.dbaprovider.QueryKomisiyaTable;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Changekom extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	/**
	 * Create the frame.
	 * @param comboBox 
	 */
	public Changekom(JTable tab, JComboBox<String> comboBox) {
		setModal(true);
		setResizable(false);
		setTitle("Комісія для підпису");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 468, 307);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 442, 205);
		contentPane.add(scrollPane);
		
		table = new JTable(){
	           /**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
	            public boolean isCellEditable(int arg0, int arg1) {	                
	                	return false;
	            }	
				
	            @Override
	            public Class<?> getColumnClass(int column) {
	                if (column==0) {
	                	return Boolean.class;
	                }
	                else {
	                    return String.class;	
					}
	            }
		};
        table.getTableHeader().setReorderingAllowed(false);
		table.setFont(new Font("Arial", Font.BOLD, 12));
		table.setModel(QueryKomisiyaTable.selectKomisiyaTableChange());		
		table.getColumnModel().getColumn(0).setPreferredWidth(8);
		table.getColumnModel().getColumn(1).setPreferredWidth(106);
		table.getColumnModel().getColumn(2).setPreferredWidth(106);
		table.getColumnModel().getColumn(3).setPreferredWidth(106);
		table.removeColumn(table.getColumnModel().getColumn(4));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
	    table.setRowSorter(sorter);
	    if(table.getRowCount()>0){
	    table.setRowSelectionInterval(0, 0);
	    }
	    table.addMouseListener(new MouseAdapter() {

			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2){
					changeselectedrow();
				}
				
			}
		});		
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(3, 3, 3, 3, (Color) SystemColor.textHighlight));
		panel.setBounds(10, 227, 442, 43);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton button = new JButton("Додати");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addnewdrow();
			}
		});
		button.setBounds(10, 11, 98, 23);
		panel.add(button);
		
		JButton button_1 = new JButton("Змінити");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				changeselectedrow();
			}
		});
		button_1.setBounds(118, 11, 98, 23);
		panel.add(button_1);
		
		JButton button_2 = new JButton("Видалити");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				deleteselectedrow();
			}
		});
		button_2.setBounds(235, 11, 98, 23);
		panel.add(button_2);
		
		JButton button_3 = new JButton("Вийти");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tablerefreshStart(tab,comboBox);
				
				dispose();				
			}
		});
		button_3.setBounds(343, 11, 89, 23);
		panel.add(button_3);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent arg0) {
				if (table.getRowCount()<1) {
					button_1.setEnabled(false);
					button_2.setEnabled(false);
				}
				else {
					button_1.setEnabled(true);
					button_2.setEnabled(true);
				}
			}
			@Override
			public void windowClosing(WindowEvent e) {
				tablerefreshStart(tab,comboBox);
			}
		});
	}
	
	void addnewdrow(){
		ChangeSpisKom csk = new ChangeSpisKom(table,table.getSelectedRow(),true);		
		csk.setVisible(true);
	}

	
	void changeselectedrow(){
		ChangeSpisKom csk = new ChangeSpisKom(table,table.getSelectedRow(),false);		
		csk.setVisible(true);
	}
	
	void deleteselectedrow(){
		int dr = JOptionPane.showConfirmDialog(null, "Дійсно видалити запис?", "Попередження.", JOptionPane.YES_NO_OPTION);
		int s=table.getSelectedRow();
		if (dr== JOptionPane.YES_OPTION && s!=-1){
			QueryKomisiyaTable.DeleteData((Integer)table.getModel().getValueAt(table.getSelectedRow(), 4));
			tablerefresh(s);
		}
		
	}
	
	void tablerefresh(int s){
		table.setModel(QueryKomisiyaTable.selectKomisiyaTableChange());		
		table.getColumnModel().getColumn(0).setPreferredWidth(8);
		table.getColumnModel().getColumn(1).setPreferredWidth(106);
		table.getColumnModel().getColumn(2).setPreferredWidth(106);
		table.getColumnModel().getColumn(3).setPreferredWidth(106);
		table.removeColumn(table.getColumnModel().getColumn(4));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
	    table.setRowSorter(sorter);
	    if (s!=0) {
	    	table.setRowSelectionInterval(s-1, s-1);
		}
	    else{
	    	if (table.getRowCount()>1) {
	    		table.setRowSelectionInterval(s+1,s+1);
			}
	    	
	    }
	   
	    
	}

	void tablerefreshStart(JTable table,JComboBox<String> combobox){
	    table.getTableHeader().setReorderingAllowed(false);
		table.setFont(new Font("Arial", Font.BOLD, 12));
		table.setModel(QueryKomisiyaTable.selectKomisiyaTable());		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);		
		table.getColumnModel().getColumn(0).setPreferredWidth(8);
		table.getColumnModel().getColumn(1).setPreferredWidth(106);
		table.getColumnModel().getColumn(2).setPreferredWidth(106);
		table.getColumnModel().getColumn(3).setPreferredWidth(106);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
	    table.setRowSorter(sorter);
	    combobox.removeAllItems();
	    String[]str=QueryKomisiyaTable.selectKomisiyaTableGol();
	    for (int i = 0; i < str.length; i++) {
			combobox.addItem(str[i]);
		}
	}
}
