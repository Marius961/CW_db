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
                validateCharacteristics(errors, smartphone.getCharacteristics());
            }
        }

    }

    private void validateCharacteristics(Errors errors, Characteristics characteristics) {
        if (characteristics.getBatteryVolume() <= 0) {
            errors.rejectValue("characteristics.batteryVolume", "smartphone.characteristics.battery.volume");
        }
        validateCamera(errors, characteristics.getCamera());

        validateProcessor(errors, characteristics.getProcessor());

        validateDisplay(errors, characteristics.getDisplay());

    }

    private void validateCamera(Errors errors, Camera camera) {
        if (camera.getNumOfPixels() <= 0) {
            errors.rejectValue("characteristics.camera.numOfPixels", "smartphone.camera.numOfPixels");
        }

        if (!camera.getResolution().contains("x")) {
            errors.rejectValue("characteristics.camera.resolution", "smartphone.camera.resolution");
        }

        if (resolutionIncorrect(camera.getResolution())) {
            errors.rejectValue("characteristics.camera.resolution", "smartphone.resolution.format.invalid");
        }
    }

    private void validateProcessor(Errors errors, Processor processor) {
        if (processor.getCores() <= 0) {
            errors.rejectValue("characteristics.processor.cores", "smartphone.processor.cores");
        }
        if (processor.getFrequency() < 0.01) {
            errors.rejectValue("characteristics.processor.frequency", "smartphone.processor.frequency.min");
        }

    }

    private void validateDisplay(Errors errors, Display display) {

        if (!display.getResolution().contains("x")) {
            errors.rejectValue("characteristics.display.resolution", "smartphone.display.resolution");
        }

        if (resolutionIncorrect(display.getResolution())) {
            errors.rejectValue("characteristics.display.resolution", "smartphone.resolution.format.invalid");
        }

        if (display.getSize() < 0.01) {
            errors.rejectValue("characteristics.display.size", "smartphone.display.size.min");
        }
    }

    private boolean resolutionIncorrect(String resolution) {
        int count = 0;
        char[] res = resolution.toLowerCase().toCharArray();
        for (int i  = 0;i< resolution.length(); i++) {
            if (res[i] == 'x'  ) {
                count++;
            }
        }

        return count > 1;
    }
}
