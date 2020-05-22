package com.museumserver.services;

import java.util.List;

import com.museumserver.entity.models.UserModification;

public interface UserModificationService {

	public List<UserModification> getModificationsByUser(Long userId);

	public List<UserModification> getUserModifications(Long modifiedUserId);

	public void addUserModification(Long userId, Long modifiedUserId, UserModification modification);

}
