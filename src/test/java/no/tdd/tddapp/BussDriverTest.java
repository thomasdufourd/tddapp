package no.tdd.tddapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BussDriverTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void en_bussskjafor_har_start_gossip() {
        BusDriver driver = new BusDriver(new Gossip("g1"));

        Gossip gossip = driver.getStartGossip();

        assertNotNull(gossip);
    }

    @Test
    void has_gossip_list() {
        BusDriver driver = new BusDriver(new Gossip("G1"));
        assertNotNull(driver.getKnownGossips());

    }


    @Test
    void gossip_list_contains_at_least_the_start_gossip() {
        Gossip gossip = new Gossip("G1");
        BusDriver driver = new BusDriver(gossip);
        assertEquals(gossip, driver.getKnownGossips().get(0));
    }

    @Test
    void add_gossip_to_list() {
        Gossip gossip = new Gossip("G1");
        BusDriver driver = new BusDriver(gossip);
        Gossip gossip2 = new Gossip("G2");
        int startLen = driver.getKnownGossips().size();
        driver.addGossip(gossip2);
        int finalLen = driver.getKnownGossips().size();

        assertTrue(finalLen > startLen);
        assertEquals(gossip2, driver.getKnownGossips().get(finalLen - 1));
    }

    @Test
    void two_bus_drivers_meet_they_exchange_their_gossips() {
        Gossip gossip1 = new Gossip("G1");
        BusDriver driver1 = new BusDriver(gossip1);

        Gossip gossip2 = new Gossip("G2");
        BusDriver driver2 = new BusDriver(gossip2);

        driver1.meets(driver2);

        assertTrue(driver1.getKnownGossips().contains(gossip1));
        assertTrue(driver2.getKnownGossips().contains(gossip2));
        assertTrue(driver1.getKnownGossips().contains(gossip2));
        assertTrue(driver2.getKnownGossips().contains(gossip1));
    }

    @Test
    void busdriver_has_route() {
        Route route = new Route();
        Gossip gossip1 = new Gossip("G1");
        BusDriver driver1 = new BusDriver(gossip1, route);

        assertNotNull(driver1.getRoute());
    }

    @Test
    void busdriver_has_route_with_stops() {
        Route route = new Route(1, 2, 3, 4);

        Gossip gossip1 = new Gossip("G1");
        BusDriver driver1 = new BusDriver(gossip1, route);

        assertEquals(1, driver1.driveToNextStop());
    }
}