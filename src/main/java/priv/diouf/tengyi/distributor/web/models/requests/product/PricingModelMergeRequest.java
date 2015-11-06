package priv.diouf.tengyi.distributor.web.models.requests.product;

import java.io.Serializable;
import java.math.BigDecimal;

import priv.diouf.tengyi.distributor.common.models.enums.PriceStrategy;

public class PricingModelMergeRequest implements Serializable {

	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * Fields
	 */

	/*
	 * Scalar Fields
	 */

	public BigDecimal factoryPrice;

	public BigDecimal unifiedPrice;

	public BigDecimal storePrice;

	public BigDecimal terminalPrice;

	public PriceStrategy strategy;

	/*
	 * Navigations
	 */

	/*
	 * Constructors
	 */

	public PricingModelMergeRequest() {
	}

	/*
	 * Properties
	 */

	public BigDecimal getFactoryPrice() {
		return factoryPrice;
	}

	public void setFactoryPrice(BigDecimal factoryPrice) {
		this.factoryPrice = factoryPrice;
	}

	public BigDecimal getUnifiedPrice() {
		return unifiedPrice;
	}

	public void setUnifiedPrice(BigDecimal unifiedPrice) {
		this.unifiedPrice = unifiedPrice;
	}

	public BigDecimal getStorePrice() {
		return storePrice;
	}

	public void setStorePrice(BigDecimal storePrice) {
		this.storePrice = storePrice;
	}

	public BigDecimal getTerminalPrice() {
		return terminalPrice;
	}

	public void setTerminalPrice(BigDecimal terminalPice) {
		this.terminalPrice = terminalPice;
	}

	public PriceStrategy getStrategy() {
		return strategy;
	}

	public void setStrategy(PriceStrategy strategy) {
		this.strategy = strategy;
	}

}
