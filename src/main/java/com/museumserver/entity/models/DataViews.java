package com.museumserver.entity.models;

public class DataViews {
	
    public interface DefaultData{};

    public interface BeaconModificationsData{}
    
    public interface BeaconData{}
    
    public interface AdministratorData{}
    
    public interface MediaModificationsData{}
    
    public interface MediaData{}
    
    public interface BeaconsRequest extends DefaultData, BeaconModificationsData, AdministratorData{}
    
    public interface ModificationsRequest extends DefaultData, MediaData, BeaconData, AdministratorData{}
    
    public interface AdministratorsRequest extends DefaultData, MediaModificationsData, BeaconModificationsData{}
    
    public interface MediaRequest extends DefaultData, MediaModificationsData, AdministratorData{}
}
