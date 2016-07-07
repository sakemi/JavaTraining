package ch19.ex02;

/**
 * {@link ch19.ex01.LinkedList 連結リスト}の要素。要素の値となるオブジェクトを保持し、次の要素への参照を持つ。
 *
 * @author sakemi
 * @param <code><T></code>
 *            保持する値の型
 * @see ch19.ex01.LinkedList
 */
public class Element<T> {
	/**保持する値*/
	private T content;
	/**次の要素への参照*/
	private Element<T> next = null;

	/**
	 * 引数で指定されたオブジェクトを保持する要素を生成する
	 */
	public Element(T content) {
		this.content = content;
	}

	/**
	 * 保持する値を返す
	 */
	public T getContent() {
		return content;
	}

	/**
	 * 引数で指定したオブジェクトを保持する
	 */
	public void setContent(T content) {
		this.content = content;
	}

	/**
	 * 次の要素の参照を返す
	 */
	public Element<T> getNext() {
		return next;
	}

	/**
	 * 引数で指定した要素を、次の要素への参照としてセットする
	 */
	public void setNext(Element<T> next) {
		this.next = next;
	}

	/**
	 * 保持する値の文字列表現を返す
	 */
	public String toString() {
		return content.toString();
	}
}
