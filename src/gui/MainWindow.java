package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import logic.MainController;
import logic.ProductCode;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

public class MainWindow extends JFrame {

	private JPanel contentPane;
	private JTextField textField_productCode;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
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
	public MainWindow() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 347, 251);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Find Product Code", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 314, 196);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lbl_productCode = new JLabel("Product Code:");
		lbl_productCode.setBounds(22, 38, 89, 14);
		panel.add(lbl_productCode);
		
		textField_productCode = new JTextField();
		textField_productCode.setBounds(121, 35, 181, 20);
		panel.add(textField_productCode);
		textField_productCode.setColumns(10);
		
		JRadioButton rdbtn_gmc = new JRadioButton("GMC");
		rdbtn_gmc.setBounds(22, 59, 67, 23);
		rdbtn_gmc.setSelected(true);
		panel.add(rdbtn_gmc);
		
		JRadioButton rdbtn_niceLabel = new JRadioButton("NiceLabel");
		rdbtn_niceLabel.setBounds(22, 85, 89, 23);
		panel.add(rdbtn_niceLabel);
		
		ButtonGroup rdbtnGroup = new ButtonGroup();
		rdbtnGroup.add(rdbtn_gmc);
		rdbtnGroup.add(rdbtn_niceLabel);
		
		JLabel lbl_printingType = new JLabel("Printing Type:");
		lbl_printingType.setBounds(94, 63, 83, 14);
		panel.add(lbl_printingType);
		
		JComboBox comboBox_printingType = new JComboBox();
		String[] printingTypeList = new String[] {"<Select type>", "Arc_Thermal", "Digital", "Offset", "PFL", "Woven"};
		comboBox_printingType.setModel(new DefaultComboBoxModel(printingTypeList));
		comboBox_printingType.setBounds(187, 59, 115, 22);
		panel.add(comboBox_printingType);
		
		JCheckBox checkBox_subProgram = new JCheckBox("Sub-Program");
		checkBox_subProgram.setBounds(187, 85, 115, 23);
		panel.add(checkBox_subProgram);
		
		JButton btn_search = new JButton("Search");
		btn_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox_printingType.getSelectedIndex() == 0 && rdbtn_gmc.isSelected()) {
					JOptionPane.showMessageDialog (null, "Select a printing type", "Warning", JOptionPane.WARNING_MESSAGE);
				}
				else {
					String printingType = "";
					boolean subProgram = checkBox_subProgram.isSelected();
					if (rdbtn_niceLabel.isSelected())
						printingType = "Thermal";
					else {
						printingType = printingTypeList[comboBox_printingType.getSelectedIndex()];
					}
					ProductCode productCode = new ProductCode(
							textField_productCode.getText(),
							printingType,
							subProgram,
							1);
					try {
						MainController.openProductCode(productCode);
					} catch (NullPointerException e2) {
						// TODO: handle exception
						JOptionPane.showMessageDialog (null, "Product Code not found.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btn_search.setBounds(213, 162, 89, 23);
		panel.add(btn_search);
		
		//////////////////////////////////////////////////////////////////////////////////////////////////
		
		rdbtn_niceLabel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				comboBox_printingType.setEnabled(false);
				
			}
		});
		
		rdbtn_gmc.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				comboBox_printingType.setEnabled(true);
			}
		});
	}
}
