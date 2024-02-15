

package edu.esprit.service;

        import java.util.Set;

public interface IServiceavisProduit<V> {
    void ajouter(V var1);

    void modifier(V var1);

    void supprimer(int var1);

    V getOneById(int var1);

    Set<V> getAll();
}