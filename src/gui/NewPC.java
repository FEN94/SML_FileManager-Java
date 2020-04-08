package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JSpinner;

public class NewPC extends JFrame {

	private JPanel contentPane;
	private JTable tableProductCode;
	private JTextField textFieldPC;
	private JCheckBox checkBoxLogo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewPC frame = new NewPC();
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
	public NewPC() {
		setResizable(false);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 867, 457);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelProductCode = new JPanel();
		panelProductCode.setBorder(new TitledBorder(null, "Product Code Table", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelProductCode.setBounds(321, 11, 520, 365);
		contentPane.add(panelProductCode);
		panelProductCode.setLayout(null);
		
		JScrollPane scrollPanePC = new JScrollPane();
		scrollPanePC.setBounds(10, 22, 500, 332);
		panelProductCode.add(scrollPanePC);
		
		tableProductCode = new JTable();
		scrollPanePC.setViewportView(tableProductCode);
		
		
		JButton btnClose = new JButton("Close");
		btnClose.setBounds(752, 387, 89, 23);
		contentPane.add(btnClose);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.setBounds(653, 387, 89, 23);
		contentPane.add(btnCreate);
		
		JLabel lblProductCode = new JLabel("ProductCode:");
		lblProductCode.setBounds(10, 60, 89, 14);
		contentPane.add(lblProductCode);
		
		textFieldPC = new JTextField();
		textFieldPC.setBounds(109, 57, 103, 20);
		contentPane.add(textFieldPC);
		textFieldPC.setColumns(10);
		
		JLabel lblPrintingType = new JLabel("Printing Type:");
		lblPrintingType.setBounds(10, 92, 89, 14);
		contentPane.add(lblPrintingType);
		
		String[] printingTypeList = new String[] {"<Select type>", "Arc_Thermal", "Digital", "Offset", "PFL", "Woven"};
		JComboBox comboBoxPrintingType = new JComboBox();
		comboBoxPrintingType.setModel(new DefaultComboBoxModel(printingTypeList));
		comboBoxPrintingType.setBounds(109, 88, 103, 22);
		contentPane.add(comboBoxPrintingType);
		
		JCheckBox checkBoxNiceLabel = new JCheckBox("NiceLabel");
		checkBoxNiceLabel.setBounds(218, 88, 97, 23);
		contentPane.add(checkBoxNiceLabel);
		checkBoxNiceLabel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (comboBoxPrintingType.isEnabled()) 
					comboBoxPrintingType.setEnabled(false);
				else
					comboBoxPrintingType.setEnabled(true);
			}
		});
		
		JCheckBox checkBoxSubProgram = new JCheckBox("Sub-Program");
		checkBoxSubProgram.setBounds(163, 117, 103, 23);
		contentPane.add(checkBoxSubProgram);
		
		checkBoxLogo = new JCheckBox("LOGO");
		checkBoxLogo.setBounds(163, 143, 97, 23);
		contentPane.add(checkBoxLogo);
		
		JLabel lblStyle = new JLabel("No. of styles");
		lblStyle.setBounds(10, 124, 89, 14);
		contentPane.add(lblStyle);
		
		JSpinner spinnerStyle = new JSpinner();
		spinnerStyle.setBounds(109, 121, 48, 20);
		spinnerStyle.setValue(1);
		contentPane.add(spinnerStyle);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(61, 173, 89, 23);
		contentPane.add(btnAdd);
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (textFieldPC.getText().isBlank()) {
					JOptionPane.showMessageDialog (null, "Enter Product Code", "Warning", JOptionPane.WARNING_MESSAGE);
				}
				else if (comboBoxPrintingType.getSelectedIndex() == 0 && !checkBoxNiceLabel.isSelected()) {
					JOptionPane.showMessageDialog (null, "Select a printing type", "Warning", JOptionPane.WARNING_MESSAGE);
				}
				else {
					createTable(checkBoxNiceLabel.isSelected());
					DefaultTableModel model = (DefaultTableModel)tableProductCode.getModel();
					String subProgram = "No";
					String logo = "No";
					String style = "1";
					if ((int)spinnerStyle.getValue() > 1) {
						style  = spinnerStyle.getValue().toString();
					}
					if (checkBoxSubProgram.isSelected()) {
						subProgram = "Yes";
					}
					if (checkBoxLogo.isSelected()) {
						logo = "Yes";
					}
					if (checkBoxNiceLabel.isSelected()) {
						model.addRow(new Object[]{textFieldPC.getText().strip(), "Thermal", style, subProgram});
					}
					else {
						model.addRow(new Object[]{textFieldPC.getText().strip(), printingTypeList[comboBoxPrintingType.getSelectedIndex()], style, subProgram, logo});
					}
				}
				
			}
		});
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.setBounds(160, 173, 89, 23);
		contentPane.add(btnRemove);
		btnRemove.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				DefaultTableModel model = (DefaultTableModel)tableProductCode.getModel();
				int row = tableProductCode.getSelectedRow();
				if (row >= 0) {
					model.removeRow(row);
				}
				else if (row < 0 && model.getRowCount() > 0) {
					model.removeRow(model.getRowCount()-1);
				}
			}
		});
		
	}
	
	private void createTable(boolean niceLabel) {
		if (this.tableProductCode.getRowCount() == 0) {
			if (niceLabel) {
				String[] columnName = {"Product Code", "Printing Type", "Style", "Sub-Program"};
				DefaultTableModel model = new DefaultTableModel(columnName, 0);
				this.tableProductCode.setModel(model);
			}
			else {
				String[] columnName = {"Product Code", "Printing Type", "Style", "Sub-Program", "LOGO"};
				DefaultTableModel model = new DefaultTableModel(columnName, 0);
				this.tableProductCode.setModel(model);
			}
		}
	}
}
