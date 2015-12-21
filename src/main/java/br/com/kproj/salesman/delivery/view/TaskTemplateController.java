package br.com.kproj.salesman.delivery.view;

import br.com.kproj.salesman.delivery.application.TaskTemplateApplication;
import br.com.kproj.salesman.delivery.infrastructure.validators.TaskTemplateValidator;
import br.com.kproj.salesman.infrastructure.entity.task.TaskTemplate;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.helpers.NormalizeEntityRequest;
import br.com.kproj.salesman.infrastructure.repository.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@RestController
public class TaskTemplateController {

    @Autowired
    private TaskTemplateApplication service;

    @Autowired
    private TaskTemplateValidator validator;

    @Autowired
    private NormalizeEntityRequest normalizeEntityRequest;

    @InitBinder(value = "taskTemplate")
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @RequestMapping(value = "/task-template/save", method = RequestMethod.POST)
    public  @ResponseBody String save(@ModelAttribute @Validated TaskTemplate taskTemplate,
                                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getAllErrors());
        }

        normalizeEntityRequest.doNestedReference(taskTemplate);
        TaskTemplate taskTemplateLoaded = service.register(taskTemplate);

        return "/task-template/" + taskTemplateLoaded.getId();
    }

    @RequestMapping(value = "/task-template/save", method = RequestMethod.PUT)
    public @ResponseBody String update(@ModelAttribute @Validated TaskTemplate taskTemplate,
                                               BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getAllErrors());
        }

        normalizeEntityRequest.addFieldsToUpdate(taskTemplate);
        TaskTemplate taskTemplateLoaded = service.register(taskTemplate);

        return "/task-template/" + taskTemplateLoaded.getId();
    }


    @RequestMapping(value="/task-template/list")
    public ModelAndView list(@PageableDefault(page=0, size=150000)Pageable pageable, Model model) {
        Pager pager = Pager.binding(pageable);

        Iterable<TaskTemplate> result = this.service.findAll(pager);

        model.addAttribute("taskTemplates", result);
        return new ModelAndView("/task-template/list-items");
    }


    @RequestMapping(value="/task-template/{templateId}")
    public ModelAndView viewInfo(@RequestParam(defaultValue="edit",required=false, value="template") String templateName,
                                 @PathVariable Long templateId, Model model) {

        Optional<TaskTemplate> result = this.service.getOne(templateId);

        model.addAttribute(result.isPresent() ? result.get() : null);
        return new ModelAndView("/task-template/" + templateName);
    }

}
