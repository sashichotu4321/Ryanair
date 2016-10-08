package testData;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Sashi on 07-10-2016.
 */
public class GetData {
    public static final String Path_TestData = System.getProperty("user.dir") + "\\src\\testData\\";
    public static final String File_TestData = "TestData.xlsx";
    public static DataFormatter df = new DataFormatter();

    public  static ArrayList<String> getCompleteRowData(String sheetName, String colName) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(Path_TestData + File_TestData));
        XSSFSheet sheet = workbook.getSheet(sheetName);
        ArrayList<String> mycollection = new ArrayList<>();

            Row currentRow = sheet.getRow(0);
            for (int j = 0; j < currentRow.getPhysicalNumberOfCells(); j++) {
                Cell currentCell = currentRow.getCell(j);
                if (df.formatCellValue(currentCell).equals(colName)) {
                    for(int i=1;i<sheet.getPhysicalNumberOfRows();i++) {
                        Row NewcurrentRow = sheet.getRow(i);
                        String strValue = String.valueOf(df.formatCellValue(NewcurrentRow.getCell(j)));
                        mycollection.add(strValue);
                }
            }
        }
        return mycollection;
    }

    public static String getCellVal(String sheetName,String colName) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(Path_TestData + File_TestData));
        XSSFSheet sheet = workbook.getSheet(sheetName);

        for(int i=0;i<sheet.getPhysicalNumberOfRows();i++) {
            Row currentRow = sheet.getRow(i);
            for (int j = 0; j < currentRow.getPhysicalNumberOfCells(); j++) {
                Cell currentCell = currentRow.getCell(j);
                if (df.formatCellValue(currentCell).equals(colName)) {
                    String strValue = null;
                    strValue = String.valueOf(df.formatCellValue(sheet.getRow(i + 1).getCell(j)));
                    return strValue;
                }

            }
        }
        return "No Data";
    }
}
