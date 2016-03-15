package ch03.ex10;

//ex12 ans.
/*LinkedListの仕様が、一つのノードに複数のcontentを持つようなものならば、
 * Object型の可変長引数をとるメソッドが必要になる
 */

//ex16
//ここまでのLinkedListは設計が悪かった気がするので、ここで修正する
public class LinkedList implements Cloneable {
	private Object content;
	private LinkedList next;
	// ex16で追加
	private static LinkedList head;

	@Override
	public LinkedList clone() {
		try {
			LinkedList nObj = (LinkedList) super.clone();
			if (nObj.next != null) {
				nObj.next = next.clone();
			}
			return nObj;
		} catch (CloneNotSupportedException e) {
			throw new InternalError(e.toString());
		}

	}

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}

	public LinkedList getNext() {
		return next;
	}

	// ex08 ans
	// 内容と次ノードの参照を初期化するコンストラクタが必要
	// ex16で修正
	public LinkedList(Object content) {
		this.content = content;
		this.next = LinkedList.head;
		LinkedList.head = this;
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

	// ex16
	public static int countNode() {
		int count = 1;
		LinkedList node = LinkedList.head;
		while (node.getNext() != null) {
			count++;
			node = node.getNext();
		}
		return count;
	}

	public static void main(String[] args) {
		Vehicle foo = new Vehicle();
		Vehicle bar = new Vehicle();

		LinkedList list2 = new LinkedList(bar);
		LinkedList list1 = new LinkedList(foo);
	}
}
