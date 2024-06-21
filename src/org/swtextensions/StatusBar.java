package org.swtextensions;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

/**
 * A custom composite widget representing a status bar with multiple panels for displaying
 * textual information.
 */
public class StatusBar extends Composite {
	private Text[] panels;

	/**
	 * Constructs a status bar with the specified number of panels.
	 *
	 * @param parent The parent composite to which this status bar belongs.
	 * @param count The number of panels to create in the status bar.
	 *              If count is less than or equal to 0, a single panel is created.
	 */
	public StatusBar(Composite parent, int count) {
		super(parent, SWT.BORDER);

		if (count <= 0) {
			count = 1;
		}

		GridLayout gridLayout = new GridLayout();
		gridLayout.horizontalSpacing = 3;
		gridLayout.makeColumnsEqualWidth = true;
		gridLayout.marginHeight = 3;
		gridLayout.marginWidth = 3;
		gridLayout.numColumns = count;
		setLayout(gridLayout);

		panels = new Text[count];
		for (int i = 0; i < count; i++) {
			panels[i] = new Text(this, SWT.BORDER | SWT.CENTER | SWT.NO_SCROLL | SWT.READ_ONLY | SWT.SINGLE);
			panels[i].setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		}
	}

	/**
	 * Retrieves the text of the panel at the specified index.
	 *
	 * @param index The index of the panel whose text to retrieve.
	 * @return The text of the panel at the specified index, or null if the index is out of range.
	 */
	public String getPanelText(int index) {
		if (index >= 0 && index < panels.length) {
			return panels[index].getText();
		} else {
			return null;
		}
	}

	/**
	 * Sets the text of the panel at the specified index.
	 *
	 * @param index The index of the panel whose text to set.
	 * @param text The text to set for the panel at the specified index.
	 */
	public void setPanelText(int index, String text) {
		if (index >= 0 && index < panels.length) {
			panels[index].setText(text);
		}
	}

	/**
	 * Sets the visibility of the status bar.
	 *
	 * @param visible True to make the status bar visible, false to hide it.
	 *                When hidden, the status bar does not occupy space in the layout.
	 */
	@Override
	public void setVisible(boolean visible) {
		if (getLayoutData() instanceof GridData) {
			((GridData) getLayoutData()).exclude = !visible;
		}
		super.setVisible(visible);
	}
}