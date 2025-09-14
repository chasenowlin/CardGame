package GameMechanics;

import java.awt.*;

public interface OnScreen {
    static final GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
    static final Toolkit toolkit = Toolkit.getDefaultToolkit();
    static final Dimension screenSize = toolkit.getScreenSize();
    static final int screenWidth = screenSize.width;
    static final int screenHeight = screenSize.height;
}
