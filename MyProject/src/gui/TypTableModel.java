package gui;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import typ.*;

//import javax.swing.event.*;

public class TypTableModel extends AbstractTableModel {

	private static final int id_col = 0;
	private static final int name_col = 1;
	private static final int modellbez_col = 2;
	private static final int entw_baur_col = 3;
	private static final int antr_arch_col = 4;
	private static final int marke_col = 5;
	private static final int storniert_col = 6;
	
	private String[] columnNames = {"id", "name", "modellbez", "entw_baur", "antr_arch", "marke", "storniert"};
	
	private List<TypBE> list;
	
	public TypTableModel(List<TypBE> list) {
		this.list = list;
	}
	
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}
	
	@Override
	public int getRowCount() {
		return list.size();
	}
	
	@Override
	public String getColumnName (int col) {
		return columnNames[col];
	}
	
	public Object getValueAt (int row, int col) {
		TypBE typBE = list.get(row);
		switch (col) {
			case id_col: return typBE.getId();
			case name_col: return typBE.getName();
			case modellbez_col: return typBE.getModellbez();
			case entw_baur_col: return typBE.getEntw_baur();
			case antr_arch_col: return typBE.getAntr_arch();
			case marke_col: return typBE.getMarke();
			case storniert_col: return typBE.getStorniert();
			default: return typBE.getModellbez();
		} 
	}
	
	@Override
	public Class getColumnClass (int col) {
		return getValueAt (0, col).getClass();
	}
	
	public static void main(String[] args) {

	}

}
