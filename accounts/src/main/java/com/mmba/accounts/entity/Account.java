package com.mmba.accounts.entity;

import jakarta.persistence.*;
import lombok.*;


@Table(name = "Account")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Account extends BaseEntity{

    @Id
    private Long accountNumber;

    private Long customerId;

    private String accountType;

    private String branchAddress;
}
