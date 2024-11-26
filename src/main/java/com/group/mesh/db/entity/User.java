package com.group.mesh.db.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "\"user\"")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private Account account;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Email> emails = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Phone> phones = new HashSet<>();

    @Column
    private String name;

    @Column
    private String password;

    @Column
    private LocalDateTime dateOfBirth;

    public void setAccount(Account account) {
        account.setUser(this);
        this.account = account;
    }

    public void addEmail(Email email) {
        email.setUser(this);
        emails.add(email);
    }

    public void addPhone(Phone phone) {
        phone.setUser(this);
        phones.add(phone);
    }
}
