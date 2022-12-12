package ldts.terrarialike.statemanager;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Objects;


//FIXME: needs a refactor, was written in a bit of a rush
public class State{

    private Class dataClass;
    private Class viewClass;
    private Class controllerClass;

    private Object dataObject;
    private Object viewObject;
    private Object controllerObject;

    public State(Class dataClass, Class viewClass, Class controllerClass){
        this.dataClass = dataClass;
        this.viewClass = viewClass;
        this.controllerClass = controllerClass;
    }
    @SuppressWarnings("unchecked")

    public void initializeDataClass(Object ... arguments){

        try {
            Constructor[] t_list  = dataClass.getConstructors();
            if(arguments.length == 0){
                for (Constructor t: t_list) {
                    if(t.getParameterCount() == 0){
                        dataObject = t.newInstance();
                        return;
                    }
                }
            }
            ArrayList<Class> classes_list = new ArrayList<>();
            for(Object t : arguments){
                classes_list.add(t.getClass());
            }
            for(Constructor t: t_list){
                if(t.getParameterCount() == classes_list.size()){
                    Class[] p_lst = t.getParameterTypes();

                    ArrayList<Class> wanted_classes_list = new ArrayList<>();
                    for(Class p : p_lst){
                        wanted_classes_list.add(p);
                    }
                    if(wanted_classes_list.equals(classes_list)){
                        dataObject = t.newInstance(arguments);
                    }

                }
            }
        } catch ( InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
    @SuppressWarnings("unchecked")

    public void initializeViewClass(Object ... arguments){
        try {
            Constructor[] t_list  = viewClass.getConstructors();
            if(arguments.length == 0){
                for (Constructor t: t_list) {
                    if(t.getParameterCount() == 0){
                        viewObject = t.newInstance();
                        return;
                    }
                }
            }
            ArrayList<Class> classes_list = new ArrayList<>();
            for(Object t : arguments){
                classes_list.add(t.getClass());
            }
            for(Constructor t: t_list){
                if(t.getParameterCount() == classes_list.size()){
                    Class[] p_lst = t.getParameterTypes();

                    ArrayList<Class> wanted_classes_list = new ArrayList<>();
                    for(Class p : p_lst){
                        wanted_classes_list.add(p);
                    }
                    if(wanted_classes_list.equals(classes_list)){
                        viewObject = t.newInstance(arguments);
                        return;
                    }


                }
            }
        } catch ( InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
    @SuppressWarnings("unchecked")

    public void initializeControllerClass(Object ... arguments){
        try {
            Constructor[] t_list  = controllerClass.getConstructors();
            if(arguments.length == 0){
                for (Constructor t: t_list) {
                    if(t.getParameterCount() == 0){
                        controllerObject = t.newInstance();
                        return;
                    }
                }
            }
            ArrayList<Class> classes_list = new ArrayList<>();
            for(Object t : arguments){
                classes_list.add(t.getClass());
            }
            for(Constructor t: t_list){
                if(t.getParameterCount() == classes_list.size()){
                    Class[] p_lst = t.getParameterTypes();

                    ArrayList<Class> wanted_classes_list = new ArrayList<>();
                    for(Class p : p_lst){
                        wanted_classes_list.add(p);
                    }
                    if(wanted_classes_list.equals(classes_list)){
                        controllerObject = t.newInstance(arguments);
                        return;
                    }


                }
            }
        } catch ( InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean areAllObjectsInitialized(){
        return dataObject != null && viewObject != null && controllerObject != null;
    }

    @SuppressWarnings("unchecked")
    public <T extends Object> T getDataObject(Class<T> wantedClass){
        if(dataObject != null){
            return (T) dataObject;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public <T extends Object> T getViewObject(Class<T> wantedClass){
        if(viewObject != null){
            return (T) viewObject;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public <T extends Object> T getControllerObject(Class<T> wantedClass){
        if( controllerObject != null){
            return (T) controllerObject;
        }
        return null;
    }


    public String getDataClassName() {
        return dataClass.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return dataClass.equals(state.dataClass) && viewClass.equals(state.viewClass) && controllerClass.equals(state.controllerClass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dataClass, viewClass, controllerClass);
    }
}
