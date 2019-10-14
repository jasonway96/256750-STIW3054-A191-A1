package WebScaper;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Compare
{
    public static int columnNumForFirst = 2;
    public static int columnNumForSecond = 0;

    public static void main() throws IOException
    {

        try
        {

            ArrayList arr1 = new ArrayList();
            ArrayList arr2 = new ArrayList();
            ArrayList arr3 = new ArrayList();

            FileInputStream file1 = new FileInputStream
                    (new File("C:\\Assignment1\\Namelist.xls"));

            FileInputStream file2 = new FileInputStream(new File(
                    "C:\\Assignment1\\Namelink.xls"));

            HSSFWorkbook workbook1 = new HSSFWorkbook(file1);
            HSSFWorkbook workbook2 = new HSSFWorkbook(file2);

            HSSFSheet sheet1 = workbook1.getSheetAt(0);
            HSSFSheet sheet2 = workbook2.getSheetAt(0);


            Iterator<Row> rowIterator1 = sheet1.iterator();
            Iterator<Row> rowIterator2 = sheet2.iterator();

            while (rowIterator1.hasNext())
            {
                Row row = rowIterator1.next();
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext())
                {

                    Cell cell = cellIterator.next();
                    if (cell.getColumnIndex() == columnNumForFirst)
                    {
                        switch (cell.getCellType())
                        {
                            case NUMERIC:
                                System.out.print(cell.getNumericCellValue());
                                arr1.add(cell.getNumericCellValue());
                                break;
                            case STRING:
                                arr1.add(cell.getStringCellValue());
                                System.out.print(cell.getStringCellValue());
                                break;
                            case BOOLEAN:
                                arr1.add(cell.getBooleanCellValue());
                                System.out.print(cell.getBooleanCellValue());
                                break;
                        }

                    }

                }

                System.out.println(" ");
            }

            file1.close();

            System.out.println("\n-----------------------------------");

            while (rowIterator2.hasNext())
            {
                Row row1 = rowIterator2.next();
                Iterator<Cell> cellIterator1 = row1.cellIterator();

                while (cellIterator1.hasNext())
                {

                    Cell cell1 = cellIterator1.next();
                    if (cell1.getColumnIndex() == columnNumForSecond)
                    {
                        switch (cell1.getCellType())
                        {
                            case NUMERIC:
                                arr2.add(cell1.getNumericCellValue());
                                System.out.print(cell1.getNumericCellValue());
                                break;
                            case STRING:
                                arr2.add(cell1.getStringCellValue());
                                System.out.print(cell1.getStringCellValue());
                                break;
                            case BOOLEAN:
                                arr2.add(cell1.getBooleanCellValue());
                                System.out.print(cell1.getBooleanCellValue());
                                break;

                        }

                    }

                }

                System.out.println("");
            }

            System.out.println("\nNameList.xls -- " + arr1.size());
            System.out.println("NameLink.xls -- " + arr2.size());

            for (Object process : arr1)
            {
                if (!arr2.contains(process))
                {
                    arr3.add(process);
                }
            }

            System.out.println("\nMatric Number which does not appear in the namelist : " + arr3);
            writeResultDataToExcel(arr3);

            file1.close();
            file2.close();

        }

        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        catch (IOException e)
        {
            e.printStackTrace();
        }



    }

    private static void writeResultDataToExcel(ArrayList arr3)
    {

        FileOutputStream resultExcel = null;
        try
        {
            resultExcel = new FileOutputStream(
                    "C:\\Assignment1\\Test.xls");

            HSSFWorkbook workBook = new HSSFWorkbook();
            HSSFSheet spreadSheet = workBook.createSheet("New");
            HSSFRow row;
            HSSFCell cell;

            int cellnumber = 2;
            for (int i1 = 1; i1 < arr3.size(); i1++) {
                row = spreadSheet.createRow(i1);
                cell = row.createCell(cellnumber);
                cell.setCellValue(arr3.get(i1).toString().trim());
            }
            int cellnumber2 = 3 ;
            for (int i2 = 1; i2 < arr3.size(); i2++) {
                row = spreadSheet.createRow(i2);
                cell = row.createCell(cellnumber2);
                cell.setCellValue(arr3.get(i2).toString().trim());
            }
            workBook.write(resultExcel);
        }

        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}

