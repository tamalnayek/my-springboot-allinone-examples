package com.sample.spring.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sample.spring.excel.jpa.CustomerRepository;
import com.sample.spring.excel.model.Customer;



@SpringBootApplication
public class MySpringbootExcelApplication {
	

	@Autowired
	CustomerRepository customerRepository;

	public static void main(String[] args) {
		SpringApplication.run(MySpringbootExcelApplication.class, args);
		
		//writeXLSFile();
		
	}

	private void readXLSFile(String fileLocation) throws Exception{
		
		FileInputStream file = new FileInputStream(new File(fileLocation));
		Workbook workbook = new XSSFWorkbook(file);
		Sheet sheet = workbook.getSheetAt(0);
		 
		Map<Integer, List<String>> data = new HashMap<>();
		int i = 0;
		for (Row row : sheet) {
		    data.put(i, new ArrayList<String>());
		    for (Cell cell : row) {
		    	/*
		        switch (cell.getCellTypeEnum()) {
		            case STRING: ... break;
		            case NUMERIC: ... break;
		            case BOOLEAN: ... break;
		            case FORMULA: ... break;
		            default: data.get(new Integer(i)).add(" ");
		        }
		        */

		    	cell.getRichStringCellValue().toString();
		    	if (DateUtil.isCellDateFormatted(cell)) {
		    	    data.get(i).add(cell.getDateCellValue() + "");
		    	} else {
		    	    data.get(i).add(cell.getNumericCellValue() + "");
		    	}
		    	data.get(i).add(cell.getBooleanCellValue() + "");
		    	data.get(i).add(cell.getCellFormula() + "");
		    	
		    }
		    i++;
		}
			
	}

	/*
	 * Write xlsx file
	 */
	static void writeXLSFile() {
		//Create xls sheet
		Workbook workbook = new XSSFWorkbook();		 
		Sheet sheet = workbook.createSheet("Persons");
		//Set sheet default size.
		sheet.setColumnWidth(0, 6000);
		sheet.setColumnWidth(1, 4000);
		
		//Create custom header.
		Row header = sheet.createRow(0);
		
		//set header style.
		CellStyle headerStyle = workbook.createCellStyle();
		headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
		headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		//Set font of header.
		XSSFFont font = ((XSSFWorkbook) workbook).createFont();
		font.setFontName("Arial");
		font.setFontHeightInPoints((short) 16);
		font.setBold(true);
		headerStyle.setFont(font);
		
		//Set 1st header name.
		Cell headerCell = header.createCell(0);
		headerCell.setCellValue("Name");
		headerCell.setCellStyle(headerStyle);
		//Set 2nd header name.
		headerCell = header.createCell(1);
		headerCell.setCellValue("Age");
		headerCell.setCellStyle(headerStyle);
		//Set cell style.
		CellStyle style = workbook.createCellStyle();
		style.setWrapText(true);
		
		Row row = sheet.createRow(1);
		//Set 1st row 1 column value.
		Cell cell = row.createCell(0);
		cell.setCellValue("Tamal Nayek");
		cell.setCellStyle(style);
		//Set 1st row 2nd column value. 
		cell = row.createCell(1);
		cell.setCellValue(30);
		cell.setCellStyle(style);
		
		File currDir = new File(".");
		String path = currDir.getAbsolutePath();
		System.out.println("**Path ="+path);
		String fileLocation = path.substring(0, path.length() - 1) + "temp.xlsx";
		 
		try {
			FileOutputStream outputStream = new FileOutputStream(fileLocation);
			workbook.write(outputStream);
			workbook.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	/*
	public void run(String... args) throws Exception {
		List<Customer> customers = Arrays.asList(
				new Customer(Long.valueOf(1), "Jack Smith", "Massachusetts", 23),
				new Customer(Long.valueOf(2), "Adam Johnson", "New York", 27),
				new Customer(Long.valueOf(3), "Katherin Carter", "Washington DC", 26),
				new Customer(Long.valueOf(4), "Jack London", "Nevada", 33),
				new Customer(Long.valueOf(5), "Jason Bourne", "California", 36));

		// save a list of Customers
		customerRepository.saveAll(customers);
	}
	*/
}
