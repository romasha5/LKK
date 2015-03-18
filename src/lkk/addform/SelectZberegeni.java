package lkk.addform;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import lkk.dbaprovider.QueryMainTable;
import lkk.start.Start;

import javax.swing.border.MatteBorder;

import com.michaelbaranov.microba.calendar.DatePicker;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class SelectZberegeni extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;


	/**
	 * Create the dialog.
	 * @param datePickerZ 
	 * @param datepicker 
	 * @param textPane_2 
	 * @param textPane_1 
	 * @param textPane 
	 * @param textField_2 
	 * @param textField_1 
	 * @param textField 
	 * @param id 
	 */
	public SelectZberegeni(JTextField textField, JTextField textField_1, JTextField textField_2, 
			JTextPane textPane, JTextPane textPane_1, JTextPane textPane_2, 
			DatePicker datepicker, DatePicker datePickerZ) {
		setTitle("Збережені довідки");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 721, 523);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 695, 415);
		setLocationRelativeTo(null);
		contentPanel.add(scrollPane);
		
		table = new JTable(){
	           /**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
	            public boolean isCellEditable(int arg0, int arg1) {	                
	                	return false;
	            }	
		};
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2){
					changeData(textField, textField_1, textField_2, textPane, textPane_1, textPane_2, datepicker, datePickerZ);
				}
			}
		});
		table.getTableHeader().setReorderingAllowed(false);
		table.setFont(new Font("Arial", Font.BOLD, 12));
		table.setModel(QueryMainTable.selectALLMainTable());	
		table.removeColumn(table.getColumnModel().getColumn(8));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
	    table.setRowSorter(sorter);
	    if(table.getRowCount()>0){
	    table.setRowSelectionInterval(0, 0);
	    }
		
		scrollPane.setViewportView(table);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(153, 204, 0)));
			buttonPane.setBounds(10, 437, 695, 47);
			contentPanel.add(buttonPane);
			buttonPane.setLayout(null);
			{
				JButton cancelButton = new JButton("Вихід");
				cancelButton.setBounds(596, 11, 89, 23);
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
			
			JButton button = new JButton("Змінити");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
						changeData(textField, textField_1, textField_2, textPane, textPane_1, textPane_2, datepicker, datePickerZ);
				}
			});
			button.setBounds(10, 11, 89, 23);
			buttonPane.add(button);
			
			JButton button_1 = new JButton("Видалити");
			button_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int dr = JOptionPane.showConfirmDialog(null, "Дійсно видалити запис?", "Попередження.", JOptionPane.YES_NO_OPTION);
					int s=table.getSelectedRow();
					if (dr== JOptionPane.YES_OPTION && s!=-1){
						QueryMainTable.deleteMainTable((Integer)table.getModel().getValueAt(table.getSelectedRow(), 8));
						tablerefresh(s);
				}
				}
			});
			button_1.setBounds(109, 11, 106, 23);
			buttonPane.add(button_1);
			
			JButton button_2 = new JButton("Друк");
			button_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			button_2.setBounds(497, 11, 89, 23);
			buttonPane.add(button_2);
		}
	}
	
	void changeData(JTextField textField, JTextField textField_1, JTextField textField_2, 
			JTextPane textPane, JTextPane textPane_1, JTextPane textPane_2, 
			DatePicker datepicker, DatePicker datePickerZ){
		DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		try {
			datepicker.setDate(format.parse((String) table.getModel().getValueAt(table.getSelectedRow(), 4)));
			datePickerZ.setDate(format.parse((String) table.getModel().getValueAt(table.getSelectedRow(), 0)));
		} catch (PropertyVetoException e1) {
			e1.printStackTrace();
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		textField.setText((String) table.getModel().getValueAt(table.getSelectedRow(), 1));
		textField_1.setText((String) table.getModel().getValueAt(table.getSelectedRow(), 2));
		textField_2.setText((String) table.getModel().getValueAt(table.getSelectedRow(), 3));
		textPane.setText((String) table.getModel().getValueAt(table.getSelectedRow(), 5));
		textPane_1.setText((String) table.getModel().getValueAt(table.getSelectedRow(), 6));
		textPane_2.setText((String) table.getModel().getValueAt(table.getSelectedRow(), 7));
		Start.id = (Integer) table.getModel().getValueAt(table.getSelectedRow(), 8);
		dispose();
	}
	
	void tablerefresh(int s){
		
		table.getTableHeader().setReorderingAllowed(false);
		table.setFont(new Font("Arial", Font.BOLD, 12));
		table.setModel(QueryMainTable.selectALLMainTable());	
		table.removeColumn(table.getColumnModel().getColumn(8));
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
}
