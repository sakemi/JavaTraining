package ch16.ex05;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Member;
import java.lang.reflect.Modifier;

import ch16.ex04.ShowAnnotation;

public class ClassContents {
	public static void main(String args[]) {
		try {
			Class<?> c = Class.forName(args[0]);
			System.out.println(c);
			printMembers(c.getFields());
			printMembers(c.getDeclaredConstructors());
			printMembers(c.getMethods());
			printNotPublicMembers(c.getDeclaredFields());
			printNotPublicMembers(c.getDeclaredMethods());
		} catch (ClassNotFoundException e) {
			System.out.println("unknown class: " + args[0]);
		}
	}

	private static void printMembers(Member[] mems) {
		for (Member m : mems) {
			String decl = m.toString();
			System.out.print(" ");
			ShowAnnotation.showAnnotation((AnnotatedElement) m);
			System.out.print(" ");
			System.out.println(strip(decl, "java.lang."));
		}
	}

	private static void printNotPublicMembers(Member[] mems) {
		for (Member m : mems) {
			if (Modifier.isPublic(m.getModifiers())) {
				continue;
			}
			String decl = m.toString();
			System.out.print(" ");
			ShowAnnotation.showAnnotation((AnnotatedElement) m);
			System.out.print(" ");
			System.out.println(strip(decl, "java.lang."));
		}
	}

	private static String strip(String from, String target) {
		return from.replaceAll(target, "");
	}
}
