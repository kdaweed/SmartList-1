package ScreenComponents;

import javax.swing.*;
import java.awt.*;

public class Screen extends JFrame {
    static short width = 660;
    static short height = 540;

    private SelectCategoryImpl selectCategory;

    private MenuBar menuBar;

    private AdminPanel adminPanel;
    private UploadButtonImpl uploadButton;
    private SelectFileButtonImpl selectFile;

    private UserPanel userPanel;

    private String user;


    public Screen(String title) {
        setDefaultLookAndFeelDecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setTitle(title);

        setSize(width, height);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        setResizable(false);

        initComponents();
    }

    private void initComponents() {
        selectCategory = new SelectCategoryImpl();
        selectFile = new SelectFileButtonImpl();
        uploadButton = new UploadButtonImpl(selectCategory);

        adminPanel = new AdminPanel(this);
        userPanel = new UserPanel(this);
        menuBar = new MenuBar(this);
    }


    public SelectCategoryImpl getSelectCategory() { return selectCategory; }

    public void setSelectCategory(SelectCategoryImpl selectCategory) { this.selectCategory = selectCategory; }

    public AdminPanel getAdminPanel() { return adminPanel; }

    public void setAdminPanel(AdminPanel adminPanel) { this.adminPanel = adminPanel; }

    public UserPanel getUserPanel() { return userPanel; }

    public void setUserPanel(UserPanel userPanel) { this.userPanel = userPanel; }

    public void setMenuBar(MenuBar menuBar) {
        this.menuBar = menuBar;
    }

    public UploadButtonImpl getUploadButton() {
        return uploadButton;
    }

    public void setUploadButton(UploadButtonImpl uploadButton) {
        this.uploadButton = uploadButton;
    }

    public SelectFileButtonImpl getSelectFile() {
        return selectFile;
    }

    public void setSelectFile(SelectFileButtonImpl selectFile) {
        this.selectFile = selectFile;
    }
}
