package ua.phones.db.services.interfaces;

import ua.phones.db.models.*;

import java.util.List;

public interface PhonesTableService {

    List<Smartphone> getAllSmartphones();

    Smartphone fillSmartphone(Smartphone smartphone);

    Characteristics fillCharacteristics(int id);

    int addCamera(Camera camera);

    void  deleteCamera(int id);

    int addDisplay(Display display);

    void deleteDisplay(int id);

    int addProcessor(Processor processor);

    void deleteProcessor(int id);

    int addCharacteristics(Characteristics characteristics);

    void deleteCharacteristics(int id);

    int addVendor(Vendor vendor);

    void deleteVendor(int id);

    void addSmartPhone(Smartphone smartphone);

    void deleteSmartPhone(int id);
}
