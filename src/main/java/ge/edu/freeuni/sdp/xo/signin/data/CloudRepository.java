package ge.edu.freeuni.sdp.xo.signin.data;

import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.table.CloudTable;
import com.microsoft.azure.storage.table.TableOperation;

import ge.edu.freeuni.sdp.xo.signin.data.entity.SignInInfoEntity;
import ge.edu.freeuni.sdp.xo.signin.data.entity.TokenEntity;
import ge.edu.freeuni.sdp.xo.signin.data.entity.id.UserInfoEntityId;

public class CloudRepository implements Repository {
	private CloudTable tableAccounts;
	private CloudTable tableActivationTokens;

	final String PARTITION_KEY = "PartitionKey";
	final String ROW_KEY = "RowKey";

	public CloudRepository(CloudTable accounts, CloudTable activationTokens) {
		this.tableAccounts = accounts;
		this.tableActivationTokens = activationTokens;
	}

	@Override
	public void insertOrUpdateSignInfo(SignInInfoEntity entity) throws StorageException {
		TableOperation operation = TableOperation.insertOrReplace(entity);
		tableAccounts.execute(operation);
	}

	@Override
	public SignInInfoEntity findByUsername(String username) throws StorageException {
		UserInfoEntityId userId = new UserInfoEntityId(username);
		TableOperation operation = TableOperation.retrieve(userId.getPartitionKey(), userId.getRowKey(),
				SignInInfoEntity.class);
		return tableAccounts.execute(operation).getResultAsType();
	}

	@Override
	public SignInInfoEntity findByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertOrUpdateToken(TokenEntity entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean hasToken(String token) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isActivated(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public SignInInfoEntity findForToken(String token) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteToken(String token) {
		// TODO Auto-generated method stub

	}

}
