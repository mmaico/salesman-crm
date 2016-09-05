package br.com.kproj.salesman.administration.approval_negotiation.application.impl;

import br.com.kproj.salesman.administration.approval_negotiation.application.ApproverFacade;
import br.com.kproj.salesman.administration.approval_negotiation.domain.model.approver.Approver;
import br.com.kproj.salesman.administration.approval_negotiation.domain.model.approver.ApproverRepository;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ApproverServiceImpl extends BaseModelServiceImpl<Approver> implements ApproverFacade {

    @Autowired
    private ApproverRepository repository;



    @Override
    public Optional<Approver> register(Approver approver) {

        Optional<Approver> result = repository.findOne(approver.getId());

        if (result.isPresent()) {
            result.get().setAvailable(approver.getAvailable());
            return repository.save(result.get());
        } else {
            return repository.save(approver);
        }
    }

    @Override
    public BaseRepository<Approver, Long> getRepository() {
        return repository;
    }
}
