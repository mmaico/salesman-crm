package br.com.kproj.salesman.administration.users.infrastructure.persistence.translator;

import br.com.kproj.salesman.administration.users.domain.model.branch.Branch;
import br.com.kproj.salesman.infrastructure.entity.BranchEntity;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import org.springframework.stereotype.Component;

import static br.com.kproj.salesman.administration.users.domain.model.branch.BranchBuilder.createBranch;


@Component
public class BranchEntityToBranchConverter implements Converter<BranchEntity, Branch> {

    @Override
    public Branch convert(BranchEntity branchEntity, Object... args) {
        if (branchEntity == null) return null;

        Branch branch = createBranch(branchEntity.getId())
                .withName(branchEntity.getName()).build();

        return branch;
    }
}
