package br.com.kproj.salesman.sales.application;


import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrder;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.repository.SalesOrderRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalesOrderApplicationImpl extends BaseModelServiceImpl<SalesOrder> implements SalesOrderApplication {


    private SalesOrderRepository repository;

    @Autowired
    public SalesOrderApplicationImpl(SalesOrderRepository repository) {
        this.repository = repository;
    }


    @Override
    public BaseRepository<SalesOrder, Long> getRepository() {
        return repository;
    }
}
