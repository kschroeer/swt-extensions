package org.swtextensions;

import org.eclipse.jface.action.IAction;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

/**
 * Enhances the Cocoa UI by hooking application menu actions.
 */
public final class CocoaUIEnhancer {
	// Private constructor to prevent instantiation of this class.
	private CocoaUIEnhancer() {
	}

	/**
	 * Hooks the application menu to the provided actions for about, preferences, and exit.
	 * 
	 * @param aboutAction The action to run when the about menu item is selected.
	 * @param prefsAction The action to run when the preferences menu item is selected.
	 * @param exitAction The action to run when the exit menu item is selected.
	 */
	public static void hookApplicationMenu(IAction aboutAction, IAction prefsAction, IAction exitAction) {
		Display display = Display.getCurrent();

		Menu systemMenu = display.getSystemMenu();
		for (MenuItem systemItem : systemMenu.getItems()) {
			switch (systemItem.getID()) {
			case SWT.ID_ABOUT:
				if (aboutAction != null) {
					systemItem.addSelectionListener(new SelectionAdapter() {
						@Override
						public void widgetSelected(SelectionEvent e) {
							aboutAction.run();
						}
					});
				}
				break;
			case SWT.ID_PREFERENCES:
				if (prefsAction != null) {
					systemItem.addSelectionListener(new SelectionAdapter() {
						@Override
						public void widgetSelected(SelectionEvent e) {
							prefsAction.run();
						}
					});
				}
				break;
			case SWT.ID_QUIT:
				if (exitAction != null) {
					systemItem.addSelectionListener(new SelectionAdapter() {
						@Override
						public void widgetSelected(SelectionEvent e) {
							exitAction.run();
						}
					});
				}
				break;
			}
		}

		display.addFilter(SWT.KeyDown, new Listener() {
			@Override
			public void handleEvent(Event e) {
				if ((e.stateMask & SWT.COMMAND) == SWT.COMMAND && e.keyCode == 'w') {
					e.doit = false;
					display.getActiveShell().close();
				}
			}
		});
	}
}