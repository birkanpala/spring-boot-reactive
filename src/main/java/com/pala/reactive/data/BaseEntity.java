package com.pala.reactive.data;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.*;

import java.util.Date;

@Getter
@Setter
public class BaseEntity {

    @Id
    private ObjectId id;

    @CreatedBy
    private String createdBy;

    @CreatedDate
    private Date createdDate;

    @LastModifiedBy
    private String updatedBy;

    @LastModifiedDate
    private Date updatedDate;

    @Version
    private Long version;

    public String getId() {
        return this.id.toHexString();
    }
}
