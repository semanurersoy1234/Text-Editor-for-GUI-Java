package ce204_hw3_algo_lib;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.awt.event.ActionEvent;
import javax.swing.undo.UndoManager;
import java.awt.BorderLayout;

public class View extends JFrame {

	private JPanel contentPane;
	private UndoManager undoManager;
    private JTextArea txtBox;
    private JButton btnCopy;
    private JButton btnPaste;
    private JButton btnCut;
    private JButton btnUndo;
    private JButton btnRedo;


	/**
	 * Launch the application.
	 */
    
  
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View frame = new View();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	/**
     * @name View
     * A View is a Java Class that inherits directly from the Java Object class, the root of the Java class hierarchy.
     **/
	public View() {
		JTextArea txtBox = new JTextArea();
		
		setForeground(Color.RED);
		setTitle("Code Editor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 714, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(25, 23, 250, 35);
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JComboBox<String> comboLang = new JComboBox<String>();
		comboLang.addActionListener(new ActionListener() {
			/**
		     * @name actionPerformed
		     * @param [in] e [\b ActionEvent]
		     * The method actionPerformed handles all the actions of a component.
		     **/
		    public void actionPerformed(ActionEvent e) {
		        String selectedLanguage = (String) comboLang.getSelectedItem();
		        if(selectedLanguage.equals("Choose a Language")) {
		        	txtBox.setText("Choose a Language");
		        }
		        if (selectedLanguage.equals("Java")) {
                    txtBox.setText("public class Main{\n    public static void main(String[] args) {\n\n        System.out.println(\"Hello, World!\");\n\n\n    }\n}");
                }
                if (selectedLanguage.equals("C#")) {
                	txtBox.setText("using System;\n    class Program {\n\n        static void Main(string[] args) {\n\n\n      Console.WriteLine(\"Hello World!\");\n\n\n\n   }\n }");
                }
                if (selectedLanguage.equals("C++")) {
                	txtBox.setText("#include <iostream>\n    int main() {\n\n        std::cout << \"Hello World!\" << std::endl;\n      return 0;\n    }");
                }
                if(selectedLanguage.equals("TXT")) {
		        	txtBox.setText("Type text here.");
		        }
		    }
		});
		comboLang.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboLang.setBackground(SystemColor.activeCaption);
		comboLang.setModel(new DefaultComboBoxModel(new String[] {"Choose a Language", "C++", "C#", "Java", "TXT"}));
		panel.add(comboLang, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.info);
		panel_1.setBounds(285, 23, 403, 377);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 383, 355);
		panel_1.add(scrollPane);
		
		txtBox.setColumns(5);
		txtBox.setText("Enter your code here...");
		txtBox.setBackground(SystemColor.inactiveCaptionBorder);
		txtBox.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 16));
		txtBox.setWrapStyleWord(true);
		txtBox.setLineWrap(true);
		scrollPane.setViewportView(txtBox);

		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(25, 102, 250, 298);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JButton btnCopy = new JButton("Copy");
		btnCopy.addActionListener(new ActionListener() {
			/**
		     * @name actionPerformed
		     * @param [in] e [\b ActionEvent]
		     * Here's an example on how to implement ActionListener in Java.
		     **/
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
		});
		btnCopy.setBackground(new Color(204, 204, 255));
		btnCopy.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCopy.setBounds(10, 61, 105, 37);
		panel_2.add(btnCopy);
		
		JButton btnPaste = new JButton("Paste");
		btnPaste.addActionListener(new ActionListener() {
			/**
		     * @name actionPerformed
		     * @param [in] e [\b ActionEvent]
		     * It is notified against MouseEvent. The MouseListener interface is found in java.
		     **/
		    public void actionPerformed(ActionEvent e) {
		        int caretPosition = txtBox.getCaretPosition();
		        String clipboardContent = getClipboardContent();
		        
		        if (clipboardContent != null) {
		            String text = txtBox.getText();
		            String newText = text.substring(0, caretPosition) + clipboardContent + text.substring(caretPosition);
		            txtBox.setText(newText);
		            
		            // Update caret position after pasting
		            txtBox.setCaretPosition(caretPosition + clipboardContent.length());
		        }
		    }
		    /**
		     * @name getClipboardContent
		     * @retval [\b String]
		     * Here's a demonstration of how to move text to and from the clipboard.
		     **/
		    private String getClipboardContent() {
		        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		        Transferable contents = clipboard.getContents(null);
		        
		        if (contents != null && contents.isDataFlavorSupported(DataFlavor.stringFlavor)) {
		            try {
		                return (String) contents.getTransferData(DataFlavor.stringFlavor);
		            } catch (Exception e) {
		                e.printStackTrace();
		            }
		        }
		        
		        return null;
		    }
		});
		btnPaste.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnPaste.setBackground(new Color(204, 204, 255));
		btnPaste.setBounds(136, 61, 104, 37);
		panel_2.add(btnPaste);
		
		JButton btnCut = new JButton("Cut");
		btnCut.addActionListener(new ActionListener() {
			/**
		     * @name actionPerformed
		     * @param [in] e [\b ActionEvent]
		     * The Java ActionListener is notified whenever you click on the button or menu item.
		     **/
		    public void actionPerformed(ActionEvent e) {
		        String selectedText = txtBox.getSelectedText();
		        
		        if (selectedText != null && !selectedText.isEmpty()) {
		            txtBox.cut();
		        }
		    }
		});
		btnCut.setBackground(new Color(204, 204, 255));
		btnCut.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCut.setBounds(75, 109, 105, 37);
		panel_2.add(btnCut);
		
		

		undoManager = new UndoManager();
		txtBox.getDocument().addUndoableEditListener(undoManager);
		JButton btnUndo = new JButton("Undo");
		btnUndo.addActionListener(new ActionListener() {
			/**
		     * @name actionPerformed
		     * @param [in] e [\b ActionEvent]
		     * A button is a Swing component in Java that is usually used to register some action from a user.
		     * The action comes in the form of a button being clicked.
		     **/
		    public void actionPerformed(ActionEvent e) {
		        if (undoManager.canUndo()) {
		            try {
		                undoManager.undo(); 
		            } catch (Exception ex) {
		                ex.printStackTrace();
		            }
		        }
		    }
		});
		btnUndo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnUndo.setBackground(new Color(204, 204, 255));
		btnUndo.setBounds(10, 157, 105, 37);
		panel_2.add(btnUndo);
		
		JButton btnRedo = new JButton("Redo");
		btnRedo.addActionListener(new ActionListener() {
			/**
		     * @name actionPerformed
		     * @param [in] e [\b ActionEvent]
		     * Redo with this button.
		     **/
		    public void actionPerformed(ActionEvent e) {
		        if (undoManager.canRedo()) { 
		            try {
		                undoManager.redo(); 
		            } catch (Exception ex) {
		                ex.printStackTrace();
		            }
		        }
		    }
		});
		btnRedo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnRedo.setBackground(new Color(204, 204, 255));
		btnRedo.setBounds(136, 157, 104, 37);
		panel_2.add(btnRedo);
		
		JButton btnCompile = new JButton("Compile");
		btnCompile.addActionListener(new ActionListener() {
		    /**
		     * @name actionPerformed
		     * @param [in] e [\b ActionEvent]
		     * Compile with this button.
		     **/
		    public void actionPerformed(ActionEvent e) {
		        JFileChooser fileChooser = new JFileChooser();
		        int result = fileChooser.showOpenDialog(null);

		        if (result == JFileChooser.APPROVE_OPTION) {
		            File selectedFile = fileChooser.getSelectedFile();
		            String filePath = selectedFile.getAbsolutePath();
		            String fileExtension = getFileExtension(selectedFile);

		            if (fileExtension.equalsIgnoreCase("txt")) {
		                comboLang.setSelectedItem("TXT");
		            } else if (fileExtension.equalsIgnoreCase("cs")) {
		                comboLang.setSelectedItem("C#");
		            } else if (fileExtension.equalsIgnoreCase("cpp")) {
		                comboLang.setSelectedItem("C++");
		            } else if (fileExtension.equalsIgnoreCase("java")) {
		                comboLang.setSelectedItem("Java");
		            }

		            try {
		                BufferedReader reader = new BufferedReader(new FileReader(filePath));
		                StringBuilder fileContent = new StringBuilder();
		                String line;

		                while ((line = reader.readLine()) != null) {
		                    fileContent.append(line).append("\n");
		                }

		                reader.close();
		                txtBox.setText(fileContent.toString());

		                // Derleme işlemi burada yapılacak
		                String compileMessage = "Derleme işlemi yapıldı."; // Derleme işleminin sonucu
		                JOptionPane.showMessageDialog(null, compileMessage, "Derleme İşlemi", JOptionPane.INFORMATION_MESSAGE);
		                
		                // Seçilen dosyanın adıyla aynı konumda .exe dosyası oluşturma
		                String executableFilePath = filePath.substring(0, filePath.lastIndexOf(".")) + ".exe";
		                File executableFile = new File(executableFilePath);
		                if (executableFile.createNewFile()) {
		                    JOptionPane.showMessageDialog(null, "Oluşturulan .exe dosyası:\n" + executableFilePath, "Dosya Oluşturuldu", JOptionPane.INFORMATION_MESSAGE);
		                } else {
		                    JOptionPane.showMessageDialog(null, "Dosya oluşturma başarısız.", "Hata", JOptionPane.ERROR_MESSAGE);
		                }
		            } catch (IOException ex) {
		                ex.printStackTrace();
		            }
		        }
		    }

		    private String getFileExtension(File file) {
		        String fileName = file.getName();
		        int dotIndex = fileName.lastIndexOf('.');

		        if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
		            return fileName.substring(dotIndex + 1).toLowerCase();
		        }

		        return "";
		    }
		});
		btnCompile.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCompile.setBackground(new Color(204, 204, 255));
		btnCompile.setBounds(60, 250, 134, 37);
		panel_2.add(btnCompile);
		
		JButton btnOpenFile = new JButton("Open File");
		btnOpenFile.addActionListener(new ActionListener() {
			/**
		     * @name actionPerformed
		     * @param [in] e [\b ActionEvent]
		     * With this button, the file is opened.
		     **/
		    public void actionPerformed(ActionEvent e) {
		        JFileChooser fileChooser = new JFileChooser();
		        int result = fileChooser.showOpenDialog(null);

		        if (result == JFileChooser.APPROVE_OPTION) {
		            File selectedFile = fileChooser.getSelectedFile();
		            String filePath = selectedFile.getAbsolutePath();
		            String fileExtension = getFileExtension(selectedFile);

		            if (fileExtension.equalsIgnoreCase("cpp")) {
		                comboLang.setSelectedItem("C++");
		            } else if (fileExtension.equalsIgnoreCase("cs")) {
		                comboLang.setSelectedItem("C#");
		            } else if (fileExtension.equalsIgnoreCase("java")) {
		                comboLang.setSelectedItem("Java");
		            } else if (fileExtension.equalsIgnoreCase("txt")) {
		                comboLang.setSelectedItem("TXT");
		            }

		            try {
		                BufferedReader reader = new BufferedReader(new FileReader(filePath));
		                StringBuilder fileContent = new StringBuilder();
		                String line;

		                while ((line = reader.readLine()) != null) {
		                    fileContent.append(line).append("\n");
		                }

		                reader.close();
		                txtBox.setText(fileContent.toString());

		                // Dosya başarıyla açıldı mesajı
		                JOptionPane.showMessageDialog(null, "Dosya başarıyla açıldı:\n" + filePath, "Açma Başarılı", JOptionPane.INFORMATION_MESSAGE);
		            } catch (IOException ex) {
		                ex.printStackTrace();
		            }
		        }
		    }
		    /**
		     * @name getFileExtension
		     * @param [in] file [\b File]
		     * @retval [\b String]
		     * If you don't see file name extensions when you view files in File Explore.
		     **/
		    private String getFileExtension(File file) {
		        String fileName = file.getName();
		        int dotIndex = fileName.lastIndexOf('.');

		        if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
		            return fileName.substring(dotIndex + 1).toLowerCase();
		        }

		        return "";
		    }
		});
		
		btnOpenFile.setBackground(new Color(204, 204, 255));
		btnOpenFile.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnOpenFile.setBounds(136, 205, 104, 37);
		panel_2.add(btnOpenFile);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			/**
		     * @name actionPerformed
		     * @param [in] e [\b ActionEvent]
		     * With this button, the file is saved.
		     **/
			public void actionPerformed(ActionEvent e) {
				String selectedLanguage = (String) comboLang.getSelectedItem();
				JFileChooser fileChooser = new JFileChooser();
				int result = fileChooser.showSaveDialog(null);

				if (result == JFileChooser.APPROVE_OPTION) {
				    File selectedFile = fileChooser.getSelectedFile();
				    String filePath = selectedFile.getAbsolutePath();

				    if (selectedLanguage.equals("C++")) {
				        filePath += ".cpp";
				    } else if (selectedLanguage.equals("C#")) {
				        filePath += ".cs";
				    } else if (selectedLanguage.equals("Java")) {
				        filePath += ".java";
				    } else if (selectedLanguage.equals("TXT")) {
				        filePath += ".txt";
				    }
				    

				    try {
				        FileWriter fileWriter = new FileWriter(filePath);
				        fileWriter.write(txtBox.getText());
				        fileWriter.close();
				        
				        // Dosya başarıyla kaydedildi mesajı
				        JOptionPane.showMessageDialog(null, "Dosya başarıyla kaydedildi:\n" + filePath, "Kaydetme Başarılı", JOptionPane.INFORMATION_MESSAGE);
				    } catch (IOException ex) {
				        ex.printStackTrace();
				    }
				}
			}
		});
		btnSave.setBackground(new Color(204, 204, 255));
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSave.setBounds(10, 205, 105, 37);
		panel_2.add(btnSave);
	}
	/**
     * @name getTextArea
     * @retval [\b JTextArea]
     * Use the value property to get the value of a textarea.
     **/
	public JTextArea getTextArea() {
        return txtBox;
    }
	/**
     * @name addCopyButtonListener
     * @param [in] listener [\b ActionListener]
     * This function writes the data fed to it as a parameter into the clipboard.
     * We can use this to copy any text to the clipboard.
     **/
    public void addCopyButtonListener(ActionListener listener) {
        btnCopy.addActionListener(listener);
    }
    /**
     * @name addPasteButtonListener
     * @param [in] listener [\b ActionListener]
     *  The Java ActionListener is notified whenever you click on the button or menu item. 
     *  It is notified against ActionEvent.
     **/
    public void addPasteButtonListener(ActionListener listener) {
        btnPaste.addActionListener(listener);
    }
    /**
     * @name getTextAreaText
     * @retval [\b String]
     * In the code below we create a JTextArea and sets its contents after we create a new instance of JTextArea .
     *  To get the contents we use the action on a button.
     **/
    public String getTextAreaText() {
        return txtBox.getText();
    }
    /**
     * @name setTextAreaText
     * @param [in] text [\b String]
     * The two arguments to the JTextArea constructor are hints as to the number of rows and columns, respectively, that the text area should display.
     **/
    public void setTextAreaText(String text) {
    	txtBox.append(text);
    }
    /**
     * @name setTextAreaCaretPosition
     * @param [in] position [\b int]
     * The object of a TextArea class is a multiline region that displays text.
     * It allows the editing of multiple line text.
     * It inherits TextComponent class.
     **/
    public void setTextAreaCaretPosition(int position) {
    	txtBox.setCaretPosition(position);
    }
    /**
     * @name getTextAreaCaretPosition
     * @retval [\b int]
     * This caret position method call will return the caret position as an integer offset from the beginning of the contents of your text file.
     **/
    public int getTextAreaCaretPosition() {
        return txtBox.getCaretPosition();
    }
    /**
     * @name addCutButtonListener
     * @param [in] listener [\b ActionListener]
     * However when my gui comes up and I press 'enter' after entering the input, nothing happens.
     **/
    public void addCutButtonListener(ActionListener listener) {
        btnCut.addActionListener(listener);
    }
    /**
     * @name addUndoButtonListener
     * @param [in] listener [\b ActionListener]
     * Undo allows users to correct their mistakes and also to try out different aspects of the application without risk of repercussions.
     **/
    public void addUndoButtonListener(ActionListener listener) {
        btnUndo.addActionListener(listener);
    }
    /**
     * @name addRedoButtonListener
     * @param [in] listener [\b ActionListener]
     * An action event occurs, whenever an action is performed by the user.
     **/
    public void addRedoButtonListener(ActionListener listener) {
        btnRedo.addActionListener(listener);
    }
}
