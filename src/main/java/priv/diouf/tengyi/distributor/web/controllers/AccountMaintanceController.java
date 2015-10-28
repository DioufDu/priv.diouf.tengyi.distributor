package priv.diouf.tengyi.distributor.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import priv.diouf.tengyi.distributor.services.AccountMaintanceService;
import priv.diouf.tengyi.distributor.web.annontations.AuthenticatedRole;
import priv.diouf.tengyi.distributor.web.models.requests.account.AccountCreationRequest;
import priv.diouf.tengyi.distributor.web.models.requests.account.AccountUpdateRequest;
import priv.diouf.tengyi.distributor.web.models.responses.account.AccountDetail;

@RestController
public class AccountMaintanceController extends GeneralController {

	/*
	 * Fields
	 */

	@Autowired
	protected AccountMaintanceService accountMaintanceService;

	/*
	 * Create
	 */

	@AuthenticatedRole("Admin")
	@RequestMapping(value = "account", method = RequestMethod.POST)
	public AccountDetail createAccount(@RequestBody(required = true) AccountCreationRequest request) {
		return new AccountDetail(accountMaintanceService.create(request));
	}

	/*
	 * Update
	 */

	@AuthenticatedRole("Admin")
	@RequestMapping(value = "account/{accountId}", method = RequestMethod.PUT)
	public AccountDetail modifyAccount(@RequestBody(required = true) AccountUpdateRequest request,
			@PathVariable("accountId") long accountId) {
		return new AccountDetail(accountMaintanceService.update(accountId, request));
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
}
