package ge.edu.freeuni.sdp.xo.signin;


import javax.ws.rs.Path;

import ge.edu.freeuni.sdp.xo.signin.data.InMemoryRepository;
import ge.edu.freeuni.sdp.xo.signin.data.Repository;

import com.microsoft.azure.storage.StorageException;

@Path("memtodos")
public class InMemoryTaskService extends TaskService {
	
	@Override
	public Repository getRepository() throws StorageException {
		return InMemoryRepository.instance();
	}

}
