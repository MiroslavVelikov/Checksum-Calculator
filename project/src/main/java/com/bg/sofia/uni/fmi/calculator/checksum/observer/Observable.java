package main.java.com.bg.sofia.uni.fmi.calculator.checksum.observer;

import java.util.ArrayList;
import java.util.List;

public abstract class Observable {

    private final List<Observer> observers;

    protected Observable() {
        this.observers = new ArrayList<>();
    }

    public void registerObserver(Observer obs) {
        observers.add(obs);
    }

    public void notifyObservers(Object message) {
        for (Observer obs : observers) {
            obs.update(this, message);
        }
    }

}
