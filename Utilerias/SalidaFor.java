package Utilerias;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class SalidaFor {
	
	public static void imprimerConScroll(String cadena)
	{
		JTextArea area=new JTextArea(cadena,40,40);
		JScrollPane panel= new JScrollPane(area,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		JOptionPane.showMessageDialog(null, panel);
	}
}
