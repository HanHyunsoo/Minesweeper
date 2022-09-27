package view;

import javax.swing.*;
import java.awt.*;

abstract class AbstractView extends JFrame {
    private Dimension dimension;

    public AbstractView(Dimension dimension) {
        this.dimension = dimension;
        setSize(dimension);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public Dimension getDimension() {
        return dimension;
    }
}
