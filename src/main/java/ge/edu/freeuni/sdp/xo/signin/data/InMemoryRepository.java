package ge.edu.freeuni.sdp.xo.signin.data;

import java.util.HashMap;
import java.util.Map;

import ge.edu.freeuni.sdp.xo.signin.data.entity.SignInInfoEntity;
import ge.edu.freeuni.sdp.xo.signin.data.entity.UserInfoEntity;
import ge.edu.freeuni.sdp.xo.signin.data.json.UserInfo;

import com.microsoft.azure.storage.StorageException;

public class InMemoryRepository implements Repository {
	Map<String, SignInInfoEntity> mUsers = new HashMap<String, SignInInfoEntity>();
	Map<String, SignInInfoEntity> mEmails = new HashMap<String, SignInInfoEntity>();
	Map<String, UserInfoEntity> mTokens = new HashMap<String, UserInfoEntity>();

	private static Repository instance;

	private InMemoryRepository(Map<String, SignInInfoEntity> usernames,
			Map<String, SignInInfoEntity> emails,
			Map<String, UserInfoEntity> tokens) {
		mUsers = usernames;
		mEmails = emails;
		mTokens = tokens;
	}

	public static Repository getInstance() {
		if (instance == null) {
			Map<String, SignInInfoEntity> usernames = new HashMap<String, SignInInfoEntity>();
			Map<String, SignInInfoEntity> emails = new HashMap<String, SignInInfoEntity>();
			Map<String, UserInfoEntity> tokens = new HashMap<String, UserInfoEntity>();
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
	public UserInfoEntity findByEmail(String email) {
		SignInInfoEntity entity = mEmails.get(email);
		if (entity == null)
			return null;
		UserInfo info = new UserInfo();
		info.setEmail(entity.getEmail());
		info.setUsername(entity.getUsername());
		return UserInfoEntity.fromUserInfo(info);
	}

	@Override
	public UserInfoEntity findByUsername(String username) {
		SignInInfoEntity entity = mUsers.get(username);
		if (entity == null)
			return null;
		UserInfo info = new UserInfo();
		info.setEmail(entity.getEmail());
		info.setUsername(entity.getUsername());
		return UserInfoEntity.fromUserInfo(info);
	}

	@Override
	public UserInfoEntity findForToken(String token) {
		return mTokens.get(token);
	}

	@Override
	public boolean hasToken(String token) {
		return mTokens.containsKey(token);
	}

	@Override
	public void deleteToken(String token) {
		mTokens.remove(token);
	}

	@Override
	public void insertToken(String token, String email) {
		UserInfoEntity entity = findByEmail(email);
		mTokens.put(token, entity);
	}

	@Override
	public SignInInfoEntity findUserCredentials(UserInfoEntity userInfo) {
		// TODO Auto-generated method stub
		return null;
	}

}
