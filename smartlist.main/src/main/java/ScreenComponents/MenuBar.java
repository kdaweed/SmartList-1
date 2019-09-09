package ScreenComponents;

import SQL.InitTables;

import javax.swing.*;
import java.awt.*;

public class MenuBar {

    Screen screen;
    

    MenuBar(Screen screen) {
        this.screen = screen;
        initPanels();
    }

    /**
     * creates admin and user panel
     */
    private void initPanels() {

        final CardLayout cardLayout = new CardLayout();
        final JPanel cardPanel = new JPanel(cardLayout);

        cardPanel.add(screen.getAdminPanel(), "ADMIN");
        cardPanel.add(screen.getUserPanel(), "USER");

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        JButton resetDB = new JButton("RESET DB");      // TODO delete this button, when project is finished
        JButton adminButton = new JButton("ADMIN");
        JButton userButton = new JButton("USER");
        buttonPanel.add(resetDB);       // TODO delete this button, when project is finished
        buttonPanel.add(adminButton);
        buttonPanel.add(userButton);

        resetDB.addActionListener(e -> {    // TODO delete this button, when project is finished
            InitTables.main(null);
        });
        System.out.println();
        adminButton.addActionListener(e -> {
            cardLayout.show(cardPanel, "ADMIN");
        });

        userButton.addActionListener(e -> {
            cardLayout.show(cardPanel, "USER");
        });

        screen.add(buttonPanel, BorderLayout.PAGE_START);
        screen.add(cardPanel, BorderLayout.CENTER);
    }
}
