package ge.edu.freeuni.sdp.xo.signin.data;

import ge.edu.freeuni.sdp.xo.signin.data.entity.SignInInfoEntity;
import ge.edu.freeuni.sdp.xo.signin.data.entity.TokenEntity;

import com.microsoft.azure.storage.StorageException;

public interface Repository {

	public abstract void insertOrUpdateSignInfo(SignInInfoEntity entity) throws StorageException;

	public abstract SignInInfoEntity findByUsername(String username) throws StorageException;

	public abstract SignInInfoEntity findByEmail(String email);

	public abstract void insertOrUpdateToken(TokenEntity entity);

	public abstract boolean hasToken(String token);

	public abstract boolean isActivated(String username);

	public abstract SignInInfoEntity findForToken(String token);

	public abstract void deleteToken(String token);
}
