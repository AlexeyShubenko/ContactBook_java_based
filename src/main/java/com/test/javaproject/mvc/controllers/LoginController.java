package com.test.javaproject.mvc.controllers;

import com.test.javaproject.mvc.exceptions.PasswordErrorException;
import com.test.javaproject.mvc.service.impl.WorkService;
import com.test.javaproject.mvc.domains.LoginTempObject;
import com.test.javaproject.mvc.dto.UserDto;
import com.test.javaproject.mvc.exceptions.UserNotFindException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Objects;


@Controller
@SessionAttributes(value="userDto")
public class LoginController {

	private WorkService service;

	@Autowired
	Environment env;

	@Autowired
	public LoginController(WorkService service){
		this.service = service;
	}

	///redirect to startPage, where you can log in
	///LoginTempObject - have data of log in user
	@RequestMapping(value = {"/","startPage"}, method = RequestMethod.GET)
	public ModelAndView startPageLog(Model model) {
		ModelAndView modelAndView = new ModelAndView();
		if(!model.containsAttribute("userDtoToLogin")){
			model.addAttribute("userDtoToLogin", new UserDto());
		}
		modelAndView.setViewName("startPage");
		return modelAndView;
	}

	///after logging in, entered data id checked
	///loginObject - come from jsp,model - object with data from jsp
	@RequestMapping(value="/checkLoginUser", method=RequestMethod.POST)
	public String checkLoginUser(@Valid @ModelAttribute("userDtoToLogin") UserDto userDtoToLogin, BindingResult result,
								 RedirectAttributes attributes, HttpSession session){
		if(result.hasErrors()){
			attributes.addFlashAttribute("error", "Input data is wrong!");
			return "redirect:/";
		}
		UserDto userDto;
		try{
			userDto = service.getUserServiceImpl().verifyUser(userDtoToLogin.getLoginName(),userDtoToLogin.getPassword());
        }catch (UserNotFindException ex){
			attributes.addFlashAttribute("error", ex.getMessage());
			return "redirect:/";
		}
        catch (PasswordErrorException ex){
			attributes.addFlashAttribute("error", "Password is not right!");
            return "redirect:/";
        }
		//if all right
		session.setAttribute("userDto", userDto);
		return "redirect:/showContacts";
	}

	
}
