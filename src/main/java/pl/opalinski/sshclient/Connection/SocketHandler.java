package pl.opalinski.sshclient.Connection;

import com.jcraft.jsch.JSchException;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

@Component
public class SocketHandler extends TextWebSocketHandler {

    private final SSHConnector shell;

    public SocketHandler(SSHConnector shell) {
        this.shell = shell;
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message)
            throws InterruptedException, IOException, JSchException {
        OutputStream outputStream = shell.getSShConnectionOutputStream();
        byte[] preparedMessage = message.getPayload().getBytes(StandardCharsets.UTF_8);
        outputStream.write(preparedMessage);
        outputStream.flush();
    }


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        shell.run();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                byte[] response = new byte[1024];
                try {
                    InputStream in = shell.getSShConnectionInputStream();

                    while (true) {
                        while (in.available() > 0) {
                            int i = in.read(response, 0, 1024);
                            TextMessage preparedResponse = new TextMessage(new String(response, 0, i));
                            session.sendMessage(preparedResponse);
                        }

                    }
                } catch (IOException | JSchException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();

    }


}
