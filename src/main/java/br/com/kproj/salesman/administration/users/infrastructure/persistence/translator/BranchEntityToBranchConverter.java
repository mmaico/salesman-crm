package br.com.kproj.salesman.administration.users.infrastructure.persistence.translator;

import br.com.kproj.salesman.administration.users.domain.model.branch.Branch;
import br.com.kproj.salesman.administration.users.domain.model.branch.BranchBuilder;
import br.com.kproj.salesman.administration.users.domain.model.user.User;
import br.com.kproj.salesman.infrastructure.entity.BranchEntity;
import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import org.springframework.stereotype.Component;

import static br.com.kproj.salesman.administration.users.domain.model.branch.BranchBuilder.createBranch;
import static br.com.kproj.salesman.administration.users.domain.model.user.UserBuilder.createUser;


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
