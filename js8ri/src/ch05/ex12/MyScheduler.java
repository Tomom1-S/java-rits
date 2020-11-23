package ch05.ex12;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

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
        return appointments;
    }

    public void startNotification() {
        service = Executors.newSingleThreadScheduledExecutor();
        scheduledFuture = service.scheduleAtFixedRate(() -> {
            final Optional<Appointment> nextApp = appointments.stream().findFirst();
            if (!nextApp.isPresent()) {
                return;
            }

            final Appointment appointment = nextApp.get();
            if (appointment.getTime().isAfter(ZonedDateTime.now().plusHours(1))) {
                return;
            }

            System.out.println(appointment);
            appointments.remove(appointment);
        }, 0, 5, TimeUnit.MINUTES);
    }

    public void stopNotification() {
        if (scheduledFuture == null) {
            return;
        }

        scheduledFuture.cancel(false);
    }
}
