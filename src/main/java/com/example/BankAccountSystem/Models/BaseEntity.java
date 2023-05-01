package com.example.BankAccountSystem.Models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.MappedSuperclass;
import java.util.Date;
@Getter
@Setter
@Data
@MappedSuperclass
public class BaseEntity {
    @CreationTimestamp
    Date createdDate;

    @UpdateTimestamp
    Date updatedDate;
    String isActive;

}
