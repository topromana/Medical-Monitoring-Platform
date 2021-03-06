import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

import java.io.File;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public final class Producer {
    /**
     * This is a.
     * Javadoc comment.
     */
    private static final  String QUEUE_NAME = "hello";
    private Producer() {
    //not called
    }
    /**
     * This is a.
     * @param argv
     * Javadoc comment.
     */
    public static void main(final String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            FileReader reader =
                    new FileReader(new File("F:\\DS\\activity.txt"));
            Scanner rd = new Scanner(reader);
            String patientId = rd.next();
            String start = rd.next() + " " + rd.next();
            String end = rd.next() + " " + rd.next();
            String act = rd.next();
            LocalDateTime startDate =
                    LocalDateTime.parse(start, DateTimeFormatter
                            .ofPattern("yyyy-MM-dd HH:mm:ss"));
            LocalDateTime endDate =
                    LocalDateTime.parse(end, DateTimeFormatter
                            .ofPattern("yyyy-MM-dd HH:mm:ss"));
            long startMillis =
                    startDate.atZone(ZoneId.systemDefault()).toInstant()
                            .toEpochMilli();
            long endMillis =
                    endDate.atZone(ZoneId.systemDefault()).toInstant()
                            .toEpochMilli();
            String message;
            message = "[{\"patient_id\":"
                    +
                    "\"" + patientId + "\",\"activity\":" + "\""
                    +
                    act + "\",\"start_time\":" + "\"" + startMillis
                    +
                    "\", \"end_time\":" + "\""
                    +
                    endMillis + "\"}]";

            channel.basicPublish("", QUEUE_NAME, null,
                    message.getBytes(StandardCharsets.UTF_8));
            while (rd.hasNext()) {
                patientId = rd.next();
                start = rd.next() + " " + rd.next();
                end = rd.next() + " " + rd.next();
                act = rd.next();
                startDate = LocalDateTime.parse(start,
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                endDate = LocalDateTime.parse(end,
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                startMillis =
                        startDate.atZone(ZoneId.systemDefault())
                        .toInstant()
                        .toEpochMilli();
                endMillis =
                        endDate.atZone(ZoneId.systemDefault())
                        .toInstant()
                        .toEpochMilli();
                message = "[{\"patient_id\":"
                        +
                        "\"" + patientId + "\",\"activity\":" + "\"" + act
                        +
                        "\",\"start_time\":"
                        +
                        "\"" + startMillis + "\", \"end_time\":"
                        +
                        "\"" + endMillis + "\"}]";

                channel.basicPublish("", QUEUE_NAME, null,
                        message.getBytes(StandardCharsets.UTF_8));
                //Thread.sleep(1000);
            }
            System.out.println(" [x] Sent '" + " message");
        }
    }
}
