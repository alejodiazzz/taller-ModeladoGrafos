package Logica;

public class Vertex<T> {
    public void setInfo(T info) {
        this.info = info;
    }

    private T info;

    public Vertex(T info) {
        this.info = info;
    }

    public T getInfo() {
        return info;
    }
}

