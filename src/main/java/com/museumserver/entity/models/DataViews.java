package com.museumserver.entity.models;

public class DataViews {
	
    public interface DefaultData{};
    
    public interface UserData{};
    
    public interface ExhibitionData{};
    
    public interface ArtworkData{};
    
    public interface BeaconData{};
    
    public interface MediaData{};
    
    public interface UserModificationsData{};
    
    public interface ExhibitionModificationsData{};
    
    public interface ArtworkModificationsData{};

    public interface BeaconModificationsData{};
    
    public interface MediaModificationsData{};
    
    public interface ExhibitionListData{};
    
    public interface ArtworkListData{};
    
    public interface BeaconListData{};
    
    public interface MediaListData{};
    
    public interface UsersRequest extends DefaultData, MediaModificationsData, BeaconModificationsData, ArtworkModificationsData, ExhibitionModificationsData, UserModificationsData{};
    
    public interface ExhibitionRequest extends DefaultData, ArtworkListData, MediaListData{};
    
    public interface ArtworkRequest extends DefaultData, BeaconListData, MediaListData, ExhibitionData{};
    
    public interface BeaconsRequest extends DefaultData, UserData, ArtworkData{};
    
    public interface MediaRequest extends DefaultData, UserData, ExhibitionListData, ArtworkListData{};
    
    public interface ModificationsRequest extends DefaultData, MediaData, BeaconData, UserData, ExhibitionData, ArtworkData{};
}
