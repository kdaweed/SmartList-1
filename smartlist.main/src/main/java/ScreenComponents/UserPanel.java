package ScreenComponents;

import ScreenComponents.Screen;

import javax.swing.*;

public class UserPanel extends JPanel {

    Screen screen;

    Screen screen2;

    UserPanel(Screen screen) {
        this.screen = screen;


    }


    void setVisibility(boolean value) {
        setVisible(value);
    }
}
