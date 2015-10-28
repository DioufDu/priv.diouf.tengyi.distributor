package priv.diouf.tengyi.distributor.web.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class GeneralController {

	/*
	 * Fields
	 */

	@Autowired
	private HttpServletRequest httpServletRequest;

	@Autowired
	private HttpServletResponse httpServletResponse;

	/*
	 * Properties
	 */

	protected HttpServletRequest getHttpServletRequest() {
		return httpServletRequest;
	}

	protected HttpServletResponse getHttpServletResponse() {
		return httpServletResponse;
	}

	/*
	 * Protected & Private Methods
	 */
}
