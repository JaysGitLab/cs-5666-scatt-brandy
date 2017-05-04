package scatt.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.MigLayout;
import scatt.GraderWeightedComponent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

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
    private JLabel lblFormula;

    /**
     * Create the panel for grading.
     * 
     * @param context - an object that is used to store/retrieve grading related
     *            context.
     */
    public GradePanel(GraderContext context)
    {
        addFocusListener(new FocusAdapter()
        {
            @Override
            public void focusGained(FocusEvent arg0)
            {
                updateFormulaLabel();
            }
        });
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
        add(btnNewButton, "flowx,cell 0 0,alignx left,aligny top");

        JScrollPane scrollPane = new JScrollPane();
        add(scrollPane, "cell 0 1,grow");

        gradeTable = new JTable();
        //@formatter:off
        gradeTable.setModel(new DefaultTableModel(new Object[][] {},
                new String[] {"Student Name", "Grade" }));
        //@formatter:on
        scrollPane.setViewportView(gradeTable);

        lblFormula = new JLabel("Formula:");
        add(lblFormula, "cell 0 0");
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
        updateFormulaLabel();
        gradeAndAddEntries();
        //@formatter:on

    }

    /**
     * Grade and add graded entries to the table.
     */
    private void gradeAndAddEntries()
    {
        removeAllRows();
        try
        {
            context.grade();
            ArrayList<String[]> pairs = context.getGradedPairList();
            for (String[] pair : pairs)
            {
                // @formatter:off
                tableModel.insertRow(tableModel.getRowCount(), new Object[] {
                        pair[0], pair[1] });
                // @formatter:on
            }
        }
        catch (WeightedGraderFailureException e)
        {
            displayGraderFailedAsStudentEntry();
        }
    }

    /**
     * Updates the formula label to reflect weights of components.
     */
    public void updateFormulaLabel()
    {
        if (context != null)
        {
            Object[] allComponents = context.getAllComponents();
            String newLabelStr = "";

            for (Object obj : allComponents)
            {
                GraderWeightedComponent comp = (GraderWeightedComponent) obj;
                newLabelStr += " + ("
                        + String.format("%.3f", comp.getWeightFrom0To1())
                        + ")*";
                String moduleName = comp.getModuleName();
                moduleName = moduleName.split(" ")[0];
                
                newLabelStr += moduleName;
            }
            newLabelStr = newLabelStr.substring(3);
            lblFormula.setText("Formula: " + newLabelStr);
        }
    }

    /**
     * Clears and adds a single student entry that lets user know there is a
     * problem with grader.
     */
    private void displayGraderFailedAsStudentEntry()
    {
        //@formatter:off
        removeAllRows();
        tableModel
                .insertRow(tableModel.getRowCount(), new Object[] {
                    "Grader failed",
                    "please check weights"});
        updateFormulaLabel();
        //@formatter:on

    }

    /**
     * Remove all the rows from the table.
     */
    private void removeAllRows()
    {
        for (int i = tableModel.getRowCount() - 1; i >= 0; --i)
        {
            tableModel.removeRow(i);
        }
    }
}
