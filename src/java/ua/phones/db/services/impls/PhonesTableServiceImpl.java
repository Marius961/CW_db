package ua.phones.db.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.phones.db.dao.interfaces.*;
import ua.phones.db.models.*;
import ua.phones.db.services.interfaces.PhonesTableService;

import java.util.List;


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
        List<Smartphone> smartphones = smartphoneDAO.getAllSmartphones();
        if (smartphones == null) {
            return null;
        }
        for (Smartphone smartphone : smartphones) {
            fillSmartphone(smartphone);
        }
        return  smartphones;
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
        boolean completed = cameraDAO.deleteCamera(id);
        if(!completed) {
            System.out.println("Камера з id:" + id + " не може бути видалена, так як використовується");
        } else {
            System.out.println("Камеру з id:" + id + " успішно видалено");
        }
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
        Display tempDisplay1 = displayDAO.getDisplay(display.getId());
        if (tempDisplay1 != display) {
            displayDAO.updateDisplay(display);
        }
    }

    @Override
    public void deleteDisplay(int id) {
        boolean completed = displayDAO.deleteDisplay(id);
        if (!completed) {
            System.out.println("Дисплей з id:" + id + " не може бути видалений, так як використовується");
        } else {
            System.out.println("Дисплей з id:" + id + " успішно видалено");
        }
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
        boolean completed = processorDAO.deleteProcessor(id);
        if (!completed) {
            System.out.println("Процесор з id:" + id + " не може бути видалений, так як використовується");
        } else {
            System.out.println("Процесор з id:" + id + " успішно видалено");
        }
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
        Characteristics tempCharacteristics1 = characteristicsDAO.getCharacteristics(characteristics.getId());
        if (tempCharacteristics1 != characteristics) {
            characteristicsDAO.updateCharacteristics(characteristics);
        }
        updateProcessor(characteristics.getProcessor());
        updateCamera(characteristics.getCamera());
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
        boolean completed = vendorDAO.deleteVendor(id);
        if (!completed) {
            System.out.println("Виробник з id:" + id + " не може бути видалений, так як використовується");
        } else {
            System.out.println("Виробник з id:" + id + " успішно видалено");
        }
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
        Smartphone tempSmartphone = smartphoneDAO.getSmartphone(smartphone.getId());
        if (tempSmartphone != smartphone) {
            smartphoneDAO.updateSmartphone(smartphone);
        }
        updateVendor(smartphone.getVendor());
        updateCharacteristics(smartphone.getCharacteristics());
    }

    @Override
    public void deleteSmartPhone(int id) {
        Smartphone smartphone = smartphoneDAO.getSmartphone(id);
        smartphoneDAO.deleteSmartphome(id);
        deleteVendor(smartphone.getId());
        deleteCharacteristics(smartphone.getCharacteristicsId());
    }
}
