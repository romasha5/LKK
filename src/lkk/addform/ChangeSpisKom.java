package lkk.addform;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.border.MatteBorder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import lkk.dbaprovider.QueryKomisiyaTable;

public class ChangeSpisKom extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JCheckBox checkBox;
	/**
	 * Create the dialog.
	 * @param tableC 
	 * @param i 
	 * @param b 
	 * @param changekom 
	 */
	public ChangeSpisKom(JTable tableC, int i, boolean b) {
		setModal(true);
		setResizable(false);
		setAlwaysOnTop(true);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				dispose();
			}
		});
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 459, 213);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		contentPanel.setBounds(10, 11, 434, 116);
		contentPanel.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(255, 204, 51)));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		
		
		JLabel label = new JLabel("Прізвище:");
		label.setFont(new Font("Tahoma", Font.BOLD, 12));
		label.setBounds(10, 11, 70, 14);
		contentPanel.add(label);
		
		JLabel label_1 = new JLabel("Ім'я:");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_1.setBounds(10, 36, 46, 14);
		contentPanel.add(label_1);
		
		JLabel label_2 = new JLabel("По-батькові:");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_2.setBounds(10, 61, 86, 14);
		contentPanel.add(label_2);
		
		JLabel label_3 = new JLabel("Статус:");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_3.setBounds(10, 86, 46, 14);
		contentPanel.add(label_3);
		
		textField = new JTextField();
		textField.setBounds(111, 9, 313, 20);
		contentPanel.add(textField);
		textField.setColumns(10);		
		textField.selectAll();
		
		textField_1 = new JTextField();
		textField_1.setBounds(111, 34, 313, 20);
		contentPanel.add(textField_1);
		textField_1.setColumns(10);		
		textField_1.selectAll();
		
		textField_2 = new JTextField();
		textField_2.setBounds(111, 59, 313, 20);
		contentPanel.add(textField_2);
		textField_2.setColumns(10);
		textField_2.selectAll();
		
		checkBox = new JCheckBox("Голова комісії");
		checkBox.setFont(new Font("Tahoma", Font.ITALIC, 10));
		checkBox.setBounds(111, 83, 97, 23);
		contentPanel.add(checkBox);

		setaddorchange(tableC, i, b);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(204, 255, 102)));
			buttonPane.setBounds(10, 138, 434, 39);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						Boolean sel = checkBox.isSelected()?true:false;
						if (b) {							
							QueryKomisiyaTable.AddData(textField.getText(), textField_1.getText(), 
									textField_2.getText(), sel);										
						}
						else{
							QueryKomisiyaTable.UpdateData(textField.getText(), textField_1.getText(), 
									textField_2.getText(), sel, (Integer)tableC.getModel().getValueAt(i, 4));
						}
						tablerefresh(tableC,b,i);
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Відміна");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		

	}
	
	void setaddorchange(JTable tableC,int i,boolean b){
		if(!b){
				textField.setText((String)tableC.getModel().getValueAt(i, 1));
				textField_1.setText((String)tableC.getModel().getValueAt(i, 2));
				textField_2.setText((String)tableC.getModel().getValueAt(i, 3));
			
				if((Boolean)tableC.getValueAt(i, 0)==false){
					this.checkBox.setSelected(false);
				}
				else {
					this.checkBox.setSelected(true);
				}
		}
	}
	
	void tablerefresh(JTable tableC,Boolean b,int i){
		tableC.setModel(QueryKomisiyaTable.selectKomisiyaTableChange());		
		tableC.getColumnModel().getColumn(0).setPreferredWidth(8);
		tableC.getColumnModel().getColumn(1).setPreferredWidth(106);
		tableC.getColumnModel().getColumn(2).setPreferredWidth(106);
		tableC.getColumnModel().getColumn(3).setPreferredWidth(106);
		tableC.removeColumn(tableC.getColumnModel().getColumn(4));
		tableC.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tableC.getModel());
	    tableC.setRowSorter(sorter);
	    if (b) {
	    	tableC.setRowSelectionInterval(tableC.getRowCount()-1, tableC.getRowCount()-1);
		}
	    else {
	    	tableC.setRowSelectionInterval(i, i);
		}
	    
	}
}
