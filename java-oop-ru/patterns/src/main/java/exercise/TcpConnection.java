//package exercise;
package exercise.connections;

import java.util.List;
import java.util.ArrayList;

// BEGIN
public class TcpConnection {

    private String ipAddress;
    private int port;
    private Connection currentState;
    private List<String> data;

    public TcpConnection(String ipAddress, int port) {
        this.ipAddress = ipAddress;
        this.port = port;
        this.currentState = new Disconnected(this);
        this.data = new ArrayList<>();
    }

    public void changeState(Connection newState) {
        this.currentState = newState;
    }

    public String getCurrentState() {
        return this.currentState.getCurrentState();
    }

    public void connect() {
        this.currentState.connect();
    }

    public void disconnect() {
        this.currentState.disconnect();
    }

    public void write(String data) {
        this.currentState.write(data);
    }

    public List<String> getData() {
        return data;
    }

}
// END
