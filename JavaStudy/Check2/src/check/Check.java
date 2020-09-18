package check;
import constants.Constants;
public class Check {


	private static  String firstName =  "Sugimura";
	private static  String lastName = "Kenta";


	private static String printName(String firstName, String lastName) {
		return firstName + lastName;
	}


	public static void main(String[] args) {

		System.out.println("printNameメソッド → "+printName(firstName,lastName));

		Constants con = new Constants();
		Pet pet = new Pet(con.CHECK_CLASS_JAVA,con.CHECK_CLASS_HOGE);
		RobotPet robo = new RobotPet(con.CHECK_CLASS_R2D2,con.CHECK_CLASS_LUKE);

		pet.introduce();
		robo.introduce();

	}
}
