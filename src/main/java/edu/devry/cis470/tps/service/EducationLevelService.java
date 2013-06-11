package edu.devry.cis470.tps.service;

import java.util.List;

import edu.devry.cis470.tps.domain.EducationLevel;

public interface EducationLevelService {

	public List<EducationLevel> getEducationLevelsForMinimum(String level);
}
