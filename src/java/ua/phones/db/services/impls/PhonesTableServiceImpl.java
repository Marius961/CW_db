package ua.phones.db.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.phones.db.dao.interfaces.*;
import ua.phones.db.models.*;
import ua.phones.db.services.interfaces.PhonesTableService;

import java.util.*;


@Service
public class PhonesTableServiceImpl implements PhonesTableService {

    private CameraDAO cameraDAO;
    private CharacteristicsDAO characteristicsDAO;
    private DisplayDAO displayDAO;
    private ProcessorDAO processorDAO;
    private SmartphoneDAO smartphoneDAO;
    private VendorDAO vendorDAO;

    @Autowired
    public void setDAO(CameraDAO cameraDAO, CharacteristicsDAO characteristicsDAO, DisplayDAO displayDAO, ProcessorDAO processorDAO, SmartphoneDAO smartphoneDAO, VendorDAO vendorDAO) {
        this.cameraDAO = cameraDAO;
        this.characteristicsDAO = characteristicsDAO;
        this.displayDAO = displayDAO;
        this.processorDAO = processorDAO;
        this.smartphoneDAO = smartphoneDAO;
        this.vendorDAO = vendorDAO;
    }

    @Override
    public List<Smartphone> getAllSmartphones() {
        List<Smartphone> smartPhones = smartphoneDAO.getAllSmartphones();
        if (smartPhones == null) {
            return null;
        }
        for (Smartphone smartphone : smartPhones) {
            fillSmartphone(smartphone);
        }
        return  smartPhones;
    }

    @Override
    public List<Smartphone> search(String name) {
        List<Smartphone> smartPhones = smartphoneDAO.getSearchedPhones(name);

        for (Smartphone smartphone : smartPhones) {
            fillSmartphone(smartphone);
            System.out.println(smartphone.getVendor().getName());
        }
        return smartPhones;
    }

    @Override
    public Smartphone fillSmartphone(Smartphone smartphone) {
        Characteristics characteristics = fillCharacteristics(smartphone.getCharacteristicsId());
        smartphone.setCharacteristics(characteristics);
        Vendor vendor = vendorDAO.getVendor(smartphone.getVendorId());
        smartphone.setVendor(vendor);
        return smartphone;
    }

    @Override
    public Characteristics fillCharacteristics(int id) {
        Characteristics characteristics = characteristicsDAO.getCharacteristics(id);
        characteristics.setDisplay(displayDAO.getDisplay(characteristics.getDisplayId()));
        characteristics.setCamera(cameraDAO.getCamera(characteristics.getCameraId()));
        characteristics.setProcessor(processorDAO.getProcessor(characteristics.getProcessorId()));
        return characteristics;
    }

    @Override
    public int addCamera(Camera camera) {
        Camera tempCamera = cameraDAO.getCamera(camera);
        if (tempCamera != null) {
            return tempCamera.getId();
        }
        return cameraDAO.insertCamera(camera);
    }

    @Override
    public void updateCamera(Camera camera) {
        Camera tempCamera = cameraDAO.getCamera(camera.getId());
        if (tempCamera != camera) {
            cameraDAO.updateCamera(camera);
        }
    }

    @Override
    public void deleteCamera(int id) {
        cameraDAO.deleteCamera(id);
    }

    @Override
    public int addDisplay(Display display) {
        Display tempDisplay = displayDAO.getDisplay(display);
        if (tempDisplay != null) {
            return tempDisplay.getId();
        }
        return displayDAO.insertDisplay(display);
    }

    @Override
    public void updateDisplay(Display display) {
        Display tempDisplay = displayDAO.getDisplay(display.getId());
        if (tempDisplay != display) {
            displayDAO.updateDisplay(display);
        }
    }

    @Override
    public void deleteDisplay(int id) {
        displayDAO.deleteDisplay(id);
    }

    @Override
    public int addProcessor(Processor processor) {
        Processor tempProcessor = processorDAO.getProcessor(processor);
        if (tempProcessor != null) {
            return tempProcessor.getId();
        }
        return processorDAO.insertProcessor(processor);
    }

    @Override
    public void updateProcessor(Processor processor) {
        Processor tempProcessor = processorDAO.getProcessor(processor.getId());
        if (tempProcessor != processor) {
            processorDAO.updateProcessor(processor);
        }
    }

    @Override
    public void deleteProcessor(int id) {
        processorDAO.deleteProcessor(id);
    }

    @Override
    public int addCharacteristics(Characteristics characteristics) {
        Characteristics tempCharacteristics1 = characteristicsDAO.getCharacteristics(characteristics);
        if (tempCharacteristics1 != null) {
            return tempCharacteristics1.getId();
        }
        characteristics.setCameraId(addCamera(characteristics.getCamera()));
        characteristics.setDisplayId(addDisplay(characteristics.getDisplay()));
        characteristics.setProcessorId(addProcessor(characteristics.getProcessor()));
        return characteristicsDAO.insertCharacteristics(characteristics);
    }

    @Override
    public void updateCharacteristics(Characteristics characteristics) {
        updateCamera(characteristics.getCamera());
        updateProcessor(characteristics.getProcessor());
        updateDisplay(characteristics.getDisplay());
    }

    @Override
    public void deleteCharacteristics(int id) {
        Characteristics characteristics = characteristicsDAO.getCharacteristics(id);
        characteristicsDAO.deleteCharacteristics(id);
        deleteCamera(characteristics.getCameraId());
        deleteDisplay(characteristics.getDisplayId());
        deleteProcessor(characteristics.getProcessorId());
    }

    @Override
    public int addVendor(Vendor vendor) {
        Vendor tempVendor1 = vendorDAO.getVendor(vendor);
        if (tempVendor1 != null) {
            return tempVendor1.getId();
        }
        return vendorDAO.insertVendor(vendor);
    }

    @Override
    public void updateVendor(Vendor vendor) {
        Vendor tempVendor = vendorDAO.getVendor(vendor.getId());
        if (tempVendor != vendor) {
            vendorDAO.updateVendor(vendor);
        }
    }

    @Override
    public void deleteVendor(int id) {
        vendorDAO.deleteVendor(id);
    }

    @Override
    public void addSmartPhone(Smartphone smartphone) {
        Smartphone tempSmartphone = smartphoneDAO.getSmartphone(smartphone);
        if(tempSmartphone == null) {
            smartphone.setVendorId(addVendor(smartphone.getVendor()));
            smartphone.setCharacteristicsId(addCharacteristics(smartphone.getCharacteristics()));
            smartphoneDAO.insertSmartphone(smartphone);
        }
    }

    @Override
    public void updateSmartPhone(Smartphone smartphone) {
        smartphone = prepareSmartphone(smartphone);
        updateVendor(smartphone.getVendor());
        updateCharacteristics(smartphone.getCharacteristics());
        smartphoneDAO.updateSmartphone(smartphone);

    }

    @Override
    public void deleteSmartPhone(int id) {
        Smartphone smartphone = smartphoneDAO.getSmartphone(id);
        smartphoneDAO.deleteSmartphome(id);
        deleteCharacteristics(smartphone.getCharacteristicsId());
        deleteVendor(smartphone.getVendorId());
    }

    @Override
    public Map<String, Integer> getCountOfSmartphones() {
        return smartphoneDAO.getPhonesFromVendorsCount();
    }

    private Smartphone prepareSmartphone(Smartphone smartphone) {
        smartphone.getVendor().setId(smartphone.getVendorId());
        Characteristics tempCharacteristics = smartphone.getCharacteristics();
        tempCharacteristics.getCamera().setId(tempCharacteristics.getCameraId());
        tempCharacteristics.getProcessor().setId(tempCharacteristics.getProcessorId());
        tempCharacteristics.getDisplay().setId(tempCharacteristics.getDisplayId());
        smartphone.setCharacteristics(tempCharacteristics);
        return smartphone;
    }
}
