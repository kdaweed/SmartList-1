package ScreenComponents;

import SQL.Queries;

import javax.swing.*;

public class SelectCategoryImpl extends JComboBox {
    public SelectCategoryImpl() {
        setLocation((int) (Screen.width * 0.1), (int) (Screen.height * 0.15));
        setSize((int) (Screen.width * 0.3), (int) (Screen.height * 0.1));

        DefaultListCellRenderer dlcr = new DefaultListCellRenderer();
        dlcr.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
        setRenderer(dlcr);
        addCategories();

        //setVisible(true); //TODO Default sollte false sein, erst true wenn man Admin ist
    }

    public void addCategories() {
        for (String categories : Queries.getAllCategories_Name()) {
            addItem(categories);
        }
    }
}
