package ch05.ex12;

import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class MyScheduler {
    final private Set<Appointment> appointments = new TreeSet<>(Comparator.naturalOrder());
    private ScheduledExecutorService service;
    private ScheduledFuture<?> scheduledFuture;

    public void addAppointment(final String name, final ZonedDateTime time) {
        if (time.isBefore(ZonedDateTime.now())) {
            throw new IllegalArgumentException("time should be future time!");
        }

        appointments.add(new Appointment(name, time));
    }

    public Set<Appointment> getAppointments() {
        // 柴田さん：
        // appointment が private なので、そのまま返すと private なフィールドを変更できてしまう
        // コピーを返すようにすべき
        return appointments.stream().collect(Collectors.toSet());
    }

    public void startNotification() {
        service = Executors.newSingleThreadScheduledExecutor();
        scheduledFuture = service.scheduleAtFixedRate(() -> {
            final Optional<Appointment> nextApp;
            // 柴田さん：appointment をスレッドセーフにする
            synchronized (appointments) {
                nextApp = appointments.stream().findFirst();
            }
            if (!nextApp.isPresent()) {
                return;
            }

            final Appointment appointment = nextApp.get();
            if (appointment.getTime().isAfter(ZonedDateTime.now().plusHours(1))) {
                return;
            }

            System.out.println(appointment);
            // 柴田さん：appointment をスレッドセーフにする
            synchronized (appointments) {
                appointments.remove(appointment);
            }
        }, 0, 5, TimeUnit.MINUTES);
    }

    public void stopNotification() {
        if (scheduledFuture == null) {
            return;
        }

        scheduledFuture.cancel(false);
    }
}
