package com.customer.relationship.management.app.accounts;

import com.customer.relationship.management.app.leads.Lead;
import com.customer.relationship.management.app.notes.Note;
import com.customer.relationship.management.app.users.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "accounts")
@Getter
@Setter
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_status", nullable = false)
    private AccountStatus accountStatus;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany(mappedBy = "account")
    @OrderBy("noteDate DESC")
    private List<Note> notes;

    @JsonManagedReference
    @OneToMany(mappedBy = "account")
    @OrderBy("createdAt DESC")
    private List<Lead> leads;

    @OneToMany(mappedBy = "account")
    @JsonIgnore
    private List<Task> tasks;
}