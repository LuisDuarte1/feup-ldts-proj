package ldts.terrarialike.controller;

import ldts.terrarialike.statemanager.StateManager;

public abstract class AbstractStateController {

    private StateManager stateManager;

    public AbstractStateController(StateManager stateManager){
        this.stateManager = stateManager;
    }
}
