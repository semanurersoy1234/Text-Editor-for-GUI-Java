package ce204_hw3_algo_lib;

import java.awt.*;
import java.awt.datatransfer.*;
import java.io.IOException;

public class Model {
    private Clipboard clipboard;
    /**
     * @name Model
     * A model object is a Java object that represents, or models, an item in the application domain.
     **/
    public Model() {
        this.clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    }
    /**
     * @name copyText
     * @param [in] text [\b String]
     * A manuscript or earlier published version of a text, used as the basis for an emended, scholarly edition.
     **/
    public void copyText(String text) {
        StringSelection selection = new StringSelection(text);
        clipboard.setContents(selection, null);
    }
    /**
     * @name pasteText
     * @retval [\b String]
     * Paste is an operating system and programs action that lets you copy an object or text from one location and place it to another location.
     **/
    public String pasteText() {
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
     * @name getClipboard
     * @retval [\b String]
     * If the data is represented by a DataFlavor that doesn't correspond to a Java class.
     **/
    public String getClipboard() {
        String clipboardText = "";
        try {
            clipboardText = (String) clipboard.getData(DataFlavor.stringFlavor);
        } catch (UnsupportedFlavorException | IOException e) {
            e.printStackTrace();
        }
        return clipboardText;
    }
}

