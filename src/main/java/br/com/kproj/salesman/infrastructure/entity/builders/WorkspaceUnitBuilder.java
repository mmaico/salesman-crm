package br.com.kproj.salesman.infrastructure.entity.builders;


import br.com.kproj.salesman.infrastructure.entity.WorkspaceUnit;

public class WorkspaceUnitBuilder extends AbstractBuilder<WorkspaceUnit>  {

	public WorkspaceUnitBuilder() {
		this.entity = new WorkspaceUnit();
	}

	public WorkspaceUnitBuilder(Long id) {
		this();
		this.entity.setId(id);
	}

	public static WorkspaceUnitBuilder createWorkspace(Long id) {
		return new WorkspaceUnitBuilder(id);
	}

	public static WorkspaceUnitBuilder createWorkspace() {
		return new WorkspaceUnitBuilder();
	}
}
