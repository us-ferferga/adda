package test;

import java.util.List;

import ejercicios.ejercicio5.AlgoritmoGenetico;
import ejercicios.ejercicio5.Modelo;
import ejercicios.ejercicio5.Solucion;
import us.lsi.ag.agchromosomes.AlgoritmoAG;
import us.lsi.ag.agstopping.StoppingConditionFactory;
import util.Helper;

public class TestEjercicio5 {

	public static Solucion testAG() {
		AlgoritmoAG.ELITISM_RATE = 0.30;
		AlgoritmoAG.CROSSOVER_RATE = 0.8;
		AlgoritmoAG.MUTATION_RATE = 0.7;
		AlgoritmoAG.POPULATION_SIZE = 50;

		StoppingConditionFactory.NUM_GENERATIONS = 40000;
		StoppingConditionFactory.SOLUTIONS_NUMBER_MIN = 1;
		StoppingConditionFactory.FITNESS_MIN = 317.7;
		StoppingConditionFactory.stoppingConditionType = StoppingConditionFactory.StoppingConditionType.GenerationCount;
		var alg = AlgoritmoAG.of(new AlgoritmoGenetico());
		alg.ejecuta();
		return alg.bestSolution();
	}

	public static void main(String[] args) {
		for (int i = 1; i < 4; i++) {
			String inputPath = System.getProperty("user.dir") + "\\tests\\PI5Ej5DatosEntrada" + i + ".txt";
			String referenceFilePath = System.getProperty("user.dir") + "\\tests\\PI5Ej5DatosEntrada.txt";

			List<String> referenceFileLines = Helper.leerLineas(referenceFilePath);
			new Modelo(inputPath);
			Modelo.predicadosOrigenDestino(referenceFileLines, i);
			Solucion solAG = testAG();
			System.out.println("========  AG (PI5Ej5DatosEntrada" + i + ".txt) ========");
			System.out.println();
			System.out.println(solAG);
			System.out.println();
			System.out.println("========  AG (PI5Ej5DatosEntrada" + i + ".txt) ========");
		}
	}
}
