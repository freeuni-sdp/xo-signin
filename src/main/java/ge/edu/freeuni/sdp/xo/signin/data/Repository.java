package ge.edu.freeuni.sdp.xo.signin.data;

import ge.edu.freeuni.sdp.xo.signin.data.entity.SignInInfoEntity;
import com.microsoft.azure.storage.StorageException;

public interface Repository {

	public abstract void insertOrUpdate(SignInInfoEntity entity) throws StorageException;

	public abstract SignInInfoEntity findByUsername(String username) throws StorageException;

	public abstract SignInInfoEntity findByEmail(String email);

	public abstract void insertToken(String token, String email);

	public abstract boolean hasToken(String token);

	public abstract SignInInfoEntity findForToken(String token);

	public abstract void deleteToken(String token);
}
