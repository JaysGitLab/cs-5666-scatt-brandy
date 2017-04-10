package scatt.gui;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.GridBagConstraints;
import java.awt.FlowLayout;

public class LoaderPanel extends JPanel {
	
    public FileHandler fileHandler;
	public JButton btnJfilechoser;
	public JButton btnSinglesb;
	/**
	 * Create the panel.
	 */
	public LoaderPanel() {
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		fileHandler = new FileHandler();
		
		btnJfilechoser = new JButton(".sb2 directory");

        // The function called when the button is pressed.
        btnJfilechoser.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                fileHandler.openDirectoryFromFileMenu();
            	//test();
            }
        });
        
        btnSinglesb = new JButton("single .sb2");
        btnSinglesb.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                fileHandler.openFileFromFileMenu();
            }
        });
        add(btnSinglesb);
        add(btnJfilechoser);

	}
	public void test(){
		System.out.println("TEST");
	}
}
