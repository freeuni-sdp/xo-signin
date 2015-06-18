package ge.edu.freeuni.sdp.xo.signin.data.entity.id;

public class UserInfoEntityId {
	private String partitionKey;
	private String rowKey;

	public UserInfoEntityId(String email, String username) {
		this.partitionKey = email;
		this.rowKey = username;
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
