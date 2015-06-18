package ge.edu.freeuni.sdp.xo.signin.data;

import ge.edu.freeuni.sdp.xo.signin.data.entity.SignInInfoEntity;
import ge.edu.freeuni.sdp.xo.signin.data.entity.UserInfoEntity;

import com.microsoft.azure.storage.StorageException;

public interface Repository {

	public abstract void insertOrUpdate(SignInInfoEntity entity)
			throws StorageException;

	public abstract UserInfoEntity findByEmail(String email);

	public abstract UserInfoEntity findByUsername(String username);

	public abstract UserInfoEntity findForToken(String token);

	public abstract boolean hasToken(String token);

	public abstract void deleteToken(String token);

	public abstract void insertToken(String token, String email);

	public abstract SignInInfoEntity findUserCredentials(UserInfoEntity userInfo);

}
