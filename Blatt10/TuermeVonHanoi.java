
public class TuermeVonHanoi {
	/**
	 * main Methode nimmt ein Arbument welches die anzahl an zu verschiebenen Scheiben ist
	 * @param args
	 */
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
	
	/**
	 * Die move Methode loest das Tuerme von Hanoi Spiel mit einem Rekursieven Ansatz. 
	 * Bei diesem werden erst quantity - 1 scheiben auf den hilfsslot verschoben um dann die verbleibene größte scheibe auf das target zu verschieben 
	 * anschließend wird das verschieben der nun auf help liegenden scheiben zum target aufgerugfen
	 * 
	 * 
	 * @param quantity
	 * @param start
	 * @param help
	 * @param target
	 */
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
