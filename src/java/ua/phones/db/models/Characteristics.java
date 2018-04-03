package ua.phones.db.models;

public class Characteristics {

    private int id;
    private int batteryVolume;
    private int processorId;
    private int cameraId;
    private int displayId;

    private Processor processor;
    private Camera camera;
    private Display display;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBatteryVolume() {
        return batteryVolume;
    }

    public void setBatteryVolume(int batteryVolume) {
        this.batteryVolume = batteryVolume;
    }

    public int getProcessorId() {
        return processorId;
    }

    public void setProcessorId(int processorId) {
        this.processorId = processorId;
    }

    public int getCameraId() {
        return cameraId;
    }

    public void setCameraId(int cameraId) {
        this.cameraId = cameraId;
    }

    public int getDisplayId() {
        return displayId;
    }

    public void setDisplayId(int displayId) {
        this.displayId = displayId;
    }

    public Processor getProcessor() {
        return processor;
    }

    public void setProcessor(Processor processor) {
        this.processor = processor;
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }
}
