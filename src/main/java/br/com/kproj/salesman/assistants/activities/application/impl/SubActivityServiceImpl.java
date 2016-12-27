package br.com.kproj.salesman.assistants.activities.application.impl;

import br.com.kproj.salesman.assistants.activities.application.SubActivityFacade;
import br.com.kproj.salesman.assistants.activities.application.validators.SubActivityBusinessRules;
import br.com.kproj.salesman.assistants.activities.domain.model.personal.Activity;
import br.com.kproj.salesman.assistants.activities.domain.model.personal.rootactivity.RootActivity;
import br.com.kproj.salesman.assistants.activities.domain.model.personal.subactivity.SubActivity;
import br.com.kproj.salesman.assistants.activities.domain.model.personal.subactivity.SubActivityRepository;
import br.com.kproj.salesman.assistants.activities.domain.model.personal.subactivity.SubActivityToRootActivity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static br.com.kproj.salesman.assistants.activities.domain.model.user.Owner.owner;

@Service
public class SubActivityServiceImpl extends BaseModelServiceImpl<SubActivity> implements SubActivityFacade {


    private SubActivityRepository repository;
    private SubActivityBusinessRules rules;


    @Autowired
    public SubActivityServiceImpl(SubActivityRepository repository, SubActivityBusinessRules rules) {
        this.repository = repository;
        this.rules = rules;
    }

    @Override
    public Optional<SubActivity> register(SubActivityToRootActivity subActivityToRootActivity) {
        Activity parent = subActivityToRootActivity.getParent();
        SubActivity subActivity = subActivityToRootActivity.getSubActivity();
        subActivity.setParent(parent);

        rules.checkRules(subActivity);

        return owner().save(subActivity).childOf(parent);
    }

    @Override
    public Iterable<SubActivity> findAll(RootActivity rootActivity, Pageable pageable) {
        return repository.findAll(rootActivity, pageable);
    }

    @Override
    public BaseRepository<SubActivity, Long> getRepository() {
        return repository;
    }
}
