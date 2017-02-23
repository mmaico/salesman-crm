package br.com.kproj.salesman.assistants.archive.infrastructure.persistence.translate;

import br.com.kproj.salesman.assistants.archive.domain.model.archive.PhysicalFile;
import br.com.kproj.salesman.infrastructure.converters.Specification;
import br.com.kproj.salesman.infrastructure.converters.TwoWayMerge;
import br.com.kproj.salesman.infrastructure.entity.AppFileEntity;
import org.springframework.stereotype.Component;

@Component
public class PhysicalFileTranslate implements TwoWayMerge<PhysicalFile, AppFileEntity> {


    @Override
    public void merge(PhysicalFile source, AppFileEntity target, Specification... specs) {


    }

    @Override
    public void mergeBack(AppFileEntity source, PhysicalFile target, Specification... specs) {

    }
}
