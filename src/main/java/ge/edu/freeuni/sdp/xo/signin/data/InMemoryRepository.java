package ge.edu.freeuni.sdp.xo.signin.data;

import java.util.HashMap;
import java.util.Map;

import com.microsoft.azure.storage.StorageException;

import ge.edu.freeuni.sdp.xo.signin.data.entity.SignInInfoEntity;
import ge.edu.freeuni.sdp.xo.signin.data.json.SigninInfo;

public class InMemoryRepository implements Repository {
	Map<String, SignInInfoEntity> mUsers = new HashMap<String, SignInInfoEntity>();
	Map<String, SignInInfoEntity> mEmails = new HashMap<String, SignInInfoEntity>();
	Map<String, SignInInfoEntity> mTokens = new HashMap<String, SignInInfoEntity>();

	private static Repository instance;

	private InMemoryRepository(Map<String, SignInInfoEntity> usernames, Map<String, SignInInfoEntity> emails,
			Map<String, SignInInfoEntity> tokens) {
		mUsers = usernames;
		mEmails = emails;
		mTokens = tokens;
	}

	public static Repository getInstance() {
		if (instance == null) {
			Map<String, SignInInfoEntity> usernames = new HashMap<String, SignInInfoEntity>();
			Map<String, SignInInfoEntity> emails = new HashMap<String, SignInInfoEntity>();
			Map<String, SignInInfoEntity> tokens = new HashMap<String, SignInInfoEntity>();
			instance = new InMemoryRepository(usernames, emails, tokens);
		}
		return instance;
	}

	@Override
	public void insertOrUpdate(SignInInfoEntity entity) throws StorageException {
		mUsers.put(entity.getUsername(), entity);
		mEmails.put(entity.getEmail(), entity);
	}

	@Override
	public SignInInfoEntity findByUsername(String username) {
		SignInInfoEntity entity = mUsers.get(username);
		if (entity == null)
			return null;
		SigninInfo info = new SigninInfo();
		info.setEmail(entity.getEmail());
		info.setUsername(entity.getUsername());
		return SignInInfoEntity.fromSignInfo(info);
	}

	@Override
	public SignInInfoEntity findByEmail(String email) {
		SignInInfoEntity entity = mEmails.get(email);
		if (entity == null)
			return null;
		SigninInfo info = new SigninInfo();
		info.setEmail(entity.getEmail());
		info.setUsername(entity.getUsername());
		return SignInInfoEntity.fromSignInfo(info);
	}

	@Override
	public void insertToken(String token, String email) {
		SignInInfoEntity entity = findByEmail(email);
		mTokens.put(token, entity);
	}

	@Override
	public boolean hasToken(String token) {
		return mTokens.containsKey(token);
	}

	@Override
	public SignInInfoEntity findForToken(String token) {
		return mTokens.get(token);
	}

	@Override
	public void deleteToken(String token) {
		mTokens.remove(token);
	}

}
