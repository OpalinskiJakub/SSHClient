package pl.opalinski.sshclient.Entities;


import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    private String address;
}
