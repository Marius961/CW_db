package ua.phones.db.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ua.phones.db.dao.interfaces.SmartphoneDAO;
import ua.phones.db.models.*;

@Component
public class SmartPhoneValidator implements Validator {
    private SmartphoneDAO smartphoneDAO;

    @Autowired
    private void setDAO(SmartphoneDAO smartphoneDAO) {
        this.smartphoneDAO = smartphoneDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"model", "smartphone.field.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "vendor.name", "smartphone.field.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "characteristics.camera.resolution", "smartphone.field.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "characteristics.processor.model", "smartphone.field.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "characteristics.display.model", "smartphone.field.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "characteristics.display.resolution", "smartphone.field.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "characteristics.display.technology", "smartphone.field.empty");
        if (target instanceof Smartphone) {
            Smartphone smartphone = (Smartphone) target;
            Smartphone tempSmartphone = smartphoneDAO.getSmartPhoneByModel(smartphone.getModel());
            if (tempSmartphone != null && smartphone.getId() == 0) {
                errors.rejectValue("model", "smartphone.model.found");
            } else {
                Characteristics characteristics = smartphone.getCharacteristics();
                System.out.println(characteristics.getBatteryVolume() + " bt vol");
                if (characteristics.getBatteryVolume() <= 0) {
                    errors.rejectValue("characteristics.batteryVolume", "smartphone.characteristics.battery.volume");
                }
                Camera camera = characteristics.getCamera();
                if (camera.getNumOfPixels() <= 0) {
                    errors.rejectValue("characteristics.camera.numOfPixels", "smartphone.camera.numOfPixels");
                }

                if (!camera.getResolution().contains("x")) {
                    errors.rejectValue("characteristics.camera.resolution", "smartphone.camera.resolution");
                }

                int count = 0;
                char[] res = camera.getResolution().toLowerCase().toCharArray();
                for (int i  = 0;i<camera.getResolution().length(); i++) {
                    if (res[i] == 'x'  ) {
                        count++;
                    }
                }

                if (count > 1) {
                    errors.rejectValue("characteristics.camera.resolution", "smartphone.camera.resolution.format.invalid");
                }
                Processor processor = characteristics.getProcessor();
                if (processor.getCores() <= 0) {
                    errors.rejectValue("characteristics.processor.cores", "smartphone.processor.cores");
                }
                if (processor.getFrequency() < 0.01) {
                    errors.rejectValue("characteristics.processor.frequency", "smartphone.processor.frequency.min");
                }

                Display display = characteristics.getDisplay();
                if (!display.getResolution().contains("x")) {
                    errors.rejectValue("characteristics.display.resolution", "smartphone.display.resolution");
                }

                count = 0;
                res = camera.getResolution().toLowerCase().toCharArray();
                for (int i  = 0;i<camera.getResolution().length(); i++) {
                    if (res[i] == 'x'  ) {
                        count++;
                    }
                }

                if (count > 1) {
                    errors.rejectValue("characteristics.display.resolution", "smartphone.display.resolution.format.invalid");
                }

                if (display.getSize() < 0.01) {
                    errors.rejectValue("characteristics.display.size", "smartphone.display.size.min");
                }
            }
        }

    }
}
