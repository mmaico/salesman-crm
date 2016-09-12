package br.com.kproj.salesman.delivery.view;

import br.com.kproj.salesman.delivery.application.tasks.TaskApplication;
import br.com.kproj.salesman.delivery.infrastructure.validators.TaskValidator;
import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.enums.TaskStatusEntity;
import br.com.kproj.salesman.infrastructure.entity.task.TaskEntity;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.helpers.view.NormalizeEntityRequest;
import br.com.kproj.salesman.infrastructure.repository.Pager;
import br.com.kproj.salesman.infrastructure.security.helpers.SecurityHelper;
import com.google.common.collect.Sets;
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

import static br.com.kproj.salesman.infrastructure.entity.builders.TaskBuilder.createTaskBuilder;
import static br.com.kproj.salesman.infrastructure.validators.ValidatorHelper.hasContraintViolated;

@RestController
public class TaskController {

    @Autowired
    private TaskApplication service;

    @Autowired
    private TaskValidator validator;

    @Autowired
    private NormalizeEntityRequest normalizeEntityRequest;

    @Autowired
    private SecurityHelper security;

    @InitBinder(value = "task")
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @RequestMapping(value = "/tasks/save", method = RequestMethod.POST)
    public  @ResponseBody String save(@ModelAttribute @Validated TaskEntity taskEntity, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getAllErrors());
        }

        normalizeEntityRequest.doNestedReference(taskEntity);
        TaskEntity taskEntitySaved = service.register(taskEntity);

        return "/tasks/" + taskEntitySaved.getId();
    }

    @RequestMapping(value = "/tasks/new", method = RequestMethod.GET)
    public  ModelAndView newTask() {

        return new ModelAndView("/delivery/tasks/edit");
    }

    @RequestMapping(value = "/tasks/{parentTaskId}/subtask", method = RequestMethod.POST)
    public  ModelAndView saveSubtask(@ModelAttribute TaskEntity taskEntity, @PathVariable("parentTaskId") Long parentTaskId, Model model) {

        hasContraintViolated(taskEntity, validator);

        normalizeEntityRequest.doNestedReference(taskEntity);
        service.registerSubtask(createTaskBuilder(parentTaskId).build(), taskEntity);

        Optional<TaskEntity> taskParentLoaded = service.getOne(parentTaskId);

        model.addAttribute("task", taskParentLoaded.isPresent() ? taskParentLoaded.get() : null);
        return new ModelAndView("/delivery/tasks/includes/subtask");
    }

    @RequestMapping(value = "/tasks/save", method = RequestMethod.PUT)
    public @ResponseBody String update(@ModelAttribute @Validated TaskEntity taskEntity, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getAllErrors());
        }

        if (taskEntity.isNew()) {
            throw new ValidationException(Sets.newHashSet("task.update.without.id"));
        }

        normalizeEntityRequest.addFieldsToUpdate(taskEntity);

        normalizeEntityRequest.doNestedReference(taskEntity);
        TaskEntity taskEntitySaved = service.register(taskEntity);

        return "/task/" + taskEntitySaved.getId();
    }


    @RequestMapping(value="/tasks/list")
    public ModelAndView list(@PageableDefault(page=0, size=150000)Pageable pageable, Model model) {
        Pager pager = Pager.binding(pageable);

        Iterable<TaskEntity> result = this.service.findAll(pager);

        model.addAttribute("tasks", result);
        return new ModelAndView("/task/list-items");
    }


    @RequestMapping(value="/tasks/{taskId}")
    public ModelAndView viewInfo(@RequestParam(defaultValue="detail",required=false, value="template") String templateName,
                                 @PathVariable Long taskId, Model model) {

        Optional<TaskEntity> result = this.service.getOne(taskId);

        model.addAttribute(result.isPresent() ? result.get() : null);
        return new ModelAndView("/delivery/tasks/" + templateName);
    }

    @RequestMapping(value="/tasks/{taskId}/signed", method = RequestMethod.PUT)
    public @ResponseBody void signedTask(@PathVariable Long taskId) {
        TaskEntity taskEntity = createTaskBuilder(taskId).build();
        UserEntity user = null;//security.getPrincipal().getUser();

        this.service.signedTask(user, taskEntity);
    }

    @RequestMapping(value="/tasks/{taskId}/unsigned", method = RequestMethod.PUT)
    public @ResponseBody void unsignedTask(@PathVariable Long taskId) {
        TaskEntity taskEntity = createTaskBuilder(taskId).build();
        UserEntity user = null;//security.getPrincipal().getUser();

        this.service.unsignedTask(user, taskEntity);
    }

    @RequestMapping(value="/tasks/{taskId}/change-status/{status}", method = RequestMethod.PUT)
    public @ResponseBody void signedTask(@PathVariable Long taskId, @PathVariable String status) {
        TaskEntity taskEntity = createTaskBuilder(taskId)
                .withStatus(TaskStatusEntity.get(status)).build();

        UserEntity user = null; //security.getPrincipal().getUser();

        this.service.changeStatus(taskEntity, user);
    }

}
