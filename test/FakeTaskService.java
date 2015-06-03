import com.microsoft.azure.storage.StorageException;

import ge.edu.freeuni.sdp.xo.signin.TaskService;
import ge.edu.freeuni.sdp.xo.signin.data.Repository;


public class FakeTaskService extends TaskService {
	@Override
	public Repository getRepository() throws StorageException {
		return FakeRepository.instance();
	}
}
