package view.button;

import model.Point;

import javax.swing.*;
import java.awt.*;

public class CellButton extends JButton {
    private final model.Point point;

    /**
     * Creates a button with no set text or icon.
     */
    public CellButton(Point point) {
        this.point = point;
    }

    /**
     * Creates a button with an icon.
     *
     * @param icon the Icon image to display on the button
     */
    public CellButton(Icon icon, Point point) {
        super(icon);
        this.point = point;
    }

    /**
     * Creates a button with text.
     *
     * @param text the text of the button
     */
    public CellButton(String text, Point point) {
        super(text);
        this.point = point;
    }

    /**
     * Creates a button where properties are taken from the
     * <code>Action</code> supplied.
     *
     * @param a the <code>Action</code> used to specify the new button
     * @since 1.3
     */
    public CellButton(Action a, Point point) {
        super(a);
        this.point = point;
    }

    /**
     * Creates a button with initial text and an icon.
     *
     * @param text the text of the button
     * @param icon the Icon image to display on the button
     */
    public CellButton(String text, Icon icon, Point point) {
        super(text, icon);
        this.point = point;
    }

    /**
     * Gets the foreground color of this component.
     *
     * @return this component's foreground color; if this component does
     * not have a foreground color, the foreground color of its parent
     * is returned
     * @see #setForeground
     * @since 1.0
     */
    @Override
    public Color getForeground() {
        if (!isEnabled()) {
            return  (Color)UIManager.getLookAndFeelDefaults().get("Button.disabledText");
        }
        return super.getForeground();
    }

    /**
     * Sets the foreground color of this component.  It is up to the
     * look and feel to honor this property, some may choose to ignore
     * it.
     *
     * @param fg the desired foreground <code>Color</code>
     * @see Component#getForeground
     */
    @Override
    public void setForeground(Color fg) {
        super.setForeground(fg);
    }

    public Point getPoint() {
        return point;
    }
}
