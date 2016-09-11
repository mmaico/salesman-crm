package br.com.kproj.salesman.delivery.view.dtos;


import br.com.kproj.salesman.infrastructure.entity.OperationRegionEntity;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;
import br.com.kproj.salesman.infrastructure.entity.task.TaskTemplateEntity;

public class TaskTemplateDTO {

    private Long id;
    private String title;
    private String description;
    private Integer quantityDaysTofinishAfertSignedContract;
    private SaleableUnitEntity saleable;
    private OperationRegionEntity region;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantityDaysTofinishAfertSignedContract() {
        return quantityDaysTofinishAfertSignedContract;
    }

    public void setQuantityDaysTofinishAfertSignedContract(Integer quantityDaysTofinishAfertSignedContract) {
        this.quantityDaysTofinishAfertSignedContract = quantityDaysTofinishAfertSignedContract;
    }

    public SaleableUnitEntity getSaleable() {
        return saleable;
    }

    public void setSaleable(SaleableUnitEntity saleable) {
        this.saleable = saleable;
    }

    public OperationRegionEntity getRegion() {
        return region;
    }

    public void setRegion(OperationRegionEntity region) {
        this.region = region;
    }

    public static TaskTemplateDTO build(TaskTemplateEntity template) {
        TaskTemplateDTO taskTemplate = new TaskTemplateDTO();
        taskTemplate.setId(template.getId());
        taskTemplate.setDescription(template.getDescription());
        taskTemplate.setTitle(template.getTitle());
        taskTemplate.setRegion(template.getRegion());
        taskTemplate.setSaleable(template.getSaleable());
        return taskTemplate;
    }
}
