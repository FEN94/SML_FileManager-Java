package logic;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class MainController {
	
	private static String searchPC(ProductCode productCode) {
		String path = "";
		String pc = productCode.getProductCode();
		
		if (productCode.getPrintingType().equalsIgnoreCase("Thermal"))
			path = "C:/NiceLabel/Thermal";
		else
			path = "C:/GMC/" + productCode.getPrintingType();
		
		path += "/" + productCode.getProgram();
		
		if (!productCode.getSubProgram().isBlank())
			path += "/" + productCode.getSubProgram();
		
		File file = new File(path);
		String[] folderList = file.list();
		
		if (Arrays.asList(folderList).contains(pc)) {
			if (!productCode.getPrintingType().equalsIgnoreCase("Thermal")) {
				path += "/" + pc + "/WFD";
				return path;
			}
			else
				return path + "/" + pc;
		}
		else
			return null;
		
	}
	
	public static void openProductCode(ProductCode productCode) {
		
		String path = MainController.searchPC(productCode);
		
		try {
			Desktop.getDesktop().open(new File(path));
		} catch (IOException e) {
			// TODO: handle exception
		}
	}
	
	public static void openGmcTool() {
		try {
			Desktop.getDesktop().open(new File("C:/GMC/GMC_2020.jar"));
		} catch (IOException e) {
			// TODO: handle exception
		}
	}
	
	private static void styleFolder(int number, String path) {
		for (int i = 1; i <= number; i++) {
			if (String.valueOf(i).length() == 1) {
				new File(path + "/00" + String.valueOf(i)).mkdirs();
			}
			else if (String.valueOf(i).length() == 2) {
				new File(path + "/0" + String.valueOf(i)).mkdirs();
			}
			else {
				new File(path + "/" + String.valueOf(i)).mkdirs();
			}
		}
	}
	
	public static void createFolder(ArrayList<ProductCode> pcList, String gmc_nl) {
		//Create folder for each Product Code
		for (ProductCode productCode : pcList) {
			String path = "C:/" + gmc_nl + "/" + productCode.getPrintingType() + "/" + productCode.getProgram();
			// Check if have sub-program
			if (!productCode.getSubProgram().isBlank()) 
				path += "/" + productCode.getSubProgram();
			path += "/" + productCode.getProductCode();
			if (gmc_nl == "GMC")
				path += "/WFD";
			// Make new Product Code folder
			File folder = new File(path);
			folder.mkdirs();
			// Create styles folders if more than one
			if (productCode.getStyles() > 1)
				MainController.styleFolder(productCode.getStyles(), folder.getPath());
			// Check if have images
			if (productCode.isImage()) {
				File logoFolder = new File(path + "/LOGO");
				logoFolder.mkdirs();
			}
		}
	}

}
