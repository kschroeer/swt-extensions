# swt-extensions
@author Kay Schröer (acsf.dev@gmail.com)

This is a collection of components and helper objects that extend the Standard Widget Toolkit (SWT) and JFace to provide commonly used functionalities.

## Classes

- `CocoaUIEnhancer`: Enhances the Cocoa UI by hooking application menu actions.
- `Dimension`: Utility object for dimension calculations.
- `FileChooser`: A utility class that provides methods for displaying SWT file dialogs for opening and saving files with additional options for checking file existence and overwriting.
- `MessageDialog`: Utility object for displaying various message dialogs.
- `ModalDialog`: A modal dialog with customizable buttons.
- `NumberValidator`: A listener to validate input in a text field to ensure it is a number.
- `SideBar`: A custom composite widget representing a sidebar with a table of items that can switch between different panes using a stack layout.
- `StatusBar`: A custom composite widget representing a status bar with multiple panels for displaying textual information.
- `TabManager`: A utility class for managing tab indexes associated with dialog classes. This class uses a static map to store and retrieve tab indexes based on dialog class names.
- `TabTraverse`: A listener to enable tab traversal.
