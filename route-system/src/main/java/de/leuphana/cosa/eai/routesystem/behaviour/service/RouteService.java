package de.leuphana.cosa.eai.routesystem.behaviour.service;

import de.leuphana.cosa.eai.routesystem.structure.Route;

public interface RouteService {
    String ROUTE_CREATED_TOPIC = "routeservice/route/created";
    String ROUTE_KEY = "route";

    Route selectRoute();
}
