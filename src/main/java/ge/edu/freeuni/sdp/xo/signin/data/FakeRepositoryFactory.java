package ge.edu.freeuni.sdp.xo.signin.data;

public class FakeRepositoryFactory {
	private static Repository repo;

	public static Repository getInMemoryRepository() {
		if (repo == null) {
			repo = InMemoryRepository.getInstance();
		}
		return repo;
	}

}
