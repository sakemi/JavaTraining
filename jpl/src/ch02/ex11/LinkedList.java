package ch02.ex11;

public class LinkedList {
	private Object content;
	private LinkedList next;

	// ex08 ans
	// 内容と次ノードの参照を初期化するコンストラクタが必要
	public LinkedList(Object content, LinkedList next) {
		this.content = content;
		this.next = next;
	}

	// ex11
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.content);
		if (this.next != null) {
			sb.append(", ").append(next.toString());
		}

		return sb.toString();
	}

	public static void main(String[] args) {
		Vehicle foo = new Vehicle();
		Vehicle bar = new Vehicle();

		LinkedList list2 = new LinkedList(bar, null);
		LinkedList list1 = new LinkedList(foo, list2);
	}
}
