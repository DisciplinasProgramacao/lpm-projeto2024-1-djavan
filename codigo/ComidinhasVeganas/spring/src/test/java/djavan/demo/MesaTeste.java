import static org.junit.Assert.assertTrue;

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
