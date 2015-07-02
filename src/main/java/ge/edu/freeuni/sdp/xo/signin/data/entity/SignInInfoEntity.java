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
		this.Email = userinfo.getEmail();
		this.Username = userinfo.getUsername();
		this.Password = userinfo.getPassword();
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

	private String Email;
	private String Username;
	private String Password;

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		this.Email = email;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		this.Username = username;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		this.Password = password;
	}

}
