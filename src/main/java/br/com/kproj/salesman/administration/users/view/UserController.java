package br.com.kproj.salesman.administration.users.view;

import br.com.kproj.salesman.administration.users.application.UserFacade;
import br.com.kproj.salesman.administration.users.domain.model.user.User;
import br.com.kproj.salesman.administration.users.domain.model.user.UserValidator;
import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.helpers.view.NormalizeAttrUpdateHelper;
import br.com.kproj.salesman.infrastructure.repository.Pager;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static br.com.kproj.salesman.infrastructure.helpers.MultipartFileUtils.safe;

@RestController
public class UserController {

    @Autowired
    private UserFacade service;

    @Autowired
    @Qualifier("userViewValidator")
    private UserValidator validator;

    @Autowired
    private NormalizeAttrUpdateHelper attributesToUpdate;


    @RequestMapping(value = "/users/add", method = RequestMethod.POST)
    public  @ResponseBody String save(@ModelAttribute User user, @RequestParam(value="avatarFile", required = false) MultipartFile file) throws IOException {

        validator.checkRules(user);
        attributesToUpdate.addAttributesToUpdate(user);
        user.setAvatar(safe(file).getBytes());

        Optional<User> userSaved = service.register(user);

        return "/users/" + userSaved.get().getId();
    }

    @RequestMapping(value = "/users/add", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<Void> update(@ModelAttribute User user) throws IOException {

        attributesToUpdate.addAttributesToUpdate(user);
        service.register(user);

        return new ResponseEntity<>(HttpStatus.OK);
    }
//
//    @RequestMapping(value = "/users/credentials", method = RequestMethod.PUT)
//    public @ResponseBody ResponseEntity updateCredentials(@ModelAttribute UserEntity user) throws IOException {
//
//        normalizeEntityRequest.addFieldsToUpdate(user);
//        service.register(user);
//
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    @RequestMapping(value = "/users/avatar", method = RequestMethod.POST)
//    public @ResponseBody ResponseEntity updateAvatar(@ModelAttribute UserVO userVO, @ModelAttribute("file") MultipartFile file, BindingResult bindingResult, Model model) throws IOException {
//
//        if (bindingResult.hasErrors()) {
//            throw new ValidationException(bindingResult.getAllErrors());
//        }
//
//        UserEntity user = userVO.getOwner();
//        user.setAvatar(file.getBytes());
//        user.addFields("avatar");
//        service.register(user);
//
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
    @RequestMapping("/users/list")
    public ModelAndView list(@PageableDefault(size=150)Pageable pageable, Model model) {

        Pager pager = Pager.binding(pageable);

        Iterable<User> result = this.service.findAll(pager);

        model.addAttribute("users", result);
        return new ModelAndView("/users/list-items");
    }

    @RequestMapping(value="/users/create")
    public ModelAndView newUser(Model model) {

        return new ModelAndView("/users/newUser");
    }

    @RequestMapping("/users/{id}/avatar")
	public void getImage(HttpServletResponse response, @PathVariable Long id) throws IOException {

		Optional<User> userOptional = this.service.getOne(id);

		if (userOptional.isPresent() && userOptional.get().getAvatar() != null) {
			IOUtils.write(userOptional.get().getAvatar(), response.getOutputStream());
		} else {
            IOUtils.write(UserEntity.getDefaultAvatar(), response.getOutputStream());
        }
	}

    @RequestMapping(value="/users/{userId}")
    public ModelAndView viewInfo(@PathVariable Long userId, Model model) {

        Optional<User> result = this.service.getOne(userId);

        model.addAttribute("user", result.get());
        return new ModelAndView("/users/edit");
    }




}
