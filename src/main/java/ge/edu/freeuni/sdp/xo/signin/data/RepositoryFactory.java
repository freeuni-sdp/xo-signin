package ge.edu.freeuni.sdp.xo.signin.data;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.table.CloudTable;
import com.microsoft.azure.storage.table.CloudTableClient;

public class RepositoryFactory {
	private static Repository repo;
	private static String tableAccounts = "xosignin_accounts";
	private static String tableActivationTokens = "xosignin_activation_tokens";

	public static Repository getInMemoryRepository() {
		if (repo == null) {
			repo = InMemoryRepository.getInstance();
		}
		return repo;
	}

	public static Repository getCloudRepository() throws StorageException {
		return new CloudRepository(getTable(tableAccounts), getTable(tableActivationTokens));
	}

	private static CloudTable getTable(String tableName) throws StorageException {
		final String storageConnectionString = "DefaultEndpointsProtocol=http;" + "AccountName=freeunisdptodo;"
				+ "AccountKey=+UKHsHFQUWDjoHT1S7q4Ivc1phivLmXwWESvpcRCCJwhs1BnShkaFOOQs+BmI4XWtNnyg78S6ovbD2J5QCKxsQ==";

		CloudStorageAccount storageAccount;
		try {
			storageAccount = CloudStorageAccount.parse(storageConnectionString);
		} catch (InvalidKeyException | URISyntaxException e) {
			e.printStackTrace();
			return null;
		}

		CloudTableClient tableClient = storageAccount.createCloudTableClient();
		CloudTable cloudTable;
		try {
			cloudTable = new CloudTable(tableName, tableClient);
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return null;
		}
		cloudTable.createIfNotExists();
		return cloudTable;
	}

}
