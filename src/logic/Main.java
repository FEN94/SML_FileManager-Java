package logic;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ProductCode pc = new ProductCode("UG4E003", "PFL", true, true, 4);
		ArrayList<ProductCode> pcList = new ArrayList<ProductCode>();
		pcList.add(pc);
		MainController.createFolder(pcList, "GMC");
		
	}

}
