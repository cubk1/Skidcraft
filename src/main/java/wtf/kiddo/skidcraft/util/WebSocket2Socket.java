package wtf.kiddo.skidcraft.util;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;
import java.nio.ByteBuffer;

public final class WebSocket2Socket extends Thread {

    private final String address;

    private final int port;

    public WebSocket2Socket(String address, int localPort) {
        this.address = address;
        this.port = localPort;
    }

    @Override
    public void run() {
        System.out.println("WebSocket2Socket by Dimples#1337");
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println(serverSocket.getInetAddress());
            while (true) {
                Socket accept = serverSocket.accept();
                new ProcessThread(accept,address).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class ProcessThread extends Thread {
        Socket client;
        WebSocket server;

        public ProcessThread(Socket client,String wsurl) throws Exception {
            this.client = client;
            this.server = new WebSocket(new URI(wsurl), client);
            this.server.connect();
            while (!server.isOpen()) {
                sleep(500);
            }
        }

        @Override
        public void run() {
            try {
                InputStream inputStream = client.getInputStream();

                byte[] buf = new byte[1024];
                int length;
                while ((length = inputStream.read(buf)) > 0) {
                    byte[] buf2 = new byte[length];
                    System.arraycopy(buf, 0, buf2, 0, length);
                    server.send(buf2);
                }
            } catch (Throwable ignored) {

            }
        }
    }

    class WebSocket extends WebSocketClient {
        OutputStream outputStream;

        public WebSocket(URI serverUri, Socket client) throws IOException {
            super(serverUri);
            this.outputStream = client.getOutputStream();
        }

        @Override
        public void onOpen(ServerHandshake serverHandshake) {
            System.out.println(this + " Connected");
        }

        @Override
        public void onMessage(String s) {

        }

        @Override
        public void onMessage(ByteBuffer bytes) {
            try {
                outputStream.write(bytes.array());
                outputStream.flush();
            } catch (IOException e) {
                this.close();
            }
        }

        @Override
        public void onClose(int i, String s, boolean b) {
            System.out.println(this + " Closed");
        }

        @Override
        public void onError(Exception e) {
            e.printStackTrace();
        }
    }
}
