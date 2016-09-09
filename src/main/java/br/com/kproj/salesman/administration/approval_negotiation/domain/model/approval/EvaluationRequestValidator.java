package br.com.kproj.salesman.administration.approval_negotiation.domain.model.approval;


public interface EvaluationRequestValidator {

    void hasValidInfoToEvaluation(EvaluationRequest request);
}
