package ua.phones.db.dao.interfaces;

import ua.phones.db.models.Smartphone;

import java.util.List;

public interface SmartphoneDAO {

    List<Smartphone> getAllSmartphones();

    Smartphone getSmartphone(int id);

    Smartphone getSmartphone(Smartphone smartphone);

    Smartphone getSmartPhoneByModel(String model);

    void insertSmartphone(Smartphone smartphone);

    void deleteSmartphome(int id);

    void updateSmartphone(Smartphone smartphone);

}
