package ua.phones.db.dao.interfaces;

import ua.phones.db.models.Smartphone;

import java.util.List;

public interface SmartphoneDAO {

    List<Smartphone> getAllSmartphones();

    Smartphone getSmartphome(int id);

    boolean insertSmartphone(Smartphone smartphone);

    boolean deleteSmartphome(int id);

    boolean updateSmartphone(Smartphone smartphone);
}
