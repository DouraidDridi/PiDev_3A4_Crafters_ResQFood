package edu.esprit.service;

import java.util.Set;

public interface IServiceAvis<T> {
    public  void ajouter(T t) ;
    public void modifier(T t) ;
    public void supprimer(int id) ;
    public Set<T> getAll();
    public T getOneById(int id) ;
}
