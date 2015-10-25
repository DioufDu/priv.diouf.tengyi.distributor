package priv.diouf.tengyi.distributor.persistence.models;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import priv.diouf.tengyi.distributor.common.model.PhotoFormat;
import priv.diouf.tengyi.distributor.common.model.PhotoType;
import priv.diouf.tengyi.distributor.persistence.PersistenceConfig;

@Entity
@Table(name = "T_PHOTO", schema = PersistenceConfig.PERSISTENCE_SCHEMA_NAME)
@DiscriminatorColumn(name = "PHOTO_TYPE")
public abstract class Photo implements Serializable {

	/**
	 * Generated Version UID
	 */
	private static final long serialVersionUID = 1743378879605244580L;

	/**
	 * ID
	 */

	@Id
	@GeneratedValue
	@Column(name = "ID")
	protected Long id;

	/*
	 * Scalar Fields
	 */

	@Column(name = "VERSION", nullable = false)
	protected String version;

	@Column(name = "MIME", nullable = false)
	@Enumerated(EnumType.STRING)
	protected PhotoFormat photoFormat;

	@Column(name = "PHOTO_TYPE", nullable = false, insertable = false, updatable = false)
	@Enumerated(EnumType.STRING)
	protected PhotoType photoType;

	@Column(name = "CONTENT", nullable = false)
	@Basic(fetch = FetchType.LAZY)
	protected byte[] content;

	/*
	 * Navigations
	 */

	/*
	 * Constructors
	 */

	public Photo() {

	}

	/*
	 * Properties
	 */

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public PhotoFormat getPhotoFormat() {
		return photoFormat;
	}

	public void setPhotoFormat(PhotoFormat photoFormat) {
		this.photoFormat = photoFormat;
	}

	public PhotoType getPhotoType() {
		return photoType;
	}

	public void setPhotoType(PhotoType photoType) {
		this.photoType = photoType;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}
}
