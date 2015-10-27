package priv.diouf.tengyi.distributor.web.controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import priv.diouf.tengyi.distributor.persistence.initializer.DataInitializerHolder;
import priv.diouf.tengyi.distributor.web.annontations.AuthenticatedRole;

@RestController
public class AdminController {

	/*
	 * Fields
	 */

	@Autowired
	protected HttpServletResponse httpServletResponse;

	@Autowired
	protected DataInitializerHolder dataInitializerHolder;

	/*
	 * Photo Actions
	 */

	@AuthenticatedRole("Admin")
	@RequestMapping(value = "admin/initial", method = RequestMethod.GET)
	public void initialTestData() {
		dataInitializerHolder.execute();
	}

	/*
	 * Private & Protected Methods
	 */

}
