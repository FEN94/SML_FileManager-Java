package logic;

public class ProductCode {
	
	private String productCode, printingType, program, subProgram;
	private int styles;
	private boolean image;
	
	public ProductCode() {
		
		this.productCode = "";
		this.printingType = "";
		this.program = "";
		this.subProgram = "";
		this.image = false;
		this.styles = 1;
	}
	
	public ProductCode(String productCode, String printingType, boolean subProgram, boolean image, int styles) {
		this.productCode = productCode;
		this.printingType = printingType;
		this.program = productCode.substring(0, 2);
		if (subProgram)
			this.subProgram = productCode.substring(2, 4);
		else
			this.subProgram = "";
		this.image = image;
		this.styles = styles;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getPrintingType() {
		return printingType;
	}

	public void setPrintingType(String printingType) {
		this.printingType = printingType;
	}

	public String getProgram() {
		return program;
	}

	public void setProgram(String program) {
		this.program = program;
	}

	public String getSubProgram() {
		return subProgram;
	}

	public void setSubProgram(String subProgram) {
		this.subProgram = subProgram;
	}

	public boolean isImage() {
		return image;
	}

	public void setImage(boolean image) {
		this.image = image;
	}

	public int getStyles() {
		return styles;
	}

	public void setStyles(int styles) {
		this.styles = styles;
	}

}
