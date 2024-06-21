package org.swtextensions;

import java.io.File;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

/**
 * A utility class that provides methods for displaying SWT file dialogs for
 * opening and saving files with additional options for checking file existence
 * and overwriting.
 */
public class FileChooser {
	private boolean checkExists;
	private boolean checkOverwrite;
	private String fileName;
	private String[] filterExtensions;
	private String[] filterNames;
	private String message;
	private Shell parentShell;
	private String text;

	/**
	 * Constructs a new FileChooser with default settings.
	 *
	 * @param parentShell The parent shell for the file dialogs.
	 */
	public FileChooser(Shell parentShell) {
		this.checkExists = true;
		this.checkOverwrite = true;
		this.fileName = null;
		this.filterExtensions = new String[] { "*.*" };
		this.filterNames = new String[] { "All files (*.*)" };
		this.message = "";
		this.parentShell = parentShell;
		this.text = "";
	}

	/**
	 * Opens a file dialog for selecting a file to open.
	 *
	 * @return The selected file name, or null if the dialog was canceled.
	 */
	public String openDialog() {
		FileDialog openFileDialog = new FileDialog(parentShell, SWT.OPEN);
		if (fileName != null) {
			openFileDialog.setFileName(fileName);
		}
		openFileDialog.setFilterExtensions(filterExtensions);
		openFileDialog.setFilterNames(filterNames);
		openFileDialog.setText(text);

		boolean completed;
		do {
			completed = true;
			fileName = openFileDialog.open();
			if (fileName != null) {
				if (checkExists) {
					File file = new File(fileName);
					if (!file.isFile() || !file.exists()) {
						MessageDialog.openError(String.format(message, fileName));
						completed = false;
					}
				}
			}
		} while (!completed);

		return fileName;
	}

	/**
	 * Opens a file dialog for selecting a file to save.
	 *
	 * @return The selected file name, or null if the dialog was canceled.
	 */
	public String saveDialog() {
		FileDialog saveFileDialog = new FileDialog(parentShell, SWT.SAVE);
		if (fileName != null) {
			saveFileDialog.setFileName(fileName);
		}
		saveFileDialog.setFilterExtensions(filterExtensions);
		saveFileDialog.setFilterNames(filterNames);
		saveFileDialog.setText(text);

		boolean completed;
		do {
			completed = true;
			fileName = saveFileDialog.open();
			if (fileName != null) {
				if (checkOverwrite) {
					File file = new File(fileName);
					if (file.isFile() && file.exists()) {
						if (MessageDialog.openConfirm(String.format(message, fileName)) == SWT.NO) {
							completed = false;
						}
					}
				}
			}
		} while (!completed);

		return fileName;
	}

	/**
	 * Returns whether the file existence check is enabled.
	 *
	 * @return True if file existence check is enabled, false otherwise.
	 */
	public boolean getCheckExists() {
		return checkExists;
	}

	/**
	 * Sets whether to enable file existence check.
	 *
	 * @param checkExists True to enable file existence check, false otherwise.
	 */
	public void setCheckExists(boolean checkExists) {
		this.checkExists = checkExists;
	}

	/**
	 * Returns whether the file overwrite check is enabled.
	 *
	 * @return True if file overwrite check is enabled, false otherwise.
	 */
	public boolean getCheckOverwrite() {
		return checkOverwrite;
	}

	/**
	 * Sets whether to enable file overwrite check.
	 *
	 * @param checkOverwrite True to enable file overwrite check, false otherwise.
	 */
	public void setCheckOverwrite(boolean checkOverwrite) {
		this.checkOverwrite = checkOverwrite;
	}

	/**
	 * Returns the current file name selected in the dialogs.
	 *
	 * @return The current file name.
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * Sets the default file name to be selected in the dialogs.
	 *
	 * @param fileName The default file name to set.
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * Returns the array of filter extensions for the file dialogs.
	 *
	 * @return An array of filter extensions.
	 */
	public String[] getFilterExtensions() {
		return filterExtensions;
	}

	/**
	 * Sets the array of filter extensions for the file dialogs.
	 *
	 * @param filterExtensions An array of filter extensions to set.
	 */
	public void setFilterExtensions(String[] filterExtensions) {
		this.filterExtensions = filterExtensions;
	}

	/**
	 * Returns the array of filter names for the file dialogs.
	 *
	 * @return An array of filter names.
	 */
	public String[] getFilterNames() {
		return filterNames;
	}

	/**
	 * Sets the array of filter names for the file dialogs.
	 *
	 * @param filterNames An array of filter names to set.
	 */
	public void setFilterNames(String[] filterNames) {
		this.filterNames = filterNames;
	}

	/**
	 * Returns the message to be displayed as an error when a check fails.
	 *
	 * @return The message.
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the message to be displayed as an error when a check fails.
	 *
	 * @param message The message to set.
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Returns the text displayed as caption in the file dialogs.
	 *
	 * @return The text.
	 */
	public String getText() {
		return text;
	}

	/**
	 * Sets the text to be displayed as caption in the file dialogs.
	 *
	 * @param text The text to set.
	 */
	public void v(String text) {
		this.text = text;
	}
}