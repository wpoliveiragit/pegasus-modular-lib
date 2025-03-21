package br.com.pegasus.module.toolkit.grapho;

import java.util.stream.Stream;

public class MainGraphoTest {

	public static void main(String args[]) {
		DijkstraGrapho dij = new DijkstraGrapho(5);
		dij.addAresta(toArrayInt("-0", "1", "10"));
		dij.addAresta(toArrayInt("0", "2", "5"));
		dij.addAresta(toArrayInt("1", "2", "2"));
		dij.addAresta(toArrayInt("1", "3", "1"));
		dij.addAresta(toArrayInt("2", "1", "3"));
		dij.addAresta(toArrayInt("2", "3", "9"));
		dij.addAresta(toArrayInt("2", "4", "2"));
		dij.addAresta(toArrayInt("3", "4", "4"));
		dij.addAresta(toArrayInt("4", "3", "6"));
		dij.addAresta(toArrayInt("4", "0", "7"));

		int origem = 0;
		int destino = 3;

		dij.printMatriz();
		System.out.println("-----");
		System.out.printf("Menor distancia entre (%d x %d) é %d\n", origem, destino, dij.calculaPeso(origem, destino));

		dij.printDistancias();
	}

	private static int[] toArrayInt(String... values) {
		return Stream.of(values).mapToInt(Integer::parseInt).toArray();
	}

}