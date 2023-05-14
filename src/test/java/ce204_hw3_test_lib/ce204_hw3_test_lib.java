package ce204_hw3_test_lib;

import static org.junit.Assert.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.UndoableEditEvent;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

import org.junit.Assert;
import org.junit.Test;
import ce204_hw3_algo_lib.*;

public class ce204_hw3_test_lib {

	@Test
    public void testBtnCopy() {
        // Mock objects
        JTextFieldMock txtBox = new JTextFieldMock();
        String selectedText = "Selected Text";
        txtBox.setSelectedText(selectedText);

        // Create ActionListener instance
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedText = txtBox.getSelectedText();

                if (selectedText == null || selectedText.isEmpty()) {
                    // No selected text, copy the entire content
                    txtBox.selectAll();
                    txtBox.copy();
                } else {
                    // Copy the selected text
                    txtBox.copy();
                }
            }
        };

        // Simulate button click event
        actionListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, ""));

        // Assert the expected behavior
        Assert.assertEquals(selectedText, txtBox.getCopyContent());
    }
	
	@Test
    public void testBtnCopy2() {
        // Mock objects
        JTextFieldMock txtBox = new JTextFieldMock();
        String selectedText = "Bedirhan";
        txtBox.setSelectedText(selectedText);

        // Create ActionListener instance
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedText = txtBox.getSelectedText();

                if (selectedText == null || selectedText.isEmpty()) {
                    // No selected text, copy the entire content
                    txtBox.selectAll();
                    txtBox.copy();
                } else {
                    // Copy the selected text
                    txtBox.copy();
                }
            }
        };

        // Simulate button click event
        actionListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, ""));

        // Assert the expected behavior
        Assert.assertEquals(selectedText, txtBox.getCopyContent());
    }
	
	@Test
    public void testBtnCopy3() {
        // Mock objects
        JTextFieldMock txtBox = new JTextFieldMock();
        String selectedText = "comp";
        txtBox.setSelectedText(selectedText);

        // Create ActionListener instance
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedText = txtBox.getSelectedText();

                if (selectedText == null || selectedText.isEmpty()) {
                    // No selected text, copy the entire content
                    txtBox.selectAll();
                    txtBox.copy();
                } else {
                    // Copy the selected text
                    txtBox.copy();
                }
            }
        };

        // Simulate button click event
        actionListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, ""));

        // Assert the expected behavior
        Assert.assertEquals(selectedText, txtBox.getCopyContent());
    }
	
    @Test
    public void testBtnCut() {
        // Mock objects
        JTextAreaMock2 txtArea = new JTextAreaMock2();
        txtArea.setText("Hello, world!");

        // Create ActionListener instance
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtArea.cut();
            }
        };

        // Simulate button click event
        actionListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, ""));

        // Assert the expected behavior
        Assert.assertEquals("Hello, world!", txtArea.getText());
        Assert.assertNull(txtArea.getClipboardContent());
    }
    
    @Test
    public void testBtnCut2() {
        // Mock objects
        JTextAreaMock2 txtArea = new JTextAreaMock2();
        txtArea.setText("Welcome to Trabzon");

        // Create ActionListener instance
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtArea.cut();
            }
        };

        // Simulate button click event
        actionListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, ""));

        // Assert the expected behavior
        Assert.assertEquals("Welcome to Trabzon", txtArea.getText());
        Assert.assertNull(txtArea.getClipboardContent());
    }
    
    @Test
    public void testBtnCut3() {
        // Mock objects
        JTextAreaMock2 txtArea = new JTextAreaMock2();
        txtArea.setText("Introduction Computer");

        // Create ActionListener instance
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtArea.cut();
            }
        };

        // Simulate button click event
        actionListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, ""));

        // Assert the expected behavior
        Assert.assertEquals("Introduction Computer", txtArea.getText());
        Assert.assertNull(txtArea.getClipboardContent());
    }
    
    @Test
    public void testBtnPaste() {
        // Mock objects
        JTextFieldMock txtBox = new JTextFieldMock();
        String clipboardContent = "Clipboard Content";
        txtBox.setClipboardContent(clipboardContent);

        // Create ActionListener instance
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtBox.paste();
            }
        };

        // Simulate button click event
        actionListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, ""));

        // Assert the expected behavior
        Assert.assertEquals(clipboardContent, txtBox.getPastedContent());
    }    
    
    @Test
    public void testBtnPaste2() {
        // Mock objects
        JTextFieldMock txtBox = new JTextFieldMock();
        String clipboardContent = "Clipboard";
        txtBox.setClipboardContent(clipboardContent);

        // Create ActionListener instance
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtBox.paste();
            }
        };

        // Simulate button click event
        actionListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, ""));

        // Assert the expected behavior
        Assert.assertEquals(clipboardContent, txtBox.getPastedContent());
    }    
    
    @Test
    public void testBtnPaste3() {
        // Mock objects
        JTextFieldMock txtBox = new JTextFieldMock();
        String clipboardContent = "Computer Network";
        txtBox.setClipboardContent(clipboardContent);

        // Create ActionListener instance
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtBox.paste();
            }
        };

        // Simulate button click event
        actionListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, ""));

        // Assert the expected behavior
        Assert.assertEquals(clipboardContent, txtBox.getPastedContent());
    }    
    
    @Test
    public void testBtnSave() {
        // Mock objects
        JTextAreaMock txtArea = new JTextAreaMock();
        JFileChooserMock fileChooser = new JFileChooserMock();
        fileChooser.setDialogResult(JFileChooser.APPROVE_OPTION);
        fileChooser.setSelectedFile("test.txt");

        // Create ActionListener instance
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int dialogResult = fileChooser.showSaveDialog(null);
                if (dialogResult == JFileChooser.APPROVE_OPTION) {
                    String selectedFilePath = fileChooser.getSelectedFilePath();
                    txtArea.saveToFile(selectedFilePath);
                }
            }
        };

        // Simulate button click event
        actionListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, ""));

        // Assert the expected behavior
        Assert.assertEquals("test.txt", txtArea.getSavedFilePath());
    }
    @Test
    public void testBtnSave2() {
        // Mock objects
        JTextAreaMock txtArea = new JTextAreaMock();
        JFileChooserMock fileChooser = new JFileChooserMock();
        fileChooser.setDialogResult(JFileChooser.APPROVE_OPTION);
        fileChooser.setSelectedFile("deneme.cpp");

        // Create ActionListener instance
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int dialogResult = fileChooser.showSaveDialog(null);
                if (dialogResult == JFileChooser.APPROVE_OPTION) {
                    String selectedFilePath = fileChooser.getSelectedFilePath();
                    txtArea.saveToFile(selectedFilePath);
                }
            }
        };

        // Simulate button click event
        actionListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, ""));

        // Assert the expected behavior
        Assert.assertEquals("deneme.cpp", txtArea.getSavedFilePath());
    }
    
    @Test
    public void testBtnSave3() {
        // Mock objects
        JTextAreaMock txtArea = new JTextAreaMock();
        JFileChooserMock fileChooser = new JFileChooserMock();
        fileChooser.setDialogResult(JFileChooser.APPROVE_OPTION);
        fileChooser.setSelectedFile("bedirhan.cs");

        // Create ActionListener instance
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int dialogResult = fileChooser.showSaveDialog(null);
                if (dialogResult == JFileChooser.APPROVE_OPTION) {
                    String selectedFilePath = fileChooser.getSelectedFilePath();
                    txtArea.saveToFile(selectedFilePath);
                }
            }
        };

        // Simulate button click event
        actionListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, ""));

        // Assert the expected behavior
        Assert.assertEquals("bedirhan.cs", txtArea.getSavedFilePath());
    }
    
    @Test
    public void testBtnOpenFile() {
        // Mock objects
        JTextAreaMock txtArea = new JTextAreaMock();
        JFileChooserMock fileChooser = new JFileChooserMock();
        fileChooser.setDialogResult(JFileChooser.APPROVE_OPTION);
        fileChooser.setSelectedFile("test.txt");

        // Create ActionListener instance
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int dialogResult = fileChooser.showOpenDialog(null);
                if (dialogResult == JFileChooser.APPROVE_OPTION) {
                    String selectedFilePath = fileChooser.getSelectedFilePath();
                    txtArea.loadFromFile(selectedFilePath);
                }
            }
        };

        // Simulate button click event
        actionListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, ""));

        // Assert the expected behavior
        Assert.assertEquals("test.txt", txtArea.getLoadedFilePath());
    }
    
    @Test
    public void testBtnOpenFile2() {
        // Mock objects
        JTextAreaMock txtArea = new JTextAreaMock();
        JFileChooserMock fileChooser = new JFileChooserMock();
        fileChooser.setDialogResult(JFileChooser.APPROVE_OPTION);
        fileChooser.setSelectedFile("sema.java");

        // Create ActionListener instance
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int dialogResult = fileChooser.showOpenDialog(null);
                if (dialogResult == JFileChooser.APPROVE_OPTION) {
                    String selectedFilePath = fileChooser.getSelectedFilePath();
                    txtArea.loadFromFile(selectedFilePath);
                }
            }
        };

        // Simulate button click event
        actionListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, ""));

        // Assert the expected behavior
        Assert.assertEquals("sema.java", txtArea.getLoadedFilePath());
    }
    
    @Test
    public void testBtnOpenFile3() {
        // Mock objects
        JTextAreaMock txtArea = new JTextAreaMock();
        JFileChooserMock fileChooser = new JFileChooserMock();
        fileChooser.setDialogResult(JFileChooser.APPROVE_OPTION);
        fileChooser.setSelectedFile("bedirhan.java");

        // Create ActionListener instance
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int dialogResult = fileChooser.showOpenDialog(null);
                if (dialogResult == JFileChooser.APPROVE_OPTION) {
                    String selectedFilePath = fileChooser.getSelectedFilePath();
                    txtArea.loadFromFile(selectedFilePath);
                }
            }
        };

        // Simulate button click event
        actionListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, ""));

        // Assert the expected behavior
        Assert.assertEquals("bedirhan.java", txtArea.getLoadedFilePath());
    }
    
    
 // Mock class for JTextField
    private static class JTextFieldMock extends JTextField {
        private String selectedText;
        private String copyContent;
        private String clipboardContent;
        private String pastedContent;

        public void setSelectedText(String selectedText) {
            this.selectedText = selectedText;
        }

        public String getSelectedText() {
            return selectedText;
        }

        public void copy() {
            copyContent = getSelectedText();
        }

        public String getCopyContent() {
            return copyContent;
        }
        
        public void setClipboardContent(String clipboardContent) {
            this.clipboardContent = clipboardContent;
        }

        public void paste() {
            pastedContent = clipboardContent;
        }

        public String getPastedContent() {
            return pastedContent;
        }
    }

    // Mock class for JTextArea
    private static class JTextAreaMock {
        private String savedFilePath;
        private String loadedFilePath;

        public void loadFromFile(String filePath) {
            loadedFilePath = filePath;
        }

        public String getLoadedFilePath() {
            return loadedFilePath;
        }
        
        public void saveToFile(String filePath) {
            savedFilePath = filePath;
        }

        public String getSavedFilePath() {
            return savedFilePath;
        }
    }

    // Mock class for JFileChooser
    private static class JFileChooserMock extends JFileChooser {
        private int dialogResult;
        private String selectedFile;
        

        public void setDialogResult(int dialogResult) {
            this.dialogResult = dialogResult;
        }

        public void setSelectedFile(String selectedFile) {
            this.selectedFile = selectedFile;
        }

        public int showSaveDialog(java.awt.Component parent) {
            return dialogResult;
        }
        
        public int showOpenDialog(java.awt.Component parent) {
            return dialogResult;
        }

        public String getSelectedFilePath() {
            return selectedFile;
        }
    }
    
    private static class JTextAreaMock2 extends JTextArea {
    	private String clipboardContent;

        public void cut() {
            clipboardContent = getSelectedText(); // Güncellendi
            super.cut();
        }

        public String getClipboardContent() {
            return clipboardContent;
        }
    }
}
