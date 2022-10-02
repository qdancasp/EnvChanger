import java.io.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;


public class Main {

    // Környezeti változó beállítása
    //static String keyString = "Teszt1";
    static String keyString = "Teszt2";
    //static String keyString = "Release";




    public static void main(String[] args) throws IOException, InvalidFormatException {
        changeEnviromentLoop(getAllFilesFromDirectory());
    }


    public static File[] getAllFilesFromDirectory() throws IOException {
        File folder = new File("D:\\Ide");
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                listOfFiles[i].getName();
                System.out.println(listOfFiles[i].getName());

            } else if (listOfFiles[i].isDirectory()) {

            }
        }
        return listOfFiles;
    }

    public static Workbook getSheetAsString(String result) throws IOException {
        Workbook workbook = WorkbookFactory.create(new FileInputStream(result));
        Sheet sheet = workbook.getSheetAt(0);
        sheet.forEach(row -> {
            row.forEach(cell -> {
            });
            System.out.println();
        });
        workbook.close();
        return workbook;
    }

    public static int getReleaseRow(String result) throws IOException {
        Workbook workbook = WorkbookFactory.create(new FileInputStream(result));
        Sheet sheet = workbook.getSheetAt(0);
        for (Row row : sheet) {
            for (Cell cell : row) {
                if (cell.getCellType()== CellType.STRING) {
                    if (cell.getRichStringCellValue().getString().trim().equals("Release")) {
                        return row.getRowNum();
                    }
                }
            }
        }
        return 0;
    }

    public static int getTesztIRow(String result) throws IOException {
        Workbook workbook = WorkbookFactory.create(new FileInputStream(result));
        Sheet sheet = workbook.getSheetAt(0);
        for (Row row : sheet) {
            for (Cell cell : row) {
                if (cell.getCellType()== CellType.STRING) {
                    if (cell.getRichStringCellValue().getString().trim().equals("Teszt1")) {
                        return row.getRowNum();
                    }
                }
            }
        }
        return 0;
    }

    public static int getTesztIIRow(String result) throws IOException {
        Workbook workbook = WorkbookFactory.create(new FileInputStream(result));
        Sheet sheet = workbook.getSheetAt(0);
        for (Row row : sheet) {
            for (Cell cell : row) {
                if (cell.getCellType()== CellType.STRING) {
                    if (cell.getRichStringCellValue().getString().trim().equals("Teszt2")) {
                        return row.getRowNum();
                    }
                }
            }
        }
        return 0;
    }

    public static void changeEnviromentLoop(File[] file) throws IOException {
        String result = null;
        for (File strTemp : file){
            result = strTemp.getAbsolutePath();
            getSheetAsString(result);
            workbookWriter(result);
        }
    }

    public static void workbookWriter(String result) throws IOException {
        Workbook workbook = WorkbookFactory.create(new FileInputStream(result));
        Sheet sheet = workbook.getSheetAt(0);

        switch (keyString){
            case "Teszt2":
                Row rowRel = sheet.getRow(getReleaseRow(result));
                Cell cell = rowRel.getCell(1);
                cell.setCellValue(0);

                Row rowTI = sheet.getRow(getTesztIRow(result));
                Cell cellI = rowTI.getCell(1);
                cellI.setCellValue(0);

                Row rowTII = sheet.getRow(getTesztIIRow(result));
                Cell cellII = rowTII.getCell(1);
                cellII.setCellValue(1);
                break;

            case "Teszt1":
                Row TrowRel = sheet.getRow(getReleaseRow(result));
                Cell Tcell = TrowRel.getCell(1);
                Tcell.setCellValue(0);

                Row TrowTI = sheet.getRow(getTesztIRow(result));
                Cell TcellI = TrowTI.getCell(1);
                TcellI.setCellValue(1);

                Row TrowTII = sheet.getRow(getTesztIIRow(result));
                Cell TcellII = TrowTII.getCell(1);
                TcellII.setCellValue(0);
                break;

            case "Release":
                Row RrowRel = sheet.getRow(getReleaseRow(result));
                Cell Rcell = RrowRel.getCell(1);
                Rcell.setCellValue(1);

                Row RrowTI = sheet.getRow(getTesztIRow(result));
                Cell RcellI = RrowTI.getCell(1);
                RcellI.setCellValue(0);

                Row RrowTII = sheet.getRow(getTesztIIRow(result));
                Cell RcellII = RrowTII.getCell(1);
                RcellII.setCellValue(0);
                break;
        }
        FileOutputStream fileOut = new FileOutputStream(result);
        workbook.write(fileOut);
        fileOut.close();
        workbook.close();
    }
}
