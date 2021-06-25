package ru.ds950;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;

public class Main {
    public static void main(String[] args) throws ParseException {
        //парсим JSON
        JsonParser jsonParser = new JsonParser();
        //создаем список объектов и заполняем данными из json
        List<Tickets> parsTickets = jsonParser.parser(args[0]);
        //создаем массив времени перелёта
        long[] times = new long[parsTickets.size()];
        //проходимся по списку билетов
        for (Tickets s : parsTickets) {
            String time1 = String.valueOf(s.departure_time);
            String time2 = String.valueOf(s.arrival_time);
            //парсим время вылета и прибытия и вычисляем разницу в минутах
            long diffMinutes = jsonParser.dateParseAndDifference(time1, time2);
            //заполняем массив времени перелета
            times[parsTickets.indexOf(s)] = diffMinutes;
            //вывод в консоль часов и минут перелета
            //System.out.println(diffMinutes/60 + " : " + diffMinutes%60);
        }
        //читаем массив времени перелета и вычисляем среднее время перелета
        OptionalDouble a = Arrays.stream(times).average();

        //выводим в консоль
        System.out.println("Среднее время перелета между городами Владивосток и Тель-Авив составляет:\n" + (int) a.getAsDouble()/60 + " часов " + (int) a.getAsDouble()%60 + " минуты");
        //вычисляем процентиль
        double percentile = jsonParser.percentile(times, 90);

        System.out.println("90-й процентиль времени полета между городами  Владивосток и Тель-Авив - " + (int)percentile/60 + ":" + (int)percentile%60);

        }


}

