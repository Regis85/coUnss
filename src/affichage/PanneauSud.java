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

package affichage;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class PanneauSud extends JPanel {

	public PanneauSud() {
		
		this.setBorder(new BevelBorder(BevelBorder.LOWERED));
		this.setLayout(new BorderLayout());
		
		JPanel menuResultats = new JPanel();
		JPanel resultats = new JPanel();
		
		menuResultats.setLayout(new FlowLayout(FlowLayout.LEFT));
		menuResultats.add(new JLabel("Menu résultats"));
		
		this.add(menuResultats, BorderLayout.NORTH);
		this.add(resultats, BorderLayout.CENTER);
		this.add(new JPanel().add(new JLabel("Menu résultats")), BorderLayout.SOUTH);
		
		resultats.add(new JLabel(Integer.toString(resultats.getHeight())));
		
	}
}
