package br.com.kproj.salesman.auditing.delivery.domain.service;

import br.com.kproj.salesman.auditing.delivery.domain.model.audit.Auditing;

import java.util.Optional;

@FunctionalInterface
public interface TaskNewVersion {

    Optional<Auditing> andTheNewVersionIs(String data);
}
