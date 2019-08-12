package no.tdd.tddapp;

import java.util.ArrayList;
import java.util.List;

public class BusDriver {
    private Route route;
    private Gossip startGossip;
    private List<Gossip> knownGossips = new ArrayList<>();

    public BusDriver(Gossip gossip) {
        this.startGossip = gossip;
        this.knownGossips.add(this.startGossip);
    }

    public BusDriver(Gossip gossip, Route route) {
        this.startGossip = gossip;
        this.knownGossips.add(this.startGossip);
        this.route = route;
    }

    public Gossip getStartGossip() {
        return startGossip;
    }

    public List<Gossip> getKnownGossips() {
        return knownGossips;
    }

    public void addGossip(Gossip gossip) {
        knownGossips.add(gossip);
    }

    public void meets(BusDriver driver2) {
        for (Gossip g : driver2.knownGossips){
            if (!this.knownGossips.contains(g)){
                addGossip(g);
            }
        }
        driver2.knownGossips.clear();
        driver2.knownGossips.addAll(this.knownGossips);
    }

    public Route getRoute() {
        return route;
    }

    public int driveToNextStop() {
        return 1; // TODO: fix me!
    }
}
