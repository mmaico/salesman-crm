package br.com.kproj.salesman.delivery.view;

import br.com.kproj.salesman.delivery.application.tasktemplates.ChecklistTemplateApplication;
import br.com.kproj.salesman.delivery.infrastructure.validators.ChecklistTemplateValidator;
import br.com.kproj.salesman.infrastructure.entity.builders.TaskTemplateBuilder;
import br.com.kproj.salesman.infrastructure.entity.task.ChecklistTemplateEntity;
import br.com.kproj.salesman.infrastructure.helpers.view.NormalizeEntityRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static br.com.kproj.salesman.infrastructure.entity.builders.ChecklistTemplateBuilder.createChecklistTemplateBuilder;
import static br.com.kproj.salesman.infrastructure.entity.builders.SaleableUnitBuilder.createSaleableUnit;
import static br.com.kproj.salesman.infrastructure.entity.builders.TaskTemplateBuilder.createTaskTemplateBuilder;
import static br.com.kproj.salesman.infrastructure.validators.ValidatorHelper.hasContraintViolated;

@RestController
public class TaskTemplateChecklistController {

    @Autowired
    private ChecklistTemplateApplication service;

    @Autowired
    private ChecklistTemplateValidator validator;

    @Autowired
    private NormalizeEntityRequest normalizeEntityRequest;


    @RequestMapping(value = "/saleables/task-template/{taskTemplateId}/checklist-template/add", method = RequestMethod.POST)
    public  @ResponseBody
    ChecklistTemplateEntity save(@ModelAttribute ChecklistTemplateEntity checklistTemplateEntity, @PathVariable Long taskTemplateId) {

        checklistTemplateEntity.setTaskTemplateEntity(createTaskTemplateBuilder(taskTemplateId).build());
        hasContraintViolated(checklistTemplateEntity, validator);
        normalizeEntityRequest.doNestedReference(checklistTemplateEntity);

        ChecklistTemplateEntity result = service.register(checklistTemplateEntity);

        return result;

    }

    @RequestMapping(value="/saleables/task-template/{taskTemplateId}/checklist-template/add")
    public @ResponseBody void update(@ModelAttribute ChecklistTemplateEntity checklistTemplateEntity, @PathVariable Long taskTemplateId, Model model) {

        hasContraintViolated(checklistTemplateEntity, validator);

        normalizeEntityRequest.addFieldsToUpdate(checklistTemplateEntity);
        checklistTemplateEntity.setTaskTemplateEntity(createTaskTemplateBuilder(taskTemplateId).build());
        service.register(checklistTemplateEntity);
    }

    @RequestMapping(value="/saleables/task-template/{taskTemplateId}/checklist-templates")
    public ModelAndView list(@PathVariable Long taskTemplateId, Model model) {

        List<ChecklistTemplateEntity> result = service.findCheckListBy(TaskTemplateBuilder.createTaskTemplateBuilder(taskTemplateId).build());

        model.addAttribute("checklistTemplates", result);
        return new ModelAndView("/delivery/saleables/task-template/includes/checklist-template-table");

    }

    @RequestMapping(value="/saleables/task-template/checklist-templates/{checklistTemplateId}", method= RequestMethod.DELETE)
    public @ResponseBody void delete(@PathVariable Long checklistTemplateId) {
        service.delete(createChecklistTemplateBuilder(checklistTemplateId).build());
    }

}
