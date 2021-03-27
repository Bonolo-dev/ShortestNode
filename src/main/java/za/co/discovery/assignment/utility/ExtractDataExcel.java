/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.discovery.assignment.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

/**
 *
 * @author omphilebonolomonale
 */
@Component
public class ExtractDataExcel extends ExtractData{
    
    private final String EXCEL_FILE_PATH="raw_data.xlsx";
    
    private FileInputStream file;
    private Workbook workbook;
    

    public ExtractDataExcel() throws FileNotFoundException, IOException {
        
        this.file = new FileInputStream(new File(EXCEL_FILE_PATH));
        this.workbook = new XSSFWorkbook(file);
    }

    @Override
    public Map<Integer, List<String>> ExtractData() {
    
        Sheet sheet = workbook.getSheetAt(0);
        
        Map<Integer, List<String>> data = new HashMap<>();

        int i = 0;

        for (Row row : sheet) {
            data.put(i, new ArrayList<String>());
            for (Cell cell : row) {
                switch (cell.getCellTypeEnum()) {        
                    case STRING :
                        data.get(new Integer(i)).add(cell.getRichStringCellValue().getString());
                        break;        
                        
                    case NUMERIC:
                        //We need to convert all number to string to fit them in a list later
                        //data.get(i).add(String.valueOf(cell.getNumericCellValue()) + "");
                        data.get(i).add(cell.getNumericCellValue() + "");
                        break;
                    default: data.get(new Integer(i)).add(" ");    
                }
            }
            i++;
        }
        return data;
    }
    
}
