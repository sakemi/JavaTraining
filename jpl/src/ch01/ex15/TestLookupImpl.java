package ch01.ex15;

public class TestLookupImpl {

	//目視でのテスト
	public static void main(String[] args) {
		LookupImpl lookup = new LookupImpl();

		lookup.add("foo", "hoge");
		lookup.add("bar", 123);

		System.out.println(lookup.find("foo"));
		System.out.println(lookup.find("bar"));
		System.out.println(lookup.find("baz"));

		lookup.remove("foo");

		System.out.println(lookup.find("foo"));
	}
}
