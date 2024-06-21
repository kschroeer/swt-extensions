package org.swtextensions;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

/**
 * Utility object for displaying various message dialogs.
 */
public final class MessageDialog {
	// Private constructor to prevent instantiation of this class.
	private MessageDialog() {
	}

	/**
	 * Opens a confirmation dialog with Yes and No options.
	 *
	 * @param message The message to display in the dialog.
	 * @return The user's response, either SWT.YES or SWT.NO.
	 */
	public static int openConfirm(String message) {
		return openConfirm("Confirmation", message);
	}

	/**
	 * Opens a confirmation dialog with Yes and No options.
	 *
	 * @param title   The title of the dialog.
	 * @param message The message to display in the dialog.
	 * @return The user's response, either SWT.YES or SWT.NO.
	 */
	public static int openConfirm(String title, String message) {
		return showMessageDialog(title, message, SWT.YES | SWT.NO | SWT.ICON_QUESTION);
	}

	/**
	 * Opens a confirmation dialog with Yes, No, and Cancel options.
	 *
	 * @param message The message to display in the dialog.
	 * @return The user's response, either SWT.YES, SWT.NO, or SWT.CANCEL.
	 */
	public static int openConfirmWithCancel(String message) {
		return openConfirmWithCancel("Confirmation", message);
	}

	/**
	 * Opens a confirmation dialog with Yes, No, and Cancel options.
	 *
	 * @param title   The title of the dialog.
	 * @param message The message to display in the dialog.
	 * @return The user's response, either SWT.YES, SWT.NO, or SWT.CANCEL.
	 */
	public static int openConfirmWithCancel(String title, String message) {
		return showMessageDialog(title, message, SWT.YES | SWT.NO | SWT.CANCEL | SWT.ICON_QUESTION);
	}

	/**
	 * Opens an error dialog with an OK option.
	 *
	 * @param message The message to display in the dialog.
	 */
	public static void openError(String message) {
		openError("Error", message);
	}

	/**
	 * Opens an error dialog with an OK option.
	 *
	 * @param title   The title of the dialog.
	 * @param message The message to display in the dialog.
	 */
	public static void openError(String title, String message) {
		showMessageDialog(title, message, SWT.OK | SWT.ICON_ERROR);
	}

	/**
	 * Opens an information dialog with an OK option.
	 *
	 * @param message The message to display in the dialog.
	 */
	public static void openInformation(String message) {
		openInformation("Information", message);
	}

	/**
	 * Opens an information dialog with an OK option.
	 *
	 * @param title   The title of the dialog.
	 * @param message The message to display in the dialog.
	 */
	public static void openInformation(String title, String message) {
		showMessageDialog(title, message, SWT.OK | SWT.ICON_INFORMATION);
	}

	/**
	 * Opens a warning dialog with an OK option.
	 *
	 * @param message The message to display in the dialog.
	 */
	public static void openWarning(String message) {
		openWarning("Warning", message);
	}

	/**
	 * Opens a warning dialog with an OK option.
	 *
	 * @param title   The title of the dialog.
	 * @param message The message to display in the dialog.
	 */
	public static void openWarning(String title, String message) {
		showMessageDialog(title, message, SWT.OK | SWT.ICON_WARNING);
	}

	/**
	 * Retrieves the current shell or creates a new one if none is active.
	 *
	 * @return The current or a new shell.
	 */
	private static Shell getShell() {
		Display display = Display.getCurrent();
		if (display == null) {
			display = Display.getDefault();
		}

		Shell shell = display.getActiveShell();
		if (shell == null) {
			shell = new Shell();
		}

		return shell;
	}

	/**
	 * Shows a message dialog with the given title, message, and style.
	 *
	 * @param title   The title of the dialog.
	 * @param message The message to display in the dialog.
	 * @param style   The style of the dialog (e.g., SWT.ICON_ERROR).
	 * @return The user's response.
	 */
	private static int showMessageDialog(String title, String message, int style) {
		Shell parent = getShell();
		MessageBox mb = new MessageBox(parent, style);
		mb.setMessage(message);
		mb.setText(title);
		return mb.open();
	}
}