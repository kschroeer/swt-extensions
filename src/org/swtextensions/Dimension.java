package org.swtextensions;

import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Control;

/**
 * Utility object for dimension calculations.
 */
public final class Dimension {
	// Private constructor to prevent instantiation of this class.
	private Dimension() {
	}

	/**
	 * Calculates the average text size of the control's font.
	 *
	 * @param control The control for which the text size is calculated.
	 * @return The average character width and height of the control's font.
	 */
	public static Point getTextSize(Control control) {
		GC gc = new GC(control);
		try {
			gc.setFont(control.getFont());
			FontMetrics fm = gc.getFontMetrics();
			return new Point((int) fm.getAverageCharacterWidth(), fm.getHeight());
		} finally {
			gc.dispose();
		}
	}
}