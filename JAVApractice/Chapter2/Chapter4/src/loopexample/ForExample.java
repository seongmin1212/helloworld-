package loopexample;

public class ForExample {

	public static void main(String[] args) {

		int num;
		int sum;
		
	for(num = 1, sum = 0; num <= 10; num++) {
			
			sum += num;
			
			
		}
		System.out.println(sum);
		System.out.println(num);
		
		int dan = 2;
		int times = 1;
				
		while(dan <= 9) {
			
			times = 1;
			while( times <= 9) {
				if(times > dan) break;
				System.out.println(dan + "X" + times + "=" + dan*times);
				times++;
			}
			dan++;
			System.out.println();
		}
	}

}

	