package org.openjfx.ecosys2;
/**
 * Interface pour les animaux mangeurs. Définit la méthode {@code eat} pour spécifier le comportement alimentaire d'un animal.
 */
public interface Eater {
    /**
     * Définit le comportement alimentaire d'un animal.
     *
     * @param animal L'objet {@code Animal} représentant l'entité à consommer.
     */
    public void eat(Animal animal);
}

