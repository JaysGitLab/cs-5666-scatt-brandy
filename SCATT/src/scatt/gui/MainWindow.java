package scatt.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JTabbedPane;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.SpringLayout;
import javax.swing.JInternalFrame;

public class MainWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -39970827731145361L;
	private JPanel contentPane;
	private JTextField textField;
//	private JFileChooser dirChooser;
//	private JFileChooser fileChooser;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
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
	public MainWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 0;
		gbc_tabbedPane.gridy = 0;
		contentPane.add(tabbedPane, gbc_tabbedPane);
		
		JPanel Load = new JPanel();
		tabbedPane.addTab("Load", null, Load, null);
		Load.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JPanel loader = new LoaderPanel();
		Load.add(loader);
		
		JPanel Configure = new JPanel();
		tabbedPane.addTab("Configure", null, Configure, null);
		Configure.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(144, 77, 114, 19);
		Configure.add(textField);
		textField.setColumns(10);
		
		JPanel Grade = new JPanel();
		tabbedPane.addTab("Grade", null, Grade, null);
		
//		dirChooser = new JFileChooser();
//        dirChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//
//        fileChooser = new JFileChooser();
//        fileChooser
//                .setFileFilter(new FileNameExtensionFilter("sb2 file", "sb2"));
//        
//        JButton btnJfilechoser = new JButton(".sb2 directory");
//
//        // The function called when the button is pressed.
//        btnJfilechoser.addActionListener(new ActionListener()
//        {
//            public void actionPerformed(ActionEvent arg0)
//            {
//                openDirectoryFromFileMenu();
//            }
//        });
//        Load.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
//        
//        Load.add(btnJfilechoser);
//		
//		JButton btnSinglesb = new JButton("single .sb2");
//        btnSinglesb.addActionListener(new ActionListener()
//        {
//            public void actionPerformed(ActionEvent arg0)
//            {
//                openFileFromFileMenu();
//            }
//        });
//        
//        Load.add(btnSinglesb);
        
//		
//	}
//	protected void openDirectoryFromFileMenu()
//    {
//        // open file chooser and then check if they approved a file
//        if (dirChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
//        {
//            File dir = dirChooser.getSelectedFile();
//            System.out.println("opened: " + dir.getAbsolutePath());
//            System.out.println("printing found .sb2 files in directory");
//
//            // find every .sb2 in this directory and load them into memory
//            // TODO (INCOMPLETE)
//            for (File file : dir.listFiles())
//            {
//                String path = file.getAbsolutePath();
//                if (path.substring(path.length() - 4).equals(".sb2"))
//                {
//                    // create a student object? printing for now
//                    System.out.println(file.getAbsolutePath());
//                }
//            }
//        }
//    }
//
//    /**
//     * Test method that opens up a single file from the file menu.
//     */
//    protected void openFileFromFileMenu()
//    {
//        // open file chooser and then check if they approved a file
//        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
//        {
//            File openedSb2 = fileChooser.getSelectedFile();
//            // below is an example of how it might look to create a student
//            // Student student = new Student(openedSb2.getAbsolutePath());
//            System.out.println(openedSb2.getAbsolutePath());
//
//        }

    }
}
