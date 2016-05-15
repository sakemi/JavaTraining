package ch11.ex03;

import java.util.Iterator;

public class Human implements Attributed<String> {

	// 属性として、名前と身長を持ちたいとする
	private Attr<String> name;
	private Attr<Integer> tall;

	// 値がString型の属性しか操作できない!
	// 問題と直接関係ないので空実装
	@Override
	public void add(Attr<String> newAttr) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public Attr<String> find(String attrName) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Attr<String> remove(String attrName) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Iterator<Attr<String>> attrs() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

}
