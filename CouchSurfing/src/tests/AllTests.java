package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
				MenuTest.class,
				TestConnnexionBDD.class,
				TestCritereLogement.class,
				TestCustomDate.class,
				TestFormulaireConnexion.class, 
				TestFormulaireInscription.class,
				TestFormulaireProposerLogement.class,
				TestFormulaireRechercheAnnonce.class,
				TestImage.class,
				TestLogement.class,
				TestUtilisateur.class,
				TestPostule.class,
				TestImage.class,
				TestCritereLogement.class,
				TestOffre.class
				})
public class AllTests {

}
