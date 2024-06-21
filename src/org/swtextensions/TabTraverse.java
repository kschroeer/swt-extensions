package org.swtextensions;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;

/**
 * A listener to enable tab traversal.
 */
public class TabTraverse implements TraverseListener {
	/**
	 * Handles tab traversal events.
	 *
	 * @param event The traverse event.
	 */
	@Override
	public void keyTraversed(TraverseEvent event) {
		if (event.detail == SWT.TRAVERSE_TAB_NEXT || event.detail == SWT.TRAVERSE_TAB_PREVIOUS) {
			event.doit = true;
		}
	}
}