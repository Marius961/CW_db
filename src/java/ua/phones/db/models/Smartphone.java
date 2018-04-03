package ua.phones.db.models;

public class Smartphone {

    private int id;
    private int vendorId;
    private String model;
    private int characteristicsId;

    private Vendor vendor;
    private Characteristics characteristics;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVendorId() {
        return vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getCharacteristicsId() {
        return characteristicsId;
    }

    public void setCharacteristicsId(int characteristicsId) {
        this.characteristicsId = characteristicsId;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public Characteristics getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(Characteristics characteristics) {
        this.characteristics = characteristics;
    }
}
