package pl.opalinski.sshclient.WebSocket;

import com.jcraft.jsch.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.swing.*;

public class SSHConnector{
    private Channel channel;

    private Session session;
    public void run() throws JSchException {

            JSch jSch = new JSch();
            session = jSch.getSession("", "", 22);
            session.setPassword("");
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
            channel=session.openChannel("shell");
            channel.connect();

    }

    public OutputStream getSShConnectionOutputStream() throws IOException, JSchException {
        OutputStream outputStream = channel.getOutputStream();
        if(outputStream!=null){
            return outputStream;
        }else{
            throw new JSchException();
        }

    }

    public InputStream getSShConnectionInputStream() throws IOException, JSchException{
        InputStream inputStream = channel.getInputStream();
        if(inputStream!=null){
            return inputStream;
        }else{
            throw new JSchException();
        }
    }


}
