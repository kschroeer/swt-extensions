package org.swtextensions;

import java.util.HashMap;
import java.util.Map;

/**
 * A utility class for managing tab indexes associated with dialog classes.
 * This class uses a static map to store and retrieve tab indexes based on dialog class names.
 */
public final class TabManager {
	private static Map<String, Integer> map = new HashMap<>();

	// Private constructor to prevent instantiation of this class.
	private TabManager() {
	}

	/**
	 * Retrieves the tab index associated with the specified dialog class.
	 *
	 * @param dialogClass The name of the dialog class for which to retrieve the tab index.
	 * @return The tab index associated with the dialog class, or 0 if not found.
	 */
	public static int get(String dialogClass) {
		return map.containsKey(dialogClass) ? map.get(dialogClass) : 0;
	}

	/**
	 * Associates the specified tab index with the given dialog class.
	 *
	 * @param dialogClass The name of the dialog class to associate with the tab index.
	 * @param tabIndex    The tab index to be associated with the dialog class.
	 */
	public static void put(String dialogClass, int tabIndex) {
		map.put(dialogClass, tabIndex);
	}
}