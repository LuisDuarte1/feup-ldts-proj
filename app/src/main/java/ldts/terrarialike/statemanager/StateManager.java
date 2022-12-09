package ldts.terrarialike.statemanager;

import ldts.terrarialike.exceptions.NotInitializedStateException;

import java.util.ArrayList;

///The state manager is a simple state machine but it saves previous state's memory, by default.
///It's an improvement compared to the normal state machine, because if we pause the game and want to transition to
///another state without losing the progress, a solution like this is needed
public class StateManager {

    private ArrayList<State> states;

    private State selectedState;

    public StateManager(){
        states = new ArrayList<>();
    }

    public void addState(State t) throws NotInitializedStateException {
        if(!t.areAllObjectsInitialized()){
            throw new NotInitializedStateException("State not initialized yet...");//don't add state if it's not initialized yet.
        }
        if(!(states.contains(t))){
            states.add(t);
        }
    }

    public void removeState(State t){
            states.remove(t);
    }

    public ArrayList<State> getStates(){
        //TODO: maybe create a copy of states? To avoid direct mutation
        return states;
    }

    public void selectState(State t){
        if(states.contains(t)){
            selectedState = t;
        }
    }

    public State getSelectedState(){
        return selectedState;
    }
}
