package djavan.demo;
import static org.junit.jupiter.api.Assertions.assertTrue;
import djavan.demo.models.Mesa;

public class MesaTeste {
    

@Test
public void testaOcupacaoDaMesa(){
    Mesa mesaTeste = new Mesa(01, 4, true);
    assertTrue(mesaTeste.ocupar());
}

@Test
public void testaDesocupacaoDaMesa(){
    Mesa mesaTeste = new Mesa(01, 4, false);
    assertTrue(!mesaTeste.desocupar());
}
}
