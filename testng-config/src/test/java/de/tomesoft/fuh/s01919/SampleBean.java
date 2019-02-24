package de.tomesoft.fuh.s01919;

public class SampleBean {
    private String id;
    private double x;
    private double y;

    public SampleBean() {
        this.id = "(none)";
        this.x = 0.0;
        this.y = 0.0;
    }

    public SampleBean(String id, double x, double y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
