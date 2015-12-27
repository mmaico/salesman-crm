package br.com.kproj.salesman.delivery.view;

import br.com.kproj.salesman.delivery.application.TaskTemplateApplication;
import br.com.kproj.salesman.delivery.infrastructure.validators.TaskTemplateValidator;
import br.com.kproj.salesman.delivery.view.dtos.TaskTemplateDTO;
import br.com.kproj.salesman.infrastructure.entity.builders.SaleableUnitBuilder;
import br.com.kproj.salesman.infrastructure.entity.builders.TaskTemplateBuilder;
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

import static br.com.kproj.salesman.infrastructure.entity.builders.SaleableUnitBuilder.createSaleableUnit;
import static br.com.kproj.salesman.infrastructure.validators.ValidatorHelper.hasContraintViolated;

@RestController
public class SaleableTaskTemplateController {

    @Autowired
    private TaskTemplateApplication service;

    @Autowired
    private TaskTemplateValidator validator;

    @Autowired
    private NormalizeEntityRequest normalizeEntityRequest;


    @RequestMapping(value = "/saleables/{saleableId}/task-template/save", method = RequestMethod.POST)
    public  @ResponseBody TaskTemplateDTO save(@ModelAttribute TaskTemplate taskTemplate,  @PathVariable Long saleableId) {

        hasContraintViolated(taskTemplate, validator);

        normalizeEntityRequest.doNestedReference(taskTemplate);
        taskTemplate.setSaleable(createSaleableUnit(saleableId).build());
        TaskTemplate result = service.register(taskTemplate);

        return TaskTemplateDTO.build(result);

    }

    @RequestMapping(value = "/saleables/{saleableId}/task-template/save", method = RequestMethod.PUT)
    public @ResponseBody void update(@ModelAttribute TaskTemplate taskTemplate,  @PathVariable Long saleableId) {

        hasContraintViolated(taskTemplate, validator);

        normalizeEntityRequest.addFieldsToUpdate(taskTemplate);
        taskTemplate.setSaleable(createSaleableUnit(saleableId).build());
        service.register(taskTemplate);

    }

    @RequestMapping(value = "/saleables/task-template/{taskTemplateId}", method = RequestMethod.DELETE)
    public @ResponseBody void remove(@PathVariable Long taskTemplateId) {

        service.remove(TaskTemplateBuilder.createTaskTemplateBuilder(taskTemplateId).build());

    }


    @RequestMapping(value="/saleables/{saleableId}/task-template/list")
    public ModelAndView list(@PathVariable Long saleableId, Model model) {

        Iterable<TaskTemplate> result = this.service.findTaskTemplateOnlyRootBy(createSaleableUnit(saleableId).build());

        model.addAttribute("taskTemplates", result);
        model.addAttribute("saleable", createSaleableUnit(saleableId).build());

        return new ModelAndView("/delivery/saleables/task-template/includes/tasks");
    }

    @RequestMapping(value="/saleables/task-template/{templateId}")
    public ModelAndView viewInfo(@PathVariable Long templateId, Model model) {

        Optional<TaskTemplate> result = this.service.getOne(templateId);

        model.addAttribute("saleable", result.isPresent() ? result.get().getSaleable() : null);
        model.addAttribute("taskTemplate", result.isPresent() ? result.get() : null);
        return new ModelAndView("/delivery/saleables/task-template/includes/task");
    }

    @RequestMapping(value="/saleables/{saleableId}/task-template/new")
    public ModelAndView newTaskTemplate(@PathVariable Long saleableId, Model model) {

        model.addAttribute("saleable", createSaleableUnit(saleableId).build());
        return new ModelAndView("/delivery/saleables/task-template/includes/add-task");
    }

}
