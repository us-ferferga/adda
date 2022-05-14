package test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ejercicios.ejercicio1.AlgoritmoGenetico;
import ejercicios.ejercicio1.Modelo;
import ejercicios.ejercicio1.Solucion;
import us.lsi.ag.agchromosomes.AlgoritmoAG;
import us.lsi.ag.agstopping.StoppingConditionFactory;
import us.lsi.ag.agstopping.StoppingConditionFactory.StoppingConditionType;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;
import util.Helper;

public class TestEjercicio1 {

	public static Solucion testPl(Class<?> f, String model, String modelOutput) {
		try {
			AuxGrammar.generate(f, model, modelOutput);
		} catch (IOException e) {
			e.printStackTrace();
		}

		GurobiSolution sol = GurobiLp.gurobi(modelOutput);
		return new Solucion(sol);
	}

	public static Solucion testAG() {
		AlgoritmoAG.POPULATION_SIZE = 2;
		StoppingConditionFactory.NUM_GENERATIONS = 16;
		StoppingConditionFactory.FITNESS_MIN = 12.0;
		StoppingConditionFactory.SOLUTIONS_NUMBER_MIN = 1;
		StoppingConditionFactory.stoppingConditionType = StoppingConditionType.SolutionsNumber;
		var alg = AlgoritmoAG.of(new AlgoritmoGenetico());
		alg.ejecuta();
		return alg.bestSolution();
	}

	public static void main(String[] args) {
		String model = System.getProperty("user.dir") + "\\modelos_pl\\ficheros.lsi";
		Map<Integer, List<Solucion>> resultados = new HashMap<Integer, List<Solucion>>();
		// Repetimos el bucle dos veces para evitar mezclar los resultados con la salida
		// de Gurobi, ya que sobreentiendo que no se puede modificar el
		// código de los repositorios para que Gurobi se invoque
		// sin la salida de los resultados de la optimización.

		for (int i = 1; i < 4; i++) {
			String inputPath = System.getProperty("user.dir") + "\\tests\\PI5Ej1DatosEntrada" + i + ".txt";
			String modelOutput = System.getProperty("user.dir") + "\\modelos_gurobi\\ficheros-" + i + ".lp";
			new File(modelOutput).delete();
			List<String> lineas = Helper.leerLineas(inputPath);

			new Modelo(lineas);
			Solucion solPL = testPl(Modelo.class, model, modelOutput);
			Solucion solAG = testAG();
			resultados.put(i, List.of(solPL, solAG));
		}

		for (int i = 1; i < 4; i++) {
			List<Solucion> par = resultados.get(i);
			Solucion pl = par.get(0);
			Solucion ag = par.get(1);

			System.out.println("======== PLE (PI5Ej1DatosEntrada" + i + ".txt) ========");
			System.out.println();
			System.out.println(pl);
			System.out.println();
			System.out.println("======== PLE (PI5Ej1DatosEntrada" + i + ".txt) ========");
			System.out.println("========  AG (PI5Ej1DatosEntrada" + i + ".txt) ========");
			System.out.println();
			System.out.println(ag);
			System.out.println();
			System.out.println("========  AG (PI5Ej1DatosEntrada" + i + ".txt) ========");
		}
	}
}