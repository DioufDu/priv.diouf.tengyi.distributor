package priv.diouf.tengyi.distributor.persistence.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import priv.diouf.tengyi.distributor.persistence.PersistenceConfig;

@Entity
@Table(name = "T_RATE_GROUP", schema = PersistenceConfig.PERSISTENCE_SCHEMA_NAME)
public class RateGroup implements Serializable {

	private static final int ZERO_VALUE = 0;

	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = -1148437223788456689L;

	/**
	 * ID
	 */

	@Id
	@GeneratedValue
	@Column(name = "ID")
	protected Long id;

	/*
	 * Scalar Fields
	 */

	@Column(name = "COUNT")
	protected long count;

	@Column(name = "TASTE", scale = 20, precision = 21)
	protected BigDecimal taste;

	@Column(name = "STYLE", scale = 20, precision = 21)
	protected BigDecimal style;

	@Column(name = "SERVICE", scale = 20, precision = 21)
	protected BigDecimal service;

	/*
	 * Navigations
	 */

	@OneToOne(mappedBy = "rateGroup", cascade = CascadeType.REFRESH, optional = false)
	protected Dish dish;

	@OneToMany(mappedBy = "rateGroup", cascade = CascadeType.ALL, orphanRemoval = true)
	protected Set<Rate> rates;

	/*
	 * Constructors
	 */

	public RateGroup() {

	}

	public RateGroup(Dish dish) {
		this.setDish(dish);
		this.setCount(0);
		this.setTaste(new BigDecimal(ZERO_VALUE));
		this.setService(new BigDecimal(ZERO_VALUE));
		this.setStyle(new BigDecimal(ZERO_VALUE));
	}

	/*
	 * Properties
	 */

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public BigDecimal getTaste() {
		return taste;
	}

	public void setTaste(BigDecimal taste) {
		this.taste = taste;
	}

	public BigDecimal getStyle() {
		return style;
	}

	public void setStyle(BigDecimal style) {
		this.style = style;
	}

	public BigDecimal getService() {
		return service;
	}

	public void setService(BigDecimal service) {
		this.service = service;
	}

	public Dish getDish() {
		return dish;
	}

	public void setDish(Dish dish) {
		this.dish = dish;
	}

	public Set<Rate> getRates() {
		return rates;
	}

	public void setRates(Set<Rate> rates) {
		this.rates = rates;
	}

}
