import java.util.*;
public class AssassinManager {

   public AssassinNode killRing;
    public AssassinNode graveYard;

    public AssassinManager(List<String> names) {
        if (names.isEmpty()) {
            throw new IllegalArgumentException("name is empty");
        } else {
            int size = names.size();
            AssassinNode current;
            this.killRing = new AssassinNode(names.get(0));
            current = this.killRing;
            for (int i = 1; i < size; i++) {
                current.next = new AssassinNode(names.get(i));
                current = current.next;
            }
        }
    }

    public void printKillRing() {
        AssassinNode current;
        current = killRing;
        while (current.next != null) {
            System.out.println(current.name + " is stacking " + current.next.name);
            current = current.next;
        }
        System.out.println(current.name + " is stacking " + killRing.name);
    }

    public void printGraveyard() {

        for(AssassinNode current = this.graveYard; current != null; current = current.next) {
            if (current.killer == null){
                System.out.print("");
            }else
            System.out.println("    " + current.name + " was killed by " + current.killer);
        }

    }

    public boolean killRingContains(String name) {
        AssassinNode current;
        current = this.killRing;
        boolean outcome = false;
        while (current.next != null) {
            if (current.name.equalsIgnoreCase(name)) {
                outcome = true;
            }
            current = current.next;
        }
        if (current.name.equalsIgnoreCase(name)) {
            outcome = true;
        }
        return outcome;
    }

    public boolean graveyardContains(String name) {
        AssassinNode current;
        current = graveYard;
        boolean outcome = false;
        if (current == null) {
            return false;
        } else {
            while (current.next != null) {
                if (current.name.equals(name)) {
                    outcome = true;
                }
                current = current.next;
            }
            if (current.name.equals(name)) {
                outcome = true;
            }
            return outcome;
        }
    }

    public boolean gameOver() {
        return killRing.next == null;
    }

    public String winner() {
        if (gameOver()) {
            return killRing.name;
        } else {
            return null;
        }
    }

    public void kill(String name) {
        if (this.gameOver()) {
            throw new IllegalArgumentException();
        } else if (!this.killRingContains(name)) {
            throw new IllegalArgumentException();
        } else {
            AssassinNode current = killRing;
            AssassinNode save = null ;
            if (name.equalsIgnoreCase(killRing.name)){
                while (current.next != null){
                    current = current.next;
                }
                save = killRing;
                save.killer = current.name;
                killRing = killRing.next;
            }
            while( current != null && current.next != null){
                if (name.equalsIgnoreCase(current.next.name)){
                    save = killRing.next.next;
                    save.killer = current.name;
                    current.next = current.next.next;
                }
                current = current.next;
            }

            if (graveYard == null){
                graveYard = save;
            }else {
                save.next = graveYard;
                graveYard = save;
            }
        }
    }
}



