package ge.edu.freeuni.sdp.xo.signin.data.entity;

import com.microsoft.azure.storage.table.TableServiceEntity;

import ge.edu.freeuni.sdp.xo.signin.data.entity.id.TokenEntityId;
import ge.edu.freeuni.sdp.xo.signin.data.json.Token;

public class TokenEntity extends TableServiceEntity {
	public TokenEntity() {
	}

	private TokenEntity(Token token) {
		TokenEntityId id = new TokenEntityId(token.getToken());
		this.partitionKey = id.getPartitionKey();
		this.rowKey = id.getRowKey();
		this.token = token.getToken();
	}

	public static TokenEntity fromToken(Token token) {
		return new TokenEntity(token);
	}

	public Token token() {
		Token tok = new Token();
		tok.setToken(getToken());
		return tok;
	}

	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
