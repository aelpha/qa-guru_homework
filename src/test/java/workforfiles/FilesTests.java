package workforfiles;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvRecursionException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FilesTests {

    @Test
    public void checkZipFilesTest() throws Exception {
        ZipFile zipFile = new ZipFile("src/test/resources/files.zip");
        ZipEntry zipEntry = zipFile.getEntry("pdf_file.pdf");
        try (InputStream is = zipFile.getInputStream(zipEntry)) {
            PDF pdf = new PDF(is);

            assertThat(pdf.text.contains("Согласие на обработку персональных данных"))
                    .as("pdf file contains correct text").isTrue();
        }
        zipEntry = zipFile.getEntry("xlsx_file.xlsx");
        try (InputStream is = zipFile.getInputStream(zipEntry)) {
            XLS xls = new XLS(is);

            assertThat(xls.excel.getSheetAt(0).getRow(0).getCell(2).getStringCellValue().contains("Название"))
                    .as("xlsx file contains correct text").isTrue();
        }

        zipEntry = zipFile.getEntry("csv_file.csv");
        try (InputStream is = zipFile.getInputStream(zipEntry);
             CSVReader csv = new CSVReader(new InputStreamReader(is))) {
            List<String[]> content = csv.readAll();

            assertThat(content.get(0)).contains("Footnotes");
        }
    }
}
