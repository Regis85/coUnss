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
