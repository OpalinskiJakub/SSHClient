package pl.opalinski.sshclient.Connection;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Component
public class SSHConnector {
    private Channel channel;

    private final ConnectionData connectionData;

    private Session session;

    public SSHConnector(ConnectionData connectionData) {
        this.connectionData = connectionData;
    }

    public void run() throws JSchException {
        JSch jSch = new JSch();
        session = jSch.getSession(connectionData.getName(), connectionData.getAddress(), 22);
        session.setPassword(connectionData.getPassword());
        session.setConfig("StrictHostKeyChecking", "no");
        session.connect();
        channel = session.openChannel("shell");
        channel.connect();

    }

    public OutputStream getSShConnectionOutputStream() throws IOException, JSchException {
        OutputStream outputStream = channel.getOutputStream();
        if (outputStream != null) {
            return outputStream;
        } else {
            throw new JSchException();
        }

    }

    public InputStream getSShConnectionInputStream() throws IOException, JSchException {
        InputStream inputStream = channel.getInputStream();
        if (inputStream != null) {
            return inputStream;
        } else {
            throw new JSchException();
        }
    }


}
