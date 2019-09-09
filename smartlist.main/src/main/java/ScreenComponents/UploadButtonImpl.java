package ScreenComponents;

import Logger.MyLogger;
import SQL.Queries;
import org.apache.commons.lang3.time.DurationFormatUtils;
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
    private SelectCategoryImpl selectCategory;

    UploadButtonImpl(SelectCategoryImpl selectCategory) {
        setText("UPLOAD");

        setVisible(true); //TODO Default sollte false sein, erst true wenn man Admin ist

        addActionListener(e -> {
            actionPerformed(selectCategory.getSelectedItem().toString());
        });

        this.selectCategory = selectCategory;
    }

    public void actionPerformed(String category) {
        long timerStart = System.currentTimeMillis();

        Iterator<Row> rowIterator = null;

        File file = new File("smartlist.main/src/main/resources/InsertItems.xlsx");   //TODO admin user wählt file aus
        try (FileInputStream fis = new FileInputStream(file);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
            XSSFSheet sheet = workbook.getSheetAt(0);
            rowIterator = sheet.iterator();
        } catch (IOException e) {
            e.printStackTrace();
        }

        LOG.info("start importing items from " + file.getAbsolutePath() + " to " + category);

        long rowCounter = 0;
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Cell itemName = row.getCell(0);
            Cell itemPrice = row.getCell(1);

            if (!itemName.toString().equals("ITEM_NAME")) {
                rowCounter++;
                Queries.insertItem(category, itemName.toString(), Double.valueOf(itemPrice.toString()));
            }
        }

        if (Queries.failCounter == 0) {

            LOG.info("successfully imported " + rowCounter + " items; " +
                     DurationFormatUtils.formatDurationHMS(System.currentTimeMillis() - timerStart));
        } else {
            LOG.info("failed to import " + Queries.failCounter + " items out of " + rowCounter + "; " +
                     DurationFormatUtils.formatDurationHMS(System.currentTimeMillis() - timerStart));
            Queries.failCounter = 0;
        }
    }

    void setUploadPanelVisible(boolean value) {
        setVisible(value);
    }
}
