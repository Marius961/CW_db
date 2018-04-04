package ua.phones.db.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.phones.db.dao.interfaces.VendorDAO;
import ua.phones.db.models.Smartphone;
import ua.phones.db.models.Vendor;
import ua.phones.db.services.interfaces.PhonesTableService;

@Controller
public class homeController {

    private PhonesTableService phonesTableService;

    @Autowired
    private void setServices(PhonesTableService phonesTableService) {
        this.phonesTableService = phonesTableService;
    }

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public ModelAndView geyHome() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        modelAndView.addObject("phones", phonesTableService.getAllSmartphones());
        modelAndView.addObject("smartphone", new Smartphone());
        return modelAndView;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView createSmartPhone(@ModelAttribute Smartphone smartphone) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("smartPhone", smartphone);
        modelAndView.setViewName("createSmartPhone");
        return modelAndView;
    }


    @RequestMapping(value = "/create-smartphone", method = RequestMethod.POST)
    public String addSmartPhone(@ModelAttribute Smartphone smartphone) {
        phonesTableService.addSmartPhone(smartphone);
        return "redirect:home";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deletePhone(@ModelAttribute Smartphone smartphone) {
        phonesTableService.deleteSmartPhone(smartphone.getId());
        return "redirect:home";
    }

}
