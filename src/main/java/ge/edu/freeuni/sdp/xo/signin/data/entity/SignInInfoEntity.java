package ge.edu.freeuni.sdp.xo.signin.data.entity;

import ge.edu.freeuni.sdp.xo.signin.data.entity.id.UserInfoEntityId;
import ge.edu.freeuni.sdp.xo.signin.data.json.SigninInfo;

import com.microsoft.azure.storage.table.TableServiceEntity;

public class SignInInfoEntity extends TableServiceEntity {
	public SignInInfoEntity() {
	}

	private SignInInfoEntity(SigninInfo userinfo) {
		UserInfoEntityId id = new UserInfoEntityId(userinfo.getUsername());
		this.partitionKey = id.getPartitionKey();
		this.rowKey = id.getRowKey();
		this.email = userinfo.getEmail();
		this.username = userinfo.getUsername();
		this.password = userinfo.getPassword();
	}

	public static SignInInfoEntity fromSignInfo(SigninInfo userinfo) {
		return new SignInInfoEntity(userinfo);
	}

	public SigninInfo signInfo() {
		SigninInfo signInfo = new SigninInfo();
		signInfo.setEmail(getEmail());
		signInfo.setUsername(getUsername());
		signInfo.setPassword(getPassword());
		return signInfo;
	}

	private String email;
	private String username;
	private String password;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
