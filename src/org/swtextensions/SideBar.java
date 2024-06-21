package org.swtextensions;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

/**
 * A custom composite widget representing a sidebar with a table of items that
 * can switch between different panes using a stack layout.
 */
public class SideBar extends Composite {
	private Label sideAccLabel;
	private Table table;
	private Composite viewport;
	private List<Composite> panes;

	/**
	 * Constructs a sidebar composite widget.
	 *
	 * @param parent The parent composite to which this sidebar belongs.
	 */
	public SideBar(Composite parent) {
		super(parent, SWT.NONE);
		setLayout(new GridLayout());

		sideAccLabel = new Label(this, SWT.NONE);
		GridData gridData = new GridData();
		gridData.exclude = true;
		sideAccLabel.setLayoutData(gridData);
		sideAccLabel.setText("Sidebar");
		sideAccLabel.setVisible(false);

		table = new Table(this, SWT.BORDER | SWT.FULL_SELECTION | SWT.SINGLE | SWT.V_SCROLL);
		table.setHeaderVisible(true);
		table.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, true, true));
		table.setLinesVisible(true);
		table.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				changePane(table.getSelectionIndex());
			}
		});
		TableColumn col = new TableColumn(table, SWT.LEFT);
		col.setText(sideAccLabel.getText());
		col.setWidth(100);

		panes = new ArrayList<>();
	}

	/**
	 * Adds a pane to the sidebar with the specified text label.
	 *
	 * @param text The text label to display in the sidebar's table.
	 * @param pane The composite pane to be added and displayed when selected.
	 */
	public void addPane(String text, Composite pane) {
		TableItem item = new TableItem(table, SWT.NONE);
		item.setText(text);

		GridData gridData = (GridData) table.getLayoutData();
		gridData.heightHint = table.getItemCount() * table.getItemHeight();
		table.setSelection(table.getItemCount() - 1);

		panes.add(pane);
	}

	/**
	 * Changes the currently displayed pane based on the selected index in the
	 * sidebar's table.
	 *
	 * @param index The index of the pane to display.
	 */
	public void changePane(int index) {
		if (index >= 0 && index < table.getItemCount()) {
			setTopControl(panes.get(index));
			table.setSelection(index);
		}
	}

	/**
	 * Changes the currently displayed pane to the specified pane.
	 *
	 * @param pane The pane to display.
	 */
	public void changePane(Composite pane) {
		setTopControl(pane);
		for (int i = 0; i < panes.size(); i++) {
			if (panes.get(i) == pane) {
				table.setSelection(i);
				break;
			}
		}
	}

	/**
	 * Retrieves the table widget used in the sidebar.
	 *
	 * @return The table widget displaying the sidebar items.
	 */
	public Table getTable() {
		return table;
	}

	/**
	 * Sets the text label for the specified pane index in the sidebar's table.
	 *
	 * @param index The index of the pane whose text label to set.
	 * @param text  The text to set for the specified pane.
	 */
	public void setPaneText(int index, String text) {
		if (index >= 0 && index < table.getItemCount()) {
			table.getItem(index).setText(text);
		}
	}

	/**
	 * Sets the text label for the sidebar.
	 *
	 * @param text The text to set for the sidebar label.
	 */
	public void setText(String text) {
		sideAccLabel.setText(text);
		table.getColumn(0).setText(text);
	}

	/**
	 * Sets the viewport composite where the panes will be displayed using a stack
	 * layout.
	 *
	 * @param viewport The composite representing the viewport where panes are
	 *                 displayed.
	 */
	public void setViewport(Composite viewport) {
		this.viewport = viewport;
	}

	/**
	 * Sets the top control (pane) to be displayed in the viewport.
	 *
	 * @param pane The composite pane to display as the top control.
	 */
	private void setTopControl(Composite pane) {
		StackLayout stackLayout = (StackLayout) viewport.getLayout();
		stackLayout.topControl = pane;
		viewport.layout();
	}
}