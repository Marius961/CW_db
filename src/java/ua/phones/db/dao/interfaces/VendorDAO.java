package ua.phones.db.dao.interfaces;

import ua.phones.db.models.Vendor;

public interface VendorDAO {

    Vendor getVendor(int id);

    boolean insertVendor(Vendor vendor);

    boolean deleteVendor(int id);

    boolean updateVendor(Vendor vendor);

}
