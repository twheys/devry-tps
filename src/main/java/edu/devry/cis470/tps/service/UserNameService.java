package edu.devry.cis470.tps.service;

import edu.devry.cis470.tps.service.impl.NonUniqueUsernameException;

public interface UserNameService {

	boolean verifyUnique(String userName) throws NonUniqueUsernameException;

}
