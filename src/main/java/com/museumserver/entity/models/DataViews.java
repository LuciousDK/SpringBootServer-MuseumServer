package com.museumserver.entity.models;

public class DataViews {
	
    public interface DefaultData{};

    public interface BeaconModificationsData{}
    
    public interface BeaconData{}
    
    public interface AdministratorData{}
    
    public interface MediaModificationsData{}
    
    public interface MediaData{}
    
    public interface MediaListData{}
    
    public interface ExhibitionListData{}
    
    public interface ArtworkListData{}
    
    public interface BeaconListData{}
    
    public interface ExhibitionData{}
    
    public interface ArtworkData{}
    
    
    public interface ArtworkRequest extends DefaultData, BeaconListData, MediaListData{}
    
    public interface ExhibitionRequest extends DefaultData, ArtworkListData, MediaListData{}
    
    public interface BeaconsRequest extends DefaultData, BeaconModificationsData, AdministratorData, ArtworkData{}
    
    public interface ModificationsRequest extends DefaultData, MediaData, BeaconData, AdministratorData{}
    
    public interface AdministratorsRequest extends DefaultData, MediaModificationsData, BeaconModificationsData{}
    
    public interface MediaRequest extends DefaultData, MediaModificationsData, AdministratorData, ExhibitionListData, ArtworkListData{}
}
