package com.duke.dls.model.entity;

import com.duke.dls.constant.AppConstants;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public abstract class DomainBase {

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = AppConstants.DATE_TIME_FORMAT)
    @Column(name = "CREATED_DATE")
    private LocalDateTime createdDate;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = AppConstants.DATE_TIME_FORMAT)
    @Column(name = "UPDATED_DATE")
    private LocalDateTime updateDate;

    @Column(name = "UPDATED_BY")
    private String updatedBy;

    @PrePersist
    public void prePersist() {
        createdDate = LocalDateTime.now();
        createdBy = "app-user-create";
    }

    @PreUpdate
    public void preUpdate() {
        updateDate = LocalDateTime.now();
        updatedBy = "app-user-update";
    }

}
