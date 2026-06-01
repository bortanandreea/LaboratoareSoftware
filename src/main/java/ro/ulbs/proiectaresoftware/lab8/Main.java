package ro.ulbs.proiectaresoftware.lab8;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {
    private static final String DEFAULT_INPUT = "laborator8_input.xlsx";
    private static final String DEFAULT_OUTPUT2 = "laborator8_output2.xlsx";
    private static final String DEFAULT_OUTPUT3 = "laborator8_output3.xlsx";

    public static void main(String[] args) throws Exception {
        String input = args.length > 0 ? args[0] : DEFAULT_INPUT;
        String output2 = args.length > 1 ? args[1] : DEFAULT_OUTPUT2;
        String output3 = args.length > 2 ? args[2] : DEFAULT_OUTPUT3;

        readAndPrintInput(input);
        createOutput2(input, output2);
        createOutput3(input, output3);
    }

    private static void readAndPrintInput(String inputFile) throws IOException {
        DataFormatter formatter = new DataFormatter();

        try (FileInputStream fis = new FileInputStream(inputFile);
             Workbook workbook = WorkbookFactory.create(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                short lastCellNum = row.getLastCellNum();
                if (lastCellNum <= 0) {
                    System.out.println();
                    continue;
                }

                StringBuilder line = new StringBuilder();
                for (int i = 0; i < lastCellNum; i++) {
                    Cell cell = row.getCell(i, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    if (i > 0) {
                        line.append('\t');
                    }
                    line.append(cell == null ? "" : formatter.formatCellValue(cell));
                }
                System.out.println(line);
            }
        }
    }

    private static void createOutput2(String inputFile, String outputFile) throws IOException {
        try (FileInputStream fis = new FileInputStream(inputFile);
             Workbook inputWorkbook = WorkbookFactory.create(fis);
             Workbook outputWorkbook = new XSSFWorkbook()) {

            Sheet inputSheet = inputWorkbook.getSheetAt(0);
            Sheet outputSheet = outputWorkbook.createSheet(inputSheet.getSheetName());

            copySheet(inputSheet, outputSheet);

            int lastColumnCount = getMaxColumnCount(inputSheet);
            int averageColumnIndex = lastColumnCount;

            for (Row row : outputSheet) {
                if (row.getRowNum() == 0) {
                    row.createCell(averageColumnIndex).setCellValue("Average");
                    continue;
                }

                int startIndex = Math.max(0, lastColumnCount - 3);
                int endIndex = lastColumnCount - 1;
                double sum = 0.0;
                int count = 0;

                for (int i = startIndex; i <= endIndex; i++) {
                    Cell cell = row.getCell(i, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    Double value = getNumericValue(cell);
                    if (value != null) {
                        sum += value;
                        count++;
                    }
                }

                Cell averageCell = row.createCell(averageColumnIndex);
                if (count > 0) {
                    averageCell.setCellValue(sum / count);
                } else {
                    averageCell.setBlank();
                }
            }

            try (FileOutputStream fos = new FileOutputStream(outputFile)) {
                outputWorkbook.write(fos);
            }
        }
    }

    private static void createOutput3(String inputFile, String outputFile) throws IOException {
        try (FileInputStream fis = new FileInputStream(inputFile);
             Workbook inputWorkbook = WorkbookFactory.create(fis);
             Workbook outputWorkbook = new XSSFWorkbook()) {

            Sheet inputSheet = inputWorkbook.getSheetAt(0);
            Sheet outputSheet = outputWorkbook.createSheet(inputSheet.getSheetName());

            copySheet(inputSheet, outputSheet);

            int lastColumnCount = getMaxColumnCount(inputSheet);
            int averageColumnIndex = lastColumnCount;
            int startIndex = Math.max(0, lastColumnCount - 3);
            int endIndex = lastColumnCount - 1;
            String startColumn = toExcelColumn(startIndex);
            String endColumn = toExcelColumn(endIndex);

            for (Row row : outputSheet) {
                if (row.getRowNum() == 0) {
                    row.createCell(averageColumnIndex).setCellValue("Average");
                    continue;
                }

                int excelRow = row.getRowNum() + 1;
                String formula = "AVERAGE(" + startColumn + excelRow + ":" + endColumn + excelRow + ")";
                row.createCell(averageColumnIndex).setCellFormula(formula);
            }

            try (FileOutputStream fos = new FileOutputStream(outputFile)) {
                outputWorkbook.write(fos);
            }
        }
    }

    private static void copySheet(Sheet inputSheet, Sheet outputSheet) {
        for (Row inputRow : inputSheet) {
            Row outputRow = outputSheet.createRow(inputRow.getRowNum());
            short lastCellNum = inputRow.getLastCellNum();
            if (lastCellNum <= 0) {
                continue;
            }

            for (int i = 0; i < lastCellNum; i++) {
                Cell inputCell = inputRow.getCell(i, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                if (inputCell == null) {
                    continue;
                }
                Cell outputCell = outputRow.createCell(i);
                copyCellValue(inputCell, outputCell);
            }
        }
    }

    private static void copyCellValue(Cell inputCell, Cell outputCell) {
        CellType type = inputCell.getCellType();
        if (type == CellType.FORMULA) {
            outputCell.setCellFormula(inputCell.getCellFormula());
            return;
        }

        switch (type) {
            case STRING:
                outputCell.setCellValue(inputCell.getStringCellValue());
                break;
            case NUMERIC:
                outputCell.setCellValue(inputCell.getNumericCellValue());
                break;
            case BOOLEAN:
                outputCell.setCellValue(inputCell.getBooleanCellValue());
                break;
            case ERROR:
                outputCell.setCellErrorValue(inputCell.getErrorCellValue());
                break;
            case BLANK:
                outputCell.setBlank();
                break;
            default:
                outputCell.setCellValue(inputCell.toString());
                break;
        }
    }

    private static Double getNumericValue(Cell cell) {
        if (cell == null) {
            return null;
        }

        CellType type = cell.getCellType();
        switch (type) {
            case NUMERIC:
                return cell.getNumericCellValue();
            case FORMULA:
                return cell.getCachedFormulaResultType() == CellType.NUMERIC
                        ? cell.getNumericCellValue()
                        : null;
            default:
                return null;
        }
    }

    private static int getMaxColumnCount(Sheet sheet) {
        int max = 0;
        for (Row row : sheet) {
            short lastCellNum = row.getLastCellNum();
            if (lastCellNum > max) {
                max = lastCellNum;
            }
        }
        return max;
    }

    private static String toExcelColumn(int index) {
        StringBuilder builder = new StringBuilder();
        int value = index + 1;

        while (value > 0) {
            int rem = (value - 1) % 26;
            builder.insert(0, (char) ('A' + rem));
            value = (value - 1) / 26;
        }

        return builder.toString();
    }
}