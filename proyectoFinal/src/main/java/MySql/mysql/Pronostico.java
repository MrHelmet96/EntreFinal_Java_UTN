package MySql.mysql;

public class Pronostico {
	
	 Equipo equipo1;
	 Equipo equipo2;
	 int gana1;
	 int empate;
	 int gana2;
	
	 public Pronostico(Equipo equipo1,int gana1,int empate, int gana2 ,Equipo equipo2){
			this.equipo1 = equipo1;
			this.gana1 = gana1;
			this.empate = empate;
			this.gana2=gana2;
			this.equipo2= equipo2;
		}
	 
	 public Equipo getEquipo1() {
			return equipo1;
		}

		public void setEquipo1(Equipo equipo1) {
			this.equipo1 = equipo1;
		}

		public Equipo getEquipo2() {
			return equipo2;
		}

		public void setEquipo2(Equipo equipo2) {
			this.equipo2 = equipo2;
		}

		public int getGana1() {
			return gana1;
		}

		public void setGana1(int gana1) {
			this.gana1 = gana1;
		}

		public int getEmpate() {
			return empate;
		}

		public void setEmpate(int empate) {
			this.empate = empate;
		}

		public int getGana2() {
			return gana2;
		}

		public void setGana2(int gana2) {
			this.gana2 = gana2;
		}
}