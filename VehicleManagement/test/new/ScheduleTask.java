package mypack;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

class ScheduleTask {
	private final ScheduledExecutorService scheduler = Executors
			.newScheduledThreadPool(1);

	public void beepForAnHour() {
		final Runnable beeper = new Runnable() {
			public void run() {
				System.out.println("beep");
//				SendMailBySite mailBySite = new SendMailBySite();
//				String recipients[] = {"tapas.caretech@gmail.com"};
//				String subject = "Test"; 
//				String message = "Hi !!!";
//				String from = "tapasalok@gmail.com";
//				try {
//					mailBySite.postMail(recipients, subject, message, from);
//				} catch (MessagingException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
			}
		};
		final ScheduledFuture<?> beeperHandle = scheduler.scheduleAtFixedRate(
				beeper, 10, 10, SECONDS);
		scheduler.schedule(new Runnable() {
			public void run() {
				beeperHandle.cancel(true);
			}
		}, 60 * 60, SECONDS);
	}
}
