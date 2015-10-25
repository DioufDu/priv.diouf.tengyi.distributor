package priv.diouf.tengyi.distributor.web.controllers;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import priv.diouf.tengyi.distributor.common.auxiliaries.ImageHelper;
import priv.diouf.tengyi.distributor.common.model.PhotoFormat;
import priv.diouf.tengyi.distributor.common.model.PhotoType;
import priv.diouf.tengyi.distributor.persistence.models.Photo;
import priv.diouf.tengyi.distributor.services.DishQueryService;
import priv.diouf.tengyi.distributor.services.PhotoMaintanceService;
import priv.diouf.tengyi.distributor.services.PhotoQueryService;
import priv.diouf.tengyi.distributor.services.exceptions.InvalidPhotoFormatException;
import priv.diouf.tengyi.distributor.web.models.responses.photo.PhotoIdCollection;

@RestController
public class PhotoController {

	/*
	 * Fields
	 */

	@Autowired
	protected HttpServletResponse httpServletResponse;

	@Autowired
	protected DishQueryService dishQueryService;

	@Autowired
	protected PhotoQueryService photoQueryService;

	@Autowired
	protected PhotoMaintanceService photoMaintanceService;

	/*
	 * Photo Actions
	 */

	@RequestMapping(value = "photo/{photoId}", method = RequestMethod.GET)
	public void getPhoto(@PathVariable("photoId") long photoId) {
		this.getPhoto(photoId, 0);
	}

	@RequestMapping(value = "photo/{photoId}/rotate/{angle}", method = RequestMethod.GET)
	public void getPhoto(@PathVariable("photoId") long photoId, @PathVariable("angle") int angle) {
		if (photoId < 1) {
			return;
		}
		Photo photo = photoQueryService.findPhoto(photoId);
		try {
			ImageHelper.transferImage(new BufferedInputStream(new ByteArrayInputStream(photo.getContent())), photo.getPhotoType(),
					photo.getPhotoFormat(), angle).toOutputStream(this.httpServletResponse.getOutputStream());
		} catch (IOException ex) {
			throw new InvalidPhotoFormatException();
		}
	}

	@RequestMapping(value = "photo/{photoId}/{photoType}/rotate/{angle}", method = RequestMethod.GET)
	public void getPhoto(@PathVariable("photoId") long photoId, @PathVariable("photoType") PhotoType photoType,
			@PathVariable("angle") int angle) {
		if (photoId < 1) {
			return;
		}
		Photo photo = photoQueryService.findPhoto(photoId);
		try {
			ImageHelper.transferImage(new BufferedInputStream(new ByteArrayInputStream(photo.getContent())), photoType,
					photo.getPhotoFormat(), angle).toOutputStream(this.httpServletResponse.getOutputStream());
		} catch (IOException ex) {
			throw new InvalidPhotoFormatException();
		}
	}

	@RequestMapping(value = "dish/{dishId}/photo/rotate/{angle}", method = RequestMethod.GET)
	public void getDishPhoto(@PathVariable("dishId") long dishId, @PathVariable("angle") int angle) {
		if (dishId < 1) {
			return;
		}
		Photo photo = dishQueryService.findOneWithDetails(dishId).getOriginalPhoto();
		try {
			ImageHelper.transferImage(new BufferedInputStream(new ByteArrayInputStream(photo.getContent())), photo.getPhotoType(),
					photo.getPhotoFormat(), angle).toOutputStream(this.httpServletResponse.getOutputStream());
		} catch (IOException ex) {
			throw new InvalidPhotoFormatException();
		}
	}

	@RequestMapping(value = "dish/{dishId}/photo/{photoType}/rotate/{angle}", method = RequestMethod.GET)
	public void getDishPhoto(@PathVariable("dishId") long dishId, @PathVariable("photoType") PhotoType photoType,
			@PathVariable("angle") int angle) {
		if (dishId < 1) {
			return;
		}
		Photo photo = dishQueryService.findOneWithDetails(dishId).getOriginalPhoto();
		try {
			ImageHelper.transferImage(new BufferedInputStream(new ByteArrayInputStream(photo.getContent())), photoType,
					photo.getPhotoFormat(), angle).toOutputStream(this.httpServletResponse.getOutputStream());
		} catch (IOException ex) {
			throw new InvalidPhotoFormatException();
		}
	}

	@RequestMapping(value = "photo", method = RequestMethod.POST)
	public PhotoIdCollection upload(@RequestParam(value = "photo", required = true) MultipartFile file) throws IOException {
		PhotoFormat photoFormat = ImageHelper.identify(file);
		if (photoFormat != null) {
			return photoMaintanceService.generatePhotos(ImageHelper.encodeImageAsByteArray(ImageHelper.transferImage(file, photoFormat,
					PhotoType.ORIGINAL, 0), photoFormat), photoFormat);
		}
		throw new InvalidPhotoFormatException();
	}

	@RequestMapping(value = "photo/encode", method = RequestMethod.POST)
	public String encode(@RequestParam(value = "photo", required = true) MultipartFile file,
			@RequestParam(value = "type", defaultValue = "STANDARD", required = false) PhotoType photoType,
			@RequestParam(value = "angle", required = false) int angle) throws IOException {
		PhotoFormat photoFormat = ImageHelper.identify(file);
		return ImageHelper.encodeImageAsString(ImageHelper.transferImage(file, photoFormat, photoType, angle), photoFormat);
	}

	/*
	 * Private & Protected Methods
	 */

}