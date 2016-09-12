package br.com.kproj.salesman.delivery.view;

import br.com.kproj.salesman.delivery.application.tasktemplates.TaskTemplateApplication;
import br.com.kproj.salesman.delivery.infrastructure.validators.TaskTemplateValidator;
import br.com.kproj.salesman.delivery.view.dtos.TaskTemplateDTO;
import br.com.kproj.salesman.infrastructure.entity.builders.TaskTemplateBuilder;
import br.com.kproj.salesman.infrastructure.entity.task.TaskTemplateEntity;
import br.com.kproj.salesman.infrastructure.helpers.view.NormalizeEntityRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
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


    @RequestMapping(value = "/saleables/{saleableId}/task-template/add", method = RequestMethod.POST)
    public  @ResponseBody TaskTemplateDTO save(@ModelAttribute TaskTemplateEntity taskTemplateEntity, @PathVariable Long saleableId) {

        hasContraintViolated(taskTemplateEntity, validator);

        normalizeEntityRequest.doNestedReference(taskTemplateEntity);
        taskTemplateEntity.setSaleable(createSaleableUnit(saleableId).build());
        TaskTemplateEntity result = service.register(taskTemplateEntity);

        return TaskTemplateDTO.build(result);

    }

    @RequestMapping(value = "/saleables/{saleableId}/task-template/add", method = RequestMethod.PUT)
    public @ResponseBody TaskTemplateDTO update(@ModelAttribute TaskTemplateEntity taskTemplateEntity, @PathVariable Long saleableId) {

        normalizeEntityRequest.addFieldsToUpdate(taskTemplateEntity);
        taskTemplateEntity.setSaleable(createSaleableUnit(saleableId).build());
        TaskTemplateEntity result = service.register(taskTemplateEntity);

        return TaskTemplateDTO.build(result);
    }

    @RequestMapping(value = "/saleables/task-template/{taskTemplateId}", method = RequestMethod.DELETE)
    public @ResponseBody void remove(@PathVariable Long taskTemplateId) {

        service.remove(TaskTemplateBuilder.createTaskTemplateBuilder(taskTemplateId).build());

    }


    @RequestMapping(value="/saleables/{saleableId}/task-template/list")
    public ModelAndView list(@PathVariable Long saleableId, Model model) {

        Iterable<TaskTemplateEntity> result = this.service.findTaskTemplateOnlyRootBy(createSaleableUnit(saleableId).build());

        model.addAttribute("taskTemplates", result);
        model.addAttribute("saleable", createSaleableUnit(saleableId).build());

        return new ModelAndView("/delivery/saleables/task-template/includes/tasks");
    }

    @RequestMapping(value="/saleables/task-template/{templateId}")
    public ModelAndView viewInfo(@PathVariable Long templateId, Model model) {

        Optional<TaskTemplateEntity> result = this.service.getOne(templateId);

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
