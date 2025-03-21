package br.com.pegasus.module.toolkit.grapho;

import java.util.ArrayList;
import java.util.List;

public class DijkstraGrapho extends BaseGrapho implements  ContantsGrapho {

	private List<Integer> prioridade;
	private boolean verticesVisitados[];
	private long distancias[];
	private long predecessor[];

	/**
	 * Recebe o numero de vertices do grafo
	 * 
	 * @param vertices quantidade de vertices que o grafo possui
	 */
	public DijkstraGrapho(int vertices) {
		super(vertices);
		prioridade = new ArrayList<>();
		verticesVisitados = new boolean[vertices];
		distancias = new long[vertices];
		predecessor = new long[vertices];
		for (int i = GraphoConstant.ZERO; i < vertices; i++) {
			distancias[i] = Long.MAX_VALUE; // add a maior distancia possível
			verticesVisitados[i] = false;
			predecessor[i] = Long.MAX_VALUE; // add a maior distancia possível
			prioridade.add(i);
		}
	}

	public long calculaPeso(int origem, int destino) throws RuntimeException {
		if ((origem < GraphoConstant.ZERO) || (origem > vertices) || (destino < GraphoConstant.ZERO) || (destino > vertices)) {
			throw new RuntimeException("Method CalculaPeso: " + TXT_ERR_VERTICE);
		}
		distancias[origem] = GraphoConstant.ZERO;
		while (!prioridade.isEmpty()) {
			long pesoMenor = Long.MAX_VALUE; // add a maior distancia possível
			int peso = GraphoConstant.ZERO;
			for (int vertice : prioridade) {
				if (distancias[vertice] < pesoMenor) {
					pesoMenor = distancias[vertice];
					peso = vertice;
				}
			}
			prioridade.remove(Integer.valueOf(peso));
			int verticeMenorPeso = peso;
			for (int vertice = GraphoConstant.ZERO; vertice < vertices; vertice++) {
				if (matriz[verticeMenorPeso][vertice] > GraphoConstant.ZERO) {
					relaxa(verticeMenorPeso, vertice);
				}
			}
		}
		return distancias[destino];
	}

	private void relaxa(int verticeMenorPeso, int vertice) {
		long peso = distancias[verticeMenorPeso] + matriz[verticeMenorPeso][vertice];
		if (distancias[vertice] > peso) {
			distancias[vertice] = peso;
			predecessor[vertice] = verticeMenorPeso;
		}
	}

	protected void printDistancias() {
		for (int i = GraphoConstant.ZERO; i < vertices; i++) {
			System.out.print("[" + distancias[i] + "] ");
		}
		System.out.println();
	}
}