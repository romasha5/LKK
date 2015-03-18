package lkk.start;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.border.MatteBorder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import java.awt.SystemColor;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;

import com.michaelbaranov.microba.calendar.DatePicker;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import lkk.addform.Changekom;
import lkk.addform.SelectHuman;
import lkk.addform.SelectPrivid;
import lkk.addform.SelectZaklkom;
import lkk.addform.SelectZberegeni;
import lkk.dbaprovider.ConnectDBA;
import lkk.dbaprovider.QueryKomisiyaTable;
import lkk.dbaprovider.QueryMainTable;
import lkk.report.Druk;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import java.awt.Component;
import java.beans.PropertyVetoException;

public class Start extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable table;
	final DatePicker  datepicker;
	final DatePicker  datePickerZ;
	JTextPane textPane;
	JTextPane textPane_1;
	JTextPane textPane_2;
	public Boolean flag=true;
	public static Integer id=0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConnectDBA cdba = new ConnectDBA();
					cdba.CDBA();
					
					Start frame = new Start();
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
	public Start() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				JOptionPane.showMessageDialog(null, "До побачення.");
			}
		});
		setTitle("Довідки ЛКК");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 712, 710);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setResizable(false);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setToolTipText("Дані про пацієнта");
		panel.setBounds(10, 36, 686, 185);
		panel.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 255)));
		contentPane.add(panel);
		panel.setLayout(null);			
		
		JLabel label = new JLabel("Прізвище:");
		label.setFont(new Font("Tahoma", Font.BOLD, 14));
		label.setBounds(10, 8, 80, 20);
		panel.add(label);
		
		JLabel label_1 = new JLabel("Ім'я:");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_1.setBounds(10, 35, 42, 19);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("По-батькові:");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_2.setBounds(10, 61, 92, 19);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("Дата народження:");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_3.setBounds(530, 12, 123, 14);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("Адреса:");
		label_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_4.setBounds(10, 83, 58, 20);
		panel.add(label_4);
		
		
		datepicker = new DatePicker(new Date(),new SimpleDateFormat("dd.MM.yyyy"));
		datepicker.setFont(new Font("Dialog", Font.BOLD, 12));
		datepicker.setShowNoneButton(false);
		datepicker.setToolTipText("Дата народження пацієнта");
		datepicker.setBounds(535, 35, 112, 27);
		datepicker.getComponent(0).setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(datepicker);
		
		datePickerZ = new DatePicker(new Date(),new SimpleDateFormat("dd.MM.yyyy"));
		datePickerZ.setBounds(342, 5, 110, 27);		
		contentPane.add(datePickerZ);
		datePickerZ.setFont(new Font("Dialog", Font.BOLD, 16));
		datePickerZ.setShowNoneButton(false);
		datePickerZ.setToolTipText("Дата заповнення довідки");
		datePickerZ.getComponent(0).setFont(new Font("Tahoma", Font.BOLD, 14));
		
		textField = new JTextField();
		textField.setToolTipText("Прізвище пацієнта");
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setBounds(87, 10, 401, 20);
		textField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
	         if ((e.getKeyCode() == KeyEvent.VK_ENTER) || (e.getKeyCode() == KeyEvent.VK_TAB)) {
	        	 textField_1.requestFocus();
	         }
			}
		});
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setToolTipText("Ім'я пацієнта");
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_1.setBounds(49, 35, 371, 20);
		textField_1.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
	         if ((e.getKeyCode() == KeyEvent.VK_ENTER) || (e.getKeyCode() == KeyEvent.VK_TAB)) {
	        	 textField_2.requestFocus();
	         }
			}
		});
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setToolTipText("По-батькові пацієнта");
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_2.setBounds(105, 60, 383, 20);
		textField_2.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
	         if ((e.getKeyCode() == KeyEvent.VK_ENTER) || (e.getKeyCode() == KeyEvent.VK_TAB)) {
	        	 datepicker.requestFocus();
	         }
			}
		});
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		
		JButton button = new JButton("Вибір");
		button.setBounds(422, 34, 66, 23);		
		panel.add(button);
		
		

		
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(78, 91, 598, 83);
		panel.add(scrollPane_1);
		
		textPane = new JTextPane();
		textPane.setToolTipText("Адреса проживання пацієнта");
		scrollPane_1.setViewportView(textPane);
		textPane.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		textPane.setBackground(SystemColor.info);
		textPane.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{textField, textField_1, textField_2, datepicker, textPane, scrollPane_1, button, label_3, label_4, label_2, label_1, label}));
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setToolTipText("Медичні дані");
		panel_1.setBounds(10, 225, 686, 261);
		panel_1.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(34, 139, 34)));
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Привід для диспансерного обліку:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 0, 274, 25);
		panel_1.add(lblNewLabel);
		
		JLabel label_5 = new JLabel("Заключення комісії:");
		label_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_5.setBounds(10, 120, 166, 25);
		panel_1.add(label_5);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 23, 666, 99);
		panel_1.add(scrollPane_2);
		
		textPane_1 = new JTextPane();
		textPane_1.setToolTipText("Дані про діагноз пацієнта");
		scrollPane_2.setViewportView(textPane_1);
		textPane_1.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		textPane_1.setBackground(SystemColor.info);
		textPane_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(10, 143, 666, 107);
		panel_1.add(scrollPane_3);
		
		textPane_2 = new JTextPane();
		textPane_2.setToolTipText("Висновок комісії відносно стану пацієнта");
		scrollPane_3.setViewportView(textPane_2);
		textPane_2.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		textPane_2.setBackground(SystemColor.info);
		textPane_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		datepicker.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
	         if ((e.getKeyCode() == KeyEvent.VK_ENTER) || (e.getKeyCode() == KeyEvent.VK_TAB)) {
	        	 textPane.requestFocus();
	         }
			}
		});
		
		JButton button_8 = new JButton("Вибір");
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SelectPrivid sprivid = new SelectPrivid(textPane_1);
				sprivid.setVisible(true);
			}
		});
		button_8.setBounds(586, 4, 89, 17);
		panel_1.add(button_8);
		
		JButton button_9 = new JButton("Вибір");
		button_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelectZaklkom zalkom = new SelectZaklkom(textPane_2);
				zalkom.setVisible(true);
			}
		});
		button_9.setBounds(586, 123, 89, 17);
		panel_1.add(button_9);
		
		JPanel panel_2 = new JPanel();
		panel_2.setToolTipText("Формування підписантів");
		panel_2.setBounds(10, 490, 465, 184);
		panel_2.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(138, 43, 226)));
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel label_6 = new JLabel("Голова комісії:");
		label_6.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_6.setBounds(10, 9, 106, 24);
		panel_2.add(label_6);
		
		JLabel label_7 = new JLabel("Члени комісії:");
		label_7.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_7.setBounds(10, 36, 106, 24);
		panel_2.add(label_7);
		
		JComboBox<String> comboBox = new JComboBox<>(QueryKomisiyaTable.selectKomisiyaTableGol());		
		//JComboBox comboBox = new JComboBox();
		comboBox.setToolTipText("Вибір голови комісії");
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBox.setBounds(126, 11, 329, 29);
		panel_2.add(comboBox);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(126, 42, 329, 131);
		panel_2.add(scrollPane);
		
		table = new JTable(){
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public boolean isCellEditable(int arg0, int arg1) {
                if(arg1!=0){	
                	return false;
                }
                else {
					return true;
				}
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
		table.setModel(QueryKomisiyaTable.selectKomisiyaTable());		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);		
		table.getColumnModel().getColumn(0).setPreferredWidth(8);
		table.getColumnModel().getColumn(1).setPreferredWidth(106);
		table.getColumnModel().getColumn(2).setPreferredWidth(106);
		table.getColumnModel().getColumn(3).setPreferredWidth(106);
		table.removeColumn(table.getColumnModel().getColumn(4));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
	    table.setRowSorter(sorter);
		scrollPane.setViewportView(table);
		
		
		JButton button_1 = new JButton("Змінити");
		button_1.setToolTipText("Додати або змінити членів комісії та голову");
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		button_1.setBounds(10, 62, 106, 23);
		panel_2.add(button_1);
		button_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				selchlen();				
				Changekom chengekom = new Changekom(table,comboBox);
				chengekom.setVisible(true);
				
			}
		});
		
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(244, 164, 96)));
		panel_3.setBounds(479, 490, 217, 184);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		
		JButton button_2 = new JButton("Зберегти");
		button_2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(textField.getText().trim().length()>0){
					if (flag) {
						addMainTable();
					}
					else {
						JOptionPane.showMessageDialog(null, id);
						DateFormat dateformat = new SimpleDateFormat("YYYY-MM-dd");
						String dateborn=dateformat.format(datepicker.getDate());
						String datezapovn=dateformat.format(datePickerZ.getDate());
						QueryMainTable.updateMainTable(textField.getText(), textField_1.getText(), 
								textField_2.getText(), dateborn, textPane.getText(), textPane_1.getText(), textPane_2.getText(), datezapovn, id);
					}					
				}
				else {
					int dr = JOptionPane.showConfirmDialog(null, "Ви дійсно бажаєте додати не заповнену довідку?", 
							"Додавання пустої довідки.", JOptionPane.YES_NO_OPTION);
					if (dr==JOptionPane.YES_OPTION) {
						addMainTable();
					}
				}
			}
		});
		button_2.setToolTipText("Зберегти щойно набрану довідку");
		button_2.setFont(new Font("Dialog", Font.PLAIN, 11));
		button_2.setBounds(10, 11, 92, 23);
		panel_3.add(button_2);
		
		JButton button_3 = new JButton("Друкувати");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
						Druk.repo();
			}
		});
		button_3.setToolTipText("Друкувати щойно набрану довідку");
		button_3.setFont(new Font("Dialog", Font.PLAIN, 11));
		button_3.setBounds(107, 11, 100, 23);
		panel_3.add(button_3);
		
		


		JButton button_4 = new JButton("Продивитись збережені");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
					flag=false;
					SelectZberegeni zb = new SelectZberegeni(textField,textField_1,textField_2,textPane,textPane_1,textPane_2,datepicker,datePickerZ);
					zb.setVisible(true);
			}
		});
		button_4.setToolTipText("Продивитись перелік збережених довідок");
		button_4.setFont(new Font("Dialog", Font.PLAIN, 11));
		button_4.setBounds(10, 35, 197, 23);
		panel_3.add(button_4);
		
		JButton button_5 = new JButton("Відкрити теку із збереженими довідками");
		button_5.setToolTipText("Відкрити теку із збереженими довідками у форматі *.xls (Excell)");
		button_5.setFont(new Font("Dialog", Font.PLAIN, 11));
		button_5.setBounds(10, 61, 197, 40);
		panel_3.add(button_5);
		
		JButton button_6 = new JButton("Налаштування");
		button_6.setToolTipText("Деякі налаштування \"Довідки ЛКК\"");
		button_6.setFont(new Font("Dialog", Font.PLAIN, 11));
		button_6.setBounds(10, 105, 197, 23);
		panel_3.add(button_6);
		
		JButton button_7 = new JButton("Вихід");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "До побачення.");
				System.exit(0);
			}
		});
		button_7.setToolTipText("Вихід із програми без збереження");
		button_7.setFont(new Font("Dialog", Font.PLAIN, 11));
		button_7.setBounds(10, 150, 197, 23);
		panel_3.add(button_7);
		
		JLabel lblNewLabel_1 = new JLabel("Дата заповнення:");
		lblNewLabel_1.setBounds(191, 11, 152, 14);
		contentPane.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JButton button_10 = new JButton("Очистити");
		button_10.setBounds(10, 5, 124, 25);
		button_10.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				textField.setText(null);
				textField_1.setText(null);
				textField_2.setText(null);
				textPane.setText(null);
				textPane_1.setText(null);
				textPane_2.setText(null);
				try {
					datepicker.setDate(new Date());
					datePickerZ.setDate(new Date());
				} catch (PropertyVetoException e1) {
					e1.printStackTrace();
				}
			}
		});
		contentPane.add(button_10);
		
		
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SelectHuman sh = new SelectHuman(textField,textField_1,textField_2,datepicker,textPane);
				sh.setVisible(true);
			}
		});
		
		JOptionPane.showMessageDialog(null, flag);
	}
	
	void selchlen(){				
		for (int i = 0; i < table.getModel().getRowCount() ; i++) {				
			QueryKomisiyaTable.UpdateData((Boolean) table.getModel().getValueAt(i, 0),(Integer)table.getModel().getValueAt(i, 4));	
		}
	}
	
	void addMainTable(){
		DateFormat dateformat = new SimpleDateFormat("YYYY-MM-dd");
		String dateborn=dateformat.format(datepicker.getDate());
		String datezapovn=dateformat.format(datePickerZ.getDate());

		
		QueryMainTable.insertMainTable(textField.getText(), textField_1.getText(), textField_2.getText(), 
				dateborn, textPane.getText(), textPane_1.getText(), textPane_2.getText(), datezapovn);
		JOptionPane.showMessageDialog(null, "Довідку додано в БД.");
	}
}
