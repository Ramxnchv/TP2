package simulator.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

import simulator.control.Controller;

public class VelocityTable extends JPanel {
	
	private VelocityTableModel tableModel;
	
	VelocityTable(Controller ctrl) {
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createTitledBorder(
		BorderFactory.createLineBorder(Color.black, 2),
		"Averages",
		TitledBorder.LEFT, TitledBorder.TOP));
		
		// TODO complete
		this.tableModel = new VelocityTableModel(ctrl);
		JTable tabla = new JTable(tableModel);
		
		//ocultamos las lineas horizontales y verticales
		tabla.setShowHorizontalLines(false);
		tabla.setShowVerticalLines(false);
		
		//mostramos la tabla completa
		tabla.setFillsViewportHeight(true);
		
		//la añadimos a su scrollpane
		JScrollPane scroll = new JScrollPane(tabla,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.add(scroll);
		}
}
