package priv.diouf.tengyi.distributor.web.models.responses.rate;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

import com.fasterxml.jackson.annotation.JsonProperty;

import priv.diouf.tengyi.distributor.persistence.models.RateGroup;

public class RateGroupInfo implements Serializable {

	/**
	 * Generated Serial Version UID
	 */

	private static final long serialVersionUID = -2308624020688160663L;

	private static final int RATE_DIVIDER = 3;

	/*
	 * Fields
	 */

	@JsonProperty("count")
	protected long count;

	@JsonProperty("overall")
	protected BigDecimal overall;

	@JsonProperty("taste")
	protected BigDecimal taste;

	@JsonProperty("style")
	protected BigDecimal style;

	@JsonProperty("service")
	protected BigDecimal service;

	/*
	 * Constructors
	 */

	public RateGroupInfo(RateGroup rateGroup) {
		if (rateGroup == null) {
			return;
		}
		this.setCount(rateGroup.getCount());
		this.setTaste(rateGroup.getTaste());
		this.setStyle(rateGroup.getStyle());
		this.setService(rateGroup.getService());
		this.setCount(rateGroup.getCount());
		this.setOverall(rateGroup.getService().add(rateGroup.getStyle().add(rateGroup.getTaste()))
				.divide(new BigDecimal(RATE_DIVIDER), 2, RoundingMode.HALF_EVEN));
	}

	/*
	 * Properties
	 */

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public BigDecimal getOverall() {
		return overall;
	}

	public void setOverall(BigDecimal overall) {
		this.overall = overall;
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
}
