package api.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtils {

	private static Workbook book;
	private static Sheet sheet;
	private static Object data[][];
	private static Object userNames[];
	static String filepath=".//testdata//restdata.xlsx";
	public static Object[][] getUserData(String sheetName) {
		
		try {
			FileInputStream fis=new FileInputStream(filepath);
			try {
				book=WorkbookFactory.create(fis);
				sheet=book.getSheet(sheetName);
			data=new Object [sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
			for(int i=0;i<sheet.getLastRowNum();i++) {
				for(int j=0;j<sheet.getRow(i).getLastCellNum();j++) {
					data[i][j]=sheet.getRow(i+1).getCell(j).toString();
				}
			}
			} catch (EncryptedDocumentException | IOException e) {
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return data;
	}
	public static Object[] getUserName(String sheetName) {
		
		try {
			FileInputStream fis=new FileInputStream(filepath);
			book=WorkbookFactory.create(fis);
			sheet=book.getSheet(sheetName);
			userNames=new Object [sheet.getLastRowNum()];
		for(int i=0;i<sheet.getLastRowNum();i++) {
			
				userNames[i]=sheet.getRow(i+1).getCell(1).toString();
			}
		
		} catch (Exception e) {
		}
		return userNames;
	}
}
