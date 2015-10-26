package priv.diouf.tengyi.distributor.persistence.models.product;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import priv.diouf.tengyi.distributor.common.models.enums.PriceStrategy;
import priv.diouf.tengyi.distributor.persistence.PersistenceConfig;

@Entity
@Table(name = "T_PRICING_MODEL", schema = PersistenceConfig.PERSISTENCE_SCHEMA_NAME)
public class PricingModel implements Serializable {

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

	@Column(name = "FACTORY_PRICE")
	public BigDecimal factoryPrice;

	@Column(name = "UNIFIED_PRICE")
	public BigDecimal unifiedPrice;

	@Column(name = "STORE_PRICE")
	public BigDecimal storePrice;

	@Column(name = "TERMINAL_PICE")
	public BigDecimal terminalPice;

	@Column(name = "PRICE_STRATEGY")
	public PriceStrategy strategy;

	/*
	 * Embed
	 */

	/*
	 * Navigations
	 */

	@OneToOne(mappedBy = "pricingModel", cascade = CascadeType.REFRESH, optional = false)
	// @JoinColumn(name = "PRODUCT_ID")
	protected Product product;

	/*
	 * Constructors
	 */

	public PricingModel() {

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
