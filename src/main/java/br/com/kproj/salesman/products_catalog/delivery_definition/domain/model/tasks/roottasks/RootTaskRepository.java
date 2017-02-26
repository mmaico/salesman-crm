package br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.roottasks;


import br.com.kproj.salesman.infrastructure.repository.BaseRepository;

public interface RootTaskRepository extends BaseRepository<RootTask, Long> {

    void delete(RootTask task);


}
