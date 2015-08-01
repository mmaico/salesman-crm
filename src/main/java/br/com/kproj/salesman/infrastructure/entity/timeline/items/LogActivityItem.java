package br.com.kproj.salesman.infrastructure.entity.timeline.items;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("logactivity")
public class LogActivityItem extends TimelineItem {

}
