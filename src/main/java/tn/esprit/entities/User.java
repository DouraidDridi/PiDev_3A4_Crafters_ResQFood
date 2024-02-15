package tn.esprit.entities;
import java.util.Objects;
public class User {
        private int id;
        private String nom;
        private String prenom;
        private String email;
        private String password;

        public User() {
        }

        public User(String nom, String prenom, String email, String password) {
            this.nom = nom;
            this.prenom = prenom;
            this.email = email;
            this.password = password;
        }

        public User(int id, String nom, String prenom, String email, String password) {
            this.id = id;
            this.nom = nom;
            this.prenom = prenom;
            this.email = email;
            this.password = password;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNom() {
            return nom;
        }

        public void setNom(String nom) {
            this.nom = nom;
        }

        public String getPrenom() {
            return prenom;
        }

        public void setPrenom(String prenom) {
            this.prenom = prenom;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        @Override
        public String toString() {
            return "User{" +
                    "nom='" + nom + '\'' +
                    ", prenom='" + prenom + '\'' +
                    ", email='" + email + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            User user = (User) o;
            return id == user.id;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
}


