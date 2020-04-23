package logic;

import java.io.File;  
import java.io.FileInputStream;  
import java.io.IOException;  
import org.apache.poi.hssf.usermodel.HSSFSheet;  
import org.apache.poi.hssf.usermodel.HSSFWorkbook;  
import org.apache.poi.ss.usermodel.Cell;  
import org.apache.poi.ss.usermodel.FormulaEvaluator;  
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFileChooser;

public class Test {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		for (int i = 1; i <= 4; i++) {
			System.out.println(Test.readCell(i, 0) + "\n");
		}
		
		//JFileChooser fc = new JFileChooser();
		//int selection = fc.showOpenDialog(fc);
		
		
	}
	
	private static String readCell(int iRow, int iColumn) throws IOException {
		String value = null;
		Workbook wb = null;
		
		FileInputStream fis = new FileInputStream("C:/GMC/Pc_list.xlsx");
		wb = new XSSFWorkbook(fis);
		Sheet sheet = wb.getSheetAt(0);
		Row row = sheet.getRow(iRow);
		Cell cell = row.getCell(iColumn);
		value = cell.getStringCellValue();
		//value = String.valueOf((int)cell.getNumericCellValue());
		return value;
	}

}
