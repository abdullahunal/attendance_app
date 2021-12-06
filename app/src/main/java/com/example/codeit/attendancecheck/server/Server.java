package com.example.codeit.attendancecheck.server;

public abstract class Server {

    private State state = State.STOPPED;

    public void start() {
        if (state != State.STARTED) {
            state = State.STARTED;
            startServer();
        }
    }

    public void stop() {
        if (state != State.STOPPED) {
            state = State.STOPPED;
            stopServer();
        }
    }

    protected abstract void startServer();

    protected abstract void stopServer();

    public enum State {
        STARTED, STOPPED,
    }
}
