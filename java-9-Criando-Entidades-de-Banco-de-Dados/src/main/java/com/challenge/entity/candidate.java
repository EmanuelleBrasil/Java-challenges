package com.challenge.entity;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@EntityListeners(EntityListeners.class)
public class candidate {

    @EmbeddedId
    private candidateId candidateId;

    @NotNull
    private int status;

    @NotNull
    @CreatedDate
    private Timestamp createdAt;

}
