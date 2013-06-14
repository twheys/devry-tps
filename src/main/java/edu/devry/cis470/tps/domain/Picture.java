package edu.devry.cis470.tps.domain;

import javax.persistence.Entity;
import javax.persistence.Lob;

@Entity
public class Picture extends IdEntity {

	@Lob
	private byte[] bytes;
	private String imageType;

	public byte[] getBytes() {
		return bytes;
	}

	public String getImageType() {
		return imageType;
	}

	public void setBytes(final byte[] bytes) {
		this.bytes = bytes;
	}

	public void setImageType(final String imageType) {
		this.imageType = imageType;
	}
}
