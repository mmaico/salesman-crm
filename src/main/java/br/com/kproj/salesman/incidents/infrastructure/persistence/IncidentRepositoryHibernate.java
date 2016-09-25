package br.com.kproj.salesman.incidents.infrastructure.persistence;


import br.com.kproj.salesman.incidents.domain.model.incident.Incident;
import br.com.kproj.salesman.incidents.domain.model.incident.IncidentRepository;
import br.com.kproj.salesman.incidents.infrastructure.persistence.springdata.IncidentRepositorySpringData;
import br.com.kproj.salesman.infrastructure.entity.IncidentEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class IncidentRepositoryHibernate extends BaseRespositoryImpl<Incident, IncidentEntity> implements IncidentRepository {

    private IncidentRepositorySpringData repository;

    @Autowired
    public IncidentRepositoryHibernate(IncidentRepositorySpringData repository) {
        this.repository = repository;
    }

    @Override
    public BaseRepositoryLegacy<IncidentEntity, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<IncidentEntity, Incident> getConverter() {
        return null;
    }
}
