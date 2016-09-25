package br.com.kproj.salesman.infrastructure.entity.builders;


import br.com.kproj.salesman.infrastructure.entity.leads.LeadEntity;

public class LeadBuilder extends AbstractBuilder<LeadEntity>  {

	public LeadBuilder() {
		this.entity = new LeadEntity();
	}

	public LeadBuilder(Long id) {
		this();
		this.entity.setId(id);
	}

	public static LeadBuilder createLead(Long id) {
		return new LeadBuilder(id);
	}

	public static LeadBuilder createLead() {
		return new LeadBuilder();
	}
}
