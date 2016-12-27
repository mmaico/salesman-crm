package br.com.kproj.salesman.assistants.activities.application.impl;

import br.com.kproj.salesman.assistants.activities.application.RootActivityFacade;
import br.com.kproj.salesman.assistants.activities.domain.model.personal.rootactivity.RootActivity;
import br.com.kproj.salesman.assistants.activities.domain.model.personal.rootactivity.RootActivityRepository;
import br.com.kproj.salesman.assistants.activities.domain.model.personal.rootactivity.RootActivityValidator;
import br.com.kproj.salesman.assistants.activities.domain.model.user.Owner;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RootActivityServiceImpl extends BaseModelServiceImpl<RootActivity> implements RootActivityFacade {


    private RootActivityRepository repository;
    private RootActivityValidator rules;


    @Autowired
    public RootActivityServiceImpl(RootActivityRepository repository, RootActivityValidator rules) {
        this.repository = repository;
        this.rules = rules;
    }

    @Override
    public Optional<RootActivity> register(RootActivity activity) {
        rules.checkRules(activity);

        return activity.makeRoot();
    }

    @Override
    public Iterable<RootActivity> findAll(Owner owner, Pageable pageable) {
        return repository.findAll(owner, pageable);
    }

    @Override
    public BaseRepository<RootActivity, Long> getRepository() {
        return repository;
    }


}
