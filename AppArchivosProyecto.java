package Unidad6;

import java.io.BufferedReader;

//import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.io.ObjectInputStream;

import Unidad2.Fecha;
import Unidad2.Persona;
import Unidad3y4.ProfesorTiempoCompleto;
import Utilerias.SalidaFor;
import Unidad3y4.Profesor;
import Unidad3y4.ProfesorPorHoras;

public class AppArchivosProyecto {
	static AppArchivosProyecto obj = new AppArchivosProyecto();
	static ArrayList<Persona> listaProfes = null;
	//Creacion archivo binario
	public static void CreacionArchivo() {
		String nombreArchivo = null;
		BufferedReader br = null;
		JFileChooser chooser = new JFileChooser(".");

		int selected = chooser.showOpenDialog(null);
		if (selected == JFileChooser.APPROVE_OPTION) {
			nombreArchivo = chooser.getSelectedFile().getName();
		}
		// Abrir archivo
		try {
			File archivoEntrada = new File(nombreArchivo);
			br = new BufferedReader(new FileReader(archivoEntrada));
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

				if (partesDeLinea.length == 5) {
					// crear profesor tiempo completo
					unProfe = new ProfesorTiempoCompleto(curp, nombre, telefono, fecha, clave);
				} else {
					int horas = Integer.parseInt(partesDeLinea[5]);
					// Leer horas y crear objeto de profesor por horas
					unProfe = new ProfesorPorHoras(curp, nombre, telefono, fecha, clave, horas);
				}
				archivoBinarioProfes.writeObject(unProfe);
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

	//Lectura del archivo binario
	public ArrayList<Persona> leerArchivo() {
		try {
			ArrayList<Persona> listaProfes = new ArrayList<>();
			File archivoEntrante = new File("Profesores.dat");
			FileInputStream archBinario = new FileInputStream(archivoEntrante);
			ObjectInputStream objBytes = new ObjectInputStream(archBinario);
			Persona profesor = null;

			while (archBinario.available() > 0) {
				try {
					profesor = (Persona) objBytes.readObject();
				} catch (ClassNotFoundException e) {

				}
				listaProfes.add(profesor);
			}
			archBinario.close();
			return listaProfes;
		} catch (FileNotFoundException e) {
			System.err.println("No se a encontrado el archivo");
			return null;
		} catch (IOException e) {
			System.err.println("No se pudo leer el archivo");
			return null;
		}
	}
	//Metodo para los trabajadores con sueldo menor a x
	public static String nombresYTelefonos() {
		try {
			String listaNom = "";
			double salario = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el salario"));
			for (Persona profesor : listaProfes) {
				if (((Profesor) profesor).getSalario() < salario) {
					listaNom += "Nombres: " + profesor.getNombre() + "\nTelefono: " + profesor.getTelefono()
							+ "\n----------------------\n";
				}
			}

			SalidaFor.imprimerConScroll(listaNom);
		} catch (NullPointerException e) {
			System.out.println("Aún no se han leído el archivo de Objetos");
			return null;
		}
		return null;
	}

	public static String cumpleaños() {
		try {
			String cump = "";
			String[] mes = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" };
			String num;
			num = (String) (JOptionPane.showInputDialog(null, "Selecciona una mes", "", JOptionPane.PLAIN_MESSAGE, null,
					mes, mes[0]));

			for (Persona profesor : listaProfes) {
				if (((Profesor) profesor).getFecha().getMes() == Integer.parseInt(num)) {
					cump += "Nombres: " + profesor.getNombre() + "\nCurp: " + profesor.getCurp()
							+ "\n----------------------\n";
				}
			}

			SalidaFor.imprimerConScroll(cump);
		} catch (NullPointerException e) {
			System.out.println("Aún no se han leído el archivo de Objetos");
			return null;
		}
		return null;

	}

	public static void main(String[] args) {

		Object[] opciones = { "Crear Archivo", "Leer Archivo", "Lista de personas", "Personas con salario bajo",
				"Cumpleaños", "Salir" };
		String tipo = "";
		do {
			try {
				tipo = (String) JOptionPane.showInputDialog(null, "Selecciona una opcion", tipo,
						JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);
				if (tipo == null) {
					tipo = "Salir";
				}
			} catch (NullPointerException e) {
				tipo = "Salir";
			}

			switch (tipo) {
			case "Crear Archivo":
				CreacionArchivo();
				break;
			case "Leer Archivo":

				listaProfes = obj.leerArchivo();
				break;
			case "Lista de personas":
				try {
					String listaCadaProfe = "Lista Completa de Profesores" + "\n";
					for (Persona profesor : listaProfes) {
						listaCadaProfe += profesor.toString() + "\n----------------------\n";
					}
					SalidaFor.imprimerConScroll(listaCadaProfe);
				} catch (NullPointerException e) {
					System.out.println("Aún no se han leído el archivo de Objetos");
				}
				break;
			case "Personas con salario bajo":
				nombresYTelefonos();
				break;
			case "Cumpleaños":
				cumpleaños();
				break;
			case "Salir":
				break;
			}

		} while (!tipo.equals("Salir"));
	}
}