import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] time = br.readLine().split(" ");

		int year = Integer.parseInt(time[2]);
		boolean yearCheck = false;
		if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
			yearCheck = true;
		}

		String month = time[0];
		int mon = 0;
		switch (month) {
			case "January": mon = 1; break;
			case "February": mon = 2; break;
			case "March": mon = 3; break;
			case "April": mon = 4; break;
			case "May": mon = 5; break;
			case "June": mon = 6; break;
			case "July": mon = 7; break;
			case "August": mon = 8; break;
			case "September": mon = 9; break;
			case "October": mon = 10; break;
			case "November": mon = 11; break;
			case "December": mon = 12; break;
		}

		int[] days = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		int day = Integer.parseInt(time[1].replace(",", ""));
		int dayCnt = 0;
		for (int i = 1; i < mon; i++) {
			if (yearCheck && i == 2) {
				dayCnt += 29;
			} else {
				dayCnt += days[i];
			}
		}
		dayCnt += day;

		String[] hm = time[3].split(":");
		int hour = Integer.parseInt(hm[0]);
		int min = Integer.parseInt(hm[1]);
		int timeHM = hour * 60 + min;

		int timeToHour = (dayCnt - 1) * 24 * 60 + timeHM;

		int totalYear = (yearCheck ? 366 : 365) * 24 * 60;

		System.out.println((double) timeToHour * 100 / totalYear);
	}
}
