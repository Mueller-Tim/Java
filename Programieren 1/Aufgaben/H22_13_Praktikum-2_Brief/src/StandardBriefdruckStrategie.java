public class StandardBriefdruckStrategie implements BriefdruckStrategie{
	@Override
	public void druckeBrief(Brief brief){
		System.out.println(brief.getSender().getVorname() + " "  + brief.getSender().getNachname());
		System.out.println(brief.getSender().getStrasse() + " " + brief.getSender().getHausnummer());
		System.out.println(brief.getSender().getPlz() + " " + brief.getSender().getOrt());
		System.out.println();
		System.out.println(brief.getEmpfaenger().getVorname() + " "  + brief.getEmpfaenger().getNachname());
		System.out.println(brief.getEmpfaenger().getStrasse() + " " + brief.getEmpfaenger().getHausnummer());
		System.out.println(brief.getEmpfaenger().getPlz() + " " + brief.getEmpfaenger().getOrt());
		System.out.println();
		System.out.println(brief.getInhalt().getDatum());
		System.out.println();
		System.out.println(brief.getInhalt().getTitel());
		System.out.println();
		System.out.println(brief.getInhalt().getAnrede() + " " + brief.getEmpfaenger().getVorname());
		System.out.println();
		System.out.println(brief.getInhalt().getText());
	}

}
