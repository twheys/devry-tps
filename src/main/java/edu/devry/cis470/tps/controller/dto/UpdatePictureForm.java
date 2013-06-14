package edu.devry.cis470.tps.controller.dto;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class UpdatePictureForm {
	private CommonsMultipartFile fileData;

	public CommonsMultipartFile getFileData() {
		return fileData;
	}

	public void setFileData(final CommonsMultipartFile fileData) {
		this.fileData = fileData;
	}

}
