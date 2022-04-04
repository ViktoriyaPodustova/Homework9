import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Student> students = makeStudents();

        System.out.println("Уникальные курсы:");
        System.out.println(filterCourses(students));

        System.out.println("Трое самых любознательных:");
        System.out.println(getTopStudents(students));

        System.out.println("Кто ходит на курс:");
        System.out.println(findStudentsByCourse(students, new Course("Базы данных")));
    }

    public static List<Student> makeStudents() {
        List<Student> students = Arrays.asList(
                new Student(
                        "Роман",
                        Arrays.asList(
                                new Course("История"),
                                new Course("Схемотехника"),
                                new Course("Философия"),
                                new Course("Базы данных"),
                                new Course("Матанализ"),
                                new Course("Информатика")
                        )
                ),
                new Student(
                        "Александра",
                        Arrays.asList(
                                new Course("Информатика"),
                                new Course("Матанализ"),
                                new Course("Ин-яз"),
                                new Course("Базы данных")
                        )
                ),
                new Student(
                        "Паша",
                        Arrays.asList(
                                new Course("Матанализ"),
                                new Course("Схемотехника"),
                                new Course("Дискретная математика"),
                                new Course("Базы данных"),
                                new Course("Философия")
                        )
                ),
                new Student(
                        "Николай",
                        Arrays.asList(
                                new Course("Матанализ"),
                                new Course("Дискретная математика"),
                                new Course("Базы данных")
                        )
                )
        );

        return students;
    }

    public static List<Course> filterCourses(List<Student> students) {
        List<Course> courses =
                students.stream().flatMap(element -> element.getAllCourses().stream()).collect(Collectors.toList());

        return courses.stream().filter(course -> Collections.frequency(courses, course) < 2).collect(Collectors.toList());
    }

    public static List<Student> getTopStudents(List<Student> students) {
        return students.stream().sorted(new Comparator<Student>() {
                    @Override
                    public int compare(Student o1, Student o2) {
                        if (o1.getAllCourses().size() > o2.getAllCourses().size()) {return -1;}
                        else if (o1.getAllCourses().size() < o2.getAllCourses().size()) {return 1;}
                        else {return 0;}
                    }
                })
                .limit(3)
                .collect(Collectors.toList());
    }

    public static List<Student> findStudentsByCourse(List<Student> students, Course course) {
        return students.stream()
                .filter(student -> student.getAllCourses().contains(course))
                .collect(Collectors.toList());
    }
}
