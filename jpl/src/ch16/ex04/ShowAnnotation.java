package ch16.ex04;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.AnnotatedElement;

public class ShowAnnotation {
	public static void showAnnotation(AnnotatedElement ae) {
		if (ae == null) {
			throw new NullPointerException();
		}
		Annotation[] annotations = ae.getAnnotations();
		for (Annotation a : annotations) {
			System.out.println(a.toString());
		}
	}

	public static void main(String[] args) {
		showAnnotation(Dummy.class);
	}

	@Dummy1
	@Dummy2
	private class Dummy {

	}
}

@Retention(value = RetentionPolicy.RUNTIME)
@interface Dummy1 {
}

@Retention(value = RetentionPolicy.RUNTIME)
@interface Dummy2 {
}