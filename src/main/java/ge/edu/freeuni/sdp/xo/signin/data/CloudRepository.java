package ge.edu.freeuni.sdp.xo.signin.data;

import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.table.CloudTable;

import ge.edu.freeuni.sdp.xo.signin.data.entity.SignInInfoEntity;
import ge.edu.freeuni.sdp.xo.signin.data.entity.UserInfoEntity;

public class CloudRepository implements Repository {

	public CloudRepository(CloudTable  accounts, CloudTable activationTokens) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void insertOrUpdate(SignInInfoEntity entity) throws StorageException {
		// TODO Auto-generated method stub

	}

	@Override
	public UserInfoEntity findByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserInfoEntity findByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserInfoEntity findForToken(String token) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasToken(String token) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void deleteToken(String token) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insertToken(String token, String email) {
		// TODO Auto-generated method stub

	}

	@Override
	public SignInInfoEntity findUserCredentials(UserInfoEntity userInfo) {
		// TODO Auto-generated method stub
		return null;
	}

}
