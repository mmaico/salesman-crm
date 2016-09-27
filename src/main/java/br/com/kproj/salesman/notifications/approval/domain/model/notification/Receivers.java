package br.com.kproj.salesman.notifications.approval.domain.model.notification;

import br.com.kproj.salesman.infrastructure.model.CollectionBehavior;
import br.com.kproj.salesman.notifications.approval.domain.model.user.Receiver;
import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.List;


public class Receivers extends CollectionBehavior<Receiver> {

    private final List<Receiver> receivers;

    public Receivers() {
        this.receivers = Lists.newArrayList();
    }

    public Receivers(List<Receiver> notifications) {
        this.receivers = notifications;
    }

    @Override
    public Collection<Receiver> getCollection() {
        return receivers;
    }
}
