package ua.phones.db.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.phones.db.dao.interfaces.VendorDAO;
import ua.phones.db.models.Vendor;

@Controller
public class homeController {

    private VendorDAO vendorDAO;

    @Autowired
    private  void  getSQLDAO(VendorDAO vendorDAO) {
        this.vendorDAO = vendorDAO;
    }
    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public ModelAndView geyHome() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", "Hello world");
        modelAndView.setViewName("home");
        Vendor vendor = new Vendor();
        vendor.setName("Samsung");
        vendorDAO.insertVendor(vendor);
        return modelAndView;
    }
}
