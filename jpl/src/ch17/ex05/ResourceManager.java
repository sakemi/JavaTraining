package ch17.ex05;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.HashMap;
import java.util.Map;

/**
 * 取得できるリソース数を10個以下に制限する
 *
 * @author sakemi
 *
 */
public final class ResourceManager {
	final ReferenceQueue<Object> queue;
	final Map<Reference<?>, Resource> refs;
	boolean shutdown = false;

	public ResourceManager() {
		queue = new ReferenceQueue<Object>();
		refs = new HashMap<Reference<?>, Resource>();

		// リソース初期化
	}

	public synchronized void shutdown() {
		if (!shutdown) {
			shutdown = true;
		}
	}

	/**
	 * 11個以上のリソースを取得しようとした時、nullを返す
	 *
	 * @param key:任意のオブジェクト
	 * @return
	 */
	public synchronized Resource getResource(Object key) {
		if (shutdown) {
			throw new IllegalStateException();
		}
		reap();
		if (refs.size() >= 10) {
			return null;
		}
		Resource res = new ResourceImpl(key);
		Reference<?> ref = new PhantomReference<Object>(key, queue);
		refs.put(ref, res);
		return res;
	}

	private void reap() {
		while (true) {
			Reference<?> ref = queue.poll();
			if (ref == null) {
				return;
			}
			Resource res = refs.get(ref);
			refs.remove(ref);
			res.release();
			ref.clear();
		}
	}
}
