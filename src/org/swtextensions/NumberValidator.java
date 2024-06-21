package org.swtextensions;

import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.widgets.Text;

/**
 * A listener to validate input in a text field to ensure it is a number.
 */
public class NumberValidator implements VerifyListener {
	/**
	 * Verifies that the text input is a valid integer.
	 *
	 * @param event The verify event.
	 */
	@Override
	public void verifyText(VerifyEvent event) {
		Text textField = (Text) event.getSource();
		String oldStr = textField.getText();
		String newStr = oldStr.substring(0, event.start) + event.text + oldStr.substring(event.end);

		boolean isInt = true;
		if (!newStr.equals("")) {
			try {
				Integer.parseInt(newStr);
			} catch (NumberFormatException ex) {
				isInt = false;
			}
		}

		if (!isInt) {
			event.doit = false;
		}
	}
}