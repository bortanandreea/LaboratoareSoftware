package ro.ulbs.proiectaresoftware.lab8;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CitireExcel {

    public static void citesteExcel() {
        try {
            FileInputStream file = new FileInputStream(new File("laborator8_input.xlsx"));

            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                for (Cell cell : row) {

                    switch (cell.getCellType()) {
                        case STRING:
                            System.out.print(cell.getStringCellValue() + "\t");
                            break;
                        case NUMERIC:
                            System.out.print(cell.getNumericCellValue() + "\t");
                            break;
                        default:
                            System.out.print("?\t");
                    }
                }
                System.out.println();
            }

            workbook.close();
            file.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void citesteexcel2() {
        try (
                FileInputStream fis = new FileInputStream("laborator8_input.xlsx");
                Workbook inputWb = new XSSFWorkbook(fis);
                Workbook outputWb = new XSSFWorkbook()
        ) {
            Sheet inputSheet = inputWb.getSheetAt(0);
            Sheet outputSheet = outputWb.createSheet("Rezultat");

            int rowIndex = 0;

            for (Row inputRow : inputSheet) {
                Row outputRow = outputSheet.createRow(rowIndex++);

                int colIndex = 0;
                double[] ultimele3 = new double[3];
                int count = 0;

                for (Cell cell : inputRow) {
                    Cell outCell = outputRow.createCell(colIndex);

                    switch (cell.getCellType()) {
                        case STRING:
                            outCell.setCellValue(cell.getStringCellValue());
                            break;

                        case NUMERIC:
                            double val = cell.getNumericCellValue();
                            outCell.setCellValue(val);

                            if (count < 3) {
                                ultimele3[count++] = val;
                            } else {
                                ultimele3[0] = ultimele3[1];
                                ultimele3[1] = ultimele3[2];
                                ultimele3[2] = val;
                            }
                            break;
                    }

                    colIndex++;
                }

                if (count == 3) {
                    double medie = (ultimele3[0] + ultimele3[1] + ultimele3[2]) / 3;
                    outputRow.createCell(colIndex).setCellValue(medie);
                }
            }

            try (FileOutputStream fos = new FileOutputStream("laborator8_output2.xlsx")) {
                outputWb.write(fos);
            }

            System.out.println("Fisier generat!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }

