package dialogues;

import javax.swing.JTable;
import javax.swing.event.TableModelListener;

public class TableauPreferences extends JTable implements TableModelListener {
	
	public TableauPreferences(Object[][] donnees, String[] enTetes) {
		super(donnees, enTetes);
		getModel().addTableModelListener(this);
	}
	


}
