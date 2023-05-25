package Unidad6;

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class AppFlujoEntradaScanner {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
		Scanner sc =new Scanner(new File("Reticula.txt"));
		String linea = sc.nextLine();
		System.out.println(linea);
		sc.close();
		
	}catch
	(FileNotFoundException e)
	{
		System.err.println("No pudo acceder al archivo");

	}
}
}