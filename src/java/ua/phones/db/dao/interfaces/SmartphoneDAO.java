package ua.phones.db.dao.interfaces;

import ua.phones.db.models.Smartphone;

import java.util.List;
import java.util.Map;

public interface SmartphoneDAO {

    List<Smartphone> getAllSmartphones();

    Smartphone getSmartphone(int id);

    Smartphone getSmartphone(Smartphone smartphone);

    List<Smartphone> getSearchedPhones(String model);

    Smartphone getSmartPhoneByModel(String model);

    void insertSmartphone(Smartphone smartphone);

    public int getVendorCount(int vendorId);

    void deleteSmartphome(int id);

    void updateSmartphone(Smartphone smartphone);

    Map<String, Integer> getPhonesFromVendorsCount();
}
