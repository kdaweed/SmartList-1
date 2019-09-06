package ScreenComponents;

import Logger.MyLogger;
import SQL.Queries;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Logger;

public class UploadButtonImpl extends JButton {
    private final Logger LOG = MyLogger.getLogger(UploadButtonImpl.class.getName());

    UploadButtonImpl(String text) {
        setText(text);

        setLocation((int) (Screen.width * 0.6), (int) (Screen.height * 0.15));
        setSize((int) (Screen.width * 0.2), (int) (Screen.height * 0.1));

        setVisible(true); //TODO Default sollte false sein, erst true wenn man Admin ist
    }

    public void actionPerformed(String category) {
        Iterator<Row> rowIterator = null;

        File file = new File("smartlist.main/src/main/resources/InsertItems.xlsx");   //TODO admin user wählt file aus
        try (FileInputStream fis = new FileInputStream(file);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
            XSSFSheet sheet = workbook.getSheetAt(0);
            rowIterator = sheet.iterator();
        } catch (IOException e) {
            e.printStackTrace();
        }

        long rowCounter = 0;
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            rowCounter++;
            Cell itemName = row.getCell(0);
            Cell itemPrice = row.getCell(1);

            if (!itemName.toString().equals("ITEM_NAME")) {
                Queries.insertItem(category, itemName.toString(), Double.valueOf(itemPrice.toString()));
            }
        }

        if (Screen.failCounter == 0) {
            LOG.info("successfully imported " + rowCounter + " items");
        } else {
            LOG.info("Failed to import " + Screen.failCounter + " items out of " + rowCounter);
        }
    }
}
