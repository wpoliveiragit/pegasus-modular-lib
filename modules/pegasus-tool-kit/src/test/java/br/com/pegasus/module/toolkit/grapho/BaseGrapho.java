package br.com.pegasus.module.toolkit.grapho;

public class BaseGrapho implements ContantsGrapho {

	protected final int vertices; // Ponto de ligação de arestas.
	protected final int[][] matriz; // Matriz do grapho.

	public BaseGrapho(int vertices) {
		if (vertices < GraphoConstant.DOIS) { // Quantidade mínima de vertices nercessários
			throw new RuntimeException("A quantidade de vertices de um grapho deve ser maior que 1");
		}
		this.vertices = vertices;
		matriz = new int[vertices][vertices];
		for (int x = GraphoConstant.ZERO; x < vertices; x++) {
			for (int y = GraphoConstant.ZERO; y < vertices; y++) {
				matriz[x][y] = GraphoConstant.UM_; // add peso nulo ao vértice
			}
		}
	}

	protected void addAresta(int[] aresta) {
		if ((aresta[GraphoConstant.ZERO] < GraphoConstant.ZERO)//
				|| (aresta[GraphoConstant.ZERO] > vertices - GraphoConstant.UM)//
				|| (aresta[GraphoConstant.UM] < GraphoConstant.ZERO)//
				|| (aresta[GraphoConstant.UM] > vertices - GraphoConstant.UM)) {
			throw new RuntimeException("Aresta " + toString(aresta) + ": " + TXT_ERR_VERTICE);
		}
		if (aresta[GraphoConstant.DOIS] < GraphoConstant.UM) {
			throw new RuntimeException("Aresta " + toString(aresta) + ": " + TXT_ERR_PESO);
		}
		matriz[aresta[GraphoConstant.ZERO]][aresta[GraphoConstant.UM]] = aresta[GraphoConstant.DOIS];
	}

	public final void printMatriz() {
		for (int x = GraphoConstant.ZERO; x < vertices; x++) {
			for (int y = GraphoConstant.ZERO; y < vertices; y++) {
				if (matriz[x][y] > -GraphoConstant.UM) {
					System.out.println("aresta " + toString(new int[] { x, y, matriz[x][y] }));
				}
			}
		}
	}

	private String toString(int[] value) {
		return new StringBuffer()//
				.append("[").append(value[GraphoConstant.ZERO]).append("]")//
				.append(" -> ")//
				.append("[").append(value[GraphoConstant.UM]).append("]")//
				.append(" = ").append(value[GraphoConstant.DOIS])//
				.toString();
	}

}