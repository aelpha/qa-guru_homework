package workforfiles;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.google.gson.internal.bind.JsonTreeReader;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvRecursionException;
import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
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

    @Test
    public void jsonFileTest() throws Exception {
        byte[] mapData = Files.readAllBytes(Paths.get("src/test/resources/file.txt"));
        Map myMap;
        ObjectMapper objectMapper = new ObjectMapper();
        myMap = objectMapper.readValue(mapData, TreeMap.class);
        assertThat(myMap.get("id").equals(2)).as("id value is correct").isTrue();
        assertThat(myMap.get("description").equals("Роскошная коробка")).as("description value is correct")
                .isTrue();
    }

    @Test
    public void jsonFileClassTest() throws Exception {
        byte[] mapData = Files.readAllBytes(Paths.get("src/test/resources/file.txt"));
        ObjectMapper objectMapper = new ObjectMapper();
        Box box = objectMapper.readValue(mapData, Box.class);
        assertThat(box.id).isEqualTo(2);
        assertThat(box.description).isEqualTo("Роскошная коробка");
        assertThat(box.systemInfo.ololo).isEqualTo("trololo");
    }
}
