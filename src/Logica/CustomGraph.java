package Logica;

import org.jgrapht.Graph;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CustomGraph<T> {
    private Comparator<T> comparator;
    public static final int DIRECT = 0;
    public static final int INDIRECT = 1;

    private Graph<T, DefaultWeightedEdge> graph;

    public CustomGraph(Comparator<T> comparator) {
        this.comparator = comparator;
        this.graph = new DefaultDirectedWeightedGraph<>(DefaultWeightedEdge.class);
    }

    public void addVertex(T info) {
        graph.addVertex(info);
    }

    public boolean addEdge(T source, T destination, int type, int travelTime) {
        if (type == DIRECT || type == INDIRECT) {
            if (!graph.containsVertex(source) || !graph.containsVertex(destination)) {
                throw new IllegalArgumentException("Ambos vértices deben existir en el grafo");
            }

            if (!graph.containsEdge(source, destination)) {
                DefaultWeightedEdge edge = graph.addEdge(source, destination);
                graph.setEdgeWeight(edge, travelTime); // Peso directo igual al tiempo de viaje
                return true;
            }
        }

        return false;
    }

    public List<T> listGraph() {
        return new ArrayList<>(graph.vertexSet());
    }

    public String simulatePackageDelivery(T start, T destination) {
        if (!graph.containsVertex(start) || !graph.containsVertex(destination)) {
            throw new IllegalArgumentException("Las ciudades de inicio y destino deben existir en el grafo");
        }

        DijkstraShortestPath<T, DefaultWeightedEdge> dijkstra = new DijkstraShortestPath<>(graph);
        List<DefaultWeightedEdge> shortestPath = dijkstra.getPath(start, destination).getEdgeList();

        if (shortestPath == null) {
            return "No hay ruta disponible entre " + start + " y " + destination;
        }

        int totalTime = 0;
        int totalCost = 0;

        StringBuilder result = new StringBuilder();

        for (DefaultWeightedEdge edge : shortestPath) {
            T source = graph.getEdgeSource(edge);
            T target = graph.getEdgeTarget(edge);
            int travelTime = (int) graph.getEdgeWeight(edge);

            int deliveryFee = travelTime * 333;

            result.append("Desde ").append(source).append(" hasta ").append(target).append(":\n");
            result.append(" - Tiempo de viaje: ").append(travelTime).append(" minutos\n");
            result.append(" - Tarifa de entrega: $").append(deliveryFee).append("\n");

            totalTime += travelTime;
            totalCost += deliveryFee;
        }

        result.append("Tiempo de viaje total: ").append(totalTime).append(" minutos\n");
        result.append("Costo total de entrega: $").append(totalCost);

        return result.toString();
    }

}
