package Lesson8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Home {
    private Pet firstPet;
    private Pet secondPet;

    public Pet getFirstPet() {
        return firstPet;
    }

    public void setFirstPet(Pet firstPet) {
        this.firstPet = firstPet;
    }

    public Pet getSecondPet() {
        return secondPet;
    }

    public void setSecondPet(Pet secondPet) {
        this.secondPet = secondPet;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        Home home = new Home();
        BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\Rapid Blaze\\IdeaProjects\\Sem2\\src\\Lesson8\\settings.txt"));

        String l;
        if ((l = in.readLine()) != null) {
            String[] s = l.split(" ");
            Class c = Class.forName(s[1]);
            Object obj = c.newInstance();
            Pet pet = (Pet) obj;
            home.setFirstPet(pet);
        }

        if ((l = in.readLine()) != null) {
            String[] s = l.split(" ");
            Class c = Class.forName(s[1]);
            Object obj = c.newInstance();
            Pet pet = (Pet) obj;
            home.setSecondPet(pet);
        }

        System.out.println(home.getFirstPet().getName());
        System.out.println(home.getSecondPet().getName());
    }
}
