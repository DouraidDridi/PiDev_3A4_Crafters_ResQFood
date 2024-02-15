
package edu.esprit.service;

        import java.util.Set;

public interface IServiceProduit<T> {
    void ajouter(T var1);

    void modifier(T var1);

    void supprimer(int var1);

    T getOneById(int var1);

    Set<T> getAll();
}
