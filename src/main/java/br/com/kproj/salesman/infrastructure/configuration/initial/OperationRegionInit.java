package br.com.kproj.salesman.infrastructure.configuration.initial;

import br.com.kproj.salesman.infrastructure.configuration.parsers.RegionsParser;
import br.com.kproj.salesman.infrastructure.entity.OperationRegionEntity;
import br.com.kproj.salesman.infrastructure.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;


@Component
public class OperationRegionInit implements InitialProcess {


	@Autowired
	private RegionRepository repository;


	@Override
	@PostConstruct
	public void run() {

		long count = repository.count();
		if (count < 1) {
            List<OperationRegionEntity> result = RegionsParser.getRegions();

            repository.save(result);
        }
	}

	
}
