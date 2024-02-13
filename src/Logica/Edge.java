package Logica;

public class Edge<T> {
    private Vertex<T> destination;

    public void setTravelTime(int travelTime) {
        this.travelTime = travelTime;
    }

    private int travelTime;

    public void setDeliveryFee(double deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    private double deliveryFee;

    public Edge(Vertex<T> destination, int travelTime, double deliveryFee) {
        this.destination = destination;
        this.travelTime = travelTime;
        this.deliveryFee = deliveryFee;
    }

    public Vertex<T> getDestination() {
        return destination;
    }

    public int getTravelTime() {
        return travelTime;
    }

    public double getDeliveryFee() {
        return deliveryFee;
    }
}

