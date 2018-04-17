package ua.phones.db.services.interfaces;

import ua.phones.db.models.*;

import java.util.List;
import java.util.Map;

public interface PhonesTableService {

    List<Smartphone> getAllSmartphones();

    List<Smartphone> search(String name);

    Smartphone fillSmartphone(Smartphone smartphone);

    Characteristics fillCharacteristics(int id);

    int addCamera(Camera camera);

    void updateCamera(Camera camera);

    void  deleteCamera(int id);

    int addDisplay(Display display);

    void updateDisplay(Display display);

    void deleteDisplay(int id);

    int addProcessor(Processor processor);

    void updateProcessor(Processor processor);

    void deleteProcessor(int id);

    int addCharacteristics(Characteristics characteristics);

    void updateCharacteristics(Characteristics characteristics);

    void deleteCharacteristics(int id);

    int addVendor(Vendor vendor);

    void updateVendor(Vendor vendor);

    void deleteVendor(int id);

    void addSmartPhone(Smartphone smartphone);

    void updateSmartPhone(Smartphone smartphone);

    void deleteSmartPhone(int id);

    Map<String, Integer> getCountOfSmartphones();
}
