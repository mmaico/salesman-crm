package br.com.kproj.salesman.assistants.archive.infrastructure.persistence.translate;

import br.com.kproj.salesman.assistants.archive.domain.model.archive.PhysicalFile;
import br.com.kproj.salesman.infrastructure.converters.Specification;
import br.com.kproj.salesman.infrastructure.converters.TwoWayMerge;
import br.com.kproj.salesman.infrastructure.entity.AppFile;
import org.springframework.stereotype.Component;

@Component
public class PhysicalFileTranslate implements TwoWayMerge<PhysicalFile, AppFile> {


    @Override
    public void merge(PhysicalFile source, AppFile target, Specification... specs) {


    }

    @Override
    public void mergeBack(AppFile source, PhysicalFile target, Specification... specs) {

    }
}
