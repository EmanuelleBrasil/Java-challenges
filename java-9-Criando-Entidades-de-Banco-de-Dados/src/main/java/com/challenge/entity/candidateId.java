package com.challenge.entity;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class candidateId implements Serializable {
    @ManyToOne
    public user user;

    @ManyToOne
    public acceleration acceleration;

    @ManyToOne
    public company company;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof candidateId)) return false;
        candidateId that = (candidateId) o;
        return user.equals(that.user) && acceleration.equals(that.acceleration) && company.equals(that.company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, acceleration, company);
    }
}
