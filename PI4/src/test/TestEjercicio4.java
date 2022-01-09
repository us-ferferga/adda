package test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.jgrapht.graph.SimpleWeightedGraph;

import ejercicios.Ejercicio4;
import ejercicios.entidades.Distancia;
import ejercicios.entidades.Persona;
import us.lsi.common.Trio;
import util.Helper;

public class TestEjercicio4 {

	public static Trio<Double, Double, List<Persona>> cargarDatos(String ruta) {
		Double distFilas = .0;
		Double distColumnas = .0;
		List<Persona> personas = new ArrayList<>();

		List<String> lineasFichero = Helper.leerLineas(ruta);
		for (String linea : lineasFichero) {
			if (linea.startsWith("distFilas=")) {
				distFilas = Double.parseDouble(linea.replace("distFilas=", ""));
			} else if (linea.startsWith("distColumnas=")) {
				distColumnas = Double.parseDouble(linea.replace("distColumnas=", ""));
			} else if (linea.startsWith("+")) {
				personas.add(Persona.create(linea.replace("+", "").split(","), true));
			} else if (linea.startsWith("-")) {
				personas.add(Persona.create(linea.replace("-", "").split(","), false));
			}
		}
		return Trio.of(distFilas, distColumnas, personas);
	}

	public static void main(String[] args) {
		for (int i = 1; i < 3; i++) {
			String path = System.getProperty("user.dir") + String.format("\\src\\ficheros\\PI4E4_DatosEntrada%d.txt", i);
			Trio<Double, Double, List<Persona>> datos = cargarDatos(path);

			SimpleWeightedGraph<Persona, Distancia> graph = new SimpleWeightedGraph<Persona, Distancia>(null, null);

			List<Persona> copiaPersonas = new ArrayList<Persona>(datos.third());

			for (Persona persona1 : datos.third()) {
				graph.addVertex(persona1);
				copiaPersonas.remove(copiaPersonas.indexOf(persona1));
				for (Persona persona2 : copiaPersonas) {
					graph.addVertex(persona2);

					Distancia j = Ejercicio4.pitagoras(persona1, persona2, datos.first(), datos.second());
					if (j.getDistance() < 1.2) {
						graph.addEdge(persona1, persona2, j);
					}
				}
			}
			
			String rutaSalida = System.getProperty("user.dir") + String.format("\\src\\ficheros\\PI4E4_ApartadoAyB_Test%d.gv", i);
			new File(rutaSalida).delete();
			Integer dias = Ejercicio4.AyB(graph, rutaSalida);
			System.out.println(String.format("Para PI4E4_DatosEntrada%d.txt hacen falta %d días", i, dias));
		}
	}
}
