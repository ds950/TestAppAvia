package ru.ds950;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class JsonParser {

        //метод парсинга json
        List<Tickets> parser(String pathFile) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            // JSON в java объект
            TicketsList tickets = mapper.readValue(new File(pathFile), TicketsList.class);

            return tickets.getTickets();

        } catch (
                IOException e) {
            e.printStackTrace();
        }
            return null;

        }
        //метод парсинга времени и вычисления разницы в минутах
        long dateParseAndDifference(String time1, String time2) throws ParseException {
            SimpleDateFormat format = new SimpleDateFormat("HH:mm");
            Date date1 = format.parse(time1);
            Date date2 = format.parse(time2);
            long difference = date2.getTime() - date1.getTime();
            return difference / (60 * 1000);
        }

        double percentile(long[] times, double percentile) {
            Arrays.sort(times);
            int index = (int) Math.ceil((percentile / 100) * times.length);
            return times[index -1];
    }

}

