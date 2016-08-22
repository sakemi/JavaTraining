package ch22.ex15;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Calculator {
	private final Stack<Double> stack = new Stack<Double>();
	private final List<Method> operation = new ArrayList<Method>();
	private Method currentOperation;

	public Calculator() {
		Class<Math> math = Math.class;
		Method[] methods = math.getDeclaredMethods();
		for (Method m : methods) {
			operation.add(m);
		}
	}

	public void calculate(String reversePolishExpression)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		String[] token = reversePolishExpression.split(",");
		for (String s : token) {
			try {
				stack.push(Double.parseDouble(s));
			} catch (NumberFormatException e) {
				int pc = popCount(s);
				if (pc == 0) {
					stack.push(operate(s));
				} else if (pc == 1) {
					double val = stack.pop();
					stack.push(operate(s, val));
				} else if (pc == 2) {
					double val2 = stack.pop();
					double val1 = stack.pop();
					stack.push(operate(s, val1, val2));
				}
			}
		}
		System.out.println(stack.pop());
	}

	private int popCount(String operator) {
		if (operator.matches("[\\+\\-\\*/%]")) {
			return 2;
		} else {
			for (Method e : operation) {
				if (operator.equals(e.getName())) {
					if (e.getParameterCount() != 0 && !e.getParameterTypes()[0].equals(double.class)) {
						continue;
					}
					currentOperation = e;
					return currentOperation.getParameterCount();
				}
			}
			throw new IllegalArgumentException("Illegal operator: " + operator);
		}
	}

	private double operate(String operator, double... val)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		if (operator.equals("+")) {
			return val[0] + val[1];
		} else if (operator.equals("-")) {
			return val[0] - val[1];
		} else if (operator.equals("*")) {
			return val[0] * val[1];
		} else if (operator.equals("/")) {
			return val[0] / val[1];
		} else if (operator.equals(("%"))) {
			return val[0] % val[1];
		} else {
			if (val.length == 0) {
				return (Double) currentOperation.invoke(null);
			} else if (val.length == 1) {
				return (Double) currentOperation.invoke(null, val[0]);
			} else if (val.length == 2) {
				return (Double) currentOperation.invoke(null, val[0], val[1]);
			} else {
				throw new IllegalArgumentException();
			}

		}
	}

	public static void main(String[] args)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Calculator c = new Calculator();
		c.calculate(args[0]);
	}
}
