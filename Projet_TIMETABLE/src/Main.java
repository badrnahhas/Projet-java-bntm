
public class Main {

	public static void main(String[] args) {
		
		Student S1 = new Student("login2", "motdepasse3", "Badreddine", "NAHHAS", 2695, 0);
		Group G1 = new Group(1);
		//tests sur le group
		System.out.println("Dans ce groupe il y'a : " + G1.getNumber_of_students() + " étudiants");
		System.out.println("L'id_group de l'étudiant est : " + S1.getId_group() );
		G1.setStudents(S1);
		System.out.println("L'étudiant s'appelle : " + G1.getaStudent(2695).getFirst_name() + " " + G1.getaStudent(2695).getLast_name());
		System.out.println("Dans ce groupe il y'a : " + G1.getNumber_of_students() + " étudiants");
		System.out.println("L'id_group de l'étudiant est : " + S1.getId_group() );
		G1.removeaStudent(2695);
		System.out.println("Dans ce groupe il y'a : " + G1.getNumber_of_students() + " étudiants");
		System.out.println("L'id_group de l'étudiant est : " + S1.getId_group() );
	}

}
