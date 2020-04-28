package testdata;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadData {

	static FileInputStream fin;
	static XSSFWorkbook wb;
	static XSSFSheet sheet;
	
	public ReadData(String filepath, String sheetname) throws IOException
	{
		File file = new File(filepath);	
		fin = new FileInputStream(file);	
		wb = new XSSFWorkbook(fin);
		sheet = wb.getSheet(sheetname);
	}
	
	public String[][] getData() throws IOException{

			int rows = sheet.getLastRowNum();
			int cols = sheet.getRow(0).getLastCellNum()-1;
			
			String data[][]=new String[rows][cols+1];
			System.out.println(data.length);
			System.out.println(data[0].length);
			
			for(int i=1;i<=rows;i++)
			{
				for(int j=0;j<=cols;j++)
				{
					data[i-1][j]=sheet.getRow(i).getCell(j).toString();
				}
			}
			
			wb.close();
			fin.close();
			return data;
		}

}
