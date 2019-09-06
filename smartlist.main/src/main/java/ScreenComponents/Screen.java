package ScreenComponents;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Screen extends JFrame {
    static short width = 660;
    static short height = 540;
    static long failCounter;

    private String user;
    private AdminUserButtonImpl adminButton;
    private UserUserButtonImpl userButton;

    private SelectCategoryImpl selectCategory;

    private JComboBox cmbMessageList = new JComboBox(new String[]{"hi", "was", "geht"});
    private UploadButtonImpl uploadButton;
    private SelectFileButtonImpl selectFile;

    public Screen(String title) {
        setTitle(title);

        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setResizable(false);


        setSize(width, height);

        initComponents();
    }

    private void initComponents() {
        /* ADMIN-Button */

        /* USER-Button */

        /* SELECT CATEGORY-ComboBox */
        selectCategory = new SelectCategoryImpl();
        add(selectCategory);

        /* SELECT FILE-Button */

        /* UPLOAD-Button */
        uploadButton = new UploadButtonImpl("UPLOAD");
        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                uploadButton.actionPerformed(selectCategory.getSelectedItem().toString());
            }   //TODO category wird gelesen
        });
        add(uploadButton);

        /* SELECT FILE-Button */

    }

}
