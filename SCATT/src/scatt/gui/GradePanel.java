package scatt.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.MigLayout;

/**
 * The GUI panel for grading.
 * 
 * @author Mikeal Anderson
 * @author Matt Stone
 * @version 1.0
 * 
 *          Video on jtables (netbeans english):
 *          https://www.youtube.com/watch?v=TwMXA1S38qg
 * 
 *          Video on jtables (eclipse espanol):
 *          https://www.youtube.com/watch?v=GaICabukib0
 */
public class GradePanel extends JPanel
{
    private static final long serialVersionUID = -3925933517802098991L;
    private JTable gradeTable;
    private DefaultTableModel tableModel;
    private GraderContext context;

    /**
     * Create the panel for grading.
     * 
     * @param context - an object that is used to store/retrieve grading related
     *            context.
     */
    public GradePanel(GraderContext context)
    {
        this.context = context;
        setLayout(new MigLayout("", "[97px,grow]", "[25px][grow]"));

        JButton btnNewButton = new JButton("Grade");
        btnNewButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                gradeButtonPressed();
            }
        });
        add(btnNewButton, "cell 0 0,alignx left,aligny top");

        JScrollPane scrollPane = new JScrollPane();
        add(scrollPane, "cell 0 1,grow");

        gradeTable = new JTable();
        //@formatter:off
        gradeTable.setModel(new DefaultTableModel(new Object[][] {},
                new String[] {"Student Name", "Grade" }));
        //@formatter:on
        scrollPane.setViewportView(gradeTable);
        tableModel = (DefaultTableModel) gradeTable.getModel();

    }

    /**
     * Handle event of grade button being pressed.
     */
    protected void gradeButtonPressed()
    {
        System.out.println("Grade pressed");
        // tableModel.addRow
        //@formatter:off
        tableModel
                .insertRow(tableModel.getRowCount(), new Object[] {"test1"});
        
        //@formatter:on

    }
}
