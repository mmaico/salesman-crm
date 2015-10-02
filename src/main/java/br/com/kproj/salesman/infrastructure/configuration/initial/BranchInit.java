package br.com.kproj.salesman.infrastructure.configuration.initial;

import br.com.kproj.salesman.infrastructure.configuration.parsers.BranchsParser;
import br.com.kproj.salesman.infrastructure.entity.Branch;
import br.com.kproj.salesman.infrastructure.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Cria as filiais da empresa
 */
@Component
public class BranchInit implements InitialProcess {

	@Autowired
	private BranchRepository branchRepository;


	@Override
	@PostConstruct
	public void run() {

		long count = branchRepository.count();
        List<Branch> branchs = BranchsParser.getBranchs();
        if (count < 1 && !branchs.isEmpty()) {
            branchRepository.save(branchs);
        }
	}



	
}
