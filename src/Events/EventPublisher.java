package Events;

import java.util.*;

public class EventPublisher {

    private List<EventListener> listeners = new ArrayList<>();

    public void addListener(EventListener listener){
        listeners.add(listener);
    }

    public void removeListener(EventListener listener){
        listeners.remove(listener);
    }

    public void triggerEvent(){
        for(EventListener listener:listeners){
            listener.onEvent();
        }
    }

}
