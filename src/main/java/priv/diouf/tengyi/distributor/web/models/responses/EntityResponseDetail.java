package priv.diouf.tengyi.distributor.web.models.responses;

import java.io.Serializable;

public interface EntityResponseDetail<TNodeAggregation extends NodeAggregation> extends Serializable {

	TNodeAggregation getNodeAggregation();
}
