package de.projectp.escaped.game.state;

public interface State {
    void enable();
    void update(double delta, double totalUpdates);
    void disable();
}
