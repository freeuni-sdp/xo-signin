package ge.edu.freeuni.sdp.xo.signin.data;

import java.security.NoSuchAlgorithmException;
import java.util.Iterator;

import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.table.CloudTable;
import com.microsoft.azure.storage.table.TableOperation;
import com.microsoft.azure.storage.table.TableQuery;
import com.microsoft.azure.storage.table.TableQuery.QueryComparisons;

import ge.edu.freeuni.sdp.xo.signin.data.entity.SignInInfoEntity;
import ge.edu.freeuni.sdp.xo.signin.data.entity.TokenEntity;
import ge.edu.freeuni.sdp.xo.signin.data.entity.id.SignInInfoEntityId;
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
		String accFilter = TableQuery.generateFilterCondition("Email", QueryComparisons.EQUAL, email);

		TableQuery<SignInInfoEntity> query = TableQuery.from(SignInInfoEntity.class).where(accFilter);
		Iterator<SignInInfoEntity> it = tableAccounts.execute(query).iterator();

		if (it.hasNext())
			return it.next();

		return null;
	}

	@Override
	public void deleteUser(String username) throws StorageException {
		SignInInfoEntity entity = findByUsername(username);
		if (entity == null)
			return;
		try {
			deleteToken(Util.generateToken(entity.getEmail(), entity.getUsername()));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (StorageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		TableOperation deleteUser = TableOperation.delete(entity);
		tableAccounts.execute(deleteUser);
	}

	@Override
	public void insertOrUpdateToken(TokenEntity entity) throws StorageException {
		TableOperation operation = TableOperation.insertOrReplace(entity);
		tableActivationTokens.execute(operation);
	}

	@Override
	public boolean hasToken(String token) throws StorageException {
		SignInInfoEntityId tok = new SignInInfoEntityId(token);
		TableOperation operation = TableOperation.retrieve(tok.getPartitionKey(), tok.getRowKey(), TokenEntity.class);

		if (tableActivationTokens.execute(operation).getResultAsType() != null)
			return true;

		return false;
	}

	@Override
	public SignInInfoEntity findForToken(String token) throws StorageException {
		SignInInfoEntityId tok = new SignInInfoEntityId(token);
		TableOperation operation = TableOperation.retrieve(tok.getPartitionKey(), tok.getRowKey(), TokenEntity.class);
		TokenEntity entity = tableActivationTokens.execute(operation).getResultAsType();

		if (entity != null)
			return findByUsername(entity.getUsername());

		return null;
	}

	@Override
	public void deleteToken(String token) throws StorageException {
		SignInInfoEntityId tok = new SignInInfoEntityId(token);
		TableOperation operation = TableOperation.retrieve(tok.getPartitionKey(), tok.getRowKey(), TokenEntity.class);
		TokenEntity entity = tableActivationTokens.execute(operation).getResultAsType();

		if (entity == null)
			return;

		TableOperation deleteTok = TableOperation.delete(entity);
		tableActivationTokens.execute(deleteTok);
	}

}
