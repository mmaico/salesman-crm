package br.com.kproj.salesman.register.view;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.helpers.NormalizeEntityRequest;
import br.com.kproj.salesman.infrastructure.repository.Pager;
import br.com.kproj.salesman.register.application.UserService;
import br.com.kproj.salesman.register.infraestructure.validators.UserValidator;
import br.com.kproj.salesman.register.view.dto.UserVO;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private UserValidator validator;

    @Autowired
    private NormalizeEntityRequest normalizeEntityRequest;

    @InitBinder(value = "userVO")
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @RequestMapping(value = "/users/save", method = RequestMethod.POST)
    public ModelAndView save(@ModelAttribute @Validated UserVO userVO, BindingResult bindingResult, @ModelAttribute("file") MultipartFile file, Model model) {

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getFieldErrors());
        }
        
        User user = userVO.getUser();
        normalizeEntityRequest.addFieldsToUpdate(user);
        User userRegistered = service.register(user);

        model.addAttribute(userRegistered);
        return new ModelAndView("user");
    }

    @RequestMapping(value = "/users/save", method = RequestMethod.PUT)
    public ModelAndView update(@ModelAttribute @Validated UserVO userVO, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getFieldErrors());
        }
        
        User user = userVO.getUser();
        normalizeEntityRequest.addFieldsToUpdate(user);
        User userRegistered = service.register(user);

        model.addAttribute(userRegistered);
        return new ModelAndView("user");
    }

    @RequestMapping("/users/list")
    public ModelAndView list(@PageableDefault(page=0, size=15)Pageable pageable, Model model) {

        Pager pager = Pager.binding(pageable);

        Iterable<User> result = this.service.findAll(pager);

        model.addAttribute("users", result);
        return new ModelAndView("user");
    }
    
    @RequestMapping(value="/users/{userId}")
    public ModelAndView viewInfo(Long userId, Model model) {
        
        Optional<User> result = this.service.getOne(userId);

        model.addAttribute("user", result.get());
        return new ModelAndView("user");
    }
    
    @RequestMapping("/users/{id}/avatar")
	public void getBigImage(HttpServletResponse response, @PathVariable Long id, @PathVariable String size) throws IOException {
	    
		Optional<User> userOptional = this.service.getOne(id);
		
		if (userOptional.isPresent()) {
			IOUtils.write(userOptional.get().getAvatar(), response.getOutputStream());
		}
		
	}


}
