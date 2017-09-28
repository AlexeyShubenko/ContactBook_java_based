package com.test.javaproject.mvc.controllers;

import com.test.javaproject.mvc.service.impl.WorkService;
import com.test.javaproject.mvc.domains.RegEx;
import com.test.javaproject.mvc.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * Created by Alexey on 16.07.2017.
 */
@Controller
public class RegistrationController {

    private WorkService service;

    @Autowired
    public RegistrationController(WorkService service){
        this.service = service;
    }

    ///redirect to registrationPage
    @RequestMapping(value="/userRegistration", method= RequestMethod.GET)
    public ModelAndView userRegistration(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        if(!model.containsAttribute("userDto")){
            model.addAttribute("userDto", new UserDto());
        }
        modelAndView.setViewName("userRegistr");
        return modelAndView;
    }

    @RequestMapping(value="/userRegistration", method=RequestMethod.POST)
    public String saveNewUser(@Valid @ModelAttribute("userDto") UserDto userDto, BindingResult result,
                              RedirectAttributes attributes){
        if(result.hasErrors()){
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.userDto",result);
            userDto.setPassword("");
            attributes.addFlashAttribute("userDto",userDto);
            return "redirect:/userRegistration";
        }
        boolean isValidLogin = RegEx.checkValidLogin(userDto.getLogin());
        if (!isValidLogin) {
            ///wrong symbols
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.userDto",result);
            userDto.setLogin("");
            userDto.setPassword("");
            attributes.addFlashAttribute("userDto",userDto);
            attributes.addFlashAttribute("error", "Don`t user special symbols for login name!");
            return "redirect:/userRegistration";
        }
        //TODO show error messages
        boolean isExistUser = service.getUserServiceImpl().checkExistingUser(userDto.getLogin());
        if (isExistUser) {
            ///user not exist

            attributes.addFlashAttribute("error", "User with this login is exist!");
            return "redirect:/userRegistration";
        }

        service.getUserServiceImpl().saveUser(userDto);
        return "redirect:/";
    }
}
