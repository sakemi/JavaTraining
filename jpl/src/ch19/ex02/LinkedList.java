package ch19.ex02;

/**
 * <code>T</code>型の要素を格納する連結リスト
 *
 * @author sakemi
 * @param {@code text}<T> 格納する値の型
 * @see ch19.ex01.Element
 */
public class LinkedList<T> {
	/** このリストの先頭の要素への参照 */
	private Element<T> head = null;
	/** このリストの終端の要素への参照 */
	private Element<T> tail = null;
	/** このリストの要素数 */
	private int elementNum = 0;

	/**
	 * 要素を追加する
	 *
	 * @param element
	 *            追加する要素
	 */
	public void add(Element<T> element) {
		elementNum++;
		if (head == null) {
			head = element;
			tail = element;
			return;
		}

		tail.setNext(element);
		tail = element;
	}

	/**
	 * インデックスで指定した要素を返す
	 *
	 * @param index
	 *            0以上、要素数-1以下のインデックス
	 * @throws IllegalArgumentException
	 *             0未満、若しくは要素数以上のインデックスが指定された時
	 */
	public Element<T> get(int index) {
		if (index >= elementNum || index < 0) {
			throw new IllegalArgumentException("index out of bound");
		}
		Element<T> element = head;
		for (int i = 0; i < index; i++) {
			element = element.getNext();
		}
		return element;
	}

	/**
	 * 各要素が保持する値の文字列表現をスペース区切りで返す。要素がないときは空文字列を返す。
	 */
	public String toString() {
		if (head == null) {
			return "";
		}
		Element<T> elements = head;
		StringBuilder sb = new StringBuilder().append(elements.toString()).append(" ");
		while (elements.getNext() != null) {
			elements = elements.getNext();
			sb.append(elements.toString()).append(" ");
		}

		return sb.toString();
	}

	/**
	 * 要素数を返す
	 */
	public int countNode() {
		return elementNum;
	}

}
