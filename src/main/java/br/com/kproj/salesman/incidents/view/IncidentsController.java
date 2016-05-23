package br.com.kproj.salesman.incidents.view;


import br.com.kproj.salesman.incidents.application.IncidentApplication;
import br.com.kproj.salesman.incidents.infrastructure.IncidentValidator;
import br.com.kproj.salesman.infrastructure.entity.Incident;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.helpers.NormalizeEntityRequest;
import br.com.kproj.salesman.infrastructure.security.helpers.SecurityHelper;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@RestController
public class IncidentsController {

    @Autowired
    private IncidentApplication service;

    @Autowired
    private NormalizeEntityRequest normalizeEntityRequest;

    @Autowired
    private IncidentValidator validator;

    @Autowired
    private SecurityHelper security;


    @InitBinder(value = "incident")
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }


    @RequestMapping(value = "/incidents/save", method = RequestMethod.POST)
    public  @ResponseBody String save(@ModelAttribute @Validated Incident incident, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getAllErrors());
        }

        if (incident.isNew()) {
            incident.setCreatedBy(security.getPrincipal().getUser());
        }

        normalizeEntityRequest.doNestedReference(incident);
        Incident incidentLoad = service.save(incident);

        return "/incidents/" + incidentLoad.getId();
    }

    @RequestMapping(value = "/incidents/save", method = RequestMethod.PUT)
    public @ResponseBody String update(@ModelAttribute Incident incident, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getAllErrors());
        }
        if (incident.isNew()) {
            throw new ValidationException(Sets.newHashSet("incidents.update.without.id"));
        }

        normalizeEntityRequest.addFieldsToUpdate(incident);
        Incident incidentLoaded = service.save(incident);

        return "/incidents/" + incidentLoaded.getId();
    }
}
