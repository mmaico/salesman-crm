package br.com.kproj.salesman.delivery.view;

import br.com.kproj.salesman.delivery.application.tasks.ChecklistApplication;
import br.com.kproj.salesman.delivery.infrastructure.validators.ChecklistValidator;
import br.com.kproj.salesman.infrastructure.entity.builders.ChecklistBuilder;
import br.com.kproj.salesman.infrastructure.entity.task.Checklist;
import br.com.kproj.salesman.infrastructure.helpers.view.NormalizeEntityRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static br.com.kproj.salesman.infrastructure.entity.builders.ChecklistTemplateBuilder.createChecklistTemplateBuilder;
import static br.com.kproj.salesman.infrastructure.entity.builders.TaskBuilder.createTaskBuilder;
import static br.com.kproj.salesman.infrastructure.entity.builders.TaskTemplateBuilder.createTaskTemplateBuilder;
import static br.com.kproj.salesman.infrastructure.validators.ValidatorHelper.hasContraintViolated;

@RestController
public class TaskChecklistController {

    @Autowired
    private ChecklistApplication application;

    @Autowired
    private ChecklistValidator validator;

    @Autowired
    private NormalizeEntityRequest normalizeEntityRequest;


    @RequestMapping(value = "/tasks/{taskId}/checklists/save", method = RequestMethod.POST)
    public  @ResponseBody Checklist save(@ModelAttribute Checklist checklist, @PathVariable Long taskId) {

        checklist.setTask(createTaskBuilder(taskId).build());
        hasContraintViolated(checklist, validator);
        normalizeEntityRequest.doNestedReference(checklist);

        Checklist result = application.register(checklist);

        return result;

    }

    @RequestMapping(value="/tasks/{taskId}/checklists/save")
    public @ResponseBody void update(@ModelAttribute Checklist checklist, @PathVariable Long taskId, Model model) {

        hasContraintViolated(checklist, validator);

        normalizeEntityRequest.addFieldsToUpdate(checklist);
        checklist.setTask(createTaskBuilder(taskId).build());
        application.register(checklist);
    }

    @RequestMapping(value="/tasks/{taskId}/checklists")
    public ModelAndView list(@RequestParam(defaultValue="checklist-task-table",required=false, value="template") String templateName,
                             @PathVariable Long taskId, Model model) {

        List<Checklist> result = application.findCheckListBy(createTaskBuilder(taskId).build());

        model.addAttribute("checklists", result);

        return new ModelAndView("/delivery/tasks/includes/" + templateName);

    }

    @RequestMapping(value="/tasks/checklists/{checkListId}", method= RequestMethod.PUT)
    public @ResponseBody void maskOk(@PathVariable Long checkListId) {
        application.completed(ChecklistBuilder.createChecklist(checkListId).build());
    }

}
