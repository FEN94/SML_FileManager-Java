package logic;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
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

}
