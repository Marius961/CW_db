package ua.phones.db.dao.interfaces;

import ua.phones.db.models.Smartphone;

import java.util.List;
import java.util.Map;

public interface SmartphoneDAO {

    List<Smartphone> getAllSmartphones();

    Smartphone getSmartphone(int id);

    Smartphone getSmartphone(Smartphone smartphone);

    List<Smartphone> getSmartPhonesByModel(String model);

    List<Smartphone> getSmartPhoneByVendorName(String vendorName);

    Smartphone getSmartPhoneByModel(String model);

    void insertSmartphone(Smartphone smartphone);

    void deleteSmartphome(int id);

    void updateSmartphone(Smartphone smartphone);

    Map<String, Integer> getPhonesFromVendorsCount();
}
