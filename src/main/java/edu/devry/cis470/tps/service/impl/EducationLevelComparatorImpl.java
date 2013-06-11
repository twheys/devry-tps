package edu.devry.cis470.tps.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import edu.devry.cis470.tps.domain.EducationLevel;
import edu.devry.cis470.tps.service.EducationLevelService;

@Service
public class EducationLevelComparatorImpl implements EducationLevelService {

	@Override
	public List<EducationLevel> getEducationLevelsForMinimum(final String level) {
		final EducationLevel minimumLevel = EducationLevel.valueOf(level);
		final List<EducationLevel> levels = Lists.newArrayList();
		for (final EducationLevel candidate : EducationLevel.values()) {
			if (minimumLevel.getOrder() <= candidate.getOrder()) {
				levels.add(candidate);
			}
		}
		return levels;
	}

}
