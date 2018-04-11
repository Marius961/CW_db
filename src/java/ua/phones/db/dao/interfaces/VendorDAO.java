package ua.phones.db.dao.interfaces;

import ua.phones.db.models.Vendor;

public interface VendorDAO {

    Vendor getVendor(int id);

    Vendor getVendor(Vendor vendor);

    int insertVendor(Vendor vendor);

    void deleteVendor(int id);

    void updateVendor(Vendor vendor);

}
