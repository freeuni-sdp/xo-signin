package ge.edu.freeuni.sdp.xo.signin.data.entity;

import ge.edu.freeuni.sdp.xo.signin.data.entity.id.UserInfoEntityId;
import ge.edu.freeuni.sdp.xo.signin.data.json.UserInfo;

import com.microsoft.azure.storage.table.TableServiceEntity;

public class UserInfoEntity extends TableServiceEntity {
	public UserInfoEntity() {
	}

	private UserInfoEntity(UserInfo userinfo) {
		UserInfoEntityId id = new UserInfoEntityId(userinfo.getEmail(),
				userinfo.getUsername());
		this.partitionKey = id.getPartitionKey();
		this.rowKey = id.getRowKey();
		this.email = this.partitionKey;
		this.username = this.rowKey;
	}

	public static UserInfoEntity fromUserInfo(UserInfo userinfo) {
		return new UserInfoEntity(userinfo);
	}

	public UserInfo userInfo() {
		UserInfo userInfo = new UserInfo();
		userInfo.setEmail(getEmail());
		userInfo.setUsername(getUsername());
		return userInfo;
	}

	private String email;
	private String username;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
