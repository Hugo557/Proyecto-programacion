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
					String telefono= partesDeLinea[2];
					String partesFecha[]=partesDeLinea[3].split("-");
					Fecha fecha=new Fecha(Integer.parseInt(partesFecha[0]),Integer.parseInt(partesFecha[1]),Integer.parseInt(partesFecha[2]));
					String clave=partesDeLinea[4];
					// partesDeLinea(fecha).split("-");
					if (partesDeLinea.length == 5) {
						// crear profesor tiempo completo
						ProfesorTiempoCompleto unProfe =new ProfesorTiempoCompleto(curp,
																		nombre,telefono,fecha,clave);
						
					} else {
						int horas=Integer.parseInt(partesDeLinea[5]);
						// Leer horas y crear objeto de profesor por horas
						ProfesorPorHoras otroProfe=new ProfesorPorHoras(curp,nombre,telefono,fecha,clave,horas);
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
			} catch (FileNotFoundException e) {
				System.out.println("No se pudo abrir el archivo");
				// Filenotfound
			} catch(IOException e){
				System.out.println("Conflicto para leer el archivo");
			}

	}
}
