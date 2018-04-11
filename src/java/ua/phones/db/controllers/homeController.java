package ua.phones.db.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.phones.db.models.DeletedPhone;
import ua.phones.db.models.Smartphone;
import ua.phones.db.services.interfaces.PhonesTableService;
import ua.phones.db.validators.SmartPhoneValidator;


@Controller
public class homeController {

    private PhonesTableService phonesTableService;
    private SmartPhoneValidator smartphoneValidator;

    @Autowired
    private void setServices(PhonesTableService phonesTableService, SmartPhoneValidator smartphoneValidator) {
        this.phonesTableService = phonesTableService;
        this.smartphoneValidator = smartphoneValidator;
    }

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public ModelAndView geyHome() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        modelAndView.addObject("phones", phonesTableService.getAllSmartphones());
        modelAndView.addObject("smartphone", new Smartphone());
        modelAndView.addObject("deletedPhone", new DeletedPhone());
        return modelAndView;
    }

    @RequestMapping(value = "/smartPhone", method = RequestMethod.GET)
    public ModelAndView createSmartPhone(@ModelAttribute Smartphone smartphone) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("smartPhone", smartphone);
        modelAndView.setViewName("createSmartPhone");
        return modelAndView;
    }


    @RequestMapping(value = "/process", method = RequestMethod.POST)
    public String addSmartPhone(@ModelAttribute("smartPhone") Smartphone smartphone, BindingResult bindingResult) {
        smartphoneValidator.validate(smartphone, bindingResult);
        if (!bindingResult.hasErrors()) {
            if (smartphone.getId() == 0) {
                phonesTableService.addSmartPhone(smartphone);
            } else {
                phonesTableService.updateSmartPhone(smartphone);
            }
            return "redirect:home";
        } else {
            return "createSmartPhone";
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deletePhone(@ModelAttribute DeletedPhone deletedPhone) {
        phonesTableService.deleteSmartPhone(deletedPhone.getSmartphoneId());
        return "redirect:home";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView updatePhone(@ModelAttribute Smartphone smartphone) {
        ModelAndView modelAndView = new ModelAndView();
        smartphone = phonesTableService.fillSmartphone(smartphone);
        modelAndView.addObject("smartPhone", smartphone);
        modelAndView.setViewName("createSmartPhone");
        return modelAndView;
    }

}
