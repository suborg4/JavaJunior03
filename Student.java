package ru.example.javajunior03;

/**
 * Java Junior (семинары)
 * Урок 3. Сериализация
 * Разработайте класс Student с полями String name, int age, transient double GPA (средний балл).
 * Обеспечьте поддержку сериализации для этого класса.
 * Создайте объект класса Student и инициализируйте его данными.
 * Сериализуйте этот объект в файл.
 * Десериализуйте объект обратно в программу из файла.
 * Выведите все поля объекта, включая GPA, и обсудите,
 * почему значение GPA не было сохранено/восстановлено.
 */

import java.io.*;

public class Student implements Serializable {
    private String name;
    private int age;
    private transient double GPA;

    public Student(String name, int age, double GPA) {
        this.name = name;
        this.age = age;
        this.GPA = GPA;
    }

    public static void main(String[] args) {
        // Создание Student
        Student student = new Student("Борис", 20, 3.8);

        // сериализация
        try {
            FileOutputStream fileOut = new FileOutputStream("student.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(student);
            out.close();
            fileOut.close();
            System.out.println("Объект сериализован в student.ser");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // десериализация
        try {
            FileInputStream fileIn = new FileInputStream("student.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Student deserializedStudent = (Student) in.readObject();
            in.close();
            fileIn.close();

            // Вывод полей + GPA
            System.out.println("Имя студента: " + deserializedStudent.name);
            System.out.println("Возраст студента: " + deserializedStudent.age);
            System.out.println("Средний балл студента: " + deserializedStudent.GPA);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

/**
 * Значение GPA не сохранено/восстановлено из-за модификатора `transient` и
 * не будет участвовать в сериализации/десериализации.
 * Например средний балл студента это ПнД и мы не хотим сохранять/передавать конфиденциальную информацию.
 */
