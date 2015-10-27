package priv.diouf.tengyi.distributor.persistence.models.photo;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import priv.diouf.tengyi.distributor.common.models.enums.PhotoGroupType;
import priv.diouf.tengyi.distributor.persistence.PersistenceConfig;
import priv.diouf.tengyi.distributor.persistence.models.Modification;

@Entity
@Table(name = "T_PHOTO_GROUP", schema = PersistenceConfig.PERSISTENCE_SCHEMA_NAME)
@DiscriminatorColumn(name = "TYPE")
public abstract class PhotoGroup implements Serializable {

	/**
	 * Generated Serial Version UID
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
	 * Fields
	 */

	@Column(name = "TYPE", length = 16, nullable = false, insertable = false, updatable = false)
	@Enumerated(EnumType.STRING)
	protected PhotoGroupType type;

	/*
	 * Embed
	 */

	/*
	 * Navigations
	 */

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "ORIGINAL_PHOTO_ID")
	protected OriginalPhoto originalPhoto;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@JoinColumn(name = "THUMBNAIL_PHOTO_ID")
	protected ThumbnailPhoto thumbnailPhoto;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "STANDARD_PHOTO_ID")
	protected StandardPhoto standardPhoto;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@JoinColumn(name = "FULL_SCREEN_PHOTO_ID")
	protected FullScreenPhoto fullScreenPhoto;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, optional = false)
	@JoinColumn(name = "MODIFICATION_ID")
	protected Modification modification;

	/*
	 * Constructors
	 */

	public PhotoGroup() {

	}

	/*
	 * Properties
	 */

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public PhotoGroupType getType() {
		return type;
	}

	public void setType(PhotoGroupType type) {
		this.type = type;
	}

	public OriginalPhoto getOriginalPhoto() {
		return originalPhoto;
	}

	public void setOriginalPhoto(OriginalPhoto originalPhoto) {
		this.originalPhoto = originalPhoto;
	}

	public ThumbnailPhoto getThumbnailPhoto() {
		return thumbnailPhoto;
	}

	public void setThumbnailPhoto(ThumbnailPhoto thumbnailPhoto) {
		this.thumbnailPhoto = thumbnailPhoto;
	}

	public StandardPhoto getStandardPhoto() {
		return standardPhoto;
	}

	public void setStandardPhoto(StandardPhoto standardPhoto) {
		this.standardPhoto = standardPhoto;
	}

	public FullScreenPhoto getFullScreenPhoto() {
		return fullScreenPhoto;
	}

	public void setFullScreenPhoto(FullScreenPhoto fullScreenPhoto) {
		this.fullScreenPhoto = fullScreenPhoto;
	}

	public Modification getModification() {
		return modification;
	}

	public void setModification(Modification modification) {
		this.modification = modification;
	}

}
