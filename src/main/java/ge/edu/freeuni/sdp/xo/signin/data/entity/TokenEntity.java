package ge.edu.freeuni.sdp.xo.signin.data.entity;

import com.microsoft.azure.storage.table.TableServiceEntity;

import ge.edu.freeuni.sdp.xo.signin.data.entity.id.SignInInfoEntityId;
import ge.edu.freeuni.sdp.xo.signin.data.json.Token;
import ge.edu.freeuni.sdp.xo.signin.data.json.UsernameInfo;

public class TokenEntity extends TableServiceEntity {
	public TokenEntity() {
	}

	private TokenEntity(Token token, UsernameInfo uInfo) {
		SignInInfoEntityId id = new SignInInfoEntityId(token.getToken());
		this.partitionKey = id.getPartitionKey();
		this.rowKey = id.getRowKey();
		this.Token = token.getToken();
		this.Username = uInfo.getUsername();
	}

	public static TokenEntity fromToken(Token token, UsernameInfo uInfo) {
		return new TokenEntity(token, uInfo);
	}

	public Token token() {
		Token tok = new Token();
		tok.setToken(getToken());
		return tok;
	}

	private String Token;
	private String Username;

	public String getToken() {
		return Token;
	}

	public void setToken(String token) {
		this.Token = token;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		this.Username = username;
	}

}
