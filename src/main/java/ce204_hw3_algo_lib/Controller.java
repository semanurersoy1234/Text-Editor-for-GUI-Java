package ce204_hw3_algo_lib;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JTextArea;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;

public class Controller {
    private View view;
    private Model model;
    private UndoManager undoManager;

    /**
     * @name Controller
     * @param [in] view [\b View]
     * @param [in] model [\b Model]
     * A controller basically controls the flow of the data
     * It controls the data flow into model object and updates the view whenever data changes.
     * The first of the MVC elements we’ll work on implementing are the controllers.
     **/
    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
        this.undoManager = new UndoManager();
        this.view.addCopyButtonListener(new CopyButtonListener());
        this.view.addPasteButtonListener(new PasteButtonListener());
        this.view.addCutButtonListener(new CutButtonListener());
        this.view.addUndoButtonListener(new UndoButtonListener());
        this.view.addRedoButtonListener(new RedoButtonListener());
        this.view.getTextArea().getDocument().addUndoableEditListener(new UndoableEditListener() {
            public void undoableEditHappened(UndoableEditEvent e) {
                undoManager.addEdit(e.getEdit());
            }
        });
    }
    /**
     * @name getFromClipboard
     * @retval [\b String]
     * A data package to be set in the Clipboard must be wrapped in a Transferable object.
     **/
    private String getFromClipboard() {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable clipboardContents = clipboard.getContents(null);
        if (clipboardContents != null && clipboardContents.isDataFlavorSupported(DataFlavor.stringFlavor)) {
            try {
                return (String) clipboardContents.getTransferData(DataFlavor.stringFlavor);
            } catch (UnsupportedFlavorException | IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    /**
     * @name setToClipboard
     * @param [in] text [\b String]
     * Places a specified text on the clipboard.
     **/
    private void setToClipboard(String text) {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection selection = new StringSelection(text);
        clipboard.setContents(selection, null);
    }
   
    class CopyButtonListener implements ActionListener {
    	 /**
         * @name actionPerformed
         * @param [in] e [\b ActionEvent]
         * An action event occurs, whenever an action is performed by the user.
         **/
        public void actionPerformed(ActionEvent e) {
            JTextArea textArea = view.getTextArea();
            String selectedText = textArea.getSelectedText();

            if (selectedText == null || selectedText.isEmpty()) {
                selectedText = textArea.getText();
            }

            model.copyText(selectedText);
            System.out.println("Copy button action performed");
        }
    }
    
    class PasteButtonListener implements ActionListener {
    	/**
         * @name actionPerformed
         * @param [in] e [\b ActionEvent]
         * A button is a Swing component in Java that is usually used to register some action from a user. 
         **/
        public void actionPerformed(ActionEvent e) {
            System.out.println("Paste button action performed");

            String clipboardText = getFromClipboard();
            int caretPosition = view.getTextArea().getCaretPosition();

            JTextArea textArea = view.getTextArea();
            String currentText = textArea.getText();
            StringBuilder newText = new StringBuilder(currentText);

            int selectionStart = textArea.getSelectionStart();
            int selectionEnd = textArea.getSelectionEnd();
            newText.replace(selectionStart, selectionEnd, clipboardText);
            caretPosition = selectionStart + clipboardText.length();

            textArea.setText(newText.toString());
            textArea.setCaretPosition(caretPosition);
        }
    }
   
    class CutButtonListener implements ActionListener {
    	 /**
         * @name actionPerformed
         * @param [in] e [\b ActionEvent]
         * Java ActionListener Interface.
         * The Java ActionListener is notified whenever you click on the button or menu item.
         **/
        public void actionPerformed(ActionEvent e) {
            System.out.println("Cut button action performed");

            String selectedText = view.getTextArea().getSelectedText();
            if (selectedText == null) {
                selectedText = "";
            }
            model.copyText(selectedText);

            JTextArea textArea = view.getTextArea();
            int selectionStart = textArea.getSelectionStart();
            int selectionEnd = textArea.getSelectionEnd();
            StringBuilder newText = new StringBuilder(textArea.getText());
            newText.delete(selectionStart, selectionEnd);
            textArea.setText(newText.toString());

            // Set the caret position after cutting
            int newCaretPosition = selectionStart;
            textArea.setCaretPosition(newCaretPosition);
        }
    }
    
    class UndoButtonListener implements ActionListener {
    	/**
         * @name actionPerformed
         * @param [in] e [\b ActionEvent]
         * To redo an operation, we execute the redo operation of the next item in the list and step forward.
         **/
        public void actionPerformed(ActionEvent e) {
            System.out.println("Undo button action performed");
            if (undoManager.canUndo()) {
                undoManager.undo();
            }
        }
    }
   
    class RedoButtonListener implements ActionListener {
    	/**
         * @name actionPerformed
         * @param [in] e [\b ActionEvent]
         * The Undo and Redo features let you remove or repeat single or multiple typing actions.
         **/
        public void actionPerformed(ActionEvent e) {
            System.out.println("Redo button action performed");
            if (undoManager.canRedo()) {
                undoManager.redo();
            }
        }
    }
}
