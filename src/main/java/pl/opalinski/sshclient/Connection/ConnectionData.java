package pl.opalinski.sshclient.Connection;


import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;


@Getter
@Setter
@Component
public class ConnectionData {

    private long id;

    private String password;

    private String name;

    private String address;
}
