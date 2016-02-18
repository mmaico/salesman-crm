package br.com.kproj.salesman.assistants.activities.view;

import br.com.kproj.salesman.assistants.activities.application.ActivityChecklistApplication;
import br.com.kproj.salesman.delivery.application.tasks.ChecklistApplication;
import br.com.kproj.salesman.delivery.infrastructure.validators.ChecklistValidator;
import br.com.kproj.salesman.infrastructure.entity.activities.ActivityChecklist;
import br.com.kproj.salesman.infrastructure.entity.builders.ActivityChecklistBuilder;
import br.com.kproj.salesman.infrastructure.entity.builders.ChecklistBuilder;
import br.com.kproj.salesman.infrastructure.entity.builders.PersonalActivityBuilder;
import br.com.kproj.salesman.infrastructure.entity.task.Checklist;
import br.com.kproj.salesman.infrastructure.helpers.NormalizeEntityRequest;
import br.com.kproj.salesman.infrastructure.security.helpers.SecurityHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static br.com.kproj.salesman.infrastructure.entity.builders.ActivityChecklistBuilder.createActivityChecklist;
import static br.com.kproj.salesman.infrastructure.entity.builders.PersonalActivityBuilder.createActivity;
import static br.com.kproj.salesman.infrastructure.entity.builders.TaskBuilder.createTaskBuilder;
import static br.com.kproj.salesman.infrastructure.validators.ValidatorHelper.hasContraintViolated;

@RestController
public class ActivityChecklistController {

    @Autowired
    private ActivityChecklistApplication application;

    @Autowired
    private ChecklistValidator validator;

    @Autowired
    private NormalizeEntityRequest normalizeEntityRequest;


    @RequestMapping(value = "/personal-activities/{activityId}/checklists/save", method = RequestMethod.POST)
    public  @ResponseBody
    ActivityChecklist save(@ModelAttribute ActivityChecklist checklist, @PathVariable Long activityId ) {

        checklist.setActivity(createActivity(activityId).build());
        hasContraintViolated(checklist, validator);
        normalizeEntityRequest.doNestedReference(checklist);

        ActivityChecklist result = application.register(checklist);

        return result;

    }

    @RequestMapping(value="/personal-activities/{activityId}/checklists")
    public ModelAndView list(@PathVariable Long activityId, Model model) {

        List<ActivityChecklist> result = application.findCheckListBy(createActivity(activityId).build());

        model.addAttribute("checklists", result);
        return new ModelAndView("/delivery/tasks/includes/checklist-task-table");

    }

    @RequestMapping(value="/personal-activities/checklists/{checkListId}", method= RequestMethod.PUT)
    public @ResponseBody void maskOk(@PathVariable Long checkListId) {
        application.completed(createActivityChecklist(checkListId).build());
    }

}
