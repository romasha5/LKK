package lkk.addform;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import java.awt.Color;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.michaelbaranov.microba.calendar.DatePicker;

import lkk.dbaprovider.QueryMainTable;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyVetoException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SelectHuman extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;

	/**
	 * Create the dialog.
	 * @param textField_2 
	 * @param textField_1 
	 * @param textField 
	 * @param datepicker 
	 * @param textPane 
	 * @param frm 
	 */
	public SelectHuman(JTextField textField, JTextField textField_1, JTextField textField_2, DatePicker datepicker, JTextPane textPane) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				
			}
		});
		setAlwaysOnTop(true);
		setModal(true);
		setBounds(100, 100, 697, 362);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(10, 269, 661, 44);
			contentPanel.add(buttonPane);
			buttonPane.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(153, 204, 204)));
			buttonPane.setLayout(null);
			{
				JButton okButton = new JButton("Вибрати");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
							selectrow(textField, textField_1, textField_2, datepicker, textPane);
						} 
				});
				okButton.setBounds(482, 11, 86, 23);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Вийти");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setBounds(572, 11, 79, 23);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 661, 247);
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
					selectrow(textField, textField_1, textField_2, datepicker, textPane);
				}
			}
		});
		
        table.getTableHeader().setReorderingAllowed(false);
		table.setFont(new Font("Arial", Font.BOLD, 12));
		table.setModel(QueryMainTable.selectMainTable());			
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(0).setPreferredWidth(106);
		table.getColumnModel().getColumn(1).setPreferredWidth(106);
		table.getColumnModel().getColumn(2).setPreferredWidth(106);
		table.getColumnModel().getColumn(3).setPreferredWidth(70);
		table.getColumnModel().getColumn(4).setPreferredWidth(270);	
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
	    table.setRowSorter(sorter);
	    if(table.getRowCount()>0){
	    table.setRowSelectionInterval(0, 0);
	    }
		scrollPane.setViewportView(table);
	}
	
	void selectrow(JTextField textField, JTextField textField_1, JTextField textField_2, DatePicker datepicker, JTextPane textPane){
		textField.setText((String)table.getModel().getValueAt(table.getSelectedRow(), 0));
		textField_1.setText((String)table.getModel().getValueAt(table.getSelectedRow(), 1));
		textField_2.setText((String)table.getModel().getValueAt(table.getSelectedRow(), 2));
			String string = (String)table.getModel().getValueAt(table.getSelectedRow(), 3);
			DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			Date date;
			try {
				date = format.parse(string);
				try {
					datepicker.setDate(date);
				} catch (PropertyVetoException e1) {
					e1.printStackTrace();
				}
			} catch (ParseException e1) {
				e1.printStackTrace();
			}

		textPane.setText((String)table.getModel().getValueAt(table.getSelectedRow(), 4));
		dispose();
	}
}
