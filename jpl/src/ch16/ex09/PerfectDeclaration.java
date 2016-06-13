package ch16.ex09;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import ch16.ex04.ShowAnnotation;

public class PerfectDeclaration {
	public static void main(String[] args) {
		try {
			Class<?> clazz = Class.forName(args[0]);
			Field[] publicFields = clazz.getFields();
			Field[] declareFields = clazz.getDeclaredFields();
			Constructor<?>[] constructors = clazz.getConstructors();
			Method[] publicMethods = clazz.getMethods();
			Method[] declareMethods = clazz.getDeclaredMethods();

			System.out.println(clazz.toGenericString());
			printMembers(publicFields);
			printNotPublicMembers(declareFields);
			printGenericMembers(constructors);
			printGenericMembers(publicMethods);
			printGenericNotPublicMembers(declareMethods);
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	private static void printMembers(Member[] mems) {
		for (Member m : mems) {
			String decl = m.toString();
			System.out.print(" ");
			ShowAnnotation.showAnnotation((AnnotatedElement) m);
			System.out.print(" ");
			System.out.println(decl);
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
			System.out.println(decl);
		}
	}

	private static void printGenericMembers(Member[] mems) {
		for (Member m : mems) {
			if (m instanceof Constructor<?>) {
				String decl = ((Constructor<?>) m).toGenericString();
				System.out.print(" ");
				ShowAnnotation.showAnnotation((AnnotatedElement) m);
				System.out.print(" ");
				System.out.println(decl);
			} else if (m instanceof Method) {
				String decl = ((Method) m).toGenericString();
				System.out.print(" ");
				ShowAnnotation.showAnnotation((AnnotatedElement) m);
				System.out.print(" ");
				System.out.println(decl);
			}
		}
	}

	private static void printGenericNotPublicMembers(Member[] mems) {
		for (Member m : mems) {
			if (Modifier.isPublic(m.getModifiers())) {
				continue;
			}
			if (m instanceof Constructor<?>) {
				String decl = ((Constructor<?>) m).toGenericString();
				System.out.print(" ");
				ShowAnnotation.showAnnotation((AnnotatedElement) m);
				System.out.print(" ");
				System.out.println(decl);
			} else if (m instanceof Method) {
				String decl = ((Method) m).toGenericString();
				System.out.print(" ");
				ShowAnnotation.showAnnotation((AnnotatedElement) m);
				System.out.print(" ");
				System.out.println(decl);
			}
		}
	}
}
