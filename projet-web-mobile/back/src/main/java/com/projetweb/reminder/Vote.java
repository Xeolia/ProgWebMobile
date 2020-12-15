package com.projetweb.reminder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Table(name = "vote")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Vote {

    @Id
    @GeneratedValue
    private long id;

    String type;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User votant;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sondage_id", referencedColumnName = "id")
    private Sondage reference;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public User getVotant() {
        return votant;
    }

    public void setVotant(User votant) {
        this.votant = votant;
    }

    public Sondage getReference() {
        return reference;
    }

    public void setReference(Sondage reference) {
        this.reference = reference;
    }
}
