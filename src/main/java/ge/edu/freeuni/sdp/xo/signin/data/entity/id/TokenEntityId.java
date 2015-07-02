package ge.edu.freeuni.sdp.xo.signin.data.entity.id;

public class TokenEntityId {
	private String partitionKey;
	private String rowKey;

	public TokenEntityId(String partitionKey, String rowKey) {
		this.partitionKey = partitionKey;
		this.rowKey = rowKey;
	}

	public TokenEntityId(String id) {
		final int cutIndex = 12;
		this.partitionKey = id.substring(0, cutIndex);
		this.rowKey = id.substring(cutIndex, id.length());
	}

	public String getId() {
		return this.partitionKey.concat(this.rowKey);
	}

	public String getPartitionKey() {
		return partitionKey;
	}

	public String getRowKey() {
		return rowKey;
	}

}
