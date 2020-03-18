package taskqueue.task1;

public class RunTask1 {

	public static void main(String[] args) {
		System.out.println("Start task1");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("End task 1");
	}

}
