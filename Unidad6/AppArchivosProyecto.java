package Unidad6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import Unidad3y4.Profesor;

public class AppArchivosProyecto {

	public static void main(String[] args) {
		String nombreArchivo = null;
		BufferedReader br = null;
		ArrayList<Profesor> listaProfesores = new ArrayList<>();
		JFileChooser chooser = new JFileChooser(".");

		int selected = chooser.showOpenDialog(null);
		if (selected == JFileChooser.APPROVE_OPTION) {
			nombreArchivo = chooser.getSelectedFile().getName();
		}
		// Abrir archivo
			try {
				br = new BufferedReader(new FileReader(nombreArchivo));
				while (br.ready()) {

					String linea = br.readLine();
					String[] partesDeLinea = linea.split(",");
					String curp = partesDeLinea[0];
					String nombre = partesDeLinea[1];
					// partesDeLinea(fecha).split("-");
					if (partesDeLinea.length == 5) {
						// crear profesor tiempo completo
					} else {
						// Leer horas y crear objeto de profesor por horas
					}
					for (String parte : partesDeLinea) {
						System.out.println(parte + "\n");

						/*
						 * Profesor unProfesor= new Profesor(partesDeLinea[0], partesDeLinea[1],
						 * partesDeLinea[2], partesDeLinea[3], partesDeLinea[4], partesDeLinea[5],
						 * Integer.parseInt(partesDeLinea[6]));
						 */

					}

				}
			} catch (IOException e) {
				// Filenotfound
			}

	}
}
