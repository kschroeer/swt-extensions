package org.swtextensions;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

/**
 * A modal dialog with customizable buttons.
 */
public abstract class ModalDialog extends Dialog {
	protected Object[][] buttonObjects;

	/**
	 * Constructs a modal dialog with customizable buttons.
	 *
	 * @param parentShell   The parent shell under which this dialog will be
	 *                      displayed.
	 * @param buttonObjects An array of information like types and labels
	 *                      representing the buttons to be displayed.
	 */
	protected ModalDialog(Shell parentShell, Object[][] buttonObjects) {
		super(parentShell);

		for (int i = 0; i < buttonObjects.length; i++) {
			if (buttonObjects[i].length != 3 || !(buttonObjects[i][0] instanceof Integer)
					|| !(buttonObjects[i][1] instanceof String) || !(buttonObjects[i][2] instanceof Boolean)) {
				throw new IllegalArgumentException();
			}
		}

		this.buttonObjects = buttonObjects;
	}

	/**
	 * Creates the buttons for the button bar.
	 *
	 * @param parent The parent container.
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		if (buttonObjects.length > 0) {
			if (SWT.getPlatform().equals("cocoa")) {
				for (int i = buttonObjects.length - 1; i >= 0; i--) {
					createButton(parent, (Integer) buttonObjects[i][0], (String) buttonObjects[i][1],
							(Boolean) buttonObjects[i][2]);
				}
			} else {
				for (Object[] buttonObject : buttonObjects) {
					createButton(parent, (Integer) buttonObject[0], (String) buttonObject[1],
							(Boolean) buttonObject[2]);
				}
			}
		} else {
			GridLayout gridLayout = (GridLayout) parent.getLayout();
			gridLayout.marginHeight = 0;
		}
	}
}