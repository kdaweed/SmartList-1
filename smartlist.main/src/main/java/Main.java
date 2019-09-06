import ScreenComponents.Screen;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                Screen screen = new Screen("Grocery List");
                screen.setVisible(true);
            }
        });
    }
}
