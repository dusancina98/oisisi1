package rs.ac.uns.ftn.projekat.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import rs.ac.uns.ftn.projekat.additionalclass.DateCellRenderer;
import rs.ac.uns.ftn.projekat.data.BazaStudent;


public class StudentJTable extends JTable {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;

	public static TableModel model;
	public static int selectedRow=-1;
	public static JTable jt = null;
	
	public StudentJTable() {
	
		this.setRowSelectionAllowed(true);
		this.setColumnSelectionAllowed(true);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.setModel(new AbstractTableModelStudent());
		this.getTableHeader().setReorderingAllowed(false);
		this.getColumnModel().getColumn(3).setCellRenderer(new DateCellRenderer());
		this.getColumnModel().getColumn(5).setCellRenderer(new DateCellRenderer());
		new ButtonColumnDetaljiStudent(this, 7);
		new ButtonColumnPredmetiStudenta(this,8);

		
		// sortiranje
		TableRowSorter<TableModel> sorter = new TableRowSorter<>(this.getModel());
		sorter.setSortable(7, false);
		sorter.setSortable(8, false);
		this.setRowSorter(sorter);
		
		jt=this;
	    model = this.getModel();
	    
	    this.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseReleased(MouseEvent e) {
	        	JTable jt = (JTable)e.getComponent();
	        	if(BazaStudent.indikator == 0) {
		        	if (jt.getSelectedRow() != -1)
		        		selectedRow=jt.convertRowIndexToModel(jt.getSelectedRow());
	        	}else {
	        		if (jt.getSelectedRow() != -1)
		        		selectedRow= BazaStudent.getInstance().getRealRowForFilter(jt.convertRowIndexToModel(jt.getSelectedRow()));
	        		
	        	}
	        }
	    });
	    
	}
	
	public static void osvezi() {
		((AbstractTableModel) model).fireTableDataChanged();
		StudentJTable.selectedRow = -1;
	}

	
	@Override
	public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
			Component c = super.prepareRenderer(renderer, row, column);
			if (isRowSelected(row)) {
				c.setBackground(Color.LIGHT_GRAY);
			} else {
				c.setBackground(Color.WHITE);
			}
			return c;
	}
	

}
