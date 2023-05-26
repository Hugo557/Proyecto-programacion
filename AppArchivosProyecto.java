package Unidad6;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.io.ObjectInputStream;

import Unidad2.Fecha;
import Unidad2.Persona;
import Unidad3y4.ProfesorTiempoCompleto;
import Unidad3y4.Profesor;
import Unidad3y4.ProfesorPorHoras;

public class AppArchivosProyecto {

	public static void main(String[] args) {
		String nombreArchivo = null;
		BufferedReader br = null;
		JFileChooser chooser = new JFileChooser(".");

		int selected = chooser.showOpenDialog(null);
		if (selected == JFileChooser.APPROVE_OPTION) {
			nombreArchivo = chooser.getSelectedFile().getName();
		}
		// Abrir archivo
		try {
			br = new BufferedReader(new FileReader(nombreArchivo));
			ObjectOutputStream archivoBinarioProfes = new ObjectOutputStream(new FileOutputStream("Profesores.dat"));
			Profesor unProfe = null;
			while (br.ready()) {
				// leer archivo

				String linea = br.readLine();
				String[] partesDeLinea = linea.split(",");
				String curp = partesDeLinea[0];
				String nombre = partesDeLinea[1];
				String telefono = partesDeLinea[2];
				String partesFecha[] = partesDeLinea[3].split("-");
				Fecha fecha = new Fecha(Integer.parseInt(partesFecha[0]), Integer.parseInt(partesFecha[1]),
						Integer.parseInt(partesFecha[2]));
				String clave = partesDeLinea[4];

				// String salida = "";
				// Crear archivo de profes

				if (partesDeLinea.length == 5) {
					// crear profesor tiempo completo
					unProfe = new ProfesorTiempoCompleto(curp, nombre, telefono, fecha, clave);

					// salida += unProfe;
				} else {
					int horas = Integer.parseInt(partesDeLinea[5]);
					// Leer horas y crear objeto de profesor por horas
					// ProfesorPorHoras otroProfe=new
					// ProfesorPorHoras(curp,nombre,telefono,fecha,clave,horas);
					unProfe = new ProfesorPorHoras(curp, nombre, telefono, fecha, clave, horas);
					// salida += unProfe;

				}
				archivoBinarioProfes.writeObject(unProfe);

				/*
				 * for (String parte : partesDeLinea) { System.out.println(parte + "\n"); }
				 * SalidaFor.imprimerConScroll(salida);
				 * 
				 * }
				 */
			}
			archivoBinarioProfes.close();
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("No se pudo abrir el archivo");
		} catch (IOException e) {
			System.out.println("Conflicto para escribir el archivo");
			e.printStackTrace();
		}

	}

	public static ArrayList<Persona> leerArchivo() throws ClassNotFoundException, IOException {
		ArrayList<Persona> listaProfes=new ArrayList<>();
		try (ObjectInputStream ois=new ObjectInputStream(new FileInputStream("Profesores.dat"))) {
			do {
				Persona profesor = (Persona) ois.readObject();
				listaProfes.add(profesor);
			} while (true);
		} catch (EOFException e) {
			// Se alcanzó el final del archivo, se sale del bucle
			System.out.println(e.getMessage());
		}
		System.out.println(listaProfes);
		return listaProfes;
	}
	public static String menu;{
		
	}
}
