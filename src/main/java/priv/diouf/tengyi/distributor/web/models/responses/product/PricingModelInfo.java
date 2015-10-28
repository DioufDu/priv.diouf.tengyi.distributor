package priv.diouf.tengyi.distributor.web.models.responses.product;

import java.io.Serializable;
import java.math.BigDecimal;

import priv.diouf.tengyi.distributor.common.models.enums.PriceStrategy;
import priv.diouf.tengyi.distributor.persistence.models.product.PricingModel;

public class PricingModelInfo implements Serializable {

	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * Fields
	 */

	public BigDecimal factoryPrice;

	public BigDecimal unifiedPrice;

	public BigDecimal storePrice;

	public BigDecimal terminalPice;

	public PriceStrategy strategy;

	/*
	 * Navigations
	 */

	/*
	 * Constructors
	 */

	public PricingModelInfo(PricingModel pricingModel) {
		if (pricingModel == null) {
			return;
		}
		// Scalar Fields
		this.setFactoryPrice(pricingModel.getFactoryPrice());
		this.setUnifiedPrice(pricingModel.getUnifiedPrice());
		this.setStorePrice(pricingModel.getStorePrice());
		this.setTerminalPice(pricingModel.getTerminalPice());
		this.setStrategy(pricingModel.getStrategy());
		// Navigations
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

	public BigDecimal getTerminalPice() {
		return terminalPice;
	}

	public void setTerminalPice(BigDecimal terminalPice) {
		this.terminalPice = terminalPice;
	}

	public PriceStrategy getStrategy() {
		return strategy;
	}

	public void setStrategy(PriceStrategy strategy) {
		this.strategy = strategy;
	}

}
