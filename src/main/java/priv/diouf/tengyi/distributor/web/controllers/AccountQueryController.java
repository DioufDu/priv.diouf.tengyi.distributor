package priv.diouf.tengyi.distributor.web.controllers;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import priv.diouf.tengyi.distributor.common.auxiliaries.ImageHelper;
import priv.diouf.tengyi.distributor.common.models.enums.PhotoType;
import priv.diouf.tengyi.distributor.persistence.models.account.Account;
import priv.diouf.tengyi.distributor.persistence.models.photo.Photo;
import priv.diouf.tengyi.distributor.services.AccountQueryService;
import priv.diouf.tengyi.distributor.services.exceptions.InvalidPhotoFormatException;
import priv.diouf.tengyi.distributor.web.models.requests.account.AccountAdvancedSearchRequest;
import priv.diouf.tengyi.distributor.web.models.requests.account.AccountBasicSearchRequest;
import priv.diouf.tengyi.distributor.web.models.responses.account.AccountDetail;
import priv.diouf.tengyi.distributor.web.models.responses.account.AccountInfo;
import priv.diouf.tengyi.distributor.web.models.responses.account.AccountPage;
import priv.diouf.tengyi.distributor.web.models.responses.account.AccountStatistics;

@RestController
public class AccountQueryController extends GeneralController {

	/*
	 * Fields
	 */

	@Autowired
	protected AccountQueryService accountQueryService;

	/*
	 * Retrieve Actions
	 */

	/*
	 * Retrieve Actions - Statistics
	 */

	@RequestMapping(value = "account", method = RequestMethod.GET)
	public AccountStatistics generateStatistics() {
		return new AccountStatistics(accountQueryService.generateStatistics());
	}

	/*
	 * Retrieve Actions - Single
	 */

	@RequestMapping(value = "account/{accountId}", method = RequestMethod.GET)
	public AccountDetail findOneWithAllDetails(@PathVariable("accountId") long accountId) {
		return new AccountDetail(accountQueryService.findOneWithAllDetails(accountId));
	}

	/*
	 * Retrieve Actions - Search & Page
	 */

	@RequestMapping(value = "account/search/basic", method = RequestMethod.POST)
	public AccountPage<AccountInfo> basicSearch(@RequestBody AccountBasicSearchRequest queryRequest) {
		return this.generateAccountPage(accountQueryService.findAll(queryRequest.getCriteria(), queryRequest.getPageRequest()));
	}

	@RequestMapping(value = "account/search/advanced", method = RequestMethod.POST)
	public AccountPage<AccountInfo> advancedSearch(@RequestBody AccountAdvancedSearchRequest queryRequest) {
		return this.generateAccountPage(accountQueryService.findAll(queryRequest.getCriteria(), queryRequest.getPageRequest()));
	}

	/*
	 * Retrieve Actions - Account Avatar
	 */

	@RequestMapping(value = "account/{accountId}/avatar/rotate/{angle}", method = RequestMethod.GET)
	public void getDishPhoto(@PathVariable("accountId") long accountId, @PathVariable("angle") int angle) {
		if (accountId < 1) {
			return;
		}
		Photo photo = accountQueryService.findOneWithAllDetails(accountId).getAvatarPhotoGroup().getOriginalPhoto();
		try {
			ImageHelper.transferImage(
					// Image Stream
					new BufferedInputStream(new ByteArrayInputStream(photo.getContent())), photo.getType(), photo.getPhotoFormat(), angle)
					// Response
					.toOutputStream(this.getHttpServletResponse().getOutputStream());
		} catch (IOException ex) {
			throw new InvalidPhotoFormatException();
		}
	}

	@RequestMapping(value = "account/{accountId}/avatar/{photoType}/rotate/{angle}", method = RequestMethod.GET)
	public void getDishPhoto(@PathVariable("accountId") long accountId, @PathVariable("photoType") PhotoType photoType,
			@PathVariable("angle") int angle) {
		if (accountId < 1) {
			return;
		}
		Photo photo = accountQueryService.findOneWithAllDetails(accountId).getAvatarPhotoGroup().getOriginalPhoto();
		try {
			ImageHelper.transferImage(
					// Image Stream
					new BufferedInputStream(new ByteArrayInputStream(photo.getContent())), photoType, photo.getPhotoFormat(), angle)
					// Response
					.toOutputStream(this.getHttpServletResponse().getOutputStream());
		} catch (IOException ex) {
			throw new InvalidPhotoFormatException();
		}
	}

	/*
	 * Others
	 */

	/*
	 * Protected & Private Methods
	 */

	private AccountPage<AccountInfo> generateAccountPage(Page<Account> accounts) {
		return new AccountPage<AccountInfo>(accounts.getContent(), new AccountPage.Helper<AccountInfo>() {
			@Override
			protected AccountInfo createModel(Account account) {
				return new AccountInfo(account);
			}
		}, new PageRequest(accounts.getNumber(), accounts.getSize()), accounts.getTotalElements());
	}

}
