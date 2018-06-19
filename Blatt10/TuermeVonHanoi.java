
public class TuermeVonHanoi {
	
	public static void main(String[] args) {
		
		int n;
		
		if (args.length > 0){
			try{
				n = Integer.parseInt(args[0]);
			}catch (NumberFormatException e){
				System.out.println("USE: TuermeVonHanoi Scheibenanzahl \n Scheibenanzhaln muss int sein");

				return;
			}
		}else{
			System.out.println("USE: TuermeVonHanoi Scheibenanzahl \n Scheibenanzhaln muss int sein");
			return;
		}
		
		move(n, 'A', 'B', 'C');
		
	}
	
	
	static public void move(int quantity, char start, char help, char target){
		if (quantity == 1)
			System.out.println("Verschiebe oberste Scheibe von " + start + " zu " + target);
		else {
			move (quantity - 1, start, target, help);
			move (1, start, help, target);
			move (quantity - 1, help, start, target);
		}
	}
	
}
