package priv.diouf.tengyi.distributor.persistence.models.photo;

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

import priv.diouf.tengyi.distributor.common.models.enums.PhotoFormat;
import priv.diouf.tengyi.distributor.common.models.enums.PhotoType;
import priv.diouf.tengyi.distributor.persistence.PersistenceConfig;

@Entity
@Table(name = "T_PHOTO", schema = PersistenceConfig.PERSISTENCE_SCHEMA_NAME)
@DiscriminatorColumn(name = "TYPE")
public abstract class Photo implements Serializable {

	/**
	 * Generated Version UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */

	@Id
	@GeneratedValue
	@Column(name = "ID")
	protected long id;

	/*
	 * Scalar Fields
	 */

	@Column(name = "VERSION", nullable = false)
	protected String version;

	@Column(name = "FORMAT", nullable = false)
	@Enumerated(EnumType.STRING)
	protected PhotoFormat format;

	@Column(name = "TYPE", nullable = false, insertable = false, updatable = false)
	@Enumerated(EnumType.STRING)
	protected PhotoType type;

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
		return format;
	}

	public void setPhotoFormat(PhotoFormat photoFormat) {
		this.format = photoFormat;
	}

	public PhotoType getType() {
		return type;
	}

	public void setType(PhotoType type) {
		this.type = type;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}
}
