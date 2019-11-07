package employesAvecHeritage;

public class Test {
    public static void main(String[] args) {
        EmployeNormal en = new EmployeNormal("Employé 1");
        Commercial c = new Commercial("comercial1");
        EmployeMieuxPaye employeMieuxPaye = new EmployeMieuxPaye("employe mieux payé");

        en.setTravail(40);
        c.setTravail(40);
        employeMieuxPaye.setTravail(40);
        System.out.println(en.salaireHebdo());
        System.out.println(c.salaireHebdo());
        System.out.println(employeMieuxPaye.salaireHebdo());
    }
}
