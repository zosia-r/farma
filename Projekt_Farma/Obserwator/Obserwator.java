package Obserwator;

import java.io.File;

import zwierzeta.Zwierze;

public interface Obserwator {

    public void aktualizacja(String s, boolean b, long i, File f, Zwierze zwierze, boolean czyZagroda);

}
