package lkk.addform;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import java.awt.Color;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import lkk.dbaprovider.QueryMainTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SelectPrivid extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;


	/**
	 * Create the dialog.
	 * @param textPane 
	 */
	public SelectPrivid(JTextPane textPane) {
		setAlwaysOnTop(true);
		setModal(true);
		setTitle("Привід для диспансерного обліку");
		setBounds(100, 100, 705, 374);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(204, 204, 255)));
			buttonPane.setBounds(10, 285, 669, 41);
			contentPanel.add(buttonPane);
			buttonPane.setLayout(null);
			{
				JButton okButton = new JButton("Вибір");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						textPane.setText((String) table.getModel().getValueAt(table.getSelectedRow(), 0));
						dispose();
					}
				});
				okButton.setBounds(487, 9, 83, 23);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Вихід");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setBounds(576, 9, 83, 23);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 669, 264);
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
					textPane.setText((String) table.getModel().getValueAt(table.getSelectedRow(), 0));
					dispose();
				}
			}
		});
        table.getTableHeader().setReorderingAllowed(false);
		table.setFont(new Font("Arial", Font.BOLD, 12));
		table.setModel(QueryMainTable.selectMainTablePrivid());		
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
	    table.setRowSorter(sorter);
	    if(table.getRowCount()>0){
	    table.setRowSelectionInterval(0, 0);
	   
	    }
		scrollPane.setViewportView(table);
	}
}
