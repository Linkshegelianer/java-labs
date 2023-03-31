package exercise.connections;

public interface Connection {
    // BEGIN
    String getCurrentState();
    void connect();
    void disconnect();
    void write(String data);
    // END
}
