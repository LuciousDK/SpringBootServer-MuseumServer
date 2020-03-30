package com.museumserver.entity.models;

public class DataViews {
	
    public interface DefaultData{};
    
    public interface AppUserData{};
    
    public interface ExhibitionData{};
    
    public interface ArtworkData{};
    
    public interface BeaconData{};
    
    public interface MediaData{};
    
    public interface AppUserModificationsData{};
    
    public interface ExhibitionModificationsData{};
    
    public interface ArtworkModificationsData{};

    public interface BeaconModificationsData{};
    
    public interface MediaModificationsData{};
    
    public interface ExhibitionListData{};
    
    public interface ArtworkListData{};
    
    public interface BeaconListData{};
    
    public interface MediaListData{};
    
    public interface AppUsersRequest extends DefaultData, MediaModificationsData, BeaconModificationsData{};
    
    public interface ExhibitionRequest extends DefaultData, ArtworkListData, MediaListData{};
    
    public interface ArtworkRequest extends DefaultData, BeaconListData, MediaListData{};
    
    public interface BeaconsRequest extends DefaultData, BeaconModificationsData, AppUserData, ArtworkData{};
    
    public interface MediaRequest extends DefaultData, MediaModificationsData, AppUserData, ExhibitionListData, ArtworkListData{};
    
    public interface ModificationsRequest extends DefaultData, MediaData, BeaconData, AppUserData, ExhibitionData, ArtworkData{};
}
