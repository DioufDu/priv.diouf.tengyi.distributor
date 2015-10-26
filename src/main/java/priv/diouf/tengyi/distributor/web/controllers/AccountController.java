package priv.diouf.tengyi.distributor.web.controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import priv.diouf.tengyi.distributor.persistence.models.account.Account;
import priv.diouf.tengyi.distributor.services.AccountMaintanceService;
import priv.diouf.tengyi.distributor.services.AccountQueryService;
import priv.diouf.tengyi.distributor.services.PhotoQueryService;
import priv.diouf.tengyi.distributor.web.annontations.AuthenticatedRole;
import priv.diouf.tengyi.distributor.web.models.requests.account.AdvancedSearchRequest;
import priv.diouf.tengyi.distributor.web.models.requests.account.BasicSearchRequest;
import priv.diouf.tengyi.distributor.web.models.requests.account.CreationRequest;
import priv.diouf.tengyi.distributor.web.models.requests.account.UpdateRequest;
import priv.diouf.tengyi.distributor.web.models.responses.account.AccountDetail;
import priv.diouf.tengyi.distributor.web.models.responses.account.AccountPage;
import priv.diouf.tengyi.distributor.web.models.responses.account.AccountStatistics;

@RestController
public class AccountController {

	/*
	 * Fields
	 */

	protected static final int DEFAULT_LATEST_ACCOUNT_COUNT = 30;

	protected static final int DEFAULT_POPULAR_ACCOUNT_COUNT = 30;

	protected static final int DEFAULT_ACCOUNT_COUNT = 4;

	protected static final int DEFAULT_RECENT_ACCOUNT_COUNT = 5;

	@Autowired
	protected AccountQueryService accountQueryService;

	@Autowired
	protected AccountMaintanceService accountMaintanceService;

	@Autowired
	protected PhotoQueryService photoQueryService;

	@Autowired
	protected HttpServletResponse response;

	/*
	 * Retrieve Actions
	 */

	@RequestMapping(value = "account", method = RequestMethod.GET)
	public AccountStatistics generateStatistics() {
		return new AccountStatistics(accountQueryService.generateStatistics());
	}

	@RequestMapping(value = "account/{accountId}", method = RequestMethod.GET)
	public AccountDetail findOneWithDetails(@PathVariable("accountId") long accountId) {
		return new AccountDetail(accountQueryService.findOneWithDetails(accountId));
	}

	/*
	 * Retrieve Actions - Group
	 */

	/*
	 * Retrieve Actions - Search
	 */

	@AuthenticatedRole("Admin")
	@RequestMapping(value = "account/search/basic", method = RequestMethod.POST)
	public AccountPage basicSearch(@RequestBody BasicSearchRequest queryRequest) {
		return generateAccountPageResponse(accountQueryService.findAll(queryRequest.getCriteria(), queryRequest.getPageRequest()));
	}

	@AuthenticatedRole("Admin")
	@RequestMapping(value = "account/search/advanced", method = RequestMethod.POST)
	public AccountPage advancedSearch(@RequestBody AdvancedSearchRequest queryRequest) {
		return generateAccountPageResponse(accountQueryService.findAll(queryRequest.getCriteria(), queryRequest.getPageRequest()));
	}

	/*
	 * Retrieve Actions - Page by scenario
	 */

	/*
	 * Retrieve Photo
	 */

	/*
	 * Create
	 */

	@AuthenticatedRole("Admin")
	@RequestMapping(value = "account", method = RequestMethod.POST)
	public AccountDetail createAccount(@RequestBody(required = true) CreationRequest request) {
		Account account = accountMaintanceService.create(request);
		return new AccountDetail(account);
	}

	/*
	 * Update
	 */

	@AuthenticatedRole("Admin")
	@RequestMapping(value = "account/{accountId}", method = RequestMethod.PUT)
	public AccountDetail modifyAccount(@RequestBody(required = true) UpdateRequest request, @PathVariable("accountId") long accountId) {
		Account account = accountMaintanceService.update(accountId, request);
		return new AccountDetail(account);
	}

	/*
	 * Delete
	 */

	@AuthenticatedRole("Admin")
	@RequestMapping(value = "account/{accountId}", method = RequestMethod.DELETE)
	public void deleteAccount(@PathVariable("accountId") long accountId) {
		accountMaintanceService.delete(accountId);
	}

	/*
	 * Others
	 */

	/*
	 * Protected & Private Methods
	 */

	protected AccountPage generateAccountPageResponse(Page<Account> accounts) {
		return new AccountPage(AccountPage.createDetails(accounts.getContent()), new PageRequest(accounts.getNumber(), accounts.getSize()),
				accounts.getTotalElements());
	}
}
