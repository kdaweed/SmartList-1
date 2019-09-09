package ScreenComponents;

import ScreenComponents.Screen;
import ScreenComponents.SelectCategoryImpl;
import ScreenComponents.SelectFileButtonImpl;
import ScreenComponents.UploadButtonImpl;

import javax.swing.*;
import java.awt.*;

public class AdminPanel extends JPanel {

    private Screen screen;
    private JPanel operatingPanel;
    private SelectCategoryImpl selectCategory;
    private SelectFileButtonImpl selectFileButton;
    private UploadButtonImpl uploadButton;

    AdminPanel(Screen screen) {
        this.screen = screen;

        setLayout(new BorderLayout());

        initComponents();
    }

    private void initComponents() {
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setVgap((int) (screen.getHeight() * 0.1));
        operatingPanel = new JPanel(flowLayout);
        operatingPanel.setBackground(Color.CYAN);

        selectCategory = screen.getSelectCategory();
        selectFileButton = screen.getSelectFile();
        uploadButton = screen.getUploadButton();

        operatingPanel.add(selectCategory);
        operatingPanel.add(selectFileButton);
        operatingPanel.add(uploadButton);

        add(operatingPanel, "North");

        System.out.println("hi wasdfsdf");

        /* TODO show Log-Messages */
        JTextField textField = new JTextField("hi");
        textField.setEditable(false);
        textField.setBackground(Color.RED);
        add(textField);
    }
}
