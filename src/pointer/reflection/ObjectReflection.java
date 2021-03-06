package pointer.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ObjectReflection {
    private Object obj;

    public ObjectReflection(Object object) {
        this.obj = object;
    }

    public void showAll() {
        showClass();
        showAllFields();
        showAllConstructors();
        showAllMethods();
    }

    public void showClass() {
        Class clazz = obj.getClass();
        System.out.println("Class " + clazz.getModifiers() + " " + clazz.getName());

        for (clazz = clazz.getSuperclass(); !clazz.equals(Object.class); clazz = clazz.getSuperclass()) {
            System.out.println("Has superclass: " + clazz.getModifiers() + " " + clazz.getName());
        }
    }

    public void showAllFields() {
        Class clazz = obj.getClass();

        for (; !clazz.equals(Object.class); clazz = clazz.getSuperclass()) {
            Field[] fields = clazz.getDeclaredFields();

            for (Field field : fields) {
                field.setAccessible(true);
                try {
                    System.out.println(field.getType() + " " + field.getName() +
                            " has modifiers: " + field.getModifiers() +
                            " and value " + field.get(obj));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void showAllMethods() {
        Class clazz = obj.getClass();

        while (!clazz.equals(Object.class)) {
            Method[] methods = clazz.getDeclaredMethods();

            for (Method method : methods) {
                method.setAccessible(true);
                System.out.println(method.getDeclaringClass() + " " + method.getName() +
                        " has modifiers: " + method.getModifiers() +
                        " and return value " + method.getReturnType());
            }

            clazz = clazz.getSuperclass();
        }
    }

    public void showAllConstructors() {
        Class clazz = obj.getClass();

        for (; !clazz.equals(Object.class); clazz = clazz.getSuperclass()) {
            Constructor[] constructors = clazz.getDeclaredConstructors();

            for (Constructor constructor : constructors) {
                System.out.println(constructor.getDeclaringClass() + " " + constructor.getName() +
                        " has modifiers: " + constructor.getModifiers());
            }
        }
    }
}