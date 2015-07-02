package ge.edu.freeuni.sdp.xo.signin.data.entity;

import com.microsoft.azure.storage.table.TableServiceEntity;

import ge.edu.freeuni.sdp.xo.signin.data.entity.id.TokenEntityId;
import ge.edu.freeuni.sdp.xo.signin.data.json.Token;
import ge.edu.freeuni.sdp.xo.signin.data.json.UsernameInfo;

public class TokenEntity extends TableServiceEntity {
	public TokenEntity() {
	}

	private TokenEntity(Token token, UsernameInfo uInfo) {
		TokenEntityId id = new TokenEntityId(token.getToken());
		this.partitionKey = id.getPartitionKey();
		this.rowKey = id.getRowKey();
		this.token = token.getToken();
		this.username = uInfo.getUsername();
	}

	public static TokenEntity fromToken(Token token, UsernameInfo uInfo) {
		return new TokenEntity(token, uInfo);
	}

	public Token token() {
		Token tok = new Token();
		tok.setToken(getToken());
		return tok;
	}

	private String token;
	private String username;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
