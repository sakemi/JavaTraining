package ch17.ex05;

public class ResourceImpl implements Resource {
	Object key;
	boolean needsRelease = false;

	ResourceImpl(Object key) {
		this.key = key;
		needsRelease = true;
	}

	@Override
	public void use(Object key, Object... args) {
		// TODO 自動生成されたメソッド・スタブ
		if (key != this.key) {
			throw new IllegalArgumentException();
		}
		// リソース使用
	}

	@Override
	public void release() {
		// TODO 自動生成されたメソッド・スタブ
		if (needsRelease) {
			needsRelease = true;
		}
		// リソース開放
	}

}
