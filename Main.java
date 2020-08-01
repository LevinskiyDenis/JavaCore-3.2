package com.company;

import java.util.*;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        List<People> people = new ArrayList<>(Arrays.asList(
                //new People("Вася", 16, Sex.MAN),
                //new People("Петя", 23, Sex.MAN),
                new People("Елена", 42, Sex.WOMAN),
                //new People("Иван Иванович", 69, Sex.MAN),
                new People("Светлана", 14, Sex.WOMAN),
                //new People("Родриго", 29, Sex.MAN),
                //new People("Хулио", 64, Sex.MAN),
                new People("Джулия", 61, Sex.WOMAN)
        ));

        Stream<People> streamMilitary = people.stream();
        System.out.println("Список военнообязанных: ");
        streamMilitary.filter(x -> x.getAge() > 18)
                .filter(x -> x.getAge() < 30)
                .filter(x -> x.getSex() == Sex.MAN)
                .forEach(System.out::println);

        // Если в листе people не будет мужчин, а мы объявим double resultAverageManAge равный результату стрима AverageManAge
        // То получим NoSuchElementException, т.к. стрим не вычислит никакое значение, а значит и double был объявлен, но не инициализирован (т.е. такого объекта просто нет)
        // Чтобы так не происходило, мы можем обернуть код в try-catch или использовать класс OptionalDouble, который не выдает exception, если экземпляр класса null

        Stream<People> averageManAge = people.stream();
        System.out.println("Средний возраст всех мужчин:");
        OptionalDouble resultAverageManAge =
                averageManAge.filter(x -> x.getSex() == Sex.MAN)
                        .mapToInt(x -> x.getAge())
                        .average();

        System.out.println(resultAverageManAge);

        Stream<People> workers = people.stream();
        System.out.println("Количество работоспособных людей:");
        long numOfWorkers = people.stream()
                .filter(x -> x.getSex() == Sex.MAN)
                .filter(x -> x.getAge() > 18 && x.getAge() < 65)
                .count() +
                people.stream()
                        .filter(x -> x.getSex() == Sex.WOMAN)
                        .filter(x -> x.getAge() > 18 && x.getAge() < 60)
                        .count();
        System.out.println(numOfWorkers);
    }
}
