package exercise.connections;

// BEGIN
public class Connected implements Connection {

    private TcpConnection connection;

    public Connected(TcpConnection connection) {
        this.connection = connection;
    }

    @Override
    public String getCurrentState() {
        return "connected";
    }

    public void connect() {
        System.out.println("Error! Connection already connected");
    }

    public void disconnect() {
        connection.changeState(new Disconnected(connection));
    }

    public void write(String data) {
        connection.getData().add(data);
    }
}
// END
