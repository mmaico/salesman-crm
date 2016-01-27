package br.com.kproj.salesman.delivery.application;

import br.com.kproj.salesman.delivery.infrastructure.validators.ActDeliverySalesValidator;
import br.com.kproj.salesman.infrastructure.entity.ActDeliverySales;
import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrder;
import br.com.kproj.salesman.infrastructure.repository.ActDeliverySalesRepository;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static br.com.kproj.salesman.infrastructure.validators.ValidatorHelper.hasContraintViolated;

@Service
public class ActDeliverySalesApplicationImpl extends BaseModelServiceImpl<ActDeliverySales> implements ActDeliverySalesApplication {

    @Autowired
    private ActDeliverySalesRepository repository;

    @Autowired
    private ActDeliverySalesValidator validator;

    public ActDeliverySales register(ActDeliverySales actDeliverySales) {

        hasContraintViolated(actDeliverySales, validator);

        Optional<ActDeliverySales> result = repository.findBySalesOrderAndUser(actDeliverySales.getSalesOrder(), actDeliverySales.getUser());

        if (!result.isPresent()) {
            return super.save(actDeliverySales);
        }

        return result.get();
    }

    @Override
    public List<User> findUsersResponsibles(SalesOrder salesOrder) {
        if (salesOrder == null || salesOrder.isNew()) {
            return Lists.newArrayList();
        }
        return repository.findUserWorkingActDelivery(salesOrder);
    }


    public BaseRepository<ActDeliverySales, Long> getRepository() {
        return this.repository;
    }

    @Override
    public List<SalesOrder> findNewSalesOrder() {
        return this.repository.findSalesOrderOutActDelivery();
    }

    @Override
    public List<SalesOrder> findBy(User user) {
        return this.repository.findByUser(user);
    }

    @Override
    public List<SalesOrder> findSalesOrderInActDelivery() {
        return this.repository.findSalesOrderInActDelivery();
    }
}
