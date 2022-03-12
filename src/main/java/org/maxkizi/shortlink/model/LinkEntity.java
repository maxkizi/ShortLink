package org.maxkizi.shortlink.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "link_table")
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class LinkEntity implements Serializable {
    @Id
    @Column(name = "short_link")
    private String shortLink;
    @Column(name = "full_link")
    private String fullLink;
    @Column(name = "call_count")
    //TODO: здесь должен быть AtomicLong, а не long
    private long countOfCalls = 0L;
    @Column(name = "created_at")
    private Date createdAt;
}
