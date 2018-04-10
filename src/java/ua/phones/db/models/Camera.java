package ua.phones.db.models;

public class Camera {

    private int id;
    private String resolution;
    private int numOfPixels;

    public int getNumOfPixels() {
        return numOfPixels;
    }

    public void setNumOfPixels(int numOfPixels) {
        this.numOfPixels = numOfPixels;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }


}
