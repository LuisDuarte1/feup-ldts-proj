package ldts.terrarialike.controller;

import ldts.terrarialike.statemanager.StateManager;

public abstract class AbstractStateController {

    private final StateManager stateManager;

    public AbstractStateController(StateManager stateManager){
        this.stateManager = stateManager;
    }

    abstract public void tick();
}
