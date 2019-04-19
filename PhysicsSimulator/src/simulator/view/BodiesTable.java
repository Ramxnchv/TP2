package simulator.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

import simulator.control.Controller;

public class BodiesTable extends JPanel {
	
	private BodiesTableModel tableModel;
	
	BodiesTable(Controller ctrl) {
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createTitledBorder(
		BorderFactory.createLineBorder(Color.black, 2),
		"Bodies",
		TitledBorder.LEFT, TitledBorder.TOP));
		
		// TODO complete
		this.tableModel = new BodiesTableModel(ctrl);
		JTable tabla = new JTable(tableModel);
		tabla.setFillsViewportHeight(true);
		JScrollPane scroll = new JScrollPane(tabla,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.add(scroll);
		}

}
