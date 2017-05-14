/*
 * Copyright 2017 Régis Bouguin
 * 
 * This file is part of coUnss
 * 
 * coUnss is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License 
 * as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * 
 * coUnss is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with Foobar.  If not, see <http://www.gnu.org/licenses/>
 * 
 */
package dialogues;

import java.util.Vector;

import javax.swing.JTable;
import javax.swing.event.TableModelListener;

public class TableauPreferences extends JTable implements TableModelListener {
	
	public TableauPreferences(Object[][] donnees, String[] enTetes) {
		super(donnees, enTetes);
		getModel().addTableModelListener(this);
	}
	
	public TableauPreferences(Vector<Vector<String>> donnees, Vector<String> enTetes) {
		super(donnees, enTetes);
		getModel().addTableModelListener(this);
	}
	

}
