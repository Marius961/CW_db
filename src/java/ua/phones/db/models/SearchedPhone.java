package ua.phones.db.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SearchedPhone {

    @Size(min = 1, message = "smartphone.field.empty")
    private String smartPhoneModel;

    public String getSmartPhoneModel() {
        return smartPhoneModel;
    }

    public void setSmartPhoneModel(String smartPhoneModel) {
        this.smartPhoneModel = smartPhoneModel;
    }
}
