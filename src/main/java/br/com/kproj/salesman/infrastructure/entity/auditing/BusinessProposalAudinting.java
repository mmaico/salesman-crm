package br.com.kproj.salesman.infrastructure.entity.auditing;


import br.com.kproj.salesman.infrastructure.configuration.ServiceLocator;
import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;
import com.google.gson.Gson;
import com.jayway.jsonpath.JsonPath;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name="business_proposal_auditing")
public class BusinessProposalAudinting extends Identifiable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name="entity_id")
    @NotNull
    private Long entityId;

    @Column(columnDefinition = "LONGTEXT")
    @NotNull
    private String info;

    @ManyToOne
    @JoinColumn(name="user_id")
    @NotNull
    private UserEntity user;

    @Column(name="last_update")
    @NotNull
    private Date lastUpdate;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public BusinessProposal get() {
        Gson gson = ServiceLocator.getBean(Gson.class);
        return gson.fromJson(this.info, BusinessProposal.class);
    }

    public Boolean isEquals(String json) {
        String saleablesItemsStored = JsonPath.read(this.info, "$.saleableItems").toString();
        String paymentItemsStored = JsonPath.read(this.info, "$.paymentItems").toString();

        String saleablesItemsNew = JsonPath.read(json, "$.saleableItems").toString();
        String paymentItemsNew = JsonPath.read(json, "$.paymentItems").toString();

        String regionNew = JsonPath.read(json, "$.operationRegion").toString();
        String regionStored = JsonPath.read(this.info, "$.operationRegion").toString();


        return saleablesItemsStored.equals(saleablesItemsNew)
                && paymentItemsStored.equals(paymentItemsNew)
                && regionStored.equals(regionNew);
    }
}
